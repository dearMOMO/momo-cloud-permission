package com.momo.service.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.momo.common.error.RedisKeyEnum;
import com.momo.common.util.RedisUtil;
import com.momo.mapper.dataobject.AclDO;
import com.momo.mapper.dataobject.RoleDO;
import com.momo.mapper.req.sysmain.LoginAuthReq;
import com.momo.mapper.req.sysmain.RedisUser;
import com.momo.service.service.SuperAdminsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ProjectName: momo-cloud-permission
 * @Package: com.momo.service.cache
 * @Description: TODO
 * @Author: Jie Li
 * @CreateDate: 2019/9/10 0010 11:24
 * @UpdateDate: 2019/9/10 0010 11:24
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
@Service
@Slf4j
public class AdminSysCoreServiceCache {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SuperAdminsService superAdminsService;

    //动态权限菜单
    public List<AclDO> getUserAclList(LoginAuthReq loginAuthReq, RedisUser redisUser) {
        //如果是超级管理员，则获取所有权限点
        if (superAdminsService.checkIsSuperAdmin(redisUser.getSysUserPhone())) {
            Map<Object, Object> aclMap = redisUtil.hmget(RedisKeyEnum.REDIS_ACL_MAP.getKey() + loginAuthReq.getAclPermissionCode());
            if (MapUtils.isEmpty(aclMap)) {
                return Lists.newArrayList();
            }
            List<AclDO> aclDtoList = Lists.newArrayList();
            aclMap.forEach((o, o2) -> {
                if (null != o2) {
                    AclDO sysAcl = JSON.parseObject(String.valueOf(o2), new TypeReference<AclDO>() {
                    });
                    aclDtoList.add(sysAcl);
                }
            });
            return aclDtoList;
        }
        //根据用户id获取角色ids
        Object userRoleIdList = redisUtil.get(RedisKeyEnum.REDIS_USER_ROLES_STR.getKey() + redisUser.getTenantId() + ":" + redisUser.getBaseId());
        if (null == userRoleIdList) {
            return Lists.newArrayList();
        }
        //角色ids
        List<Long> userRoleIdListLong = JSON.parseObject(String.valueOf(userRoleIdList), new TypeReference<List<Long>>() {
        });
        if (CollectionUtils.isEmpty(userRoleIdListLong)) {
            return Lists.newArrayList();
        }
        List<String> roleIdKeys = Lists.newArrayList();
        userRoleIdListLong.forEach(aLong -> {
            if (null != aLong) {
                roleIdKeys.add(String.valueOf(aLong));
            }
        });
        //根据角色id获得角色列表
        List<Object> roleDoList = redisUtil.batchListByKeys(roleIdKeys);
        if (CollectionUtils.isEmpty(roleDoList)) {
            return Lists.newArrayList();
        }
        //角色id列表
        Set<Long> finalRoleIds = Sets.newHashSet();
        roleDoList.forEach(o -> {
            if (o != null) {
                RoleDO roleDO = JSON.parseObject(String.valueOf(o), new TypeReference<RoleDO>() {
                });
                //根据角色ids获取角色列表 临时启用和禁用角色
                //是否被禁用  0否 1禁用
                if (roleDO.getDelFlag().equals(0) && roleDO.getDisabledFlag().equals(0)) {
                    finalRoleIds.add(roleDO.getId());
                }
            }
        });
        if (CollectionUtils.isEmpty(finalRoleIds)) {
            return Lists.newArrayList();
        }
        List<String> roleIdsStrList = Lists.newArrayList();
        String roleIdsRedisKey = RedisKeyEnum.REDIS_ROLE_ACLS_MAP.getKey() + redisUser.getTenantId() + ":";
        List<String> finalRoleIdsList = finalRoleIds.stream().map(aLong -> roleIdsRedisKey + aLong).collect(Collectors.toList());
        roleIdsStrList.addAll(finalRoleIdsList);
        //根据角色ids获取权限点ids
        List<Object> aclIdsRedisKey = Lists.newArrayList();
        roleIdsStrList.forEach(s -> {
            List<Object> aclIdsObj = redisUtil.batchMapByKeys(s, Collections.singletonList(loginAuthReq.getAclPermissionCode()));
            if (CollectionUtils.isNotEmpty(aclIdsObj)) {
                aclIdsObj.forEach(o -> {
                    if (null != o) {
                        aclIdsRedisKey.add(String.valueOf(o));
                    }
                });
            }
        });
        if (CollectionUtils.isEmpty(aclIdsRedisKey)) {
            return Lists.newArrayList();
        }
        //根据权限点ids获取权限点列表
        String redisKey = RedisKeyEnum.REDIS_ACL_MAP.getKey() + loginAuthReq.getAclPermissionCode();
        List<Object> aclDoList = redisUtil.batchMapByKeys(redisKey, aclIdsRedisKey);
        if (CollectionUtils.isEmpty(aclDoList)) {
            return Lists.newArrayList();
        }
        List<AclDO> getAllAcl = Lists.newArrayList();
        aclDoList.forEach(o -> {
            if (null != o) {
                AclDO aclDO = JSON.parseObject(String.valueOf(o), new TypeReference<AclDO>() {
                });
                getAllAcl.add(aclDO);
            }
        });
        return getAllAcl;
    }
}

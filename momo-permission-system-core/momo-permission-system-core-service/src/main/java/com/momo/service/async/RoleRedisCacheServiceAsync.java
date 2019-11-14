package com.momo.service.async;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.momo.common.core.error.RedisKeyEnum;
import com.momo.common.core.util.RedisUtil;
import com.momo.mapper.cache.RoleDORedisCache;
import com.momo.mapper.dataobject.AclDO;
import com.momo.mapper.dataobject.RoleDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

/**
 * @ProjectName: momo-cloud-permission
 * @Package: com.momo.service.async
 * @Description: TODO
 * @Author: Jie Li
 * @CreateDate: 2019/9/4 0004 14:42
 * @UpdateDate: 2019/9/4 0004 14:42
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
@Service
@Slf4j
@Async("threadPoolTaskExecutor")
public class RoleRedisCacheServiceAsync {
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 更新redis角色信息
     *
     * @param roleDOAfter  更新之后的角色信息
     * @param roleDOBefore 更新之前的角色信息
     * @return
     */
    public Future<String> roleModifyToRedis(RoleDO roleDOAfter, RoleDO roleDOBefore) {
        Future<String> future = new AsyncResult<>("redis:编辑角色成功");
        RoleDORedisCache roleDORedisCache = RoleDORedisCache.builder().sysRoleName(roleDOAfter.getSysRoleName())
                .sysRoleType(roleDOAfter.getSysRoleType()).disabledFlag(roleDOBefore.getDisabledFlag())
                .id(roleDOAfter.getId()).remark(roleDOBefore.getRemark()).createBy(roleDOBefore.getCreateBy())
                .createTime(roleDOBefore.getCreateTime()).delFlag(roleDOBefore.getDelFlag()).updateTime(roleDOAfter.getUpdateTime())
                .updateBy(roleDOAfter.getUpdateBy()).tenantId(roleDOAfter.getTenantId()).uuid(roleDOAfter.getUuid()).build();
        String redisKey = RedisKeyEnum.REDIS_ROLE_STR.getKey() + roleDORedisCache.getId();
        String redisKeyAdmin = RedisKeyEnum.REDIS_ADMIN_ROLE_STR.getKey() + roleDOAfter.getTenantId();
        String roleStr = JSONObject.toJSONString(roleDORedisCache, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteDateUseDateFormat);
        redisUtil.set(redisKey, roleStr);
        //角色的类型，0：管理员(老板)，1：管理员(员工)  2:普通员工 3:其他
        if (roleDOAfter.getSysRoleType().equals(0)) {
            redisUtil.set(redisKeyAdmin, roleStr);
        } else {
            //该角色被降级处理，删除redis管理员角色
            if (roleDOBefore.getSysRoleType().equals(0) && !roleDOAfter.getSysRoleType().equals(0)) {
                redisUtil.del(redisKey);
            }
        }
        return future;
    }

    /**
     * redis:新增角色
     *
     * @param roleDO 角色信息
     * @return
     */
    public Future<String> roleSaveToRedis(RoleDO roleDO) {
        Future<String> future = new AsyncResult<>("redis:新增角色成功");
        RoleDORedisCache roleDORedisCache = RoleDORedisCache.builder().sysRoleName(roleDO.getSysRoleName())
                .sysRoleType(roleDO.getSysRoleType()).disabledFlag(roleDO.getDisabledFlag())
                .id(roleDO.getId()).remark(roleDO.getRemark()).createBy(roleDO.getCreateBy())
                .createTime(roleDO.getCreateTime()).delFlag(roleDO.getDelFlag())
                .updateTime(roleDO.getUpdateTime()).updateBy(roleDO.getUpdateBy())
                .tenantId(roleDO.getTenantId()).uuid(roleDO.getUuid()).build();
        String redisKey = RedisKeyEnum.REDIS_ROLE_STR.getKey() + roleDORedisCache.getId();
        String roleStr = JSONObject.toJSONString(roleDORedisCache, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteDateUseDateFormat);
        redisUtil.set(redisKey, roleStr);
        //角色的类型，0：管理员(老板)，1：管理员(员工)  2:普通员工 3:其他
        if (roleDO.getSysRoleType().equals(0)) {
            redisUtil.set(redisKey, roleStr);
        }
        return future;
    }

    /**
     * redis：更新角色状态
     *
     * @param roleDO 角色信息
     * @return
     */
    public Future<String> roleStatusToRedis(RoleDO roleDO, Integer disabledFlag) {
        Future<String> future = new AsyncResult<>("redis:变更角色状态成功");
        RoleDORedisCache roleDORedisCache = RoleDORedisCache.builder().sysRoleName(roleDO.getSysRoleName())
                .sysRoleType(roleDO.getSysRoleType()).disabledFlag(disabledFlag)
                .id(roleDO.getId()).remark(roleDO.getRemark()).createBy(roleDO.getCreateBy())
                .createTime(roleDO.getCreateTime()).delFlag(roleDO.getDelFlag())
                .updateTime(roleDO.getUpdateTime()).updateBy(roleDO.getUpdateBy())
                .tenantId(roleDO.getTenantId()).uuid(roleDO.getUuid()).build();
        String redisKey = RedisKeyEnum.REDIS_ROLE_STR.getKey() + roleDORedisCache.getId();
        String redisAdminKey = RedisKeyEnum.REDIS_ADMIN_ROLE_STR.getKey() + roleDORedisCache.getTenantId();
        String roleStr = JSONObject.toJSONString(roleDORedisCache, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteDateUseDateFormat);
        redisUtil.set(redisKey, roleStr);
        //角色的类型，0：管理员(老板)，1：管理员(员工)  2:普通员工 3:其他
        if (roleDO.getSysRoleType().equals(0)) {
            redisUtil.set(redisAdminKey, roleStr);
        }
        return future;
    }

    /**
     * redis: 角色ids给用户
     *
     * @param userId
     * @param roleIdList
     * @return
     */
    public Future<String> rolesToUserToRedis(Long userId, Set<Long> roleIdList) {
        Future<String> future = new AsyncResult<>("redis:变更角色状态成功");
        String redisKey = RedisKeyEnum.REDIS_USER_ROLES_STR.getKey() + userId;
        if (CollectionUtils.isEmpty(roleIdList)) {
            redisUtil.del(redisKey);
        } else {
            redisUtil.set(redisKey, JSONObject.toJSONString(roleIdList, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteDateUseDateFormat));
        }
        return future;
    }

    /**
     * redis: 权限ids给角色
     *
     * @param roleId
     * @param redisAcls
     * @return
     */
    public Future<String> aclsToRoleToRedis(Long roleId, List<AclDO> redisAcls) {
        Future<String> future = new AsyncResult<>("redis:变更角色状态成功");
        String redisKey = RedisKeyEnum.REDIS_ROLE_ACLS_MAP.getKey() + roleId;
        if (CollectionUtils.isEmpty(redisAcls)) {
            redisUtil.del(redisKey);
        } else {
            Map<String, Object> map = Maps.newHashMap();
            Set<String> sysAclPermissionCode = Sets.newHashSet();
            Multimap<String, Long> multimap = ArrayListMultimap.create();
            redisAcls.forEach(aclDO -> {
                sysAclPermissionCode.add(aclDO.getSysAclPermissionCode());
                multimap.put(aclDO.getSysAclPermissionCode(), aclDO.getId());
            });
            sysAclPermissionCode.forEach(s -> {
                List<Long> aclIds = (List<Long>) multimap.get(s);
                if (CollectionUtils.isNotEmpty(aclIds)) {
                    map.put(s, JSONObject.toJSONString(aclIds));
                }
            });
            redisUtil.hmset(redisKey, map);
        }
        return future;
    }

}

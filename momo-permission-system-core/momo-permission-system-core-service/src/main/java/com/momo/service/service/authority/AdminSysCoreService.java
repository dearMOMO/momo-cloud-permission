package com.momo.service.service.authority;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.momo.mapper.dataobject.AclDO;
import com.momo.mapper.mapper.manual.AuthorityMapper;
import com.momo.mapper.req.sysmain.RedisUser;
import com.momo.mapper.req.sysmain.LoginAuthReq;
import com.momo.service.service.SuperAdminsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @program: momo-cloud-permission
 * @description: 总部 权限 核心
 * @author: Jie Li
 * @create: 2019-07-30 22:49
 **/
@Service
public class AdminSysCoreService {
    @Autowired
    private SuperAdminsService superAdminsService;
    @Autowired
    private AuthorityMapper authorityMapper;

    public List<AclDO> getRoleAclList(Set<Long> roleIds, String aclPermissionType) {
        if (org.apache.commons.collections.CollectionUtils.isEmpty(roleIds)) {
            return Lists.newArrayList();
        }
        //根据角色id获取权限点ids
        List<Long> aclIdList = authorityMapper.aclsByRoleId(roleIds, aclPermissionType);
        if (org.apache.commons.collections.CollectionUtils.isEmpty(aclIdList)) {
            return Lists.newArrayList();
        }
        //根据权限点ids获取权限点列表
        return authorityMapper.getAllAcl(null, aclIdList);
    }

    //动态权限菜单
    public List<AclDO> getUserAclList(LoginAuthReq loginAuthReq, RedisUser redisUser) {

        //如果是超级管理员，则获取所有权限点
        if (superAdminsService.checkIsSuperAdmin(redisUser.getSysUserPhone())) {
            return authorityMapper.getAllAcl(loginAuthReq.getAclPermissionCode(), null);
        }
        //根据用户id获取角色ids
        List<Long> userRoleIdList = authorityMapper.rolesByUserId(redisUser.getBaseId());
        if (CollectionUtils.isEmpty(userRoleIdList)) {
            return Lists.newArrayList();
        }
        Set<Long> finalRoleIds = Sets.newHashSet();
        //根据角色ids获取角色列表 临时启用和禁用角色
        //是否被禁用  0否 1禁用
        List<Long> roleIds = authorityMapper.rolesByRoleId(userRoleIdList, 0);
        if (CollectionUtils.isEmpty(roleIds)) {
            return Lists.newArrayList();
        }
        finalRoleIds.addAll(roleIds);
        //根据角色ids获取权限点ids
        List<Long> userAclIdList = authorityMapper.aclsByRoleId(finalRoleIds, loginAuthReq.getAclPermissionCode());
        if (CollectionUtils.isEmpty(userAclIdList)) {
            return Lists.newArrayList();
        }
        //根据权限点ids获取权限点列表
        return authorityMapper.getAllAcl(loginAuthReq.getAclPermissionCode(), userAclIdList);
    }

    //为角色授权 权限 之前， 需要查看该角色拥有哪些权限点，以及当前登录用户可以操作哪些权限
    public List<AclDO> getUserHavingAclList(LoginAuthReq loginAuthReq, RedisUser redisUser) {
        //如果是超级管理员，则获取所有权限点
        if (superAdminsService.checkIsSuperAdmin(redisUser.getSysUserPhone())) {
            return authorityMapper.getAllAcl(loginAuthReq.getAclPermissionCode(), null);
        }
        //根据用户id获取角色ids
        List<Long> userRoleIdList = authorityMapper.rolesByUserId(redisUser.getBaseId());
        if (org.apache.commons.collections.CollectionUtils.isEmpty(userRoleIdList)) {
            return Lists.newArrayList();
        }
        Set<Long> userRoleIdListSet = userRoleIdList.stream().map(aLong -> aLong).collect(Collectors.toSet());
        //根据角色ids获取权限点ids
        List<Long> userAclIdList = authorityMapper.aclsByRoleId(userRoleIdListSet, loginAuthReq.getAclPermissionCode());
        if (org.apache.commons.collections.CollectionUtils.isEmpty(userAclIdList)) {
            return Lists.newArrayList();
        }
        //根据权限点ids获取权限点列表
        return authorityMapper.getAllAcl(loginAuthReq.getAclPermissionCode(), userAclIdList);
    }
}

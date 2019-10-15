package com.momo.service.service.authority;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.momo.mapper.dataobject.AclDO;
import com.momo.mapper.mapper.manual.AuthorityMapper;
import com.momo.mapper.req.sysmain.DynamicMenuAuthorReq;
import com.momo.mapper.req.sysmain.HasAclReq;
import com.momo.mapper.req.sysmain.RedisUser;
import com.momo.service.service.SuperAdminsService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by MOMO on 2019/4/9.
 */
@Service
public class CommonSysCoreService {

    @Autowired
    private AuthorityMapper authorityMapper;
    @Autowired
    private SuperAdminsService superAdminsService;

    public List<AclDO> getRoleAclList(Set<Long> roleIds, String aclPermissionType) {
        if (org.apache.commons.collections.CollectionUtils.isEmpty(roleIds)) {
            return Lists.newArrayList();
        }
        List<Long> aclIdList = authorityMapper.aclsByRoleId(roleIds, aclPermissionType);
        if (CollectionUtils.isEmpty(aclIdList)) {
            return Lists.newArrayList();
        }
        Set<Long> aclIdsSet = new HashSet<>(aclIdList);
        return authorityMapper.getAllAcl(null, aclIdsSet);
    }

    public List<AclDO> getUserHavingAclList(DynamicMenuAuthorReq loginAuthReq, RedisUser redisUser) {

        List<Long> userRoleIdList = authorityMapper.rolesByUserId(redisUser.getBaseId());
        if (CollectionUtils.isEmpty(userRoleIdList)) {
            return Lists.newArrayList();
        }
        Set<Long> userRoleIdListSet = new HashSet<>(userRoleIdList);
        List<Long> userAclIdList = authorityMapper.aclsByRoleId(userRoleIdListSet, loginAuthReq.getAclPermissionCode());
        if (CollectionUtils.isEmpty(userAclIdList)) {
            return Lists.newArrayList();
        }
        Set<Long> aclIdsSet = new HashSet<>(userAclIdList);
        return authorityMapper.getAllAcl(loginAuthReq.getAclPermissionCode(), aclIdsSet);
    }

    //动态权限菜单
    public List<AclDO> getUserAclList(DynamicMenuAuthorReq loginAuthReq, RedisUser redisUser) {

        //如果是超级管理员，则获取所有权限点
        if (superAdminsService.checkIsSuperAdmin(redisUser.getSysUserPhone())) {
            return authorityMapper.getAllAcl(loginAuthReq.getAclPermissionCode(), null);
        }
        //根据用户id获取角色ids
        List<Long> userRoleIdsList = authorityMapper.rolesByUserId(redisUser.getBaseId());
        if (org.springframework.util.CollectionUtils.isEmpty(userRoleIdsList)) {
            return Lists.newArrayList();
        }
        //根据角色ids获取角色列表 临时启用和禁用角色
        //是否被禁用  0否 1禁用
        List<Long> roleIds = authorityMapper.rolesByRoleId(userRoleIdsList, 0, 0);
        if (org.springframework.util.CollectionUtils.isEmpty(roleIds)) {
            return Lists.newArrayList();
        }
        Set<Long> finalRoleIds = Sets.newHashSet(roleIds);
        //根据角色ids获取权限点ids
        List<Long> aclIdsList = authorityMapper.aclsByRoleId(finalRoleIds, loginAuthReq.getAclPermissionCode());
        if (org.springframework.util.CollectionUtils.isEmpty(aclIdsList)) {
            return Lists.newArrayList();
        }
        Set<Long> aclIdsSet = Sets.newHashSet(aclIdsList);
        //根据权限点ids获取权限点列表
        return authorityMapper.getAllAcl(loginAuthReq.getAclPermissionCode(), aclIdsSet);
    }

    public boolean hasUrlAcl(HasAclReq hasAclReq) {
        if (StringUtils.isBlank(hasAclReq.getUrl())) {
            return false;
        }
        String url = truncateUrlPage(hasAclReq.getUrl());
        if (superAdminsService.checkIsSuperAdmin(hasAclReq.getSysUserPhone())) {
            return true;
        }
        List<AclDO> userAclList = getUserAclList(new DynamicMenuAuthorReq(), hasAclReq);
        Set<String> userAclIdSet = userAclList.stream().map(AclDO::getSysAclUrl).collect(Collectors.toSet());
        // 规则：只要有一个权限点有权限，那么我们就认为有访问权限
        for (String aclUrl : userAclIdSet) {
            // 判断一个用户是否具有某个权限点的访问权限
            if (StringUtils.isBlank(aclUrl)) { // 权限点无效
                continue;
            }
            if (url.equals(aclUrl)) {
                return true;
            }
        }
        return false;
    }

    private String truncateUrlPage(String strURL) {
        String strAllParam = null;
        strURL = strURL.trim().toLowerCase();
        String[] arrSplit = strURL.split("[?]");
        if (strURL.length() > 0) {
            strAllParam = arrSplit[0];
        }
        return strAllParam;
    }
}

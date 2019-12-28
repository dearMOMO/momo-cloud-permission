/**
 * Copyright (c) 2018-2019, Jie Li 李杰 (mqgnsds@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.momo.service.service.authority;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.momo.common.core.entity.RedisUser;
import com.momo.mapper.dataobject.AclDO;
import com.momo.mapper.enums.DelFlagEnum;
import com.momo.mapper.enums.DisabledFlagEnum;
import com.momo.mapper.mapper.manual.AclMapper;
import com.momo.mapper.mapper.manual.AuthorityMapper;
import com.momo.mapper.req.sysmain.DynamicMenuAuthorReq;
import com.momo.mapper.req.sysmain.HasAclReq;
import com.momo.service.service.SuperAdminsService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
    @Autowired
    private AclMapper aclMapper;

    public List<AclDO> getRoleAclList(Set<Long> roleIds, String aclPermissionType) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return Lists.newArrayList();
        }
        //根据角色id获取权限点ids
        List<Long> aclIdList = authorityMapper.aclsByRoleId(roleIds, aclPermissionType);
        if (CollectionUtils.isEmpty(aclIdList)) {
            return Lists.newArrayList();
        }
        Set<Long> aclIdSet = new HashSet<>(aclIdList);
        //根据权限点ids获取权限点列表
        return authorityMapper.getAllAcl(null, aclIdSet);
    }

    //动态权限菜单
    public List<AclDO> getUserAclList(DynamicMenuAuthorReq loginAuthReq, RedisUser redisUser) {

        //如果是超级管理员，则获取所有权限点
        if (superAdminsService.checkIsSuperAdmin(redisUser.getSysUserPhone())) {
            return authorityMapper.getAllAcl(loginAuthReq.getAclPermissionCode(), null);
        }
        //根据用户id获取角色ids
        List<Long> userRoleIdList = authorityMapper.rolesByUserId(redisUser.getBaseId());
        if (CollectionUtils.isEmpty(userRoleIdList)) {
            return Lists.newArrayList();
        }

        //根据角色ids获取角色列表 临时启用和禁用角色
        //是否被禁用  0否 1禁用
        List<Long> roleIds = authorityMapper.rolesByRoleId(userRoleIdList, DisabledFlagEnum.start.type, DelFlagEnum.ok.type);
        if (CollectionUtils.isEmpty(roleIds)) {
            return Lists.newArrayList();
        }
        Set<Long> finalRoleIds = Sets.newHashSet(roleIds);
        //根据角色ids获取权限点ids
        List<Long> aclIdsList = authorityMapper.aclsByRoleId(finalRoleIds, loginAuthReq.getAclPermissionCode());
        if (CollectionUtils.isEmpty(aclIdsList)) {
            return Lists.newArrayList();
        }
        Set<Long> aclIdsSet = new HashSet<>(aclIdsList);
        //根据权限点ids获取权限点列表
        return authorityMapper.getAllAcl(loginAuthReq.getAclPermissionCode(), aclIdsSet);
    }

    //为角色授权 权限 之前， 需要查看该角色拥有哪些权限点，以及当前登录用户可以操作哪些权限
    public List<AclDO> getUserHavingAclList(DynamicMenuAuthorReq loginAuthReq, RedisUser redisUser) {
        //如果是超级管理员，则获取所有权限点
        if (superAdminsService.checkIsSuperAdmin(redisUser.getSysUserPhone())) {
            return authorityMapper.getAllAcl(loginAuthReq.getAclPermissionCode(), null);
        }
        //根据用户id获取角色ids
        List<Long> userRoleIdList = authorityMapper.rolesByUserId(redisUser.getBaseId());
        if (CollectionUtils.isEmpty(userRoleIdList)) {
            return Lists.newArrayList();
        }
        Set<Long> userRoleIdListSet = userRoleIdList.stream().map(aLong -> aLong).collect(Collectors.toSet());
        //根据角色ids获取权限点ids
        List<Long> userAclIdList = authorityMapper.aclsByRoleId(userRoleIdListSet, loginAuthReq.getAclPermissionCode());
        if (CollectionUtils.isEmpty(userAclIdList)) {
            return Lists.newArrayList();
        }
        Set<Long> aclIdSet = new HashSet<>(userAclIdList);
        //根据权限点ids获取权限点列表
        return authorityMapper.getAllAcl(loginAuthReq.getAclPermissionCode(), aclIdSet);
    }

    public boolean hasUrlAcl(HasAclReq hasAclReq) {
        if (superAdminsService.checkIsSuperAdmin(hasAclReq.getSysUserPhone())) {
            return true;
        }
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
        strURL = strURL.trim();
        String[] arrSplit = strURL.split("[?]");
        if (strURL.length() > 0) {
            strAllParam = arrSplit[0];
        }
        return strAllParam;
    }
}

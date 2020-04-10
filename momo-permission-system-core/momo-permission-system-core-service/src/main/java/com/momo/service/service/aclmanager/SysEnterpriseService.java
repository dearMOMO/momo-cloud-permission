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
package com.momo.service.service.aclmanager;

import com.github.pagehelper.PageInfo;
import com.momo.mapper.dataobject.RoleDO;
import com.momo.mapper.dataobject.UserDO;
import com.momo.mapper.dataobject.UserGroupDO;
import com.momo.mapper.req.aclmanager.SysEnterpriseRoleReq;
import com.momo.mapper.req.aclmanager.SysEnterpriseUserReq;
import com.momo.mapper.req.aclmanager.SysUserGroupReq;
import com.momo.mapper.req.aclmanager.UserGroupPageReq;
import com.momo.mapper.req.authority.BatchRoleUserReq;
import com.momo.mapper.res.aclmanager.SysEnterpriseRoleRes;
import com.momo.mapper.res.aclmanager.SysRoleCheckedRes;
import com.momo.mapper.res.aclmanager.SysUserGroupPageRes;
import com.momo.mapper.res.aclmanager.SysUserListRes;
import com.momo.mapper.res.authority.AclTreeRes;

/**
 * @program: momo-cloud-permission
 * @description: 企业管理
 * @author: Jie Li
 * @create: 2019-08-06 13:04
 **/
public interface SysEnterpriseService {
    /**
     * 企业列表
     *
     * @param userGroupPageReq
     * @return
     */
    public PageInfo<SysUserGroupPageRes> getUserGroupPage(UserGroupPageReq userGroupPageReq);

    /**
     * 企业详情
     *
     * @param userGroupPageReq
     * @return
     */
    UserGroupDO detail(UserGroupPageReq userGroupPageReq);

    /**
     * 企业拥有的权限
     *
     * @param userGroupPageReq
     * @return
     */
    AclTreeRes aclDetail(UserGroupPageReq userGroupPageReq);

    /**
     * 为企业授权权限
     *
     * @param userGroupPageReq
     * @return
     */
    String aclsToEnterprise(UserGroupPageReq userGroupPageReq);

    /**
     * 企业编辑
     *
     * @param sysUserGroupReq
     * @return
     */
    String modify(SysUserGroupReq sysUserGroupReq);

    /**
     * 企业新增
     *
     * @param sysUserGroupReq
     * @return
     */
    String save(SysUserGroupReq sysUserGroupReq);

    /**
     * 企业状态
     *
     * @param sysUserGroupReq
     * @return
     */
    String status(SysUserGroupReq sysUserGroupReq);

    //########################      企业角色相关      #################################

    /**
     * 企业角色列表
     *
     * @param sysEnterpriseRoleReq
     * @return
     */
    SysEnterpriseRoleRes roleList(SysEnterpriseRoleReq sysEnterpriseRoleReq);

    /**
     * 企业角色新增
     *
     * @param sysEnterpriseRoleReq
     * @return
     */
    String roleAdd(SysEnterpriseRoleReq sysEnterpriseRoleReq);

    /**
     * 设置角色状态
     *
     * @param sysEnterpriseRoleReq
     * @return
     */
    String roleStatus(SysEnterpriseRoleReq sysEnterpriseRoleReq);

    /**
     * 企业角色详情
     *
     * @param sysEnterpriseRoleReq
     * @return
     */
    RoleDO roleDetail(SysEnterpriseRoleReq sysEnterpriseRoleReq);

    /**
     * 企业角色编辑
     *
     * @param sysEnterpriseRoleReq
     * @return
     */
    String roleModify(SysEnterpriseRoleReq sysEnterpriseRoleReq);

    /**
     * 企业权限给角色(回显)
     *
     * @param sysEnterpriseRoleReq
     * @return
     */
    AclTreeRes roleHaveAclTree(SysEnterpriseRoleReq sysEnterpriseRoleReq);

    /**
     * 企业权限给角色(授权)
     *
     * @param batchRoleUserReq
     * @return
     */
    String aclsToRole(BatchRoleUserReq batchRoleUserReq);

    //########################      企业用户相关      #################################

    /**
     * 企业用户列表
     *
     * @param sysEnterpriseUserReq
     * @return
     */
    SysUserListRes userList(SysEnterpriseUserReq sysEnterpriseUserReq);

    /**
     * 新增企业用户
     *
     * @param sysEnterpriseUserReq
     * @return
     */
    String userAdd(SysEnterpriseUserReq sysEnterpriseUserReq);

    /**
     * 企业用户详情
     *
     * @param sysEnterpriseUserReq
     * @return
     */
    UserDO userDetail(SysEnterpriseUserReq sysEnterpriseUserReq);

    /**
     * 企业用户编辑
     *
     * @param sysEnterpriseUserReq
     * @return
     */
    String userModify(SysEnterpriseUserReq sysEnterpriseUserReq);

    /**
     * 企业用户授权角色(回显)
     *
     * @param sysEnterpriseUserReq
     * @return
     */
    SysRoleCheckedRes userCheckRoles(SysEnterpriseUserReq sysEnterpriseUserReq);

    /**
     * 企业用户授权角色(授权)
     *
     * @param sysEnterpriseUserReq
     * @return
     */
    String rolesToUser(SysEnterpriseUserReq sysEnterpriseUserReq);

    /**
     * 用户状态设置
     *
     * @param sysEnterpriseUserReq
     * @return
     */
    String userStatus(SysEnterpriseUserReq sysEnterpriseUserReq);

    /**
     * 企业用户重置密码
     *
     * @param sysEnterpriseUserReq
     * @return
     */
    String sysUserPwd(SysEnterpriseUserReq sysEnterpriseUserReq);

}

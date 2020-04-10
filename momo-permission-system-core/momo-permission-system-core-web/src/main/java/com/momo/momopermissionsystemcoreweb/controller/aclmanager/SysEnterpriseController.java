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
package com.momo.momopermissionsystemcoreweb.controller.aclmanager;

import com.momo.common.core.common.JSONResult;
import com.momo.mapper.req.aclmanager.SysEnterpriseRoleReq;
import com.momo.mapper.req.aclmanager.SysEnterpriseUserReq;
import com.momo.mapper.req.aclmanager.SysUserGroupReq;
import com.momo.mapper.req.aclmanager.UserGroupPageReq;
import com.momo.mapper.req.authority.BatchRoleUserReq;
import com.momo.service.service.aclmanager.SysEnterpriseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: momo-cloud-permission
 * @description: 企业管理
 * @author: Jie Li
 * @create: 2019-08-06 13:03
 **/
@RestController
@RequestMapping(value = "/platform/enterprise", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
@Slf4j
public class SysEnterpriseController {

    @Autowired
    private SysEnterpriseService sysEnterpriseService;

    /**
     * 企业列表
     *
     * @param userGroupPageReq
     * @return
     */
    @RequestMapping("/sysUserGroupPage/v1")
    public JSONResult getUserGroupPage(@RequestBody UserGroupPageReq userGroupPageReq) {
        return JSONResult.ok(sysEnterpriseService.getUserGroupPage(userGroupPageReq));
    }

    /**
     * 企业详情
     *
     * @param userGroupPageReq
     * @return
     */
    @RequestMapping("/detail/v1")
    public JSONResult detail(@Validated(UserGroupPageReq.Detail.class) @RequestBody UserGroupPageReq userGroupPageReq) {
        return JSONResult.ok(sysEnterpriseService.detail(userGroupPageReq));
    }

    /**
     * 企业拥有的权限
     *
     * @param userGroupPageReq
     * @return
     */
    @RequestMapping("/aclDetail/v1")
    public JSONResult aclDetail(@Validated(UserGroupPageReq.Permission.class) @RequestBody UserGroupPageReq userGroupPageReq) {
        return JSONResult.ok(sysEnterpriseService.aclDetail(userGroupPageReq));
    }

    /**
     * 为企业授权权限
     *
     * @param userGroupPageReq
     * @return
     */
    @RequestMapping("/aclsToEnterprise/v1")
    public JSONResult aclsToEnterprise(@Validated(UserGroupPageReq.Permission.class) @RequestBody UserGroupPageReq userGroupPageReq) {
        return JSONResult.ok(sysEnterpriseService.aclsToEnterprise(userGroupPageReq));
    }

    /**
     * 企业编辑
     *
     * @param sysUserGroupReq
     * @return
     */
    @RequestMapping("/modify/v1")
    public JSONResult modify(@Validated(SysUserGroupReq.Modify.class) @RequestBody SysUserGroupReq sysUserGroupReq) {
        return JSONResult.ok(sysEnterpriseService.modify(sysUserGroupReq));
    }

    /**
     * 企业新增
     *
     * @param sysUserGroupReq
     * @return
     */
    @RequestMapping("/save/v1")
    public JSONResult save(@Validated(SysUserGroupReq.Save.class) @RequestBody SysUserGroupReq sysUserGroupReq) {
        return JSONResult.ok(sysEnterpriseService.save(sysUserGroupReq));
    }

    /**
     * 企业状态
     *
     * @param sysUserGroupReq
     * @return
     */
    @RequestMapping("/status/v1")
    public JSONResult status(@Validated(SysUserGroupReq.Status.class) @RequestBody SysUserGroupReq sysUserGroupReq) {
        return JSONResult.ok(sysEnterpriseService.status(sysUserGroupReq));
    }
    //########################      企业角色相关      #################################

    /**
     * 企业角色列表
     *
     * @param sysEnterpriseRoleReq
     * @return
     */
    @RequestMapping("/roleList/v1")
    public JSONResult roleList(@Validated(SysEnterpriseRoleReq.Query.class) @RequestBody SysEnterpriseRoleReq sysEnterpriseRoleReq) {
        return JSONResult.ok(sysEnterpriseService.roleList(sysEnterpriseRoleReq));
    }

    /**
     * 设置角色状态
     *
     * @param sysEnterpriseRoleReq
     * @return
     */
    @RequestMapping("/roleStatus/v1")
    public JSONResult roleStatus(@Validated(SysEnterpriseRoleReq.Status.class) @RequestBody SysEnterpriseRoleReq sysEnterpriseRoleReq) {
        return JSONResult.ok(sysEnterpriseService.roleStatus(sysEnterpriseRoleReq));
    }

    /**
     * 企业角色新增
     *
     * @param sysEnterpriseRoleReq
     * @return
     */
    @RequestMapping("/roleAdd/v1")
    public JSONResult roleAdd(@Validated(SysEnterpriseRoleReq.Save.class) @RequestBody SysEnterpriseRoleReq sysEnterpriseRoleReq) {
        return JSONResult.ok(sysEnterpriseService.roleAdd(sysEnterpriseRoleReq));
    }

    /**
     * 企业角色编辑
     *
     * @param sysEnterpriseRoleReq
     * @return
     */
    @RequestMapping("/roleModify/v1")
    public JSONResult roleModify(@Validated(SysEnterpriseRoleReq.Modify.class) @RequestBody SysEnterpriseRoleReq sysEnterpriseRoleReq) {
        return JSONResult.ok(sysEnterpriseService.roleModify(sysEnterpriseRoleReq));
    }

    /**
     * 企业角色详情
     *
     * @param sysEnterpriseRoleReq
     * @return
     */
    @RequestMapping("/roleDetail/v1")
    public JSONResult roleDetail(@Validated(SysEnterpriseRoleReq.Detail.class) @RequestBody SysEnterpriseRoleReq sysEnterpriseRoleReq) {
        return JSONResult.ok(sysEnterpriseService.roleDetail(sysEnterpriseRoleReq));
    }

    /**
     * 企业权限给角色(回显)
     *
     * @param sysEnterpriseRoleReq
     * @return
     */
    @RequestMapping("/roleHaveAclTree/v1")
    public JSONResult roleHaveAclTree(@Validated(SysEnterpriseRoleReq.Permission.class) @RequestBody SysEnterpriseRoleReq sysEnterpriseRoleReq) {
        return JSONResult.ok(sysEnterpriseService.roleHaveAclTree(sysEnterpriseRoleReq));
    }

    /**
     * 企业权限给角色(授权)
     *
     * @param batchRoleUserReq
     * @return
     */
    @RequestMapping("/aclsToRole/v1")
    public JSONResult aclsToRole(@Validated(BatchRoleUserReq.Author.class) @RequestBody BatchRoleUserReq batchRoleUserReq) {
        return JSONResult.ok(sysEnterpriseService.aclsToRole(batchRoleUserReq));
    }

    //########################      企业用户相关      #################################

    /**
     * 企业用户列表
     *
     * @param sysEnterpriseUserReq
     * @return
     */
    @RequestMapping("/userList/v1")
    public JSONResult userList(@Validated(SysEnterpriseUserReq.Query.class) @RequestBody SysEnterpriseUserReq sysEnterpriseUserReq) {
        return JSONResult.ok(sysEnterpriseService.userList(sysEnterpriseUserReq));
    }

    /**
     * 新增企业用户
     *
     * @param sysEnterpriseUserReq
     * @return
     */
    @RequestMapping("/userAdd/v1")
    public JSONResult userAdd(@Validated(SysEnterpriseUserReq.Save.class) @RequestBody SysEnterpriseUserReq sysEnterpriseUserReq) {
        return JSONResult.ok(sysEnterpriseService.userAdd(sysEnterpriseUserReq));
    }

    /**
     * 企业用户详情
     *
     * @param sysEnterpriseUserReq
     * @return
     */
    @RequestMapping("/userDetail/v1")
    public JSONResult userDetail(@Validated(SysEnterpriseUserReq.Detail.class) @RequestBody SysEnterpriseUserReq sysEnterpriseUserReq) {
        return JSONResult.ok(sysEnterpriseService.userDetail(sysEnterpriseUserReq));
    }

    /**
     * 企业用户编辑
     *
     * @param sysEnterpriseUserReq
     * @return
     */
    @RequestMapping("/userModify/v1")
    public JSONResult userModify(@Validated(SysEnterpriseUserReq.Modify.class) @RequestBody SysEnterpriseUserReq sysEnterpriseUserReq) {
        return JSONResult.ok(sysEnterpriseService.userModify(sysEnterpriseUserReq));
    }

    /**
     * 企业用户授权角色(回显)
     *
     * @param sysEnterpriseUserReq
     * @return
     */
    @RequestMapping("/userCheckRoles/v1")
    public JSONResult userCheckRoles(@Validated(SysEnterpriseUserReq.Detail.class) @RequestBody SysEnterpriseUserReq sysEnterpriseUserReq) {
        return JSONResult.ok(sysEnterpriseService.userCheckRoles(sysEnterpriseUserReq));
    }

    /**
     * 企业用户授权角色(授权)
     *
     * @param sysEnterpriseUserReq
     * @return
     */
    @RequestMapping("/rolesToUser/v1")
    public JSONResult rolesToUser(@Validated(SysEnterpriseUserReq.Author.class) @RequestBody SysEnterpriseUserReq sysEnterpriseUserReq) {
        return JSONResult.ok(sysEnterpriseService.rolesToUser(sysEnterpriseUserReq));
    }

    /**
     * 用户状态设置
     *
     * @param sysEnterpriseUserReq
     * @return
     */
    @RequestMapping("/userStatus/v1")
    public JSONResult userStatus(@Validated(SysEnterpriseUserReq.Status.class) @RequestBody SysEnterpriseUserReq sysEnterpriseUserReq) {
        return JSONResult.ok(sysEnterpriseService.userStatus(sysEnterpriseUserReq));
    }

    /**
     * 企业用户重置密码
     *
     * @param sysEnterpriseUserReq
     * @return
     */
    @RequestMapping("/sysUserPwd/v1")
    public JSONResult sysUserPwd(@Validated(SysEnterpriseUserReq.Permission.class) @RequestBody SysEnterpriseUserReq sysEnterpriseUserReq) {
        return JSONResult.ok(sysEnterpriseService.sysUserPwd(sysEnterpriseUserReq));
    }
}

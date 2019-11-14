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

    @RequestMapping("/sysUserGroupPage/v1")
    public JSONResult getUserGroupPage(@RequestBody UserGroupPageReq userGroupPageReq) {
        return JSONResult.ok(sysEnterpriseService.getUserGroupPage(userGroupPageReq));
    }

    @RequestMapping("/detail/v1")
    public JSONResult detail(@Validated(UserGroupPageReq.Detail.class) @RequestBody UserGroupPageReq userGroupPageReq) {
        return JSONResult.ok(sysEnterpriseService.detail(userGroupPageReq));
    }

    @RequestMapping("/aclDetail/v1")
    public JSONResult aclDetail(@Validated(UserGroupPageReq.Permission.class) @RequestBody UserGroupPageReq userGroupPageReq) {
        return JSONResult.ok(sysEnterpriseService.aclDetail(userGroupPageReq));
    }

    @RequestMapping("/aclsToEnterprise/v1")
    public JSONResult aclsToEnterprise(@Validated(UserGroupPageReq.Permission.class) @RequestBody UserGroupPageReq userGroupPageReq) {
        return JSONResult.ok(sysEnterpriseService.aclsToEnterprise(userGroupPageReq));
    }

    @RequestMapping("/modify/v1")
    public JSONResult modify(@Validated(SysUserGroupReq.Modify.class) @RequestBody SysUserGroupReq sysUserGroupReq) {
        return JSONResult.ok(sysEnterpriseService.modify(sysUserGroupReq));
    }

    @RequestMapping("/save/v1")
    public JSONResult save(@Validated(SysUserGroupReq.save.class) @RequestBody SysUserGroupReq sysUserGroupReq) {
        return JSONResult.ok(sysEnterpriseService.save(sysUserGroupReq));
    }

    @RequestMapping("/status/v1")
    public JSONResult status(@Validated(SysUserGroupReq.Status.class) @RequestBody SysUserGroupReq sysUserGroupReq) {
        return JSONResult.ok(sysEnterpriseService.status(sysUserGroupReq));
    }
    //########################      企业角色相关      #################################

    @RequestMapping("/roleList/v1")
    public JSONResult roleList(@Validated(SysEnterpriseRoleReq.Query.class) @RequestBody SysEnterpriseRoleReq sysEnterpriseRoleReq) {
        return JSONResult.ok(sysEnterpriseService.roleList(sysEnterpriseRoleReq));
    }

    @RequestMapping("/roleStatus/v1")
    public JSONResult roleStatus(@Validated(SysEnterpriseRoleReq.Status.class) @RequestBody SysEnterpriseRoleReq sysEnterpriseRoleReq) {
        return JSONResult.ok(sysEnterpriseService.roleStatus(sysEnterpriseRoleReq));
    }

    @RequestMapping("/roleAdd/v1")
    public JSONResult roleAdd(@Validated(SysEnterpriseRoleReq.save.class) @RequestBody SysEnterpriseRoleReq sysEnterpriseRoleReq) {
        return JSONResult.ok(sysEnterpriseService.roleAdd(sysEnterpriseRoleReq));
    }

    @RequestMapping("/roleModify/v1")
    public JSONResult roleModify(@Validated(SysEnterpriseRoleReq.Modify.class) @RequestBody SysEnterpriseRoleReq sysEnterpriseRoleReq) {
        return JSONResult.ok(sysEnterpriseService.roleModify(sysEnterpriseRoleReq));
    }

    @RequestMapping("/roleDetail/v1")
    public JSONResult roleDetail(@Validated(SysEnterpriseRoleReq.Detail.class) @RequestBody SysEnterpriseRoleReq sysEnterpriseRoleReq) {
        return JSONResult.ok(sysEnterpriseService.roleDetail(sysEnterpriseRoleReq));
    }

    @RequestMapping("/roleHaveAclTree/v1")
    public JSONResult roleHaveAclTree(@Validated(SysEnterpriseRoleReq.Permission.class) @RequestBody SysEnterpriseRoleReq sysEnterpriseRoleReq) {
        return JSONResult.ok(sysEnterpriseService.roleHaveAclTree(sysEnterpriseRoleReq));
    }

    @RequestMapping("/aclsToRole/v1")
    public JSONResult aclsToRole(@Validated(BatchRoleUserReq.Author.class) @RequestBody BatchRoleUserReq batchRoleUserReq) {
        return JSONResult.ok(sysEnterpriseService.aclsToRole(batchRoleUserReq));
    }

    //########################      企业用户相关      #################################

    @RequestMapping("/userList/v1")
    public JSONResult userList(@Validated(SysEnterpriseUserReq.Query.class) @RequestBody SysEnterpriseUserReq sysEnterpriseUserReq) {
        return JSONResult.ok(sysEnterpriseService.userList(sysEnterpriseUserReq));
    }

    @RequestMapping("/userAdd/v1")
    public JSONResult userAdd(@Validated(SysEnterpriseUserReq.save.class) @RequestBody SysEnterpriseUserReq sysEnterpriseUserReq) {
        return JSONResult.ok(sysEnterpriseService.userAdd(sysEnterpriseUserReq));
    }

    @RequestMapping("/userDetail/v1")
    public JSONResult userDetail(@Validated(SysEnterpriseUserReq.Detail.class) @RequestBody SysEnterpriseUserReq sysEnterpriseUserReq) {
        return JSONResult.ok(sysEnterpriseService.userDetail(sysEnterpriseUserReq));
    }

    @RequestMapping("/userModify/v1")
    public JSONResult userModify(@Validated(SysEnterpriseUserReq.Modify.class) @RequestBody SysEnterpriseUserReq sysEnterpriseUserReq) {
        return JSONResult.ok(sysEnterpriseService.userModify(sysEnterpriseUserReq));
    }

    @RequestMapping("/userCheckRoles/v1")
    public JSONResult userCheckRoles(@Validated(SysEnterpriseUserReq.Detail.class) @RequestBody SysEnterpriseUserReq sysEnterpriseUserReq) {
        return JSONResult.ok(sysEnterpriseService.userCheckRoles(sysEnterpriseUserReq));
    }

    @RequestMapping("/rolesToUser/v1")
    public JSONResult rolesToUser(@Validated(SysEnterpriseUserReq.Author.class) @RequestBody SysEnterpriseUserReq sysEnterpriseUserReq) {
        return JSONResult.ok(sysEnterpriseService.rolesToUser(sysEnterpriseUserReq));
    }

    @RequestMapping("/userStatus/v1")
    public JSONResult userStatus(@Validated(SysEnterpriseUserReq.Status.class) @RequestBody SysEnterpriseUserReq sysEnterpriseUserReq) {
        return JSONResult.ok(sysEnterpriseService.userStatus(sysEnterpriseUserReq));
    }

    @RequestMapping("/sysUserPwd/v1")
    public JSONResult sysUserPwd(@Validated(SysEnterpriseUserReq.Permission.class) @RequestBody SysEnterpriseUserReq sysEnterpriseUserReq) {
        return JSONResult.ok(sysEnterpriseService.sysUserPwd(sysEnterpriseUserReq));
    }
}

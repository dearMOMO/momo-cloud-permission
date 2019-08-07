package com.momo.momopermissionsystemcoreweb.controller.authority;

import com.momo.common.common.JSONResult;
import com.momo.mapper.req.authority.BatchRoleUserReq;
import com.momo.mapper.req.authority.RoleReq;
import com.momo.mapper.req.sysmain.LoginAuthReq;
import com.momo.service.service.authority.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by MOMO on 2019/4/9.
 */
@RestController
@RequestMapping(value = "/platform/role", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
@Slf4j
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/save/v1")
    public JSONResult save(@Validated(RoleReq.save.class) @RequestBody RoleReq roleReq) {
        return JSONResult.ok(roleService.insertSelective(roleReq));
    }


    @RequestMapping("/showRole/v1")
    public JSONResult showRole(@Validated(RoleReq.Detail.class) @RequestBody RoleReq roleReq) {
        return JSONResult.ok(roleService.showRole(roleReq));
    }

    @RequestMapping("/modify/v1")
    public JSONResult modify(@Validated(RoleReq.Modify.class) @RequestBody RoleReq roleReq) {
        return JSONResult.ok(roleService.updateByPrimaryKeySelective(roleReq));
    }

    @RequestMapping("/status/v1")
    public JSONResult status(@Validated(RoleReq.Status.class) @RequestBody RoleReq roleReq) {
        return JSONResult.ok(roleService.updateState(roleReq));
    }

    @PostMapping("/rolesToUser/v1")
    public JSONResult rolesToUser(@Validated(BatchRoleUserReq.Status.class) @RequestBody BatchRoleUserReq batchRoleUserReq) {
        return JSONResult.ok(roleService.rolesToUser(batchRoleUserReq));
    }

    @PostMapping("/aclsToRole/v1")
    public JSONResult aclsToRole(@Validated(BatchRoleUserReq.Permission.class) @RequestBody BatchRoleUserReq batchRoleUserReq) {
        return JSONResult.ok(roleService.aclsToRole(batchRoleUserReq));
    }

    @PostMapping("/roleList/v1")
    public JSONResult roleList(@RequestBody RoleReq batchRoleUserReq) {
        return JSONResult.ok(roleService.roleList(batchRoleUserReq));
    }

    @PostMapping("/roleHaveAclTree/v1")
    public JSONResult roleHaveAclTree(@Validated(LoginAuthReq.Detail.class) @RequestBody LoginAuthReq loginAuthReq) {
        return JSONResult.ok(roleService.roleHaveAclTree(loginAuthReq));
    }

    @PostMapping("/userCheckRoles/v1")
    public JSONResult userCheckRoles(@Validated(RoleReq.Permission.class) @RequestBody RoleReq roleReq) {
        return JSONResult.ok(roleService.userCheckedRoles(roleReq));
    }
}

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
package com.momo.momopermissionsystemcoreweb.controller.authority;

import com.momo.common.core.common.JSONResult;
import com.momo.mapper.req.authority.BatchRoleUserReq;
import com.momo.mapper.req.authority.RoleReq;
import com.momo.mapper.req.sysmain.DynamicMenuAuthorReq;
import com.momo.service.service.authority.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @program: momo-cloud-permission
 * @description: 角色设置
 * @author: Jie Li
 * @create: 2019-07-31 14:59
 **/
@RestController
@RequestMapping(value = "/platform/role", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
@Slf4j
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 角色新增
     *
     * @param roleReq
     * @return
     */
    @RequestMapping("/save/v1")
    public JSONResult save(@Validated(RoleReq.Save.class) @RequestBody RoleReq roleReq) {
        return JSONResult.ok(roleService.insertSelective(roleReq));
    }


    /**
     * 角色详情
     *
     * @param roleReq
     * @return
     */
    @RequestMapping("/showRole/v1")
    public JSONResult showRole(@Validated(RoleReq.Detail.class) @RequestBody RoleReq roleReq) {
        return JSONResult.ok(roleService.showRole(roleReq));
    }

    /**
     * 是否禁用管理员按钮
     *
     * @return
     */
    @RequestMapping("/disabledAdminRoleButton/v1")
    public JSONResult showAdminRoleButton() {
        return JSONResult.ok(roleService.disabledAdminRoleButton());
    }

    /**
     * 角色编辑
     *
     * @param roleReq
     * @return
     */
    @RequestMapping("/modify/v1")
    public JSONResult modify(@Validated(RoleReq.Modify.class) @RequestBody RoleReq roleReq) {
        return JSONResult.ok(roleService.updateByPrimaryKeySelective(roleReq));
    }

    /**
     * 角色状态
     *
     * @param roleReq
     * @return
     */
    @RequestMapping("/status/v1")
    public JSONResult status(@Validated(RoleReq.Status.class) @RequestBody RoleReq roleReq) {
        return JSONResult.ok(roleService.updateState(roleReq));
    }

    /**
     * 角色给用户(授权)
     *
     * @param batchRoleUserReq
     * @return
     */
    @PostMapping("/rolesToUser/v1")
    public JSONResult rolesToUser(@Validated(BatchRoleUserReq.Status.class) @RequestBody BatchRoleUserReq batchRoleUserReq) {
        return JSONResult.ok(roleService.rolesToUser(batchRoleUserReq));
    }

    /**
     * 为角色授权权限
     *
     * @param batchRoleUserReq
     * @return
     */
    @PostMapping("/aclsToRole/v1")
    public JSONResult aclsToRole(@Validated(BatchRoleUserReq.Permission.class) @RequestBody BatchRoleUserReq batchRoleUserReq) {
        return JSONResult.ok(roleService.aclsToRole(batchRoleUserReq));
    }

    /**
     * 角色列表
     *
     * @param batchRoleUserReq
     * @return
     */
    @PostMapping("/roleList/v1")
    public JSONResult roleList(@RequestBody RoleReq batchRoleUserReq) {
        return JSONResult.ok(roleService.roleList(batchRoleUserReq));
    }

    /**
     * 为角色授权 权限 之前， 需要查看该角色拥有哪些权限点，以及当前登录用户可以操作哪些权限
     *
     * @param loginAuthReq
     * @return
     */
    @PostMapping("/roleHaveAclTree/v1")
    public JSONResult roleHaveAclTree(@Validated(DynamicMenuAuthorReq.Detail.class) @RequestBody DynamicMenuAuthorReq loginAuthReq) {
        return JSONResult.ok(roleService.roleHaveAclTree(loginAuthReq));
    }

    /**
     * 角色给用户(回显)
     *
     * @param roleReq
     * @return
     */
    @PostMapping("/userCheckRoles/v1")
    public JSONResult userCheckRoles(@Validated(RoleReq.Permission.class) @RequestBody RoleReq roleReq) {
        return JSONResult.ok(roleService.userCheckedRoles(roleReq));
    }
}

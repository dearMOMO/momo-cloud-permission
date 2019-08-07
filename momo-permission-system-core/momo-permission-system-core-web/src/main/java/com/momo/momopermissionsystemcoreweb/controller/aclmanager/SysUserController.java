package com.momo.momopermissionsystemcoreweb.controller.aclmanager;

import com.momo.common.common.JSONResult;
import com.momo.mapper.req.aclmanager.SysUserAddRes;
import com.momo.mapper.req.aclmanager.SysUserListReq;
import com.momo.service.service.aclmanager.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @program: momo-cloud-permission
 * @description: 用户管理
 * @author: Jie Li
 * @create: 2019-08-02 17:18
 **/
@RestController
@RequestMapping(value = "/platform/sysUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
@Slf4j
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    /**
     * 用户分页列表
     *
     * @param sysUserListReq
     * @return
     */
    @PostMapping("/sysUserList/v1")
    public JSONResult sysUserList(@RequestBody SysUserListReq sysUserListReq) {
        return JSONResult.ok(sysUserService.sysUserList(sysUserListReq));
    }

    /**
     * 用户新增--账号密码
     *
     * @param sysUserAddRes
     * @return
     */
    @PostMapping("/sysUserAdd/v1")
    public JSONResult sysUserAdd(@Validated(SysUserAddRes.Add.class) @RequestBody SysUserAddRes sysUserAddRes) {
        return JSONResult.ok(sysUserService.sysUserAdd(sysUserAddRes));
    }

    /**
     * 查询用户详情
     *
     * @param sysUserAddRes
     * @return
     */
    @PostMapping("/sysUserDetail/v1")
    public JSONResult sysUserDetail(@Validated(SysUserAddRes.Detail.class) @RequestBody SysUserAddRes sysUserAddRes) {
        return JSONResult.ok(sysUserService.sysUserDetail(sysUserAddRes));
    }

    /**
     * 编辑
     *
     * @param sysUserAddRes
     * @return
     */
    @PostMapping("/sysUserModify/v1")
    public JSONResult sysUserModify(@Validated(SysUserAddRes.Modify.class) @RequestBody SysUserAddRes sysUserAddRes) {
        return JSONResult.ok(sysUserService.sysUserModify(sysUserAddRes));
    }

    /**
     * 用户状态
     *
     * @param sysUserAddRes
     * @return
     */
    @PostMapping("/sysUserStatus/v1")
    public JSONResult sysUserStatus(@Validated(SysUserAddRes.Status.class) @RequestBody SysUserAddRes sysUserAddRes) {
        return JSONResult.ok(sysUserService.sysUserStatus(sysUserAddRes));
    }

}

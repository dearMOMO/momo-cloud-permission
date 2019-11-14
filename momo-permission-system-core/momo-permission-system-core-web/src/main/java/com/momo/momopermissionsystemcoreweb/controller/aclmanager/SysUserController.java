package com.momo.momopermissionsystemcoreweb.controller.aclmanager;

import com.momo.common.core.common.JSONResult;
import com.momo.mapper.req.aclmanager.SysUserAddReq;
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
     * @param sysUserAddReq
     * @return
     */
    @PostMapping("/sysUserAdd/v1")
    public JSONResult sysUserAdd(@Validated(SysUserAddReq.Add.class) @RequestBody SysUserAddReq sysUserAddReq) {
        return JSONResult.ok(sysUserService.sysUserAdd(sysUserAddReq));
    }

    /**
     * 查询用户详情
     *
     * @param sysUserAddReq
     * @return
     */
    @PostMapping("/sysUserDetail/v1")
    public JSONResult sysUserDetail(@Validated(SysUserAddReq.Detail.class) @RequestBody SysUserAddReq sysUserAddReq) {
        return JSONResult.ok(sysUserService.sysUserDetail(sysUserAddReq));
    }

    /**
     * 编辑
     *
     * @param sysUserAddReq
     * @return
     */
    @PostMapping("/sysUserModify/v1")
    public JSONResult sysUserModify(@Validated(SysUserAddReq.Modify.class) @RequestBody SysUserAddReq sysUserAddReq) {
        return JSONResult.ok(sysUserService.sysUserModify(sysUserAddReq));
    }

    /**
     * 用户状态
     *
     * @param sysUserAddReq
     * @return
     */
    @PostMapping("/sysUserStatus/v1")
    public JSONResult sysUserStatus(@Validated(SysUserAddReq.Status.class) @RequestBody SysUserAddReq sysUserAddReq) {
        return JSONResult.ok(sysUserService.sysUserStatus(sysUserAddReq));
    }


    /**
     * 用户密码
     *
     * @param sysUserAddReq
     * @return
     */
    @PostMapping("/sysUserPwd/v1")
    public JSONResult sysUserPwd(@Validated(SysUserAddReq.Permission.class) @RequestBody SysUserAddReq sysUserAddReq) {
        return JSONResult.ok(sysUserService.sysUserPwd(sysUserAddReq));
    }

}

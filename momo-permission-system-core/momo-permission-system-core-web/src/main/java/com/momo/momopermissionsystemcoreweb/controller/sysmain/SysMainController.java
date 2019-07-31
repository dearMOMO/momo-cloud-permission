package com.momo.momopermissionsystemcoreweb.controller.sysmain;

import com.momo.common.common.JSONResult;
import com.momo.mapper.req.sysmain.SysUserLoginReq;
import com.momo.service.service.sysmain.SysMainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: momo-cloud-permission
 * @description: TODO
 * @author: Jie Li
 * @create: 2019-07-30 16:39
 **/
@RestController
@RequestMapping(value = "/platform/sysMain", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
@Slf4j
public class SysMainController {
    @Autowired
    private SysMainService sysMainService;

    /**
     * 用户登录
     *
     * @param sysUserLoginReq
     * @return
     */
    @PostMapping("/login/v1")
    public JSONResult userLogin(@Validated(SysUserLoginReq.Query.class) @RequestBody SysUserLoginReq sysUserLoginReq, HttpServletRequest request) {
        String msg = sysMainService.userLogin(sysUserLoginReq, request);
        return JSONResult.ok(msg, "登录成功");
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @PostMapping("/getUserInfo/v1")
    public JSONResult getUserInfo() {
        return JSONResult.ok(sysMainService.getUserInfo());
    }

    /**
     * 验证码
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(path = "/images/captcha/v1")
    public JSONResult captcha() throws Exception {
        return sysMainService.createVerificationCode();
    }
}

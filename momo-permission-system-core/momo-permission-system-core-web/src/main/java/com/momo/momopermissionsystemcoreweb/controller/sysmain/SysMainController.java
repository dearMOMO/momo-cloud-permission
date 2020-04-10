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
package com.momo.momopermissionsystemcoreweb.controller.sysmain;

import com.momo.common.core.common.JSONResult;
import com.momo.common.log.annotation.SystemCoreLog;
import com.momo.mapper.req.sysmain.SysUserLoginReq;
import com.momo.service.service.sysmain.SysMainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: momo-cloud-permission
 * @description: 用户会话相关
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
    @SystemCoreLog(description = "用户登录")
    @PostMapping("/login/v1")
    public JSONResult userLogin(@Validated(SysUserLoginReq.Query.class) @RequestBody SysUserLoginReq sysUserLoginReq, HttpServletRequest request) {
        String msg = sysMainService.userLogin(sysUserLoginReq, request);
        return JSONResult.ok(msg, "登录成功");
    }

    /**
     * 用户退出
     *
     * @return
     */
    @PostMapping("/logout/v1")
    public JSONResult logout() {
        String msg = sysMainService.logout();
        return JSONResult.ok(msg);
    }

    /**
     * 登陆成功后 获取用户信息
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

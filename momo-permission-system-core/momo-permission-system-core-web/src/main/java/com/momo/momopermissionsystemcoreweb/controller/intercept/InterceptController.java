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
package com.momo.momopermissionsystemcoreweb.controller.intercept;

import com.momo.common.core.common.JSONResult;
import com.momo.mapper.req.sysmain.HasAclReq;
import com.momo.service.service.authority.AdminSysCoreService;
import com.momo.service.service.authority.CommonSysCoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: InterceptController
 * @Author: Jie Li
 * @Date 2019-10-15 17:53
 * @Description: 当前用户是否有权限访问服务权限
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
@RestController
@RequestMapping(value = "/platform/intercept", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
@Slf4j
public class InterceptController {
    @Autowired
    private AdminSysCoreService adminSysCoreService;
    @Autowired
    private CommonSysCoreService commonSysCoreService;

    /**
     * 校验当前用户是否有权限访问服务权限
     *
     * @param hasAclReq
     * @return
     */
    @RequestMapping("/hasAcl/v1")
    public JSONResult hasAcl(@RequestBody HasAclReq hasAclReq) {
        if (hasAclReq.getTenantId().equals(1L)) {
            boolean hasUrlAcl = adminSysCoreService.hasUrlAcl(hasAclReq);
            return JSONResult.ok(hasUrlAcl);
        } else {
            boolean hasUrlAcl = commonSysCoreService.hasUrlAcl(hasAclReq);
            return JSONResult.ok(hasUrlAcl);
        }
    }
}

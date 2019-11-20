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
package com.momo.momopermissiongateway.feign;

import com.momo.common.core.common.JSONResult;
import com.momo.momopermissiongateway.req.HasAclReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @ClassName: HasAclFeign
 * @Author: Jie Li
 * @Date 2019-10-15 18:00
 * @Description: 网关权限拦截
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
@FeignClient(name = "momo-permission-system-core", fallback = HasAclServiceFeign.HasAclServiceImplFeign.class)
public interface HasAclServiceFeign {

    @PostMapping(value = "/platform/intercept/hasAcl/v1", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    JSONResult hasAcl(@RequestBody HasAclReq hasAclReq);

    @Component
    @Slf4j
    class HasAclServiceImplFeign implements HasAclServiceFeign {
        @Override
        public JSONResult hasAcl(HasAclReq hasAclReq) {
            log.error("hasAcl error");
            return JSONResult.errorMsg("服务调用权限校验异常");
        }
    }
}

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
package com.momo.service.async;

import com.alibaba.fastjson.JSONObject;
import com.momo.common.core.common.UserAgentGetter;
import com.momo.common.core.util.DateUtils;
import com.momo.mapper.dataobject.LoginLogDO;
import com.momo.mapper.mapper.manual.LoginLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 李杰 on 2019/2/10.
 */
@Service
@Slf4j
public class LoginLogAsync {
    @Autowired
    private LoginLogMapper loginLogMapper;

    @Async("threadPoolTaskExecutor")
    public void loginLog(LoginLogDO entity, HttpServletRequest request){
        UserAgentGetter userAgentGetter = new UserAgentGetter(request);
        entity.setCreateTime(DateUtils.getDateTime());
        entity.setUserIp(userAgentGetter.getIpAddr());
        entity.setUserLoginDevice(userAgentGetter.getDevice());
        entity.setUserLoginSys(userAgentGetter.getOS());
        entity.setUserLoginBrowser(userAgentGetter.getBrowser());
        log.info("登录日志记录:{}", JSONObject.toJSONString(entity));
        loginLogMapper.insertSelective(entity);
    }
}


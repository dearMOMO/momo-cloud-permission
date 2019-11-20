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
package com.momo.service.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.momo.common.core.entity.RedisUser;
import com.momo.common.core.error.RedisKeyEnum;
import com.momo.common.core.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by MOMO on 2019/1/8.
 */
@Slf4j
public class BaseService {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 由网关 传入最新的user信息
     *
     * @return
     */
    public RedisUser redisUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String authToken = request.getHeader(RedisKeyEnum.REDIS_KEY_USER_HEADER_CODE.getKey());
        //解析token
        if (!StringUtils.isEmpty(authToken)) {
            //解析token
            String userInfo = jwtTokenUtil.getUsernameFromToken(authToken);
            return JSON.parseObject(userInfo, new TypeReference<RedisUser>() {
            });
        }
        RedisUser redisUser = new RedisUser();
        redisUser.setSysUserName("MOMO");
        return redisUser;
    }

    /**
     * 用户登录时，存入jwt信息，更新用户无法，更新jwt
     *
     * @param token
     * @return
     */
    public RedisUser jwtUser(String token) {
        String tok = jwtTokenUtil.getUsernameFromToken(token);
        RedisUser redisUser = JSON.parseObject(tok, new TypeReference<RedisUser>() {
        });
        return redisUser;
    }

}

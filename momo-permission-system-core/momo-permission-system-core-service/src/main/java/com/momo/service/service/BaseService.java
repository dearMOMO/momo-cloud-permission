package com.momo.service.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.momo.common.core.error.RedisKeyEnum;
import com.momo.common.core.util.JwtTokenUtil;
import com.momo.common.core.entity.RedisUser;
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
            RedisUser redisUser = JSON.parseObject(userInfo, new TypeReference<RedisUser>() {
            });
            return redisUser;
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

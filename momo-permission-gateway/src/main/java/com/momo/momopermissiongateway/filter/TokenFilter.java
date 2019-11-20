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
package com.momo.momopermissiongateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.momo.common.core.common.JSONResult;
import com.momo.common.core.entity.RedisUser;
import com.momo.common.core.error.RedisKeyEnum;
import com.momo.common.core.util.DateUtils;
import com.momo.common.core.util.JwtTokenUtil;
import com.momo.common.core.util.RedisUtil;
import com.momo.momopermissiongateway.configuration.InterceptUrlConfiguration;
import com.momo.momopermissiongateway.feign.HasAclServiceFeign;
import com.momo.momopermissiongateway.req.HasAclReq;
import com.momo.momopermissiongateway.res.JwtResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URLEncoder;
import java.util.Date;

/**
 * Created by MOMO on 2018/12/28.
 * 2.全局过滤器（GlobalFilter）
 * JTW 校验
 */
@Component
@Slf4j
public class TokenFilter implements GlobalFilter, Ordered {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private RedisUtil redisUtil;

    // JWT 失效时间小于60分钟，更新JWT
    private final static Long EXPIREDJWT = 3600L;
    //redis 30 分钟会话失效时间
    private final static Long EXPIREDREDIS = 1800L;
    @Autowired
    private InterceptUrlConfiguration interceptUrlConfiguration;
    @Autowired
    private HasAclServiceFeign hasAclServiceFeign;
    @Value("${momo.modifyAdministratorAccount}")
    private boolean modifyAdministratorAccount;
    @Value("${momo.modifyAdministratorAccountUrl}")
    private String modifyAdministratorAccountUrl;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String uuid = exchange.getRequest().getHeaders().getFirst(RedisKeyEnum.REDIS_KEY_HEADER_INFO.getKey());
        String authToken = "";
        ServerHttpRequest serverHttpRequest = exchange.getRequest();

        try {
            URI uri = serverHttpRequest.getURI();
            String path = uri.getPath();
            if (interceptUrlConfiguration.checkIgnoreUrl(path)) {
                return chain.filter(exchange);
            }
            if (StringUtils.isBlank(uuid)) {
                return JwtResponse.jwtResponse(exchange, HttpStatus.UNAUTHORIZED.value(), "token出错");
            } else {

                //用户 Token
                Object sessionJwt = redisUtil.get(RedisKeyEnum.REDIS_KEY_USER_INFO.getKey() + uuid);
                if (sessionJwt == null) {
                    return JwtResponse.jwtResponse(exchange, HttpStatus.UNAUTHORIZED.value(), "会话已失效，请重新登录");
                }
                authToken = String.valueOf(sessionJwt);
                //解析token
                String userInfo = jwtTokenUtil.getUsernameFromToken(authToken);
                RedisUser redisUser = JSON.parseObject(userInfo, new TypeReference<RedisUser>() {
                });
                if (!redisUser.getTenantId().equals(interceptUrlConfiguration.getTeantId()) && interceptUrlConfiguration.checkEnterpriseUrl(path)) {
                    return JwtResponse.jwtResponse(exchange, HttpStatus.UNAUTHORIZED.value(), "您无权限访问");
                }
                //不允许修改超级管理员密码
                if (interceptUrlConfiguration.checkIsSuperAdmin(redisUser.getSysUserPhone()) && path.equalsIgnoreCase(modifyAdministratorAccountUrl)) {
                    return JwtResponse.jwtResponse(exchange, HttpStatus.INTERNAL_SERVER_ERROR.value(), "做我女朋友，工资卡归您保管 ٩꒰▽ ꒱۶⁼³₌₃ 超级管理员密码不允许修改");
                }

                //权限拦截
                HasAclReq hasAclReq = new HasAclReq();
                hasAclReq.setUrl(path);
                hasAclReq.setTenantId(redisUser.getTenantId());
                hasAclReq.setSysUserPhone(redisUser.getSysUserPhone());
                JSONResult hasAcl = hasAclServiceFeign.hasAcl(hasAclReq);
                if (hasAcl.getStatus() == 500) {
                    return JwtResponse.jwtResponse(exchange, HttpStatus.INTERNAL_SERVER_ERROR.value(), hasAcl.getMsg());
                } else if (hasAcl.getStatus() == 200) {
                    if (!(boolean) hasAcl.getData()) {
                        return JwtResponse.jwtResponse(exchange, HttpStatus.UNAUTHORIZED.value(), "您无权限访问");
                    }
                } else {
                    return JwtResponse.jwtResponse(exchange, HttpStatus.UNAUTHORIZED.value(), "您无权限访问");
                }
                //第三方
                //jwt 失效时间
                Date getExpirationDateFromToken = jwtTokenUtil.getExpirationDateFromToken(String.valueOf(sessionJwt));
                long remainingMinutes = DateUtils.getMinuteDifference(getExpirationDateFromToken, DateUtils.getDateTime()
                );
                //刷新JTW
                if (remainingMinutes <= EXPIREDJWT) {
                    // randomKey和token已经生成完毕
                    final String randomKey = jwtTokenUtil.getRandomKey();
                    final String newToken = jwtTokenUtil.generateToken(userInfo, randomKey);
                    redisUtil.set(RedisKeyEnum.REDIS_KEY_USER_INFO.getKey() + redisUser.getRedisUserKey(), newToken, RedisKeyEnum.REDIS_KEY_USER_INFO.getExpireTime());
                    authToken = newToken;
                }
                //刷新Redis-token时间
                Long o = redisUtil.getExpire(RedisKeyEnum.REDIS_KEY_USER_INFO.getKey() + redisUser.getRedisUserKey());
                if (o <= EXPIREDREDIS) {
                    //刷新redis时间
                    redisUtil.expire(RedisKeyEnum.REDIS_KEY_USER_INFO.getKey() + redisUser.getRedisUserKey(), RedisKeyEnum.REDIS_KEY_USER_INFO.getExpireTime());
                }
                //刷新Redis-userId时间
                Long userId = redisUtil.getExpire(RedisKeyEnum.REDIS_KEY_USER_ID.getKey() + redisUser.getBaseId());
                if (userId <= EXPIREDREDIS) {
                    //刷新redis时间
                    redisUtil.expire(RedisKeyEnum.REDIS_KEY_USER_ID.getKey() + redisUser.getBaseId(), RedisKeyEnum.REDIS_KEY_USER_ID.getExpireTime());
                }
                ServerHttpRequest request = exchange
                        .getRequest().mutate()
                        // 统一头部，用于防止直接调用服务
                        .header(RedisKeyEnum.REDIS_KEY_USER_HEADER_CODE.getKey(), authToken)
                        .header("exit", uuid)
                        .build();
                exchange.getResponse().getHeaders().add("testresponse", "testresponse");
                //将新的request 变成 change对象
                ServerWebExchange serverWebExchange = exchange.mutate().request(request).build();
                return chain.filter(serverWebExchange);
            }
        } catch (MalformedJwtException e) {
            log.error("JWT格式错误异常:{}======>>>:{}====={}", uuid, e.getMessage(), e);
            return JwtResponse.jwtResponse(exchange, HttpStatus.UNAUTHORIZED.value(), "JWT格式错误");
        } catch (SignatureException e) {
            log.error("JWT签名错误异常:{}======>>>:{}", uuid, e.getMessage(), e);
            return JwtResponse.jwtResponse(exchange, HttpStatus.UNAUTHORIZED.value(), "JWT签名错误");
        } catch (ExpiredJwtException e) {
            log.error("JWT过期异常:{}======>>>:{}", uuid, e.getMessage(), e);
            return JwtResponse.jwtResponse(exchange, HttpStatus.UNAUTHORIZED.value(), "会话已失效，请重新登录");
        } catch (UnsupportedJwtException e) {
            log.error("不支持的JWT异常:{}======>>>:{}", uuid, e.getMessage(), e);
            return JwtResponse.jwtResponse(exchange, HttpStatus.UNAUTHORIZED.value(), "JWT格式不正确");
        } catch (Exception e) {
            log.error("TokenFilter，不支持的异常:{}======>>>:{}", uuid, e.getMessage(), e);
            return JwtResponse.jwtResponse(exchange, HttpStatus.UNAUTHORIZED.value(), "TokenFilter：token 解析异常:");
        }
    }

    @Override
    public int getOrder() {
        return -999999999;
    }

    private String urlEncode(Object o) {
        try {
            return URLEncoder.encode(JSONObject.toJSONString(o), "UTF-8");
        } catch (Exception e) {
            log.error("urlEncode:{}", e.getMessage());
        }
        return "";
    }

}
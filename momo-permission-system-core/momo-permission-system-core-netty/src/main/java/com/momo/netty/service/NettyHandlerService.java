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
package com.momo.netty.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.momo.common.core.entity.RedisUser;
import com.momo.common.core.error.RedisKeyEnum;
import com.momo.common.core.util.DateUtils;
import com.momo.common.core.util.JwtTokenUtil;
import com.momo.common.core.util.RedisUtil;
import com.momo.netty.utils.ChannelManager;
import com.momo.netty.utils.IMMessage;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName: NettyHandlerService
 * @Author: Jie Li
 * @Date 2019-11-25 16:33
 * @Description: TODO
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
@Service
@Slf4j
@Async("threadPoolTaskExecutor")
public class NettyHandlerService {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    // JWT 失效时间小于5分钟，更新JWT 60*5
    private final static Long EXPIREDJWT = 300L;

    public void refreshTken(ChannelHandlerContext ctx, String msg) {
        try {
            // 获取客户端传输过来的消息
            IMMessage imMessageClient = JSON.parseObject(msg, new TypeReference<IMMessage>() {
            });
            //首次连接成功获取token
            if (imMessageClient.getMsgType().equals(RedisKeyEnum.NETTY_REFRESH_TOKEN.getExpireTime())) {
                Object tokenObj = redisUtil.get(RedisKeyEnum.REDIS_KEY_USER_INFO.getKey() + imMessageClient.getToken());
                if (null == tokenObj) {
                    ctx.channel().close();
                    return;
                }
                try {
                    //解析token
                    String userInfo = jwtTokenUtil.getUsernameFromToken(String.valueOf(tokenObj));
                    RedisUser redisUser = JSON.parseObject(userInfo, new TypeReference<RedisUser>() {
                    });
                    RedisUser nettyRedisUser = RedisUser.builder().baseId(redisUser.getBaseId()).sysUserPhone(redisUser.getSysUserPhone())
                            .tenantId(redisUser.getTenantId()).build();
                    final String randomKey = jwtTokenUtil.getRandomKey();
                    //60*20
                    final String token = jwtTokenUtil.generateTokenNetty(JSONObject.toJSONString(nettyRedisUser), randomKey, 1200L);
                    IMMessage imMessage = new IMMessage(RedisKeyEnum.NETTY_REFRESH_TOKEN.getExpireTime(), token, null);
                    //下发token给客户端
                    ChannelManager.ctxWrite(ctx, imMessage);
                } catch (MalformedJwtException e) {
                    log.error("JWT格式错误异常:======>>>:{}====={}", e.getMessage(), e);
                    ctx.channel().close();
                } catch (SignatureException e) {
                    log.error("JWT签名错误异常:======>>>:{}", e.getMessage(), e);
                    ctx.channel().close();
                } catch (ExpiredJwtException e) {
                    log.error("JWT过期异常:======>>>:{}", e.getMessage(), e);
                    ctx.channel().close();
                } catch (UnsupportedJwtException e) {
                    log.error("不支持的JWT异常:======>>>:{}", e.getMessage(), e);
                    ctx.channel().close();
                } catch (Exception e) {
                    log.error("TokenFilter，不支持的异常:{}======>>>:{}", e.getMessage(), e);
                    ctx.channel().close();
                }
            } else {
                String tokenClient = imMessageClient.getToken();
                //解析token
                String userInfo = jwtTokenUtil.getUsernameFromToken(tokenClient);
                RedisUser redisUser = JSON.parseObject(userInfo, new TypeReference<RedisUser>() {
                });
                Date getExpirationDateFromToken = jwtTokenUtil.getExpirationDateFromToken(tokenClient);
                long remainingMinutes = DateUtils.getMinuteDifference(getExpirationDateFromToken, DateUtils.getDateTime()
                );
                if (remainingMinutes <= EXPIREDJWT) {
                    RedisUser nettyRedisUser = RedisUser.builder().baseId(redisUser.getBaseId()).sysUserPhone(redisUser.getSysUserPhone())
                            .tenantId(redisUser.getTenantId()).build();
                    final String randomKey = jwtTokenUtil.getRandomKey();
                    //60*20
                    final String token = jwtTokenUtil.generateTokenNetty(JSONObject.toJSONString(nettyRedisUser), randomKey, 1200L);
                    IMMessage imMessage = new IMMessage(RedisKeyEnum.NETTY_REFRESH_TOKEN.getExpireTime(), token, null);
                    //客户端刷新token
                    ChannelManager.ctxWrite(ctx, imMessage);
                }
            }
        } catch (MalformedJwtException e) {
            log.error("JWT格式错误异常:======>>>:{}====={}", e.getMessage(), e);
            ctx.channel().close();
        } catch (SignatureException e) {
            log.error("JWT签名错误异常:======>>>:{}", e.getMessage(), e);
            ctx.channel().close();
        } catch (ExpiredJwtException e) {
            log.error("JWT过期异常:======>>>:{}", e.getMessage(), e);
            ctx.channel().close();
        } catch (UnsupportedJwtException e) {
            log.error("不支持的JWT异常:======>>>:{}", e.getMessage(), e);
            ctx.channel().close();
        } catch (Exception e) {
            log.error("TokenFilter，不支持的异常:{}======>>>:{}", e.getMessage(), e);
            ctx.channel().close();
        }
    }
}

package com.momo.momopermissiongateway.res;

import com.alibaba.fastjson.JSONObject;
import com.momo.common.core.common.JSONResult;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * Created by MOMO on 2018/12/28.
 */
public class JwtResponse {
    public static Mono<Void> jwtResponse(ServerWebExchange exchange, Integer httpStatus, String responseMsg) {
        byte[] bytes = JSONObject.toJSONString(JSONResult.errorException(httpStatus.intValue(), responseMsg, responseMsg)).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
        return exchange.getResponse().writeWith(Flux.just(buffer));
    }
}

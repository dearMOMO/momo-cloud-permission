package com.momo.momopermissiongateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.momo.momopermissiongateway.common.JSONResult;
import com.momo.momopermissiongateway.configuration.CrossDomainConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @program: momo-cloud
 * @description: gateway自带的RequestRateLimiter可定制的内容太少，真正用的话，需要：
 *                  1. 自定义限流后的response返回值
 *                  2. 不同的key（即接口）限流数不同
 *                  所以需要自定义一个限流的gateway filter
 * @author: Jie Li
 * @create: 2019-07-17 10:45
 **/
@Component
@Slf4j
public class RateCheckGatewayFilterFactory extends AbstractGatewayFilterFactory<RateCheckGatewayFilterFactory.Config> implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    private RateCheckRedisRateLimiter rateLimiter;
    private KeyResolver keyResolver;

    public RateCheckGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        log.info("RateCheckGatewayFilterFactory.setApplicationContext，applicationContext=" + context);
        applicationContext = context;
    }

    @Override
    public GatewayFilter apply(Config config) {
        this.rateLimiter = applicationContext.getBean(RateCheckRedisRateLimiter.class);
        this.keyResolver = applicationContext.getBean(config.keyResolver, KeyResolver.class);

        return (exchange, chain) -> {
            Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);

            return keyResolver.resolve(exchange).flatMap(key ->
                    // TODO: if key is empty?
                    rateLimiter.isAllowed(route.getId(), key).flatMap(response -> {
                        log.info("response: " + response);
                        // TODO: set some headers for rate, tokens left
                        if (response.isAllowed()) {
                            return chain.filter(exchange);
                        }
                        //超过了限流的response返回值
                        return setRateCheckResponse(exchange);
                    }));
        };
    }

    private Mono<Void> setRateCheckResponse(ServerWebExchange exchange) {
        //超过了限流
        ServerHttpResponse response = exchange.getResponse();
        //设置headers
        HttpHeaders httpHeaders = response.getHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
        httpHeaders.add("Access-Control-Allow-Origin", CrossDomainConfiguration.ALLOWED_ORIGIN);
        httpHeaders.add("Access-Control-Allow-Methods", CrossDomainConfiguration.ALLOWED_METHODS);
        httpHeaders.add("Access-Control-Max-Age", CrossDomainConfiguration.MAX_AGE);
        httpHeaders.add("Access-Control-Allow-Headers", CrossDomainConfiguration.ALLOWED_HEADERS);
        httpHeaders.add("Access-Control-Expose-Headers", CrossDomainConfiguration.ALLOWED_Expose);
        httpHeaders.add("Access-Control-Allow-Credentials", "true");
        httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        //设置body
        JSONResult jsonResult = JSONResult.build(HttpStatus.TOO_MANY_REQUESTS.value(), "当前访问人数过多，请稍后重试", "");
        DataBuffer bodyDataBuffer = response.bufferFactory().wrap(JSONObject.toJSONString(jsonResult).getBytes());
        log.info("限流了================");
        return response.writeWith(Mono.just(bodyDataBuffer));
    }

    public static class Config {
        private String keyResolver;//限流id

        public String getKeyResolver() {
            return keyResolver;
        }
        public void setKeyResolver(String keyResolver) {
            this.keyResolver = keyResolver;
        }
    }
}

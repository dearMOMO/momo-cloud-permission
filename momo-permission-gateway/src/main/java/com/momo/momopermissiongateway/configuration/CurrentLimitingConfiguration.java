package com.momo.momopermissiongateway.configuration;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

/**
 * @program: momo-cloud
 * @description: 限流
 * @author: Jie Li
 * @create: 2019-07-17 22:33
 **/
@Configuration
public class CurrentLimitingConfiguration {

    //根据api接口来限流
    //获取请求地址的uri作为限流key
    @Bean(name = "apiKeyResolver")
    @Primary
    public KeyResolver apiKeyResolver() {
        return exchange -> {
            return Mono.just(exchange.getRequest().getPath().value());
        };
    }

    //用户限流
    //使用这种方式限流，请求路径中必须携带userId参数
    @Bean(name = "userKeyResolver")
    public KeyResolver userKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("userId"));
    }

    //IP限流
    @Bean(name = "ipKeyResolver")
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }

}

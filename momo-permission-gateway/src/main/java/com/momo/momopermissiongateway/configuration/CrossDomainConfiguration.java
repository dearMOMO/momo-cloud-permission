package com.momo.momopermissiongateway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import org.springframework.web.util.pattern.PathPatternParser;
import reactor.core.publisher.Mono;

/**
 * Created by MOMO on 2018/12/27.
 */
@Configuration
public class CrossDomainConfiguration {
    //这里为支持的请求头，如果有自定义的header字段请自己添加（不知道为什么不能使用*）
    public static final String ALLOWED_HEADERS = "x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN,userInfo,x-token,token,client";
    public static final String ALLOWED_METHODS = "POST, GET, OPTIONS,DELETE,PUT";
    public static final String ALLOWED_ORIGIN = "*";
    public static final String ALLOWED_Expose = "*";
    public static final String MAX_AGE = "96400L";
//    private static final String MAX_AGE = "60*60*24L";

//    @Bean
//    public WebFilter corsFilter() {
//        return (ServerWebExchange ctx, WebFilterChain chain) -> {
//            ServerHttpRequest request = ctx.getRequest();
//            if (CorsUtils.isCorsRequest(request)) {
//                ServerHttpResponse response = ctx.getResponse();
//                HttpHeaders headers = response.getHeaders();
////                headers.add("Access-Control-Allow-Origin", ALLOWED_ORIGIN);
//                headers.add("Access-Control-Allow-Methods", ALLOWED_METHODS);
//                headers.add("Access-Control-Max-Age", MAX_AGE);
//                headers.add("Access-Control-Allow-Headers", ALLOWED_HEADERS);
//                headers.add("Access-Control-Expose-Headers", ALLOWED_Expose);
//                headers.add("Access-Control-Allow-Credentials", "true");
//                if (request.getMethod() == HttpMethod.OPTIONS) {
//                    response.setStatusCode(HttpStatus.OK);
//                    return Mono.empty();
//                }
//            }
//            return chain.filter(ctx);
//        };
//    }

    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedMethod("*");
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }

    /**
     *
     *如果使用了注册中心（如：Eureka），进行控制则需要增加如下配置
     */
   /* @Bean
    public RouteDefinitionLocator discoveryClientRouteDefinitionLocator(DiscoveryClient discoveryClient) {
        return new DiscoveryClientRouteDefinitionLocator(discoveryClient);
    }*/
}

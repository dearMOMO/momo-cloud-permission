package com.momo.momopermissiongateway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

import static org.springframework.web.cors.CorsConfiguration.ALL;

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

    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // cookie跨域
        config.setAllowCredentials(Boolean.TRUE);
        config.addAllowedMethod(ALL);
        config.addAllowedOrigin(ALL);
        config.addAllowedHeader(ALL);
        // 配置前端js允许访问的自定义响应头
        config.addExposedHeader("x-token");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }
}

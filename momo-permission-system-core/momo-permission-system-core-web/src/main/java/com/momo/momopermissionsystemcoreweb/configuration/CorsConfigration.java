///**
// * Copyright (c) 2018-2019, Jie Li 李杰 (mqgnsds@163.com).
// * <p>
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// * <p>
// * http://www.apache.org/licenses/LICENSE-2.0
// * <p>
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package com.momo.momopermissionsystemcoreweb.configuration;
//
///**
// * Created by Kagome on 2019/5/7.
// */
//
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
///**
// * 跨域配置
// *  https://www.cnblogs.com/cityspace/p/6858969.html
// */
//@Configuration
//public class CorsConfigration {
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        // #允许向该服务器提交请求的URI，*表示全部允许
//        config.addAllowedOrigin(CorsConfiguration.ALL);
//        // 允许cookies跨域
//        config.setAllowCredentials(true);
//        // #允许访问的头信息,*表示全部
//        config.addAllowedHeader(CorsConfiguration.ALL);
//        // 允许提交请求的方法，*表示全部允许
//        config.addAllowedMethod(CorsConfiguration.ALL);
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }
//
//    /**
//     * 配置过滤器
//     */
//    @Bean
//    public FilterRegistrationBean someFilterRegistration() {
//        FilterRegistrationBean<CorsFilter> registration = new FilterRegistrationBean<>();
//        registration.setFilter(corsFilter());
//        registration.addUrlPatterns("/*");
//        registration.setName("corsFilter");
//        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        return registration;
//    }
//}
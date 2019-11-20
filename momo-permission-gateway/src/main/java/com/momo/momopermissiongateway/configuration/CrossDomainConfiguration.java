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
package com.momo.momopermissiongateway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * @ClassName: CrossDomainConfiguration
 * @Author: Jie Li
 * @Date 2019-11-20 15:03
 * @Description: TODO
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
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
        config.addAllowedMethod(CorsConfiguration.ALL);
        config.addAllowedOrigin(CorsConfiguration.ALL);
        config.addAllowedHeader(CorsConfiguration.ALL);
        // 配置前端js允许访问的自定义响应头
        config.addExposedHeader("x-token");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }
}


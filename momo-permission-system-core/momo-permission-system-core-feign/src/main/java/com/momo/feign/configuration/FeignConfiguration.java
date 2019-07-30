package com.momo.feign.configuration;

import com.momo.feign.interceptor.FeignBasicAuthRequestInterceptor;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: momo-cloud
 * @description: 配置feign日志输出
 *              日志的输出还需要在配置文件中指定才能生效
 * logging:
 *   level:
 *     com.momo.cloudspringserver.feign.FeignService: info
 * @author: Jie Li
 * @create: 2019-07-16 22:11
 **/
@Configuration
public class FeignConfiguration {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
    @Bean
    Logger momoFeignLogger(){
        return new MomoFeignLogger();
    }
    /**
     * 创建Feign请求拦截器，在发送请求前设置认证的token,各个微服务将token设置到环境变量中来达到通用
     * @return
     */
    @Bean
    public FeignBasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new FeignBasicAuthRequestInterceptor();
    }
}

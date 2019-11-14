package com.momo.momopermissionsystemcoreweb.configuration;

/**
 * @ClassName: OkHttpConfig
 * @Author: Jie Li
 * @Date 2019-10-25 16:38
 * @Description: TODO
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/

import feign.Feign;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * 配置 okhttp 与连接池
 * ConnectionPool 默认创建5个线程，保持5分钟长连接
 */
@Configuration
@ConditionalOnClass(Feign.class)
@AutoConfigureBefore(FeignAutoConfiguration.class) //SpringBoot自动配置
public class OkHttpConfig {

    // 默认老外留给你彩蛋中文乱码，加上它就 OK
   /* @Bean
    public Encoder encoder() {
        return new FormEncoder();
    }*/

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                //设置连接超时
                .connectTimeout(10, TimeUnit.SECONDS)
                //设置读超时
                .readTimeout(10, TimeUnit.SECONDS)
                //设置写超时
                .writeTimeout(10, TimeUnit.SECONDS)
                //是否自动重连
                .retryOnConnectionFailure(true)
                .addInterceptor(new LoggerInterceptor())
                .connectionPool(new ConnectionPool(10, 5L, TimeUnit.MINUTES))
                .build();
    }
}

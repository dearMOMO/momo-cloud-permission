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

import feign.Feign;
import okhttp3.*;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: OkHttpConfig
 * @Author: Jie Li
 * @Date 2019-10-25 16:50
 * @Description: 配置 okhttp 与连接池
 * ConnectionPool 默认创建5个线程，保持5分钟长连接
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
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
    public okhttp3.OkHttpClient okHttpClient() {
        return new okhttp3.OkHttpClient.Builder()
                //设置连接超时
                .connectTimeout(10, TimeUnit.SECONDS)
                //设置读超时
                .readTimeout(10, TimeUnit.SECONDS)
                //设置写超时
                .writeTimeout(10, TimeUnit.SECONDS)
                //是否自动重连
                .retryOnConnectionFailure(true)
                .addInterceptor(new OkHttpLoggerInterceptor())
                .connectionPool(new ConnectionPool(10, 5L, TimeUnit.MINUTES))
                .build();
    }
}

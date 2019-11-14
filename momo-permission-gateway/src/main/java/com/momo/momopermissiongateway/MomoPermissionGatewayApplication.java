package com.momo.momopermissiongateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients({"com.momo.momopermissiongateway.feign"}) //调用其他服务的api
@ComponentScan(basePackages = "com.momo")
@EnableDiscoveryClient //让注册中心能够发现 ,可以是其他注册中心
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, RedisAutoConfiguration.class, RedisRepositoriesAutoConfiguration.class})
public class MomoPermissionGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MomoPermissionGatewayApplication.class, args);
    }

}

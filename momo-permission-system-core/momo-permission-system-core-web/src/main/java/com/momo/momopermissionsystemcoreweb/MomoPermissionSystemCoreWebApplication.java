package com.momo.momopermissionsystemcoreweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient //让注册中心能够发现 ,可以是其他注册中心
@ComponentScan(basePackages = "com.momo")
@EnableFeignClients({"com.momo.feign"}) //调用其他服务的api
@SpringBootApplication
public class MomoPermissionSystemCoreWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MomoPermissionSystemCoreWebApplication.class, args);
    }

}

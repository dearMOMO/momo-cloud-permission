package com.momo.momopermissioneureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@EnableEurekaServer
public class MomoPermissionEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MomoPermissionEurekaApplication.class, args);
    }

    @Configuration
    public static class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
           /* http.authorizeRequests().anyRequest().permitAll()
                    .and().csrf().disable();*/
            //Spring Could 2.0 eureka 配置spring.security后其他服务无法连接注册中心
            http.csrf().disable();
            super.configure(http);
        }
    }
}

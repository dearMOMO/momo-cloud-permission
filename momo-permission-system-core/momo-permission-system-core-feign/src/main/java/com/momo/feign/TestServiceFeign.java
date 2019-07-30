//package com.momo.feign;
//
//import com.momo.common.common.JSONResult;
//import com.momo.feign.configuration.FeignConfiguration;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
///**
// * @program: momo-cloud-permission
// * @description: TODO
// * @author: Jie Li
// * @create: 2019-07-30 09:56
// **/
//@FeignClient(name = "cloud-spring-server", configuration = {FeignConfiguration.class}, fallback = TestServiceFeign.TestServiceFeignImpl.class)
//public interface TestServiceFeign {
//    @PostMapping(value = "/platform/userInfo/v1", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public JSONResult getUserInfo(@RequestBody SysUser sysUser);
//
//    @Component
//    @Slf4j
//    static class TestServiceFeignImpl implements TestServiceFeign {
//        @Override
//        public JSONResult getUserInfo(SysUser sysUser) {
//            log.error("feign调用异常:");
//            return JSONResult.errorMap("feign调用异常");
//        }
//    }
//}

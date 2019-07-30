package com.momo.feign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program: momo-cloud
 * @description: Feign请求拦截器
 * @author: Jie Li
 * @create: 2019-07-16 22:56
 **/
@Configuration // 加上该注解 ，则不需要FeignClient里面加属性configuration
public class FeignBasicAuthRequestInterceptor implements RequestInterceptor {
    public FeignBasicAuthRequestInterceptor() {
    }

    @Override
    public void apply(RequestTemplate template) {
        HttpServletRequest request = getHttpServletRequest();
        template.header("Content-Type", "application/json;charset=UTF-8");
//        if (Objects.isNull(request)) {
//            return;
//        }
//
//        Map<String, String> headers = getHeaders(request);
//        if (headers.size() > 0) {
//            Iterator<Map.Entry<String, String>> iterator = headers.entrySet().iterator();
//            while (iterator.hasNext()) {
//                Map.Entry<String, String> entry = iterator.next();
//                // 把请求过来的header请求头 原样设置到feign请求头中
//                // 包括token
//                template.header(entry.getKey(), entry.getValue());
//            }
//        }
    }
    private HttpServletRequest getHttpServletRequest() {

        try {
            // 这种方式获取的HttpServletRequest是线程安全的
            return ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        } catch (Exception e) {

            return null;
        }
    }

    private Map<String, String> getHeaders(HttpServletRequest request) {

        Map<String, String> map = new LinkedHashMap<>();

        Enumeration<String> enums = request.getHeaderNames();
        while (enums.hasMoreElements()) {
            String key = enums.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map;
    }
}

package com.momo.momopermissionsystemcoreweb.configuration;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李杰 on 2018/9/24.
 */
@Component
public class FastJsonConfiguration {
    //如果采用注解这种方式，感觉都可以不用放在这个地方
    //只要在spring容器启动的时候被扫描到就行了
    @Bean
    public HttpMessageConverters fastJsonConfigure(){
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //日期格式化
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        //修改配置返回内容的过滤
        fastJsonConfig.setSerializerFeatures(
//                SerializerFeature. WriteMapNullValue,//是否输出值为null的字段,默认为false
                SerializerFeature.WriteDateUseDateFormat,//格式化标签
        		SerializerFeature.WriteNullListAsEmpty , //List字段如果为null,输出为[],而非null
                SerializerFeature.DisableCircularReferenceDetect,//消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
//                SerializerFeature.WriteMapNullValue,//是否输出值为null的字段,默认为false。
                SerializerFeature.WriteNullStringAsEmpty,//字符类型字段如果为null,输出为"",而非null
                SerializerFeature.WriteNullBooleanAsFalse//Boolean字段如果为null,输出为false,而非null
        );
        converter.setFastJsonConfig(fastJsonConfig);
        //处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        converter.setSupportedMediaTypes(fastMediaTypes);
        converter.setFastJsonConfig(fastJsonConfig);
        //将fastjson添加到视图消息转换器列表内
        return new HttpMessageConverters(converter);
    }
}

package com.momo.common.log;

import com.momo.common.log.aspect.SysLogAspect;
import com.momo.common.log.event.SysLogListener;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @ClassName: LogAutoConfiguration
 * @Author: Jie Li
 * @Date 2019-11-14 14:55
 * @Description: 自动配置类
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
@EnableAsync
@Configuration
@AllArgsConstructor
@ConditionalOnWebApplication
public class LogAutoConfiguration {

    @Bean
    public SysLogListener sysLogListener() {
        return new SysLogListener();
    }

    @Bean
    public SysLogAspect sysLogAspect() {
        return new SysLogAspect();
    }
}

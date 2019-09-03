package com.momo.momopermissionsystemcoreweb.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * @program: momo-cloud-permission
 * @description: 异步工具类
 * @author: Jie Li
 * @create: 2019-07-30 09:50
 **/
@Slf4j
@Configuration
@PropertySource(value = "classpath:config/AsyncTaskExecutePoolConfig.properties")
public class AsyncTaskExecutePool implements AsyncConfigurer {


    @Value("${async.pool.corePoolSize}")
    private int corePoolSize;//核心线程池大小

    @Value("${async.pool.maxPoolSize}")
    private int maxPoolSize;//最大线程数

    @Value("${async.pool.keepAliveSeconds}")
    private int keepAliveSeconds;//活跃时间

    @Value("${async.pool.queueCapacity}")
    private int queueCapacity;//队列容量


    /**
     * myTaskAsynPool即配置线程池的方法名，此处如果不写自定义线程池的方法名，会使用默认的线程池
     */
    //@Async("threadPoolTaskExecutor")
    // Future<String> future = new AsyncResult<>("success!");
    @Bean("threadPoolTaskExecutor")
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程池大小
        executor.setCorePoolSize(corePoolSize);
        //最大线程数
        executor.setMaxPoolSize(maxPoolSize);
        //队列容量
        executor.setQueueCapacity(queueCapacity);
        //活跃时间
        executor.setKeepAliveSeconds(keepAliveSeconds);
        //线程名字前缀
        executor.setThreadNamePrefix("taskExecutor-");

        // setRejectedExecutionHandler：当pool已经达到max size的时候，如何处理新任务
        // CallerRunsPolicy：不在新线程中执行任务，而是由调用者所在的线程来执行
        //对拒绝task的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //加载
        executor.initialize();
        return executor;
    }

    /**
     * 异步任务中异常处理
     *
     * @return
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (arg0, arg1, arg2) -> {
            log.error("==========================   异步程序执行异常 begin    =======================");
            log.info("Exception message - {}", arg0.getMessage());
            log.info("Method name - {}", arg1.getName());
            for (Object param : arg2) {
                log.error("Parameter value - {}", param);
            }
            log.error("==========================   异步程序执行异常 end    =======================");
        };
//        return new AsyncUncaughtExceptionHandler() {
//
//            @Override
//            public void handleUncaughtException(Throwable arg0, Method arg1, Object... arg2) {
//                log.error("异步程序执行异常==========================" + arg0.getMessage() + "=======================", arg0);
//                log.error("exception method:" + arg1.getName());
//            }
//        };
    }


}

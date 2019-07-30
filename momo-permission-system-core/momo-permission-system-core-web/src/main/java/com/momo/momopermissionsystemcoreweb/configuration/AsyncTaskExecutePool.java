package com.momo.momopermissionsystemcoreweb.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * @program: momo-cloud-permission
 * @description: 异步工具类
 * @author: Jie Li
 * @create: 2019-07-30 09:50
 **/
@Configuration
@EnableAsync
public class AsyncTaskExecutePool {

    private final static Logger log = LoggerFactory.getLogger(AsyncTaskExecutePool.class);

    private int corePoolSize = 20;

    private int maxPoolSize = 50;

    private int keepAliveSeconds = 300;

    private int queueCapacity = 200;

    /**
     * myTaskAsynPool即配置线程池的方法名，此处如果不写自定义线程池的方法名，会使用默认的线程池
     */
    //@Async("threadPoolTaskExecutor")
    @Bean("threadPoolTaskExecutor")
    public Executor ThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程数目
        executor.setCorePoolSize(getCorePoolSize());
        //指定最大线程数
        executor.setMaxPoolSize(getMaxPoolSize());
        //队列中最大的数目
        executor.setQueueCapacity(getQueueCapacity());
        //线程空闲后的最大存活时间
        executor.setKeepAliveSeconds(getKeepAliveSeconds());
        //线程名称前缀
        executor.setThreadNamePrefix("taskExecutor-");
        //rejection-policy：当pool已经达到max size的时候，如何处理新任务
        //CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
        //对拒绝task的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //加载
        executor.initialize();
        return executor;
    }


    public int getCorePoolSize() {
        return corePoolSize;
    }

    public AsyncTaskExecutePool setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
        return this;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public AsyncTaskExecutePool setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
        return this;
    }

    public int getKeepAliveSeconds() {
        return keepAliveSeconds;
    }

    public AsyncTaskExecutePool setKeepAliveSeconds(int keepAliveSeconds) {
        this.keepAliveSeconds = keepAliveSeconds;
        return this;
    }

    public int getQueueCapacity() {
        return queueCapacity;
    }

    public AsyncTaskExecutePool setQueueCapacity(int queueCapacity) {
        this.queueCapacity = queueCapacity;
        return this;
    }
}

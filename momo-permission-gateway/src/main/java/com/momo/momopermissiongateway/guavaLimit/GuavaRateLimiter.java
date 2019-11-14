package com.momo.momopermissiongateway.guavaLimit;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: GuavaRateLimiter
 * @Author: Jie Li
 * @Date 2019-11-14 16:44
 * @Description: TODO
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
public class GuavaRateLimiter {

    public static ConcurrentHashMap<String, RateLimiter> resourceRateLimiter = new ConcurrentHashMap<>(32);

    //初始化限流工具RateLimiter
    static {
        createResourceRateLimiter("order", 50);
    }

    public static void createResourceRateLimiter(String resource, double qps) {
        if (resourceRateLimiter.contains(resource)) {
            resourceRateLimiter.get(resource).setRate(qps);
        } else {
            //创建限流工具，每秒发出50个令牌指令
            RateLimiter rateLimiter = RateLimiter.create(qps);
            resourceRateLimiter.put(resource, rateLimiter);

        }

    }

    public static void main(String[] args) {
        for (int i = 0; i < 5000; i++) {
            new Thread(() -> {
                //如果获得令牌指令，则执行业务逻辑
                if (resourceRateLimiter.get("order").tryAcquire(10, TimeUnit.MICROSECONDS)) {
                    System.out.println("执行业务逻辑");
                } else {
                    System.out.println("限流");
                }
            }).start();
        }

    }
}

/**
 * Copyright (c) 2018-2019, Jie Li 李杰 (mqgnsds@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.momo.momopermissiongateway.limit;

import com.google.common.util.concurrent.RateLimiter;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: GuavaRateLimiter
 * @Author: Jie Li
 * @Date 2019-11-14 16:44
 * @Description: guava本地限流
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
public class GuavaRateLimiter {

    static ConcurrentHashMap<String, RateLimiter> resourceRateLimiter = new ConcurrentHashMap<>(32);

    //初始化限流工具RateLimiter
    static {
        createResourceRateLimiter("/platform/sysMain/login/v1", 50d);
    }

    public static void createResourceRateLimiter(String resource, Double qps) {
        if (qps == null) {
            qps = 50d;
        }
        if (resourceRateLimiter.contains(resource)) {
            resourceRateLimiter.get(resource).setRate(qps);
        } else {
            //创建限流工具，每秒发出50个令牌指令
            RateLimiter rateLimiter = RateLimiter.create(qps);
            resourceRateLimiter.put(resource, rateLimiter);

        }

    }

    public static void updateResourceRateLimiter(String resource, Double qps) {
        if (StringUtils.isBlank(resource)) {
            return;
        }
        if (qps == null) {
            qps = 50d;
        }
        //创建限流工具，每秒发出50个令牌指令
        RateLimiter rateLimiter = RateLimiter.create(qps);
        resourceRateLimiter.put(resource, rateLimiter);
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

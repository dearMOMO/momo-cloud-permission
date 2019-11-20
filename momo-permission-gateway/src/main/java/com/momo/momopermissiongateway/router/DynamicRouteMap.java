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
package com.momo.momopermissiongateway.router;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: DynamicRouteMap
 * @Author: Jie Li
 * @Date 2019-11-20 15:16
 * @Description: 本地存储动态路由
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
@Slf4j
public class DynamicRouteMap {
    public static Map<String, RouteDefinition> ROUTEMAP = new ConcurrentHashMap<String, RouteDefinition>();

    public static void put(String routeStr) {
        RouteDefinition routeDefinition = JSON.parseObject(routeStr, new TypeReference<RouteDefinition>() {
        });
        ROUTEMAP.put(routeDefinition.getId(), routeDefinition);
    }

    public static void put(String id, RouteDefinition routeDefinition) {

        ROUTEMAP.put(id, routeDefinition);
    }

    public static Object get(String id) {
        return ROUTEMAP.get(id);
    }

    public static List<RouteDefinition> routeList() {
        List<RouteDefinition> list = Lists.newArrayList();
        ROUTEMAP.forEach((k, v) -> list.add(v));
        return list;
    }
}

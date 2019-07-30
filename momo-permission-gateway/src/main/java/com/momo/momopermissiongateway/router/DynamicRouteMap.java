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
 * Created by MOMO on 2019/2/27.
 */
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
        ROUTEMAP.forEach((k, v) -> {
            list.add(v);
        });
        return list;
    }
}

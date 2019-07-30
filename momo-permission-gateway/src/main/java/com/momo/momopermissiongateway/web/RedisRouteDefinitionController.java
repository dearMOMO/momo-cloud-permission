package com.momo.momopermissiongateway.web;

import com.momo.momopermissiongateway.common.JSONResult;
import com.momo.momopermissiongateway.res.RouteRes;
import com.momo.momopermissiongateway.router.DynamicRouteMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by MOMO on 2019/2/26.
 */
@RestController
@RequestMapping(value = "/gateway/router", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
@Slf4j
public class RedisRouteDefinitionController {

    //获取配置文件中的路由参数
    @Autowired
    private GatewayProperties properties;
    private Map<String, RouteDefinition> routeMap = DynamicRouteMap.ROUTEMAP;

    //查看本地map所有路由信息
    @PostMapping("/getRouteAll")
    public JSONResult routeAll() {

        return JSONResult.ok(DynamicRouteMap.routeList());
    }

    //查看路由信息
    @PostMapping("/getRouteInfo")
    public JSONResult getRouteInfo(@RequestBody RouteRes routeRes) {
        Object obj = DynamicRouteMap.get(routeRes.getId());
        return JSONResult.ok(obj);
    }

}

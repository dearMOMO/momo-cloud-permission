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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: DynamicRouteService
 * @Author: Jie Li
 * @Date 2019-11-20 15:18
 * @Description: 使用Redis保存自定义路由配置（代替默认的InMemoryRouteDefinitionRepository
 * 存在问题：每次请求都会调用getRouteDefinitions，当网关较多时，会影响请求速度，考虑放到本地Map中，使用消息通知Map更新。
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
@Component
@Slf4j
public class DynamicRouteService implements RouteDefinitionRepository, ApplicationEventPublisherAware {


    public static final String GATEWAY_ROUTES = "geteway_routes";
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ApplicationEventPublisher publisher;
    //获取配置文件中的路由参数
    @Autowired
    private GatewayProperties properties;
    //项目启动 获取yml路由信息 然后存放在本地map
    private Map<String, RouteDefinition> routeMap = DynamicRouteMap.ROUTEMAP;


    private void notifyChanged() {
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {

        //TODO 将redis改为 本地 map，用mq/配置中心更新map
        List<RouteDefinition> allRouteDefinitions = new ArrayList<>();
//        if (CollectionUtils.isEmpty(routeMap)) {
//            //获取配置文件中的路由参数
//            List<RouteDefinition> routeDefinitions = properties.getRoutes();
//            routeDefinitions.forEach(routeDefinition -> {
//                DynamicRouteMap.put(routeDefinition.getId(), routeDefinition);
//            });
//            allRouteDefinitions.addAll(routeDefinitions);
//        } else {
        routeMap.forEach((k, v) -> {
            allRouteDefinitions.add(v);
        });
//        }
//        log.info("路由信息为:{}", JSONObject.toJSONString(allRouteDefinitions));
        return Flux.fromIterable(allRouteDefinitions);
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return route
                .flatMap(routeDefinition -> {
                    redisTemplate.opsForHash().put(GATEWAY_ROUTES, routeDefinition.getId(),
                            JSON.toJSONString(routeDefinition));
                    return Mono.empty();
                });
    }

    /**
     * 更新路由
     */
    public String update(RouteDefinition definition) {
        try {
            this.delete(Mono.just(definition.getId()));
        } catch (Exception e) {
            return "update fail,not find route  routeId: " + definition.getId();
        }
        try {
            this.save(Mono.just(definition)).subscribe();
            notifyChanged();
            return "success";
        } catch (Exception e) {
            return "update route  fail";
        }


    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(id -> {
            if (redisTemplate.opsForHash().hasKey(GATEWAY_ROUTES, id)) {
                redisTemplate.opsForHash().delete(GATEWAY_ROUTES, id);
                return Mono.empty();
            }
            return Mono.defer(() -> Mono.error(new NotFoundException("RouteDefinition not found: " + routeId)));
        });
    }

    @PostConstruct
    public void initializeRouteToMap() {
        //获取配置文件中的路由参数
        List<RouteDefinition> routeDefinitions = properties.getRoutes();
        routeDefinitions.forEach(routeDefinition -> {
            routeMap.put(routeDefinition.getId(), routeDefinition);
        });
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}

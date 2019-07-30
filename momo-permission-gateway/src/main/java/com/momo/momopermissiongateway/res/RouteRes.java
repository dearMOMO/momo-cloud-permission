package com.momo.momopermissiongateway.res;

import lombok.Data;

/**
 * Created by MOMO on 2019/2/27.
 */
@Data
public class RouteRes {
    private Integer pId;
//    @NotBlank(message = "路由id 必填", groups = {Detail.class, Modify.class})
    private String id;
//    @NotBlank(message = "路由的请求匹配规则 必填", groups = {Modify.class})
    private String predicates;
//    @NotBlank(message = "请求转发前的filter 必填", groups = {Modify.class})
    private String filters;
//    @NotBlank(message = "http请求为lb://前缀 + 服务id；ws请求为lb:ws://前缀 + 服务id；表示将请求负载到哪一个服务上 必填", groups = {Modify.class})
    private String uri;
//    @NotNull(message = "这个路由的执行顺序 必填", groups = {Modify.class})
//    @Min(value = 1,message = "最小值为1")
    private Integer order;
}

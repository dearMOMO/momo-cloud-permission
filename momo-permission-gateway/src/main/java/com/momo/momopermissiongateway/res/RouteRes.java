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
package com.momo.momopermissiongateway.res;

import lombok.Data;

/**
 * @ClassName: RouteRes
 * @Author: Jie Li
 * @Date 2019-11-14 16:44
 * @Description: 路由响应实体类
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
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

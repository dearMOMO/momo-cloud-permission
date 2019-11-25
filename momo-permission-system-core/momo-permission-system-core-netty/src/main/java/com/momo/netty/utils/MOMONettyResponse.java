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
package com.momo.netty.utils;

import com.momo.common.core.common.UserChannelCurrentMap;
import lombok.*;

/**
 * @ClassName: MOMORespnse
 * @Author: Jie Li
 * @Date 2019-09-21 21:52
 * @Description: TODO
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(callSuper = true, of = {"id"})
public class MOMONettyResponse {
    //标题
    private String title = "基于最流行RBAC拓展模型，打造分布式权限管理系统";
    //文档地址
    private String document = "http://www.mqgnsds.top:9527";
    //源码
    private String gitee = "https://gitee.com/momoriven/momo-cloud-permission";
    //长链接地址
    private String websocketAddress = "ws://www.mqgnsds.top:502/ws";
    //在线数量
    private Integer onlineNum = UserChannelCurrentMap.managerChannelSize();

    public static MOMONettyResponse momoRespnse(){
        return new MOMONettyResponse();
    }
}

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

import lombok.*;

/**
 * @ClassName: IMMessage
 * @Author: Jie Li
 * @Date 2019-11-25 11:17
 * @Description: TODO
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
//@EqualsAndHashCode(of = {"id"})
public class IMMessage {
    //消息类型
    private Integer msgType;
    //业务数据
    private Object data;
    //token
    private String token;

    public IMMessage(Integer msgType, Object data, String token) {
        this.msgType = msgType;
        this.data = data;
        this.token = token;
    }
}

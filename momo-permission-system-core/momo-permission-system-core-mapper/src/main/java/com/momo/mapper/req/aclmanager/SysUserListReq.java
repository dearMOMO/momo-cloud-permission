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
package com.momo.mapper.req.aclmanager;

import com.momo.common.core.error.BaseReq;
import lombok.*;

/**
 * @program: momo-cloud-permission
 * @description: TODO
 * @author: Jie Li
 * @create: 2019-08-02 17:28
 **/
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(callSuper = true, of = {"id"})
public class SysUserListReq extends BaseReq {
    /**
     * sysUserName 姓名.
     */
    private String sysUserName;
    /**
     * disabledFlag 是否被禁用  0否 1禁用.
     */
    private Integer disabledFlag;

}

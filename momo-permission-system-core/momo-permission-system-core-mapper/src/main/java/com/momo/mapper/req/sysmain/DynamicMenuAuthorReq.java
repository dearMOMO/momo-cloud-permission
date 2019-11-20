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
package com.momo.mapper.req.sysmain;

import com.momo.common.core.error.BaseReq;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by Kagome on 2019/3/23.
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(of = {"id"})
public class DynamicMenuAuthorReq extends BaseReq {
    //用户ID
    private Long userId;
    //权限类型
    @NotNull(message = "动态权限菜单的权限类型 必填",groups = {Permission.class})
    private String aclPermissionCode;
    @NotBlank(message = "角色UUID必填",groups = {Detail.class})
    private String uuid;
    //角色ID
    private Long roleId;
}

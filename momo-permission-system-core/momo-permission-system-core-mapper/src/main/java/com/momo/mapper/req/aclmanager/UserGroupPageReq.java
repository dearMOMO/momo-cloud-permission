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

import com.google.common.collect.Lists;
import com.momo.common.core.error.BaseReq;
import com.momo.mapper.dataobject.AclDO;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @program: momo-cloud-permission
 * @description: TODO
 * @author: Jie Li
 * @create: 2019-08-06 13:14
 **/
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(callSuper = true, of = {"id"})
public class UserGroupPageReq extends BaseReq {

    @NotBlank(message = "企业uuid 必填", groups = {Detail.class, Permission.class})
    private String uuid;
    //企业名称
    private String userGroupName;
    //disabledFlag 状态 0启用  1禁用.
    private Integer disabledFlag;
    //权限列表
    private List<AclDO> acls = Lists.newArrayList();
}

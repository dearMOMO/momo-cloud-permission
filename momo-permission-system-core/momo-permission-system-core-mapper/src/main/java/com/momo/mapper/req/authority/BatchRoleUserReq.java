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
package com.momo.mapper.req.authority;

import com.google.common.collect.Lists;
import com.momo.common.core.error.BaseReq;
import com.momo.mapper.dataobject.AclDO;
import com.momo.mapper.dataobject.RoleDO;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Created by MOMO on 2019/3/22.
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(of = {"id"})
public class BatchRoleUserReq extends BaseReq {
    @NotBlank(message = "enterpriseUuid必填", groups = {Author.class})
    private String enterpriseUuid;
    //##########        角色列表给用户
    @NotBlank(message = "用户uuid 必填", groups = {Status.class})
    private String userUuid;
    private List<Long> roleIds = Lists.newArrayList();
    private List<RoleDO> roles = Lists.newArrayList();

    //##########        权限列表给角色
    @NotBlank(message = "角色uuid 必填", groups = {Permission.class,Author.class})
    private String roleUuid;
    private List<AclDO> acls = Lists.newArrayList();

}

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
package com.momo.mapper.res.authority;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.momo.mapper.dataobject.RoleAclDO;
import lombok.*;

import java.util.List;
import java.util.Map;

/**
 * @program: momo-cloud-permission
 * @description: TODO
 * @author: Jie Li
 * @create: 2019-08-05 14:48
 **/
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(callSuper = true, of = {"id"})
public class AclTreeRes {
    //权限树
    private List<AclLevelRes> aclLevelRes;
    //默认展开
    private List<String> defaultexpandedKeys;
    //默认选中
    private List<String> defaultCheckedKeys;

}

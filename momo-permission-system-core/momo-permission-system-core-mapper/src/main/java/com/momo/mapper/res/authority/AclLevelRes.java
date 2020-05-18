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
import com.momo.mapper.dataobject.AclDO;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @program: momo-cloud-permission
 * @description: TODO
 * @author: Jie Li
 * @create: 2019-07-30 22:46
 **/
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, of = {"id"})
public class AclLevelRes extends AclDO {

    private String label;
    private String idStr;
    private String sysAclParentIdStr;

    private List<AclLevelRes> children = Lists.newArrayList();
    //默认选中/展开
    private List<String> defaultexpandedKeys;
    // 是否要默认选中
    private boolean checked = false;

    // 是否有权限操作
    private boolean hasAcl = false;
    // 是否有权限操作
    private boolean disabled = true;

    public static AclLevelRes adapt(AclDO acl) {
        AclLevelRes dto = new AclLevelRes();
        BeanUtils.copyProperties(acl, dto);
        dto.setLabel(acl.getSysAclName());
        dto.setIdStr(String.valueOf(acl.getId()));
        dto.setSysAclParentIdStr(String.valueOf(acl.getSysAclParentId()));
        return dto;
    }

    //***********       动态权限菜单      ***********

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单权限标识
     */
    private String perms;

    /**
     * 前端path / 即跳转路由
     */
    private String path;

    /**
     * 菜单组件
     */
    private String component;

    /**
     * 父菜单ID
     */
    private Integer parentId;

    /**
     * 图标
     */
    private String icon;





    /**
     * 非数据库字段
     * 父菜单名称
     */
    private String parentName;


}

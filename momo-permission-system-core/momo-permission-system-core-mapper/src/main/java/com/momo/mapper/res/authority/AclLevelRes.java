package com.momo.mapper.res.authority;

import com.google.common.collect.Lists;
import com.momo.mapper.dataobject.AclDO;
import lombok.*;
import org.springframework.beans.BeanUtils;

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
        return dto;
    }
}

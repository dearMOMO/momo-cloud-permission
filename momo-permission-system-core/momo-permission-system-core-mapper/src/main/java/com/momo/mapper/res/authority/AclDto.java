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
public class AclDto extends AclDO {
    private List<AclDto> children = Lists.newArrayList();
    // 是否要默认选中
    private boolean checked = false;

    // 是否有权限操作
    private boolean hasAcl = false;

    public static AclDto adapt(AclDO acl) {
        AclDto dto = new AclDto();
        BeanUtils.copyProperties(acl, dto);
        return dto;
    }
}

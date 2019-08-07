package com.momo.mapper.res.aclmanager;

import com.momo.mapper.dataobject.RoleDO;
import lombok.*;

import java.util.List;
import java.util.Set;

/**
 * @program: momo-cloud-permission
 * @description: TODO
 * @author: Jie Li
 * @create: 2019-08-07 17:34
 **/
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(callSuper = true, of = {"id"})
public class SysRoleCheckedRes extends RoleDO {
    //选中列表
    private Set<Long> checkList;
    //是否禁用
    private boolean disabled = false;
    //角色列表
    private List<SysRoleCheckedRes> roles;
}

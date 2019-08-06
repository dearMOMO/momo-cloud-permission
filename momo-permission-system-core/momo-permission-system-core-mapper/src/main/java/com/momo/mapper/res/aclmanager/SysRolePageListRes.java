package com.momo.mapper.res.aclmanager;

import com.momo.mapper.dataobject.RoleDO;
import lombok.*;

/**
 * @program: momo-cloud-permission
 * @description: TODO
 * @author: Jie Li
 * @create: 2019-08-05 22:00
 **/
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(callSuper = true, of = {"id"})
public class SysRolePageListRes extends RoleDO {
    //是否显示编辑按钮
    private boolean editButton = true;
    //是否显示授权按钮
    private boolean authorButton = true;
    //是否显示状态按钮
    private boolean flagButton = true;
}

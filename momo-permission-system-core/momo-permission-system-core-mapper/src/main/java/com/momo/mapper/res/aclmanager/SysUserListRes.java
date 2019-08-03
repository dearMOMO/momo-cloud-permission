package com.momo.mapper.res.aclmanager;

import com.momo.mapper.dataobject.RoleDO;
import com.momo.mapper.dataobject.UserDO;
import lombok.*;

import java.util.List;

/**
 * @program: momo-cloud-permission
 * @description: TODO
 * @author: Jie Li
 * @create: 2019-08-02 17:26
 **/
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(callSuper = true, of = {"id"})
public class SysUserListRes extends UserDO {
    //是否显示编辑按钮
    private boolean editButton = true;
    //是否显示修改按钮
    private boolean pwdButton = true;
    //是否被禁用  0否 1禁用
    private Integer bindingFlag;
    //绑定名称
    private String bindingName;
    //账号已经绑定的类型
    private List<SysUserListRes> bindingsTypeList;
    //账号未经绑定的类型
    private List<SysUserListRes> unBindingsTypeList;
    //角色列表
    private List<RoleDO> roles;
}

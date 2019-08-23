package com.momo.mapper.res.aclmanager;

import com.github.pagehelper.PageInfo;
import com.momo.mapper.dataobject.RoleDO;
import com.momo.mapper.dataobject.UserDO;
import lombok.*;

import java.util.Date;
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
    //是否显示修改密码按钮
    private boolean pwdButton = true;
    //是否显示状态按钮
    private boolean flagButton = true;
    //是否显示授权角色按钮
    private boolean roleButton = true;


    //账号密码  是否绑定
    private boolean pwdBinding = false;
    //账号密码  是否被禁用  0否 1禁用
    private Integer pwdBindingFlag;
    //账号密码  绑定名称
    private String pwdBindingName;
    //账号密码  绑定时间
    private Date pwdBindingDate;

    //账号密码  是否绑定
    private boolean wxBinding = false;
    //微信  是否被禁用  0否 1禁用
    private Integer wxBindingFlag;
    private String wxBindingName;
    //微信  绑定时间
    private Date wxBindingDate;

    //角色列表
    private List<RoleDO> roles;

    //企业用户分页列表
    private PageInfo<SysUserListRes> sysUserListResPageInfo;
    //企业名称
    private String sysEnterpriseName;
}

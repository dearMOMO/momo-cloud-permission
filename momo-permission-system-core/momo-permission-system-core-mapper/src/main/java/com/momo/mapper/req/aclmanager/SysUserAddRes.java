package com.momo.mapper.req.aclmanager;

import com.momo.common.error.BaseReq;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @program: momo-cloud-permission
 * @description: TODO
 * @author: Jie Li
 * @create: 2019-08-06 19:55
 **/
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(callSuper = true, of = {"id"})
public class SysUserAddRes extends BaseReq {

    @NotBlank(message = "uuid必填", groups = {Detail.class, Status.class})
    private String uuid;
    /**
     * sysUserName 姓名.
     */
    @NotBlank(message = "姓名必填", groups = {Add.class, Modify.class})
    private String sysUserName;
    /**
     * sysUserEmail 邮箱.
     */
    private String sysUserEmail;
    /**
     * sysUserPhone 手机号.
     */
    private String sysUserPhone;
    /**
     * flag 是否被禁用  0否 1禁用.
     */
    @NotNull(message = "状态必填", groups = {Add.class, Modify.class, Status.class})
    private Integer flag;
    /**
     * sysUserLoginName 登录名.
     */
    @NotBlank(message = "登录名必填", groups = {Add.class})
    @Email(message = "邮箱格式不正确")
    private String sysUserLoginName;
    /**
     * sysUserPwd 密码.
     */
    @NotBlank(message = "密码必填", groups = {Add.class})
    private String sysUserPwd;
    /**
     * remark 备注.
     */
    private String remark;
}

package com.momo.mapper.req.sysmain;


import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * @program: momo-cloud-permission
 * @description: TODO
 * @author: Jie Li
 * @create: 2019-07-30 15:12
 **/
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(of = {"id"})
public class SysUserLoginReq extends BaseReq {
    /**
     * sysUserPwd 密码.
     */
    @NotBlank(message = "密码必填", groups = {Query.class})
    private String sysUserPwd;

    /**
     * sysUserLoginName 登录名.
     */
    @NotBlank(message = "登录名必填", groups = {Query.class})
    private String sysUserLoginName;
    @NotBlank(message = "验证码不能为空", groups = {Query.class})
    private String verificationCode;//验证码
    @NotBlank(message = "验证码唯一标识必填", groups = {Query.class})
    private String verUUidCode;//该验证码对应唯一一个用户
}

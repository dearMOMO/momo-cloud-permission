package com.momo.mapper.req.sysmain;

import com.momo.common.error.BaseReq;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by Kagome on 2019/3/23.
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(of = {"id"})
public class LoginAuthReq extends BaseReq {
    //用户ID
    private Long userId;
    //权限类型
    @NotNull(message = "动态权限菜单的权限类型 必填",groups = {Permission.class})
    private Long aclPermissionType;
    @NotBlank(message = "角色UUID必填",groups = {Detail.class})
    private String sysRoleUuid;
    //角色ID
    private Long roleId;
}

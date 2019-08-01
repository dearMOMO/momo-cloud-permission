package com.momo.mapper.req.authority;

import com.momo.common.error.BaseReq;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * Created by MOMO on 2019/4/9.
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(of = {"id"})
public class RoleReq extends BaseReq {

    /**
     * uuid UUID.
     */
    @NotBlank(message = "uuid必填",groups = {Modify.class,Status.class,Detail.class})
    private String uuid;
    /**
     * compId 企业id.
     */
    private Long groupId;
    /**
     * flag 状态 0启用  1禁用.
     */
    @NotNull(message = "状态 0启用  1禁用 必填",groups = {save.class,Modify.class,Status.class})
    private Integer flag;

    /**
     * roleName 角色名称.
     */
    @NotBlank(message = "角色名称 必填",groups = {save.class,Modify.class})
    private String sysRoleName;
    /**
     * roleType 角色的类型，0：管理员角色，1：普通用户 2其他.
     */
    @NotNull(message = "角色的类型 必填",groups = {save.class,Modify.class})
    private Integer sysRoleType;

}

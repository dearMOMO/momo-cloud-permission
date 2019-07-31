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
public class AclReq extends BaseReq {

    /**
     * sysAclModuleType 菜单系统类型 1 系统管理 2资产管理.
     */
    @NotNull(message = "菜单系统类型 必填", groups = {save.class, Modify.class})
    private Long sysAclPermissionType;
    /**
     * sysAclModuleParentId 上级权限id.
     */
    @NotNull(message = "上级权限id 必填", groups = {save.class, Modify.class})
    private Long sysAclParentId;
    /**
     * remark 备注.
     */
    private String remark;


    /**
     * sysAclUrl 请求的url, 可以填正则表达式.
     */
    @NotBlank(message = "请求的url 必填", groups = {save.class, Modify.class})
    private String sysAclUrl;
    /**
     * sysAclCode 权限码.
     */
    private String sysAclCode;
    /**
     * sysAclIcon 图标class.
     */
    private String sysAclIcon;
    /**
     * sysAclName 权限名称.
     */
    @NotBlank(message = "权限名称 必填", groups = {save.class, Modify.class})
    private String sysAclName;
    /**
     * sysAclType 类型，1：菜单，2：按钮，3：其他.
     */
    @NotNull(message = "类型，1：菜单，2：按钮，3：其他 必填", groups = {save.class, Modify.class})
    private Integer sysAclType;
    /**
     * sysAclUuid 唯一，32位字符串，查询用.
     */
    @NotBlank(message = "uuid 必填", groups = {Status.class, Modify.class})
    private String uuid;
    /**
     * sysAclState 状态 0启用  1禁用.
     */
    @NotNull(message = "状态 0启用  1禁用 必填", groups = {save.class,Status.class, Modify.class})
    private Integer status;
    /**
     * sysAclAction 按钮动作类型(交给前端处理）.
     */
    @NotBlank(message = "按钮动作类型 必填", groups = {save.class, Modify.class})
    private String sysAclAction;
    /**
     * sysAclRouter 所属页面(交给前端处理).
     */
    @NotBlank(message = "所属页面 必填", groups = {save.class, Modify.class})
    private String sysAclRouter;
    /**
     * sysAclSeq 权限在当前模块下的顺序，由小到大.
     */
    @NotNull(message = "权限在当前模块下的顺序，由小到大 必填", groups = {save.class, Modify.class})
    private Integer sysAclSeq;

}

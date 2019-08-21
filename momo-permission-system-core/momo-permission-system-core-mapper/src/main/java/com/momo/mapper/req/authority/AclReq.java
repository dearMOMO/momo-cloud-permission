package com.momo.mapper.req.authority;

import com.momo.common.error.BaseReq;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
    @NotBlank(message = "菜单系统类型 必填", groups = {save.class, Modify.class,Permission.class})
    private String sysAclPermissionCode;
    /**
     * sysAclModuleParentId 上级权限id.
     */
//    @NotNull(message = "上级权限id 必填", groups = {save.class, Modify.class,Permission.class})
    private Long sysAclParentId;
    @NotNull(message = "上级权限id 必填", groups = {save.class, Modify.class,Permission.class})
    private Long sysAclParentIdStr;
    /**
     * remark 备注.
     */
    private String remark;


    /**
     * sysAclUrl 请求的url, 可以填正则表达式.
     */
//    @NotBlank(message = "请求的url 必填", groups = {save.class, Modify.class})
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
    @NotBlank(message = "权限名称 必填", groups = {save.class, Modify.class,Permission.class})
    private String sysAclName;
    /**
     * 类型，-1系统 0:目录 1：菜单，2：按钮，3：其他
     */
    @NotNull(message = "类型，类型，-1系统 0:目录 1：菜单，2：按钮，3：其他 必填", groups = {save.class, Modify.class,Permission.class})
    @Max(value = 3,message = "权限类型：最大值为1")
    @Min(value = -1,message = "权限类型：最小值为-1")
    private Integer sysAclType;
    /**
     * sysAclUuid 唯一，32位字符串，查询用.
     */
    @NotBlank(message = "uuid 必填", groups = {Status.class, Modify.class,Detail.class})
    private String uuid;
    /**
     * flag 状态 0启用  1禁用.
     */
    @NotNull(message = "状态 0启用  1禁用 必填", groups = {save.class,Status.class, Modify.class,Permission.class})
    @Max(value = 1,message = "状态：最大值为1")
    @Min(value = 0,message = "状态：最小值为0")
    private Integer flag;
    /**
     * sysAclAction 按钮动作类型(交给前端处理）.
     */
//    @NotBlank(message = "按钮动作类型 必填", groups = {save.class, Modify.class})
    private String sysAclAction;
    /**
     * sysAclRouter 所属页面(交给前端处理).
     */
//    @NotBlank(message = "所属页面 必填", groups = {save.class, Modify.class})
    private String sysAclRouter;
    /**
     * sysAclSeq 权限在当前模块下的顺序，由小到大.
     */
    @NotNull(message = "权限在当前模块下的顺序，由小到大 必填", groups = {save.class, Modify.class,Permission.class})
    @Min(value = 0,message = "排序：最小值为0")
    private Integer sysAclSeq;

}

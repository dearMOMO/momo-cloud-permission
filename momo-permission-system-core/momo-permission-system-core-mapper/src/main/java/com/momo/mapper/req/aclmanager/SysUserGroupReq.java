package com.momo.mapper.req.aclmanager;

import com.momo.common.error.BaseReq;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ProjectName: momo-cloud-permission
 * @Package: com.momo.mapper.req.aclmanager
 * @Description: TODO
 * @Author: Jie Li
 * @CreateDate: 2019/8/14 0014 21:56
 * @UpdateDate: 2019/8/14 0014 21:56
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(callSuper = true, of = {"id"})
public class SysUserGroupReq extends BaseReq {

    /**
     * uuid uuid.
     */
    @NotBlank(message = "uuid必填", groups = {Detail.class, Modify.class, Status.class})
    private String uuid;
    /**
     * nameTop 顶部名称.
     */
    private String nameTop;
    /**
     * nameBottom 版权.
     */
    private String nameBottom;
    /**
     * userGroupName 用户组名称/第三方公司名称.
     */
    @NotBlank(message = "企业名称必填", groups = {Detail.class, Modify.class})
    private String userGroupName;
    /**
     * flag 状态 0启用  1禁用.
     */
    @NotNull(message = "状态 必填", groups = {Detail.class, Modify.class, Status.class})
    private Integer flag;
    /**
     * delFlag 删除状态(0-正常，1-删除).
     */
    private Integer delFlag;
    /**
     * sysOpenDay 开通的天数 -1 不限次数 自己公司公司所有.
     */
    private Integer sysOpenDay;
    /**
     * sysOpenAccountNum 已开通账号个数.
     */
    private Integer sysOpenAccountNum;
    /**
     * sysCreateAccountNum 可以开通子账户的个数 -1不限制次数  id为1 为自己公司，不限次数.
     */
    private Integer sysCreateAccountNum;

    /**
     * sysAccountEndTime 账号结束时间.
     */
    @NotBlank(message = "账号结束时间必填", groups = {Detail.class, Modify.class})
    private String sysAccountEndTime;
    /**
     * sysAccountStartTime 账号开通时间 (不传值，默认系统时间).
     */
    @NotBlank(message = "账号开通时间 必填", groups = {Detail.class, Modify.class})
    private String sysAccountStartTime;

    private String remark;
}

package com.momo.mapper.req.aclmanager;

import com.momo.common.error.BaseReq;
import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * @ProjectName: momo-cloud-permission
 * @Package: com.momo.mapper.req.aclmanager
 * @Description: 企业角色列表
 * @Author: Jie Li
 * @CreateDate: 2019/8/17 0017 10:46
 * @UpdateDate: 2019/8/17 0017 10:46
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
public class SysEnterpriseRoleReq extends BaseReq {
    @NotBlank(message = "企业uuid 必填", groups = {BaseReq.Detail.class, BaseReq.Permission.class})
    private String uuid;
    //角色类型
    private Integer roleType;
    //状态
    private Integer flag;
    //角色名称
    private String sysRoleName;
}

package com.momo.mapper.req.aclmanager;

import com.google.common.collect.Lists;
import com.momo.common.error.BaseReq;
import com.momo.mapper.dataobject.AclDO;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @program: momo-cloud-permission
 * @description: TODO
 * @author: Jie Li
 * @create: 2019-08-06 13:14
 **/
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(callSuper = true, of = {"id"})
public class UserGroupPageReq extends BaseReq {

    @NotBlank(message = "企业uuid 必填", groups = {Detail.class, Permission.class})
    private String uuid;
    //企业名称
    private String userGroupName;
    //disabledFlag 状态 0启用  1禁用.
    private Integer disabledFlag;
    //权限列表
    private List<AclDO> acls = Lists.newArrayList();
}

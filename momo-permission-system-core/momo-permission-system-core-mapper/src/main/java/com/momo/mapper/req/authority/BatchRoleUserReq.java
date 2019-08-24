package com.momo.mapper.req.authority;

import com.google.common.collect.Lists;
import com.momo.common.error.BaseReq;
import com.momo.mapper.dataobject.AclDO;
import com.momo.mapper.dataobject.RoleDO;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Created by MOMO on 2019/3/22.
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(of = {"id"})
public class BatchRoleUserReq extends BaseReq {
    //##########        角色列表给用户
    @NotBlank(message = "用户uuid 必填", groups = {Status.class})
    private String userUuid;
    private List<Long> roleIds = Lists.newArrayList();
    private List<RoleDO> roles = Lists.newArrayList();

    //##########        权限列表给角色
    @NotBlank(message = "角色uuid 必填", groups = {Permission.class})
    private String roleUuid;
    private List<AclDO> acls = Lists.newArrayList();

}

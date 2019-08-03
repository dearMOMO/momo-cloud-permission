package com.momo.mapper.dataobject.manual;

import com.momo.mapper.dataobject.RoleDO;
import com.momo.mapper.dataobject.UserAccountPwdDO;
import com.momo.mapper.dataobject.UserDO;
import lombok.*;

import java.util.List;

/**
 * @program: momo-cloud-permission
 * @description: TODO
 * @author: Jie Li
 * @create: 2019-08-02 17:36
 **/
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(callSuper = true, of = {"id"})
public class SysUserListDO extends UserDO {
    //密码绑定
    private UserAccountPwdDO  userAccountPwdDO;
    //用户所拥有的角色
    private List<RoleDO> roles;


}

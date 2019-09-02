package com.momo.mapper.res.aclmanager;

import lombok.*;

/**
 * @ProjectName: momo-cloud-permission
 * @Package: com.momo.mapper.res.aclmanager
 * @Description: TODO
 * @Author: Jie Li
 * @CreateDate: 2019/9/2 0002 15:38
 * @UpdateDate: 2019/9/2 0002 15:38
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
public class DisabledAdminRoleButtonRes {
    private boolean disabledAdminRoleButton = true;

}

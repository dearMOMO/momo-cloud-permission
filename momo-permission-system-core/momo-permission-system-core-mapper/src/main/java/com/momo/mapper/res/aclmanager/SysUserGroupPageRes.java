package com.momo.mapper.res.aclmanager;

import com.momo.mapper.dataobject.UserGroupDO;
import lombok.*;

/**
 * @ProjectName: momo-cloud-permission
 * @Package: com.momo.mapper.res.aclmanager
 * @Description: TODO
 * @Author: Jie Li
 * @CreateDate: 2019/8/15 0015 13:22
 * @UpdateDate: 2019/8/15 0015 13:22
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
public class SysUserGroupPageRes extends UserGroupDO {
    //是否过期
    private boolean expireIs = false;
    //剩余天数
    private String expireDtStr = "";

}

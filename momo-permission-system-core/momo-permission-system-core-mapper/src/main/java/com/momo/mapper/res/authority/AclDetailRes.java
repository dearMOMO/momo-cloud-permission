package com.momo.mapper.res.authority;

import com.momo.mapper.dataobject.AclDO;
import lombok.*;

/**
 * @ProjectName: momo-cloud-permission
 * @Package: com.momo.mapper.res.authority
 * @Description: TODO
 * @Author: Jie Li
 * @CreateDate: 2019/8/14 0014 11:03
 * @UpdateDate: 2019/8/14 0014 11:03
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
public class AclDetailRes extends AclDO {
    //是否可以编辑系统码
    private boolean showAclSysCode = true;
}

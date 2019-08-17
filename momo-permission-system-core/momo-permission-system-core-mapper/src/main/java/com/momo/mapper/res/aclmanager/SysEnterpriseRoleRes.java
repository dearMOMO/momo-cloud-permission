package com.momo.mapper.res.aclmanager;

import com.github.pagehelper.PageInfo;
import com.momo.mapper.dataobject.RoleDO;
import lombok.*;

/**
 * @ProjectName: momo-cloud-permission
 * @Package: com.momo.mapper.res.aclmanager
 * @Description: TODO
 * @Author: Jie Li
 * @CreateDate: 2019/8/17 0017 10:57
 * @UpdateDate: 2019/8/17 0017 10:57
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
public class SysEnterpriseRoleRes extends RoleDO {
    //是否显示编辑按钮
    private boolean editButton = true;
    //是否显示授权按钮
    private boolean authorButton = true;
    //是否显示状态按钮
    private boolean flagButton = true;
    private PageInfo<SysEnterpriseRoleRes> sysEnterpriseRoleResPageInfo;
    //企业名称
    private String sysEnterpriseName;
}

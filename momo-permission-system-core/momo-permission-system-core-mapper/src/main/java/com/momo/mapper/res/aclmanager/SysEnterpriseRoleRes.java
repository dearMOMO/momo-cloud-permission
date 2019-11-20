/**
 * Copyright (c) 2018-2019, Jie Li 李杰 (mqgnsds@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
    private boolean editButtonShow = true;
    //是否显示授权按钮
    private boolean authorButtonShow = true;
    //是否显示状态按钮
    private boolean disabledFlagButtonShow = true;
    private PageInfo<SysEnterpriseRoleRes> sysEnterpriseRoleResPageInfo;
    //企业名称
    private String sysEnterpriseName;
}

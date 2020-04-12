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
package com.momo.mapper.mapper.manual;

import com.momo.mapper.dataobject.RoleAclDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ProjectName: momo-cloud-permission
 * @Package: com.momo.mapper.mapper.manual
 * @Description: TODO
 * @Author: Jie Li
 * @CreateDate: 2019/9/3 0003 16:16
 * @UpdateDate: 2019/9/3 0003 16:16
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public interface RoleAclMapper {
    /**
     * 获取第三方管理员权限id列表
     *
     * @param teantId
     * @param aclPermissionCode
     * @return
     */
    List<Long> aclIdsByTeantId(@Param("teantId") Long teantId, @Param("aclPermissionCode") String aclPermissionCode);

    /**
     * 根据角色id查询角色与权限关系
     *
     * @param roleId
     * @return
     */
    List<RoleAclDO> getRoleAclByRoleId(@Param("roleId") Long roleId);
}

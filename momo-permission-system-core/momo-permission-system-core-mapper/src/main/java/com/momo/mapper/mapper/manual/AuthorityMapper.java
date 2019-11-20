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

import com.momo.mapper.dataobject.AclDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @program: momo-cloud-permission
 * @description: 权限 认证
 * @author: Jie Li
 * @create: 2019-07-30 22:52
 **/
public interface AuthorityMapper {

    /**
     * 根据用户id查询 用户所拥有的角色
     *
     * @param userId
     * @return
     */
    List<Long> rolesByUserId(@Param("userId") Long userId);

    /**
     * 获取所有权限点
     *
     * @param sys_acl_permission_code 权限类型
     * @param userAclIdList     权限点 ids
     * @return
     */
    List<AclDO> getAllAcl(@Param("sys_acl_permission_code") String sys_acl_permission_code, @Param("userAclIdList") Set<Long> userAclIdList);

    /**
     * 根据角色ids 查询所有角色
     *
     * @param roleId
     * @return
     */
    List<Long> rolesByRoleId(@Param("roleIds") List<Long> roleId, @Param("disabledFlag") Integer disabledFlag,@Param("delFlag")Integer delFlag);


    /**
     * 根据角色ids 查询所有权限
     *
     * @param roleId
     * @return
     */
    List<Long> aclsByRoleId(@Param("roleIds") Set<Long> roleId, @Param("sys_acl_permission_code") String sys_acl_permission_code);

    /**
     * 根据组的id 得到管理员ids
     *
     * @param tenantId
     * @return
     */
    List<Long> rolesAdminByGroupId(@Param("tenantId") Long tenantId);
}

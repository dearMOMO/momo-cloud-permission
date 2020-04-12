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


public interface AclMapper {

    int insertSelective(AclDO record);

    AclDO selectByPrimaryKey(Long id);

    AclDO selectByPrimaryUuid(String uuid);

    int updateByPrimaryKeySelective(AclDO record);

    /**
     * 验证url是否唯一
     *
     * @param url
     * @param sys_acl_permission_code
     * @param id
     * @return
     */
    int checkUrl(@Param("url") String url, @Param("sys_acl_permission_code") String sys_acl_permission_code, @Param("id") Long id);

    /**
     * 查询子孙level
     *
     * @param sysAclLevel
     * @return
     */
    public List<AclDO> getChildAclModuleListByLevel(@Param("sysAclLevel") String sysAclLevel);

    /**
     * 批量更新 level
     *
     * @param aclModuleDOS
     * @return
     */
    public int batchUpdateLevel(@Param("sysAclModuleList") List<AclDO> aclModuleDOS);

    List<AclDO> aclUuids(@Param("uuids") Set<String> uuids);

    /**
     * 检查 菜单系统类型  是否存在
     *
     * @return
     */
    int checkAclPermissionType(@Param("sys_acl_permission_code") String sys_acl_permission_code);

    /**
     * 校验是否有子菜单
     *
     * @param sys_acl_level
     * @return
     */
    int checkChildAcl(@Param("sys_acl_level") String sys_acl_level);

    /**
     * 校验名称是否存在
     *
     * @param id
     * @param sys_acl_name
     * @param sys_acl_level
     * @return
     */
    int checkAclSysName(@Param("id") Long id, @Param("sys_acl_name") String sys_acl_name, @Param("sys_acl_level") String sys_acl_level);

    /**
     * 根据url 获取权限列表
     *
     * @param url
     * @return
     */
    List<AclDO> getByUrl(@Param("url") String url);

}

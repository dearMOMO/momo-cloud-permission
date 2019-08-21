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

    List<AclDO> aclUuids(@Param("uuids")Set<String> uuids);

    /**
     * 检查 菜单系统类型  是否存在
     * @return
     */
    int checkAclPermissionType(@Param("sys_acl_permission_code")String sys_acl_permission_code);

    int checkChildAcl(@Param("sys_acl_level")String sys_acl_level);
    int checkAclSysName(@Param("id")Long id,@Param("sys_acl_name")String sys_acl_name,@Param("sys_acl_level")String sys_acl_level);

}

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
     * @param moduleType
     * @param id
     * @return
     */
    int checkUrl(@Param("url") String url, @Param("permissionType") Long moduleType, @Param("id") Long id);

    /**
     * 查询子孙level
     *
     * @param aclModuleLevel
     * @return
     */
    public List<AclDO> getChildAclModuleListByLevel(@Param("aclModuleLevel") String aclModuleLevel);

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
    int checkAclPermissionType(@Param("sys_acl_permission_type")Long sys_acl_permission_type);

}

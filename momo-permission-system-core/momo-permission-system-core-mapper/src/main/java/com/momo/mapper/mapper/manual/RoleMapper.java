package com.momo.mapper.mapper.manual;

import com.momo.mapper.dataobject.RoleAclDO;
import com.momo.mapper.dataobject.RoleDO;
import com.momo.mapper.dataobject.RoleUserDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface RoleMapper {
    int insertSelective(RoleDO record);

    RoleDO selectByPrimaryKey(Long id);

    RoleDO selectByPrimaryUuid(String uuid);

    int updateByPrimaryKeySelective(RoleDO record);

    /**
     * 删除用户和角色关系
     *
     * @param userId
     * @return
     */
    int deleteUserRolesByUserId(@Param("userId") Long userId);

    /**
     * 批量新增用户和角色关系
     *
     * @param roleUserDO
     * @return
     */
    int batchInsertUserRoles(@Param("roleUsers") List<RoleUserDO> roleUserDO);

    /**
     * 检查角色名唯一性
     *
     * @param roleName
     * @param id
     * @param tenantId
     * @return
     */
    int checkRoleName(@Param("roleName") String roleName, @Param("id") Long id, @Param("tenantId") Long tenantId);

    /**
     * 校验管理员数量
     *
     * @param id
     * @param sysRoleType
     * @param tenantId
     * @return
     */
    int checkAdminRole(@Param("id") Long id, @Param("sysRoleType") Integer sysRoleType, @Param("tenantId") Long tenantId);

    /**
     * 删除角色和权限点的关系
     *
     * @param roleId
     * @return
     */
    int deleteRoleAclsByRoleId(@Param("roleId") Long roleId);


    /**
     * 批量新增角色和权限关系
     *
     * @param roleUserDO
     * @return
     */
    int batchInsertRoleAcls(@Param("roleAcls") List<RoleAclDO> roleUserDO);

    /**
     * 查看当前用户角色
     *
     * @param userId
     * @return
     */
    List<RoleDO> rolesByCurrentId(Long userId);


    /**
     * 分页查询角色
     *
     * @param selfRoleDO
     * @return
     */
    List<RoleDO> roleList(RoleDO selfRoleDO);

    /**
     * 根据id查询角色
     *
     * @param id
     * @return
     */
    public RoleDO getRoleById(@Param("id") Long id);

    /**
     * 根据用户id得到角色列表
     *
     * @param userId
     * @return
     */
    public List<RoleDO> getRolesByUserId(@Param("userId") Long userId);

    /**
     * 根据组织id 和管理员角色 查询第三方组织的管理员信息
     *
     * @param tenantId
     * @param roleType 角色的类型，0：管理员角色，1：普通用户 2其他
     * @return
     */
    RoleDO getVipAdminRole(@Param("tenantId") Long tenantId, @Param("roleType") Integer roleType);


    List<RoleDO> getRoleListByEnterpriseId(@Param("tenantId") Long tenantId, @Param("roleType") Integer roleType,@Param("flag")Integer flag,@Param("sysRoleName")String sysRoleName);
}

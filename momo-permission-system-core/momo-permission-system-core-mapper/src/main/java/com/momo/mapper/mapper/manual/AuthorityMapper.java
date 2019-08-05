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
     * @param aclPermissionType 权限类型
     * @param userAclIdList     权限点 ids
     * @return
     */
    List<AclDO> getAllAcl(@Param("aclPermissionType") Long aclPermissionType, @Param("userAclIdList") List<Long> userAclIdList);

    /**
     * 根据角色ids 查询所有角色
     *
     * @param roleId
     * @return
     */
    List<Long> rolesByRoleId(@Param("roleIds") List<Long> roleId, @Param("roleStatus") String roleStatus);


    /**
     * 根据角色ids 查询所有权限
     *
     * @param roleId
     * @return
     */
    List<Long> aclsByRoleId(@Param("roleIds") Set<Long> roleId, @Param("sys_acl_permission_type") Long sys_acl_permission_type);

    /**
     * 根据组的id 得到管理员ids
     *
     * @param groupId
     * @return
     */
    List<Long> rolesAdminByGroupId(@Param("groupId") Long groupId);
}

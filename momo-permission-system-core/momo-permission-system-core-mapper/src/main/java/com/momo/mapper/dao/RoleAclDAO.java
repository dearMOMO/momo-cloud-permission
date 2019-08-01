package com.momo.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.momo.mapper.dataobject.RoleAclDO;
import java.util.List;
import com.momo.mapper.mapper.RoleAclDOMapper;

/**
* The Table sys_role_acl.
* 角色和权限中间表
*/
@Repository
public class RoleAclDAO{

    @Autowired
    private RoleAclDOMapper roleAclDOMapper;

    /**
     * desc:插入表:sys_role_acl.<br/>
     * @param entity entity
     * @return int
     */
    public int insert(RoleAclDO entity){
        return roleAclDOMapper.insert(entity);
    }
    /**
     * desc:批量插入表:sys_role_acl.<br/>
     * @param list list
     * @return int
     */
    public int insertBatch(List<RoleAclDO> list){
        return roleAclDOMapper.insertBatch(list);
    }
    /**
     * desc:根据主键删除数据:sys_role_acl.<br/>
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return roleAclDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:sys_role_acl.<br/>
     * @param id id
     * @return RoleAclDO
     */
    public RoleAclDO getById(Long id){
        return roleAclDOMapper.getById(id);
    }
    /**
     * desc:根据普通索引GroupId获取数据:sys_role_acl.<br/>
     * @param groupId groupId
     * @return List<RoleAclDO>
     */
    public List<RoleAclDO> queryByGroupId(Long groupId){
        return roleAclDOMapper.queryByGroupId(groupId);
    }
    /**
     * desc:根据普通索引SysAclId获取数据:sys_role_acl.<br/>
     * @param sysAclId sysAclId
     * @return List<RoleAclDO>
     */
    public List<RoleAclDO> queryBySysAclId(Long sysAclId){
        return roleAclDOMapper.queryBySysAclId(sysAclId);
    }
    /**
     * desc:根据普通索引SysAclPermissionType获取数据:sys_role_acl.<br/>
     * @param sysAclPermissionType sysAclPermissionType
     * @return List<RoleAclDO>
     */
    public List<RoleAclDO> queryBySysAclPermissionType(Long sysAclPermissionType){
        return roleAclDOMapper.queryBySysAclPermissionType(sysAclPermissionType);
    }
    /**
     * desc:根据普通索引SysRoleId获取数据:sys_role_acl.<br/>
     * @param sysRoleId sysRoleId
     * @return List<RoleAclDO>
     */
    public List<RoleAclDO> queryBySysRoleId(Long sysRoleId){
        return roleAclDOMapper.queryBySysRoleId(sysRoleId);
    }
}

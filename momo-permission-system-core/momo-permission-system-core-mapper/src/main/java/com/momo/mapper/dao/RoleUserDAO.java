package com.momo.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.momo.mapper.dataobject.RoleUserDO;
import java.util.List;
import com.momo.mapper.mapper.RoleUserDOMapper;

/**
* The Table sys_role_user.
* 角色和用户中间表
*/
@Repository
public class RoleUserDAO{

    @Autowired
    private RoleUserDOMapper roleUserDOMapper;

    /**
     * desc:插入表:sys_role_user.<br/>
     * @param entity entity
     * @return int
     */
    public int insert(RoleUserDO entity){
        return roleUserDOMapper.insert(entity);
    }
    /**
     * desc:批量插入表:sys_role_user.<br/>
     * @param list list
     * @return int
     */
    public int insertBatch(List<RoleUserDO> list){
        return roleUserDOMapper.insertBatch(list);
    }
    /**
     * desc:根据主键删除数据:sys_role_user.<br/>
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return roleUserDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:sys_role_user.<br/>
     * @param id id
     * @return RoleUserDO
     */
    public RoleUserDO getById(Long id){
        return roleUserDOMapper.getById(id);
    }
    /**
     * desc:根据普通索引GroupId获取数据:sys_role_user.<br/>
     * @param groupId groupId
     * @return List<RoleUserDO>
     */
    public List<RoleUserDO> queryByGroupId(Long groupId){
        return roleUserDOMapper.queryByGroupId(groupId);
    }
    /**
     * desc:根据普通索引RoleId获取数据:sys_role_user.<br/>
     * @param roleId roleId
     * @return List<RoleUserDO>
     */
    public List<RoleUserDO> queryByRoleId(Long roleId){
        return roleUserDOMapper.queryByRoleId(roleId);
    }
    /**
     * desc:根据普通索引UserId获取数据:sys_role_user.<br/>
     * @param userId userId
     * @return List<RoleUserDO>
     */
    public List<RoleUserDO> queryByUserId(Long userId){
        return roleUserDOMapper.queryByUserId(userId);
    }
}

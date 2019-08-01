package com.momo.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.momo.mapper.dataobject.UserGroupDO;
import java.util.List;
import com.momo.mapper.mapper.UserGroupDOMapper;

/**
* The Table sys_user_group.
* 第三方权限组
*/
@Repository
public class UserGroupDAO{

    @Autowired
    private UserGroupDOMapper userGroupDOMapper;

    /**
     * desc:插入表:sys_user_group.<br/>
     * @param entity entity
     * @return int
     */
    public int insert(UserGroupDO entity){
        return userGroupDOMapper.insert(entity);
    }
    /**
     * desc:批量插入表:sys_user_group.<br/>
     * @param list list
     * @return int
     */
    public int insertBatch(List<UserGroupDO> list){
        return userGroupDOMapper.insertBatch(list);
    }
    /**
     * desc:根据主键删除数据:sys_user_group.<br/>
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return userGroupDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:sys_user_group.<br/>
     * @param id id
     * @return UserGroupDO
     */
    public UserGroupDO getById(Long id){
        return userGroupDOMapper.getById(id);
    }
    /**
     * desc:根据普通索引UserGroupUuid获取数据:sys_user_group.<br/>
     * @param uuid uuid
     * @return List<UserGroupDO>
     */
    public List<UserGroupDO> queryByUserGroupUuid(String uuid){
        return userGroupDOMapper.queryByUserGroupUuid(uuid);
    }
}

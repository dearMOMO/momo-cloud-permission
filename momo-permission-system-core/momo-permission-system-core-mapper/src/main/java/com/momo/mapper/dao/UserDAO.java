package com.momo.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.momo.mapper.dataobject.UserDO;
import java.util.List;
import com.momo.mapper.mapper.UserDOMapper;

/**
* The Table sys_user.
* 用户
*/
@Repository
public class UserDAO{

    @Autowired
    private UserDOMapper userDOMapper;

    /**
     * desc:插入表:sys_user.<br/>
     * @param entity entity
     * @return int
     */
    public int insert(UserDO entity){
        return userDOMapper.insert(entity);
    }
    /**
     * desc:批量插入表:sys_user.<br/>
     * @param list list
     * @return int
     */
    public int insertBatch(List<UserDO> list){
        return userDOMapper.insertBatch(list);
    }
    /**
     * desc:根据主键删除数据:sys_user.<br/>
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return userDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:sys_user.<br/>
     * @param id id
     * @return UserDO
     */
    public UserDO getById(Long id){
        return userDOMapper.getById(id);
    }
    /**
     * desc:根据普通索引SysUserName获取数据:sys_user.<br/>
     * @param sysUserName sysUserName
     * @return List<UserDO>
     */
    public List<UserDO> queryBySysUserName(String sysUserName){
        return userDOMapper.queryBySysUserName(sysUserName);
    }
    /**
     * desc:根据普通索引SysUserPhone获取数据:sys_user.<br/>
     * @param sysUserPhone sysUserPhone
     * @return List<UserDO>
     */
    public List<UserDO> queryBySysUserPhone(String sysUserPhone){
        return userDOMapper.queryBySysUserPhone(sysUserPhone);
    }
    /**
     * desc:根据普通索引SysUserUuid获取数据:sys_user.<br/>
     * @param uuid uuid
     * @return List<UserDO>
     */
    public List<UserDO> queryBySysUserUuid(String uuid){
        return userDOMapper.queryBySysUserUuid(uuid);
    }
}

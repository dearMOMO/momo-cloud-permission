package com.momo.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.momo.mapper.dataobject.UserDeptDO;
import java.util.List;
import com.momo.mapper.mapper.UserDeptDOMapper;

/**
* The Table sys_user_dept.
* 用户和部门
*/
@Repository
public class UserDeptDAO{

    @Autowired
    private UserDeptDOMapper userDeptDOMapper;

    /**
     * desc:插入表:sys_user_dept.<br/>
     * @param entity entity
     * @return int
     */
    public int insert(UserDeptDO entity){
        return userDeptDOMapper.insert(entity);
    }
    /**
     * desc:批量插入表:sys_user_dept.<br/>
     * @param list list
     * @return int
     */
    public int insertBatch(List<UserDeptDO> list){
        return userDeptDOMapper.insertBatch(list);
    }
    /**
     * desc:根据主键删除数据:sys_user_dept.<br/>
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return userDeptDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:sys_user_dept.<br/>
     * @param id id
     * @return UserDeptDO
     */
    public UserDeptDO getById(Long id){
        return userDeptDOMapper.getById(id);
    }
}

package com.momo.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.momo.mapper.dataobject.UserAccountPwdDO;
import java.util.List;
import com.momo.mapper.mapper.UserAccountPwdDOMapper;

/**
* The Table sys_user_account_pwd.
* 用户基础表
*/
@Repository
public class UserAccountPwdDAO{

    @Autowired
    private UserAccountPwdDOMapper userAccountPwdDOMapper;

    /**
     * desc:插入表:sys_user_account_pwd.<br/>
     * @param entity entity
     * @return int
     */
    public int insert(UserAccountPwdDO entity){
        return userAccountPwdDOMapper.insert(entity);
    }
    /**
     * desc:批量插入表:sys_user_account_pwd.<br/>
     * @param list list
     * @return int
     */
    public int insertBatch(List<UserAccountPwdDO> list){
        return userAccountPwdDOMapper.insertBatch(list);
    }
    /**
     * desc:根据主键删除数据:sys_user_account_pwd.<br/>
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return userAccountPwdDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:sys_user_account_pwd.<br/>
     * @param id id
     * @return UserAccountPwdDO
     */
    public UserAccountPwdDO getById(Long id){
        return userAccountPwdDOMapper.getById(id);
    }
}

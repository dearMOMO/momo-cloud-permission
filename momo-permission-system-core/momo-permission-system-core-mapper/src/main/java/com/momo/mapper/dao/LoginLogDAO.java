package com.momo.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.momo.mapper.dataobject.LoginLogDO;
import java.util.List;
import com.momo.mapper.mapper.LoginLogDOMapper;

/**
* The Table sys_login_log.
* 登录日志
*/
@Repository
public class LoginLogDAO{

    @Autowired
    private LoginLogDOMapper loginLogDOMapper;

    /**
     * desc:插入表:sys_login_log.<br/>
     * @param entity entity
     * @return int
     */
    public int insert(LoginLogDO entity){
        return loginLogDOMapper.insert(entity);
    }
    /**
     * desc:批量插入表:sys_login_log.<br/>
     * @param list list
     * @return int
     */
    public int insertBatch(List<LoginLogDO> list){
        return loginLogDOMapper.insertBatch(list);
    }
    /**
     * desc:根据主键删除数据:sys_login_log.<br/>
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return loginLogDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:sys_login_log.<br/>
     * @param id id
     * @return LoginLogDO
     */
    public LoginLogDO getById(Long id){
        return loginLogDOMapper.getById(id);
    }
}

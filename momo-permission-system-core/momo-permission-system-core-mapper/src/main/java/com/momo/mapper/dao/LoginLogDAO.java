package com.momo.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.momo.mapper.dataobject.LoginLogDO;
import com.momo.mapper.mapper.LoginLogDOMapper;

/**
* The Table SYS_LOGIN_LOG.
* 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
* 登录日志
*/
@Repository
public class LoginLogDAO{

    @Autowired
    private LoginLogDOMapper loginLogDOMapper;

    /**
     * desc:插入表:SYS_LOGIN_LOG.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_LOGIN_LOG( USER_ID ,GROUP_ID ,USER_IP ,USER_LOGIN_SYS ,USER_USER_NAME ,USER_LOGIN_NAME ,USER_LOGIN_TYPE ,USER_LOGIN_BROWSER ,CREATE_TIME )VALUES( #{userId,jdbcType=BIGINT} ,#{groupId,jdbcType=BIGINT} ,#{userIp,jdbcType=VARCHAR} ,#{userLoginSys,jdbcType=VARCHAR} ,#{userUserName,jdbcType=VARCHAR} ,#{userLoginName,jdbcType=VARCHAR} ,#{userLoginType,jdbcType=VARCHAR} ,#{userLoginBrowser,jdbcType=VARCHAR} ,#{createTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    public int insert(LoginLogDO entity){
        return loginLogDOMapper.insert(entity);
    }
    /**
     * desc:更新表:SYS_LOGIN_LOG.<br/>
     * descSql =  UPDATE SYS_LOGIN_LOG SET USER_ID = #{userId,jdbcType=BIGINT} ,GROUP_ID = #{groupId,jdbcType=BIGINT} ,USER_IP = #{userIp,jdbcType=VARCHAR} ,USER_LOGIN_SYS = #{userLoginSys,jdbcType=VARCHAR} ,USER_USER_NAME = #{userUserName,jdbcType=VARCHAR} ,USER_LOGIN_NAME = #{userLoginName,jdbcType=VARCHAR} ,USER_LOGIN_TYPE = #{userLoginType,jdbcType=VARCHAR} ,USER_LOGIN_BROWSER = #{userLoginBrowser,jdbcType=VARCHAR} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    public int update(LoginLogDO entity){
        return loginLogDOMapper.update(entity);
    }
    /**
     * desc:根据主键删除数据:SYS_LOGIN_LOG.<br/>
     * descSql =  DELETE FROM SYS_LOGIN_LOG WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return loginLogDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:SYS_LOGIN_LOG.<br/>
     * descSql =  SELECT * FROM SYS_LOGIN_LOG WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return LoginLogDO
     */
    public LoginLogDO getById(Long id){
        return loginLogDOMapper.getById(id);
    }
}

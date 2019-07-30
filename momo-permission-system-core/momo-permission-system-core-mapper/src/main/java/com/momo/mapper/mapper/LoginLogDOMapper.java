package com.momo.mapper.mapper;

import com.momo.mapper.dataobject.LoginLogDO;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table SYS_LOGIN_LOG.
 * 登录日志
 */
public interface LoginLogDOMapper{

    /**
     * desc:插入表:SYS_LOGIN_LOG.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_LOGIN_LOG( USER_ID ,GROUP_ID ,USER_IP ,USER_LOGIN_SYS ,USER_USER_NAME ,USER_LOGIN_NAME ,USER_LOGIN_TYPE ,USER_LOGIN_BROWSER ,CREATE_TIME )VALUES( #{userId,jdbcType=BIGINT} ,#{groupId,jdbcType=BIGINT} ,#{userIp,jdbcType=VARCHAR} ,#{userLoginSys,jdbcType=VARCHAR} ,#{userUserName,jdbcType=VARCHAR} ,#{userLoginName,jdbcType=VARCHAR} ,#{userLoginType,jdbcType=VARCHAR} ,#{userLoginBrowser,jdbcType=VARCHAR} ,#{createTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    int insert(LoginLogDO entity);
    /**
     * desc:更新表:SYS_LOGIN_LOG.<br/>
     * descSql =  UPDATE SYS_LOGIN_LOG SET USER_ID = #{userId,jdbcType=BIGINT} ,GROUP_ID = #{groupId,jdbcType=BIGINT} ,USER_IP = #{userIp,jdbcType=VARCHAR} ,USER_LOGIN_SYS = #{userLoginSys,jdbcType=VARCHAR} ,USER_USER_NAME = #{userUserName,jdbcType=VARCHAR} ,USER_LOGIN_NAME = #{userLoginName,jdbcType=VARCHAR} ,USER_LOGIN_TYPE = #{userLoginType,jdbcType=VARCHAR} ,USER_LOGIN_BROWSER = #{userLoginBrowser,jdbcType=VARCHAR} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    int update(LoginLogDO entity);
    /**
     * desc:根据主键删除数据:SYS_LOGIN_LOG.<br/>
     * descSql =  DELETE FROM SYS_LOGIN_LOG WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:SYS_LOGIN_LOG.<br/>
     * descSql =  SELECT * FROM SYS_LOGIN_LOG WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return LoginLogDO
     */
    LoginLogDO getById(Long id);
}

package com.momo.mapper.mapper;

import com.momo.mapper.dataobject.LoginLogDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * The Table sys_login_log.
 * 登录日志
 */
public interface LoginLogDOMapper{

    /**
     * desc:插入表:sys_login_log.<br/>
     * descSql =  SELECT LAST_INSERT_ID() <![CDATA[ INSERT INTO sys_login_log( ID ,USER_ID ,GROUP_ID ,UUID ,USER_IP ,CREATE_BY ,UPDATE_BY ,USER_LOGIN_SYS ,USER_USER_NAME ,USER_LOGIN_NAME ,USER_LOGIN_DEVICE ,USER_LOGIN_BROWSER ,FLAG ,USER_LOGIN_TYPE ,CREATE_TIME ,UPDATE_TIME )VALUES( null , #{userId,jdbcType=BIGINT} , #{groupId,jdbcType=BIGINT} , #{uuid,jdbcType=VARCHAR} , #{userIp,jdbcType=VARCHAR} , #{createBy,jdbcType=VARCHAR} , #{updateBy,jdbcType=VARCHAR} , #{userLoginSys,jdbcType=VARCHAR} , #{userUserName,jdbcType=VARCHAR} , #{userLoginName,jdbcType=VARCHAR} , #{userLoginDevice,jdbcType=VARCHAR} , #{userLoginBrowser,jdbcType=VARCHAR} , #{flag,jdbcType=INTEGER} , #{userLoginType,jdbcType=INTEGER} , #{createTime,jdbcType=TIMESTAMP} , #{updateTime,jdbcType=TIMESTAMP} ) ]]>
     * @param entity entity
     * @return int
     */
    int insert(LoginLogDO entity);
    /**
     * desc:批量插入表:sys_login_log.<br/>
     * descSql =  INSERT INTO sys_login_log( ID ,USER_ID ,GROUP_ID ,UUID ,USER_IP ,CREATE_BY ,UPDATE_BY ,USER_LOGIN_SYS ,USER_USER_NAME ,USER_LOGIN_NAME ,USER_LOGIN_DEVICE ,USER_LOGIN_BROWSER ,FLAG ,USER_LOGIN_TYPE ,CREATE_TIME ,UPDATE_TIME )VALUES ( null , #{item.userId,jdbcType=BIGINT} , #{item.groupId,jdbcType=BIGINT} , #{item.uuid,jdbcType=VARCHAR} , #{item.userIp,jdbcType=VARCHAR} , #{item.createBy,jdbcType=VARCHAR} , #{item.updateBy,jdbcType=VARCHAR} , #{item.userLoginSys,jdbcType=VARCHAR} , #{item.userUserName,jdbcType=VARCHAR} , #{item.userLoginName,jdbcType=VARCHAR} , #{item.userLoginDevice,jdbcType=VARCHAR} , #{item.userLoginBrowser,jdbcType=VARCHAR} , #{item.flag,jdbcType=INTEGER} , #{item.userLoginType,jdbcType=INTEGER} , #{item.createTime,jdbcType=TIMESTAMP} , #{item.updateTime,jdbcType=TIMESTAMP} ) 
     * @param list list
     * @return int
     */
    int insertBatch(List<LoginLogDO> list);
    /**
     * desc:根据主键删除数据:sys_login_log.<br/>
     * descSql =  <![CDATA[ DELETE FROM sys_login_log WHERE ID = #{id,jdbcType=BIGINT} ]]>
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:sys_login_log.<br/>
     * descSql =  SELECT * FROM sys_login_log WHERE <![CDATA[ ID = #{id,jdbcType=BIGINT} ]]>
     * @param id id
     * @return LoginLogDO
     */
    LoginLogDO getById(Long id);
}

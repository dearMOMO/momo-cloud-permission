package com.momo.mapper.mapper;

import com.momo.mapper.dataobject.UserAccountPwdDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * The Table sys_user_account_pwd.
 * 用户基础表
 */
public interface UserAccountPwdDOMapper{

    /**
     * desc:插入表:sys_user_account_pwd.<br/>
     * descSql =  SELECT LAST_INSERT_ID() <![CDATA[ INSERT INTO sys_user_account_pwd( ID ,GROUP_ID ,SYS_USER_ID ,UUID ,REMARK ,CREATE_BY ,UPDATE_BY ,SYS_USER_PWD ,SYS_USER_AUTH_SALT ,SYS_USER_LOGIN_NAME ,FLAG ,DEL_FLAG ,SYS_LOGIN_NUMBER ,CREATE_TIME ,UPDATE_TIME )VALUES( null , #{groupId,jdbcType=BIGINT} , #{sysUserId,jdbcType=BIGINT} , #{uuid,jdbcType=VARCHAR} , #{remark,jdbcType=VARCHAR} , #{createBy,jdbcType=VARCHAR} , #{updateBy,jdbcType=VARCHAR} , #{sysUserPwd,jdbcType=VARCHAR} , #{sysUserAuthSalt,jdbcType=VARCHAR} , #{sysUserLoginName,jdbcType=VARCHAR} , #{flag,jdbcType=INTEGER} , #{delFlag,jdbcType=INTEGER} , #{sysLoginNumber,jdbcType=INTEGER} , #{createTime,jdbcType=TIMESTAMP} , #{updateTime,jdbcType=TIMESTAMP} ) ]]>
     * @param entity entity
     * @return int
     */
    int insert(UserAccountPwdDO entity);
    /**
     * desc:批量插入表:sys_user_account_pwd.<br/>
     * descSql =  INSERT INTO sys_user_account_pwd( ID ,GROUP_ID ,SYS_USER_ID ,UUID ,REMARK ,CREATE_BY ,UPDATE_BY ,SYS_USER_PWD ,SYS_USER_AUTH_SALT ,SYS_USER_LOGIN_NAME ,FLAG ,DEL_FLAG ,SYS_LOGIN_NUMBER ,CREATE_TIME ,UPDATE_TIME )VALUES ( null , #{item.groupId,jdbcType=BIGINT} , #{item.sysUserId,jdbcType=BIGINT} , #{item.uuid,jdbcType=VARCHAR} , #{item.remark,jdbcType=VARCHAR} , #{item.createBy,jdbcType=VARCHAR} , #{item.updateBy,jdbcType=VARCHAR} , #{item.sysUserPwd,jdbcType=VARCHAR} , #{item.sysUserAuthSalt,jdbcType=VARCHAR} , #{item.sysUserLoginName,jdbcType=VARCHAR} , #{item.flag,jdbcType=INTEGER} , #{item.delFlag,jdbcType=INTEGER} , #{item.sysLoginNumber,jdbcType=INTEGER} , #{item.createTime,jdbcType=TIMESTAMP} , #{item.updateTime,jdbcType=TIMESTAMP} ) 
     * @param list list
     * @return int
     */
    int insertBatch(List<UserAccountPwdDO> list);
    /**
     * desc:根据主键删除数据:sys_user_account_pwd.<br/>
     * descSql =  <![CDATA[ DELETE FROM sys_user_account_pwd WHERE ID = #{id,jdbcType=BIGINT} ]]>
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:sys_user_account_pwd.<br/>
     * descSql =  SELECT * FROM sys_user_account_pwd WHERE <![CDATA[ ID = #{id,jdbcType=BIGINT} ]]>
     * @param id id
     * @return UserAccountPwdDO
     */
    UserAccountPwdDO getById(Long id);
}

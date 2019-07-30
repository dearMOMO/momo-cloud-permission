package com.momo.mapper.mapper;

import com.momo.mapper.dataobject.UserAccountPwdDO;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table SYS_USER_ACCOUNT_PWD.
 * 会员登录
 */
public interface UserAccountPwdDOMapper{

    /**
     * desc:插入表:SYS_USER_ACCOUNT_PWD.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_USER_ACCOUNT_PWD( SYS_USER_ID ,REMARK ,CREATE_BY ,UPDATE_BY ,SYS_USER_PWD ,SYS_USER_AUTH_SALT ,SYS_USER_LOGIN_NAME ,STATUS ,DEL_FLAG ,CREATE_TIME ,UPDATE_TIME )VALUES( #{sysUserId,jdbcType=BIGINT} ,#{remark,jdbcType=VARCHAR} ,#{createBy,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{sysUserPwd,jdbcType=VARCHAR} ,#{sysUserAuthSalt,jdbcType=VARCHAR} ,#{sysUserLoginName,jdbcType=VARCHAR} ,#{status,jdbcType=INTEGER} ,#{delFlag,jdbcType=INTEGER} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    int insert(UserAccountPwdDO entity);
    /**
     * desc:更新表:SYS_USER_ACCOUNT_PWD.<br/>
     * descSql =  UPDATE SYS_USER_ACCOUNT_PWD SET SYS_USER_ID = #{sysUserId,jdbcType=BIGINT} ,REMARK = #{remark,jdbcType=VARCHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,SYS_USER_PWD = #{sysUserPwd,jdbcType=VARCHAR} ,SYS_USER_AUTH_SALT = #{sysUserAuthSalt,jdbcType=VARCHAR} ,SYS_USER_LOGIN_NAME = #{sysUserLoginName,jdbcType=VARCHAR} ,STATUS = #{status,jdbcType=INTEGER} ,DEL_FLAG = #{delFlag,jdbcType=INTEGER} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    int update(UserAccountPwdDO entity);
    /**
     * desc:根据主键删除数据:SYS_USER_ACCOUNT_PWD.<br/>
     * descSql =  DELETE FROM SYS_USER_ACCOUNT_PWD WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:SYS_USER_ACCOUNT_PWD.<br/>
     * descSql =  SELECT * FROM SYS_USER_ACCOUNT_PWD WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return UserAccountPwdDO
     */
    UserAccountPwdDO getById(Long id);
}

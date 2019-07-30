package com.momo.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.momo.mapper.dataobject.UserAccountPwdDO;
import com.momo.mapper.mapper.UserAccountPwdDOMapper;

/**
* The Table SYS_USER_ACCOUNT_PWD.
* 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
* 会员登录
*/
@Repository
public class UserAccountPwdDAO{

    @Autowired
    private UserAccountPwdDOMapper userAccountPwdDOMapper;

    /**
     * desc:插入表:SYS_USER_ACCOUNT_PWD.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_USER_ACCOUNT_PWD( SYS_USER_ID ,REMARK ,CREATE_BY ,UPDATE_BY ,SYS_USER_PWD ,SYS_USER_AUTH_SALT ,SYS_USER_LOGIN_NAME ,STATUS ,DEL_FLAG ,CREATE_TIME ,UPDATE_TIME )VALUES( #{sysUserId,jdbcType=BIGINT} ,#{remark,jdbcType=VARCHAR} ,#{createBy,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{sysUserPwd,jdbcType=VARCHAR} ,#{sysUserAuthSalt,jdbcType=VARCHAR} ,#{sysUserLoginName,jdbcType=VARCHAR} ,#{status,jdbcType=INTEGER} ,#{delFlag,jdbcType=INTEGER} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    public int insert(UserAccountPwdDO entity){
        return userAccountPwdDOMapper.insert(entity);
    }
    /**
     * desc:更新表:SYS_USER_ACCOUNT_PWD.<br/>
     * descSql =  UPDATE SYS_USER_ACCOUNT_PWD SET SYS_USER_ID = #{sysUserId,jdbcType=BIGINT} ,REMARK = #{remark,jdbcType=VARCHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,SYS_USER_PWD = #{sysUserPwd,jdbcType=VARCHAR} ,SYS_USER_AUTH_SALT = #{sysUserAuthSalt,jdbcType=VARCHAR} ,SYS_USER_LOGIN_NAME = #{sysUserLoginName,jdbcType=VARCHAR} ,STATUS = #{status,jdbcType=INTEGER} ,DEL_FLAG = #{delFlag,jdbcType=INTEGER} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    public int update(UserAccountPwdDO entity){
        return userAccountPwdDOMapper.update(entity);
    }
    /**
     * desc:根据主键删除数据:SYS_USER_ACCOUNT_PWD.<br/>
     * descSql =  DELETE FROM SYS_USER_ACCOUNT_PWD WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return userAccountPwdDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:SYS_USER_ACCOUNT_PWD.<br/>
     * descSql =  SELECT * FROM SYS_USER_ACCOUNT_PWD WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return UserAccountPwdDO
     */
    public UserAccountPwdDO getById(Long id){
        return userAccountPwdDOMapper.getById(id);
    }
}

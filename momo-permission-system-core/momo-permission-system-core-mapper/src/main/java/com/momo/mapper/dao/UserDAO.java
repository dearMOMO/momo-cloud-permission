package com.momo.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.momo.mapper.dataobject.UserDO;
import com.momo.mapper.mapper.UserDOMapper;

/**
* The Table SYS_USER.
* 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
* 用户
*/
@Repository
public class UserDAO{

    @Autowired
    private UserDOMapper userDOMapper;

    /**
     * desc:插入表:SYS_USER.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_USER( DEPT_ID ,GROUP_ID ,SYS_USER_AREA ,SYS_USER_CITY ,SYS_USER_PROVINCE ,DEL_FLAG ,CREATE_BY ,UPDATE_BY ,SYS_USER_PWD ,IS_FORBIDDEN ,SYS_USER_NAME ,SYS_USER_UUID ,SYS_USER_EMAIL ,SYS_USER_PHONE ,SYS_USER_AREA_NAME ,SYS_USER_AUTH_SALT ,SYS_USER_CITY_NAME ,SYS_USER_LOGIN_NAME ,SYS_USER_PROVINCE_NAME ,SYS_LOGIN_NUMBER ,CREATE_TIME ,UPDATE_TIME )VALUES( #{deptId,jdbcType=BIGINT} ,#{groupId,jdbcType=BIGINT} ,#{sysUserArea,jdbcType=BIGINT} ,#{sysUserCity,jdbcType=BIGINT} ,#{sysUserProvince,jdbcType=BIGINT} ,#{delFlag,jdbcType=CHAR} ,#{createBy,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{sysUserPwd,jdbcType=VARCHAR} ,#{isForbidden,jdbcType=CHAR} ,#{sysUserName,jdbcType=VARCHAR} ,#{sysUserUuid,jdbcType=VARCHAR} ,#{sysUserEmail,jdbcType=VARCHAR} ,#{sysUserPhone,jdbcType=VARCHAR} ,#{sysUserAreaName,jdbcType=VARCHAR} ,#{sysUserAuthSalt,jdbcType=VARCHAR} ,#{sysUserCityName,jdbcType=VARCHAR} ,#{sysUserLoginName,jdbcType=VARCHAR} ,#{sysUserProvinceName,jdbcType=VARCHAR} ,#{sysLoginNumber,jdbcType=INTEGER} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    public int insert(UserDO entity){
        return userDOMapper.insert(entity);
    }
    /**
     * desc:更新表:SYS_USER.<br/>
     * descSql =  UPDATE SYS_USER SET DEPT_ID = #{deptId,jdbcType=BIGINT} ,GROUP_ID = #{groupId,jdbcType=BIGINT} ,SYS_USER_AREA = #{sysUserArea,jdbcType=BIGINT} ,SYS_USER_CITY = #{sysUserCity,jdbcType=BIGINT} ,SYS_USER_PROVINCE = #{sysUserProvince,jdbcType=BIGINT} ,DEL_FLAG = #{delFlag,jdbcType=CHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,SYS_USER_PWD = #{sysUserPwd,jdbcType=VARCHAR} ,IS_FORBIDDEN = #{isForbidden,jdbcType=CHAR} ,SYS_USER_NAME = #{sysUserName,jdbcType=VARCHAR} ,SYS_USER_UUID = #{sysUserUuid,jdbcType=VARCHAR} ,SYS_USER_EMAIL = #{sysUserEmail,jdbcType=VARCHAR} ,SYS_USER_PHONE = #{sysUserPhone,jdbcType=VARCHAR} ,SYS_USER_AREA_NAME = #{sysUserAreaName,jdbcType=VARCHAR} ,SYS_USER_AUTH_SALT = #{sysUserAuthSalt,jdbcType=VARCHAR} ,SYS_USER_CITY_NAME = #{sysUserCityName,jdbcType=VARCHAR} ,SYS_USER_LOGIN_NAME = #{sysUserLoginName,jdbcType=VARCHAR} ,SYS_USER_PROVINCE_NAME = #{sysUserProvinceName,jdbcType=VARCHAR} ,SYS_LOGIN_NUMBER = #{sysLoginNumber,jdbcType=INTEGER} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    public int update(UserDO entity){
        return userDOMapper.update(entity);
    }
    /**
     * desc:根据主键删除数据:SYS_USER.<br/>
     * descSql =  DELETE FROM SYS_USER WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return userDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:SYS_USER.<br/>
     * descSql =  SELECT * FROM SYS_USER WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return UserDO
     */
    public UserDO getById(Long id){
        return userDOMapper.getById(id);
    }
}

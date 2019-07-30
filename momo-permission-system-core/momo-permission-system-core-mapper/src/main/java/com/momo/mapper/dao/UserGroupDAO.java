package com.momo.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.momo.mapper.dataobject.UserGroupDO;
import com.momo.mapper.mapper.UserGroupDOMapper;

/**
* The Table SYS_USER_GROUP.
* 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
* 第三方权限组
*/
@Repository
public class UserGroupDAO{

    @Autowired
    private UserGroupDOMapper userGroupDOMapper;

    /**
     * desc:插入表:SYS_USER_GROUP.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_USER_GROUP( DEL_FLAG ,NAME_TOP ,CREATE_BY ,UPDATE_BY ,NAME_BOTTOM ,SYS_OPEN_DAY ,USER_GROUP_NAME ,USER_GROUP_UUID ,USER_GROUP_STATUS ,SYS_OPEN_ACCOUNT_NUM ,SYS_CREATE_ACCOUNT_NUM ,CREATE_TIME ,UPDATE_TIME ,SYS_ACCOUNT_END_TIME ,SYS_ACCOUNT_START_TIME )VALUES( #{delFlag,jdbcType=CHAR} ,#{nameTop,jdbcType=VARCHAR} ,#{createBy,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{nameBottom,jdbcType=VARCHAR} ,#{sysOpenDay,jdbcType=VARCHAR} ,#{userGroupName,jdbcType=VARCHAR} ,#{userGroupUuid,jdbcType=VARCHAR} ,#{userGroupStatus,jdbcType=CHAR} ,#{sysOpenAccountNum,jdbcType=INTEGER} ,#{sysCreateAccountNum,jdbcType=INTEGER} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} ,#{sysAccountEndTime,jdbcType=TIMESTAMP} ,#{sysAccountStartTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    public int insert(UserGroupDO entity){
        return userGroupDOMapper.insert(entity);
    }
    /**
     * desc:更新表:SYS_USER_GROUP.<br/>
     * descSql =  UPDATE SYS_USER_GROUP SET DEL_FLAG = #{delFlag,jdbcType=CHAR} ,NAME_TOP = #{nameTop,jdbcType=VARCHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,NAME_BOTTOM = #{nameBottom,jdbcType=VARCHAR} ,SYS_OPEN_DAY = #{sysOpenDay,jdbcType=VARCHAR} ,USER_GROUP_NAME = #{userGroupName,jdbcType=VARCHAR} ,USER_GROUP_UUID = #{userGroupUuid,jdbcType=VARCHAR} ,USER_GROUP_STATUS = #{userGroupStatus,jdbcType=CHAR} ,SYS_OPEN_ACCOUNT_NUM = #{sysOpenAccountNum,jdbcType=INTEGER} ,SYS_CREATE_ACCOUNT_NUM = #{sysCreateAccountNum,jdbcType=INTEGER} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} ,SYS_ACCOUNT_END_TIME = #{sysAccountEndTime,jdbcType=TIMESTAMP} ,SYS_ACCOUNT_START_TIME = #{sysAccountStartTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    public int update(UserGroupDO entity){
        return userGroupDOMapper.update(entity);
    }
    /**
     * desc:根据主键删除数据:SYS_USER_GROUP.<br/>
     * descSql =  DELETE FROM SYS_USER_GROUP WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return userGroupDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:SYS_USER_GROUP.<br/>
     * descSql =  SELECT * FROM SYS_USER_GROUP WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return UserGroupDO
     */
    public UserGroupDO getById(Long id){
        return userGroupDOMapper.getById(id);
    }
}

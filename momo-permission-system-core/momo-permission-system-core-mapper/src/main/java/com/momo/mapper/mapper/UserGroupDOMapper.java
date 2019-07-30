package com.momo.mapper.mapper;

import com.momo.mapper.dataobject.UserGroupDO;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table SYS_USER_GROUP.
 * 第三方权限组
 */
public interface UserGroupDOMapper{

    /**
     * desc:插入表:SYS_USER_GROUP.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_USER_GROUP( DEL_FLAG ,NAME_TOP ,CREATE_BY ,UPDATE_BY ,NAME_BOTTOM ,SYS_OPEN_DAY ,USER_GROUP_NAME ,USER_GROUP_UUID ,USER_GROUP_STATUS ,SYS_OPEN_ACCOUNT_NUM ,SYS_CREATE_ACCOUNT_NUM ,CREATE_TIME ,UPDATE_TIME ,SYS_ACCOUNT_END_TIME ,SYS_ACCOUNT_START_TIME )VALUES( #{delFlag,jdbcType=CHAR} ,#{nameTop,jdbcType=VARCHAR} ,#{createBy,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{nameBottom,jdbcType=VARCHAR} ,#{sysOpenDay,jdbcType=VARCHAR} ,#{userGroupName,jdbcType=VARCHAR} ,#{userGroupUuid,jdbcType=VARCHAR} ,#{userGroupStatus,jdbcType=CHAR} ,#{sysOpenAccountNum,jdbcType=INTEGER} ,#{sysCreateAccountNum,jdbcType=INTEGER} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} ,#{sysAccountEndTime,jdbcType=TIMESTAMP} ,#{sysAccountStartTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    int insert(UserGroupDO entity);
    /**
     * desc:更新表:SYS_USER_GROUP.<br/>
     * descSql =  UPDATE SYS_USER_GROUP SET DEL_FLAG = #{delFlag,jdbcType=CHAR} ,NAME_TOP = #{nameTop,jdbcType=VARCHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,NAME_BOTTOM = #{nameBottom,jdbcType=VARCHAR} ,SYS_OPEN_DAY = #{sysOpenDay,jdbcType=VARCHAR} ,USER_GROUP_NAME = #{userGroupName,jdbcType=VARCHAR} ,USER_GROUP_UUID = #{userGroupUuid,jdbcType=VARCHAR} ,USER_GROUP_STATUS = #{userGroupStatus,jdbcType=CHAR} ,SYS_OPEN_ACCOUNT_NUM = #{sysOpenAccountNum,jdbcType=INTEGER} ,SYS_CREATE_ACCOUNT_NUM = #{sysCreateAccountNum,jdbcType=INTEGER} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} ,SYS_ACCOUNT_END_TIME = #{sysAccountEndTime,jdbcType=TIMESTAMP} ,SYS_ACCOUNT_START_TIME = #{sysAccountStartTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    int update(UserGroupDO entity);
    /**
     * desc:根据主键删除数据:SYS_USER_GROUP.<br/>
     * descSql =  DELETE FROM SYS_USER_GROUP WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:SYS_USER_GROUP.<br/>
     * descSql =  SELECT * FROM SYS_USER_GROUP WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return UserGroupDO
     */
    UserGroupDO getById(Long id);
}

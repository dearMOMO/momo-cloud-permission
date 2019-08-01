package com.momo.mapper.mapper;

import com.momo.mapper.dataobject.UserGroupDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * The Table sys_user_group.
 * 第三方权限组
 */
public interface UserGroupDOMapper{

    /**
     * desc:插入表:sys_user_group.<br/>
     * descSql =  SELECT LAST_INSERT_ID() <![CDATA[ INSERT INTO sys_user_group( ID ,UUID ,NAME_TOP ,CREATE_BY ,UPDATE_BY ,NAME_BOTTOM ,USER_GROUP_NAME ,FLAG ,DEL_FLAG ,SYS_OPEN_DAY ,SYS_OPEN_ACCOUNT_NUM ,SYS_CREATE_ACCOUNT_NUM ,CREATE_TIME ,UPDATE_TIME ,SYS_ACCOUNT_END_TIME ,SYS_ACCOUNT_START_TIME )VALUES( null , #{uuid,jdbcType=VARCHAR} , #{nameTop,jdbcType=VARCHAR} , #{createBy,jdbcType=VARCHAR} , #{updateBy,jdbcType=VARCHAR} , #{nameBottom,jdbcType=VARCHAR} , #{userGroupName,jdbcType=VARCHAR} , #{flag,jdbcType=INTEGER} , #{delFlag,jdbcType=INTEGER} , #{sysOpenDay,jdbcType=INTEGER} , #{sysOpenAccountNum,jdbcType=INTEGER} , #{sysCreateAccountNum,jdbcType=INTEGER} , #{createTime,jdbcType=TIMESTAMP} , #{updateTime,jdbcType=TIMESTAMP} , #{sysAccountEndTime,jdbcType=TIMESTAMP} , #{sysAccountStartTime,jdbcType=TIMESTAMP} ) ]]>
     * @param entity entity
     * @return int
     */
    int insert(UserGroupDO entity);
    /**
     * desc:批量插入表:sys_user_group.<br/>
     * descSql =  INSERT INTO sys_user_group( ID ,UUID ,NAME_TOP ,CREATE_BY ,UPDATE_BY ,NAME_BOTTOM ,USER_GROUP_NAME ,FLAG ,DEL_FLAG ,SYS_OPEN_DAY ,SYS_OPEN_ACCOUNT_NUM ,SYS_CREATE_ACCOUNT_NUM ,CREATE_TIME ,UPDATE_TIME ,SYS_ACCOUNT_END_TIME ,SYS_ACCOUNT_START_TIME )VALUES ( null , #{item.uuid,jdbcType=VARCHAR} , #{item.nameTop,jdbcType=VARCHAR} , #{item.createBy,jdbcType=VARCHAR} , #{item.updateBy,jdbcType=VARCHAR} , #{item.nameBottom,jdbcType=VARCHAR} , #{item.userGroupName,jdbcType=VARCHAR} , #{item.flag,jdbcType=INTEGER} , #{item.delFlag,jdbcType=INTEGER} , #{item.sysOpenDay,jdbcType=INTEGER} , #{item.sysOpenAccountNum,jdbcType=INTEGER} , #{item.sysCreateAccountNum,jdbcType=INTEGER} , #{item.createTime,jdbcType=TIMESTAMP} , #{item.updateTime,jdbcType=TIMESTAMP} , #{item.sysAccountEndTime,jdbcType=TIMESTAMP} , #{item.sysAccountStartTime,jdbcType=TIMESTAMP} ) 
     * @param list list
     * @return int
     */
    int insertBatch(List<UserGroupDO> list);
    /**
     * desc:根据主键删除数据:sys_user_group.<br/>
     * descSql =  <![CDATA[ DELETE FROM sys_user_group WHERE ID = #{id,jdbcType=BIGINT} ]]>
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:sys_user_group.<br/>
     * descSql =  SELECT * FROM sys_user_group WHERE <![CDATA[ ID = #{id,jdbcType=BIGINT} ]]>
     * @param id id
     * @return UserGroupDO
     */
    UserGroupDO getById(Long id);
    /**
     * desc:根据普通索引UserGroupUuid获取数据:sys_user_group.<br/>
     * descSql =  SELECT * FROM sys_user_group WHERE <![CDATA[ UUID = #{uuid,jdbcType=VARCHAR} ]]>
     * @param uuid uuid
     * @return List<UserGroupDO>
     */
    List<UserGroupDO> queryByUserGroupUuid(@Param("uuid")String uuid);
}

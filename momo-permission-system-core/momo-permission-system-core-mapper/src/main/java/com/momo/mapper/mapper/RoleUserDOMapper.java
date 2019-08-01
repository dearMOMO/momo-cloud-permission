package com.momo.mapper.mapper;

import com.momo.mapper.dataobject.RoleUserDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * The Table sys_role_user.
 * 角色和用户中间表
 */
public interface RoleUserDOMapper{

    /**
     * desc:插入表:sys_role_user.<br/>
     * descSql =  SELECT LAST_INSERT_ID() <![CDATA[ INSERT INTO sys_role_user( ID ,ROLE_ID ,USER_ID ,GROUP_ID ,UUID ,CREATE_BY ,UPDATE_BY ,FLAG ,DEL_FLAG ,CREATE_TIME ,UPDATE_TIME )VALUES( null , #{roleId,jdbcType=BIGINT} , #{userId,jdbcType=BIGINT} , #{groupId,jdbcType=BIGINT} , #{uuid,jdbcType=VARCHAR} , #{createBy,jdbcType=VARCHAR} , #{updateBy,jdbcType=VARCHAR} , #{flag,jdbcType=INTEGER} , #{delFlag,jdbcType=INTEGER} , #{createTime,jdbcType=TIMESTAMP} , #{updateTime,jdbcType=TIMESTAMP} ) ]]>
     * @param entity entity
     * @return int
     */
    int insert(RoleUserDO entity);
    /**
     * desc:批量插入表:sys_role_user.<br/>
     * descSql =  INSERT INTO sys_role_user( ID ,ROLE_ID ,USER_ID ,GROUP_ID ,UUID ,CREATE_BY ,UPDATE_BY ,FLAG ,DEL_FLAG ,CREATE_TIME ,UPDATE_TIME )VALUES ( null , #{item.roleId,jdbcType=BIGINT} , #{item.userId,jdbcType=BIGINT} , #{item.groupId,jdbcType=BIGINT} , #{item.uuid,jdbcType=VARCHAR} , #{item.createBy,jdbcType=VARCHAR} , #{item.updateBy,jdbcType=VARCHAR} , #{item.flag,jdbcType=INTEGER} , #{item.delFlag,jdbcType=INTEGER} , #{item.createTime,jdbcType=TIMESTAMP} , #{item.updateTime,jdbcType=TIMESTAMP} ) 
     * @param list list
     * @return int
     */
    int insertBatch(List<RoleUserDO> list);
    /**
     * desc:根据主键删除数据:sys_role_user.<br/>
     * descSql =  <![CDATA[ DELETE FROM sys_role_user WHERE ID = #{id,jdbcType=BIGINT} ]]>
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:sys_role_user.<br/>
     * descSql =  SELECT * FROM sys_role_user WHERE <![CDATA[ ID = #{id,jdbcType=BIGINT} ]]>
     * @param id id
     * @return RoleUserDO
     */
    RoleUserDO getById(Long id);
    /**
     * desc:根据普通索引GroupId获取数据:sys_role_user.<br/>
     * descSql =  SELECT * FROM sys_role_user WHERE <![CDATA[ GROUP_ID = #{groupId,jdbcType=BIGINT} ]]>
     * @param groupId groupId
     * @return List<RoleUserDO>
     */
    List<RoleUserDO> queryByGroupId(Long groupId);
    /**
     * desc:根据普通索引RoleId获取数据:sys_role_user.<br/>
     * descSql =  SELECT * FROM sys_role_user WHERE <![CDATA[ ROLE_ID = #{roleId,jdbcType=BIGINT} ]]>
     * @param roleId roleId
     * @return List<RoleUserDO>
     */
    List<RoleUserDO> queryByRoleId(Long roleId);
    /**
     * desc:根据普通索引UserId获取数据:sys_role_user.<br/>
     * descSql =  SELECT * FROM sys_role_user WHERE <![CDATA[ USER_ID = #{userId,jdbcType=BIGINT} ]]>
     * @param userId userId
     * @return List<RoleUserDO>
     */
    List<RoleUserDO> queryByUserId(Long userId);
}

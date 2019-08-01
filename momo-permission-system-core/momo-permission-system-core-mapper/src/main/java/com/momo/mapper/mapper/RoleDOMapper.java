package com.momo.mapper.mapper;

import com.momo.mapper.dataobject.RoleDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * The Table sys_role.
 * 角色
 */
public interface RoleDOMapper{

    /**
     * desc:插入表:sys_role.<br/>
     * descSql =  SELECT LAST_INSERT_ID() <![CDATA[ INSERT INTO sys_role( ID ,GROUP_ID ,UUID ,REMARK ,CREATE_BY ,UPDATE_BY ,SYS_ROLE_NAME ,FLAG ,DEL_FLAG ,SYS_ROLE_TYPE ,CREATE_TIME ,UPDATE_TIME )VALUES( null , #{groupId,jdbcType=BIGINT} , #{uuid,jdbcType=VARCHAR} , #{remark,jdbcType=VARCHAR} , #{createBy,jdbcType=VARCHAR} , #{updateBy,jdbcType=VARCHAR} , #{sysRoleName,jdbcType=VARCHAR} , #{flag,jdbcType=INTEGER} , #{delFlag,jdbcType=INTEGER} , #{sysRoleType,jdbcType=INTEGER} , #{createTime,jdbcType=TIMESTAMP} , #{updateTime,jdbcType=TIMESTAMP} ) ]]>
     * @param entity entity
     * @return int
     */
    int insert(RoleDO entity);
    /**
     * desc:批量插入表:sys_role.<br/>
     * descSql =  INSERT INTO sys_role( ID ,GROUP_ID ,UUID ,REMARK ,CREATE_BY ,UPDATE_BY ,SYS_ROLE_NAME ,FLAG ,DEL_FLAG ,SYS_ROLE_TYPE ,CREATE_TIME ,UPDATE_TIME )VALUES ( null , #{item.groupId,jdbcType=BIGINT} , #{item.uuid,jdbcType=VARCHAR} , #{item.remark,jdbcType=VARCHAR} , #{item.createBy,jdbcType=VARCHAR} , #{item.updateBy,jdbcType=VARCHAR} , #{item.sysRoleName,jdbcType=VARCHAR} , #{item.flag,jdbcType=INTEGER} , #{item.delFlag,jdbcType=INTEGER} , #{item.sysRoleType,jdbcType=INTEGER} , #{item.createTime,jdbcType=TIMESTAMP} , #{item.updateTime,jdbcType=TIMESTAMP} ) 
     * @param list list
     * @return int
     */
    int insertBatch(List<RoleDO> list);
    /**
     * desc:根据主键删除数据:sys_role.<br/>
     * descSql =  <![CDATA[ DELETE FROM sys_role WHERE ID = #{id,jdbcType=BIGINT} ]]>
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:sys_role.<br/>
     * descSql =  SELECT * FROM sys_role WHERE <![CDATA[ ID = #{id,jdbcType=BIGINT} ]]>
     * @param id id
     * @return RoleDO
     */
    RoleDO getById(Long id);
    /**
     * desc:根据普通索引SysRoleUuid获取数据:sys_role.<br/>
     * descSql =  SELECT * FROM sys_role WHERE <![CDATA[ UUID = #{uuid,jdbcType=VARCHAR} ]]>
     * @param uuid uuid
     * @return List<RoleDO>
     */
    List<RoleDO> queryBySysRoleUuid(@Param("uuid")String uuid);
}

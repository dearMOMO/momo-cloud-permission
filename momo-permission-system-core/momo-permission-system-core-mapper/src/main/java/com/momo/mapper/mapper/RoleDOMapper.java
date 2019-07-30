package com.momo.mapper.mapper;

import com.momo.mapper.dataobject.RoleDO;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table SYS_ROLE.
 * 角色
 */
public interface RoleDOMapper{

    /**
     * desc:插入表:SYS_ROLE.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_ROLE( GROUP_ID ,REMARK ,DEL_FLAG ,CREATE_BY ,UPDATE_BY ,SYS_ROLE_NAME ,SYS_ROLE_TYPE ,SYS_ROLE_UUID ,SYS_ROLE_STATUS ,CREATE_TIME ,UPDATE_TIME )VALUES( #{groupId,jdbcType=BIGINT} ,#{remark,jdbcType=VARCHAR} ,#{delFlag,jdbcType=CHAR} ,#{createBy,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{sysRoleName,jdbcType=VARCHAR} ,#{sysRoleType,jdbcType=CHAR} ,#{sysRoleUuid,jdbcType=VARCHAR} ,#{sysRoleStatus,jdbcType=CHAR} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    int insert(RoleDO entity);
    /**
     * desc:更新表:SYS_ROLE.<br/>
     * descSql =  UPDATE SYS_ROLE SET GROUP_ID = #{groupId,jdbcType=BIGINT} ,REMARK = #{remark,jdbcType=VARCHAR} ,DEL_FLAG = #{delFlag,jdbcType=CHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,SYS_ROLE_NAME = #{sysRoleName,jdbcType=VARCHAR} ,SYS_ROLE_TYPE = #{sysRoleType,jdbcType=CHAR} ,SYS_ROLE_UUID = #{sysRoleUuid,jdbcType=VARCHAR} ,SYS_ROLE_STATUS = #{sysRoleStatus,jdbcType=CHAR} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    int update(RoleDO entity);
    /**
     * desc:根据主键删除数据:SYS_ROLE.<br/>
     * descSql =  DELETE FROM SYS_ROLE WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:SYS_ROLE.<br/>
     * descSql =  SELECT * FROM SYS_ROLE WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return RoleDO
     */
    RoleDO getById(Long id);
}

package com.momo.mapper.mapper;

import com.momo.mapper.dataobject.RoleAclDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * The Table sys_role_acl.
 * 角色和权限中间表
 */
public interface RoleAclDOMapper{

    /**
     * desc:插入表:sys_role_acl.<br/>
     * descSql =  SELECT LAST_INSERT_ID() <![CDATA[ INSERT INTO sys_role_acl( ID ,GROUP_ID ,SYS_ACL_ID ,SYS_ROLE_ID ,SYS_ACL_PERMISSION_TYPE ,UUID ,CREATE_BY ,UPDATE_BY ,FLAG ,DEL_FLAG ,CREATE_TIME ,UPDATE_TIME )VALUES( null , #{groupId,jdbcType=BIGINT} , #{sysAclId,jdbcType=BIGINT} , #{sysRoleId,jdbcType=BIGINT} , #{sysAclPermissionType,jdbcType=BIGINT} , #{uuid,jdbcType=VARCHAR} , #{createBy,jdbcType=VARCHAR} , #{updateBy,jdbcType=VARCHAR} , #{flag,jdbcType=INTEGER} , #{delFlag,jdbcType=INTEGER} , #{createTime,jdbcType=TIMESTAMP} , #{updateTime,jdbcType=TIMESTAMP} ) ]]>
     * @param entity entity
     * @return int
     */
    int insert(RoleAclDO entity);
    /**
     * desc:批量插入表:sys_role_acl.<br/>
     * descSql =  INSERT INTO sys_role_acl( ID ,GROUP_ID ,SYS_ACL_ID ,SYS_ROLE_ID ,SYS_ACL_PERMISSION_TYPE ,UUID ,CREATE_BY ,UPDATE_BY ,FLAG ,DEL_FLAG ,CREATE_TIME ,UPDATE_TIME )VALUES ( null , #{item.groupId,jdbcType=BIGINT} , #{item.sysAclId,jdbcType=BIGINT} , #{item.sysRoleId,jdbcType=BIGINT} , #{item.sysAclPermissionType,jdbcType=BIGINT} , #{item.uuid,jdbcType=VARCHAR} , #{item.createBy,jdbcType=VARCHAR} , #{item.updateBy,jdbcType=VARCHAR} , #{item.flag,jdbcType=INTEGER} , #{item.delFlag,jdbcType=INTEGER} , #{item.createTime,jdbcType=TIMESTAMP} , #{item.updateTime,jdbcType=TIMESTAMP} ) 
     * @param list list
     * @return int
     */
    int insertBatch(List<RoleAclDO> list);
    /**
     * desc:根据主键删除数据:sys_role_acl.<br/>
     * descSql =  <![CDATA[ DELETE FROM sys_role_acl WHERE ID = #{id,jdbcType=BIGINT} ]]>
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:sys_role_acl.<br/>
     * descSql =  SELECT * FROM sys_role_acl WHERE <![CDATA[ ID = #{id,jdbcType=BIGINT} ]]>
     * @param id id
     * @return RoleAclDO
     */
    RoleAclDO getById(Long id);
    /**
     * desc:根据普通索引GroupId获取数据:sys_role_acl.<br/>
     * descSql =  SELECT * FROM sys_role_acl WHERE <![CDATA[ GROUP_ID = #{groupId,jdbcType=BIGINT} ]]>
     * @param groupId groupId
     * @return List<RoleAclDO>
     */
    List<RoleAclDO> queryByGroupId(Long groupId);
    /**
     * desc:根据普通索引SysAclId获取数据:sys_role_acl.<br/>
     * descSql =  SELECT * FROM sys_role_acl WHERE <![CDATA[ SYS_ACL_ID = #{sysAclId,jdbcType=BIGINT} ]]>
     * @param sysAclId sysAclId
     * @return List<RoleAclDO>
     */
    List<RoleAclDO> queryBySysAclId(Long sysAclId);
    /**
     * desc:根据普通索引SysAclPermissionType获取数据:sys_role_acl.<br/>
     * descSql =  SELECT * FROM sys_role_acl WHERE <![CDATA[ SYS_ACL_PERMISSION_TYPE = #{sysAclPermissionType,jdbcType=BIGINT} ]]>
     * @param sysAclPermissionType sysAclPermissionType
     * @return List<RoleAclDO>
     */
    List<RoleAclDO> queryBySysAclPermissionType(Long sysAclPermissionType);
    /**
     * desc:根据普通索引SysRoleId获取数据:sys_role_acl.<br/>
     * descSql =  SELECT * FROM sys_role_acl WHERE <![CDATA[ SYS_ROLE_ID = #{sysRoleId,jdbcType=BIGINT} ]]>
     * @param sysRoleId sysRoleId
     * @return List<RoleAclDO>
     */
    List<RoleAclDO> queryBySysRoleId(Long sysRoleId);
}

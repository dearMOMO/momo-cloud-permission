package com.momo.mapper.mapper;

import com.momo.mapper.dataobject.AclDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * The Table sys_acl.
 * 权限点表
 */
public interface AclDOMapper{

    /**
     * desc:插入表:sys_acl.<br/>
     * descSql =  SELECT LAST_INSERT_ID() <![CDATA[ INSERT INTO sys_acl( ID ,SYS_ACL_PARENT_ID ,SYS_ACL_PERMISSION_TYPE ,UUID ,REMARK ,CREATE_BY ,UPDATE_BY ,SYS_ACL_URL ,SYS_ACL_CODE ,SYS_ACL_ICON ,SYS_ACL_NAME ,SYS_ACL_LEVEL ,SYS_ACL_ACTION ,SYS_ACL_ROUTER ,FLAG ,DEL_FLAG ,SYS_ACL_SEQ ,SYS_ACL_TYPE ,CREATE_TIME ,UPDATE_TIME )VALUES( null , #{sysAclParentId,jdbcType=BIGINT} , #{sysAclPermissionType,jdbcType=BIGINT} , #{uuid,jdbcType=VARCHAR} , #{remark,jdbcType=VARCHAR} , #{createBy,jdbcType=VARCHAR} , #{updateBy,jdbcType=VARCHAR} , #{sysAclUrl,jdbcType=VARCHAR} , #{sysAclCode,jdbcType=VARCHAR} , #{sysAclIcon,jdbcType=VARCHAR} , #{sysAclName,jdbcType=VARCHAR} , #{sysAclLevel,jdbcType=VARCHAR} , #{sysAclAction,jdbcType=VARCHAR} , #{sysAclRouter,jdbcType=VARCHAR} , #{flag,jdbcType=INTEGER} , #{delFlag,jdbcType=INTEGER} , #{sysAclSeq,jdbcType=INTEGER} , #{sysAclType,jdbcType=INTEGER} , #{createTime,jdbcType=TIMESTAMP} , #{updateTime,jdbcType=TIMESTAMP} ) ]]>
     * @param entity entity
     * @return int
     */
    int insert(AclDO entity);
    /**
     * desc:批量插入表:sys_acl.<br/>
     * descSql =  INSERT INTO sys_acl( ID ,SYS_ACL_PARENT_ID ,SYS_ACL_PERMISSION_TYPE ,UUID ,REMARK ,CREATE_BY ,UPDATE_BY ,SYS_ACL_URL ,SYS_ACL_CODE ,SYS_ACL_ICON ,SYS_ACL_NAME ,SYS_ACL_LEVEL ,SYS_ACL_ACTION ,SYS_ACL_ROUTER ,FLAG ,DEL_FLAG ,SYS_ACL_SEQ ,SYS_ACL_TYPE ,CREATE_TIME ,UPDATE_TIME )VALUES ( null , #{item.sysAclParentId,jdbcType=BIGINT} , #{item.sysAclPermissionType,jdbcType=BIGINT} , #{item.uuid,jdbcType=VARCHAR} , #{item.remark,jdbcType=VARCHAR} , #{item.createBy,jdbcType=VARCHAR} , #{item.updateBy,jdbcType=VARCHAR} , #{item.sysAclUrl,jdbcType=VARCHAR} , #{item.sysAclCode,jdbcType=VARCHAR} , #{item.sysAclIcon,jdbcType=VARCHAR} , #{item.sysAclName,jdbcType=VARCHAR} , #{item.sysAclLevel,jdbcType=VARCHAR} , #{item.sysAclAction,jdbcType=VARCHAR} , #{item.sysAclRouter,jdbcType=VARCHAR} , #{item.flag,jdbcType=INTEGER} , #{item.delFlag,jdbcType=INTEGER} , #{item.sysAclSeq,jdbcType=INTEGER} , #{item.sysAclType,jdbcType=INTEGER} , #{item.createTime,jdbcType=TIMESTAMP} , #{item.updateTime,jdbcType=TIMESTAMP} ) 
     * @param list list
     * @return int
     */
    int insertBatch(List<AclDO> list);
    /**
     * desc:根据主键删除数据:sys_acl.<br/>
     * descSql =  <![CDATA[ DELETE FROM sys_acl WHERE ID = #{id,jdbcType=BIGINT} ]]>
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:sys_acl.<br/>
     * descSql =  SELECT * FROM sys_acl WHERE <![CDATA[ ID = #{id,jdbcType=BIGINT} ]]>
     * @param id id
     * @return AclDO
     */
    AclDO getById(Long id);
    /**
     * desc:根据普通索引SysAclModuleId获取数据:sys_acl.<br/>
     * descSql =  SELECT * FROM sys_acl WHERE <![CDATA[ SYS_ACL_PARENT_ID = #{sysAclParentId,jdbcType=BIGINT} ]]>
     * @param sysAclParentId sysAclParentId
     * @return List<AclDO>
     */
    List<AclDO> queryBySysAclModuleId(Long sysAclParentId);
    /**
     * desc:根据普通索引SysAclUuid获取数据:sys_acl.<br/>
     * descSql =  SELECT * FROM sys_acl WHERE <![CDATA[ UUID = #{uuid,jdbcType=VARCHAR} ]]>
     * @param uuid uuid
     * @return List<AclDO>
     */
    List<AclDO> queryBySysAclUuid(@Param("uuid")String uuid);
}

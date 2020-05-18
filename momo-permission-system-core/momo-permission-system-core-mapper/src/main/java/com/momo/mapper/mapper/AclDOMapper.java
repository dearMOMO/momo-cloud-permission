package com.momo.mapper.mapper;

import com.momo.mapper.dataobject.AclDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * The Table sys_acl.
 * 操作权限-权限点表
 */
public interface AclDOMapper{

    /**
     * desc:插入表:sys_acl.<br/>
     * descSql =  INSERT INTO sys_acl id , sys_acl_parent_id , uuid , remark , create_by , update_by , sys_acl_url , sys_acl_code , sys_acl_icon , sys_acl_name , sys_acl_level , sys_acl_action , sys_acl_router , sys_acl_component_name , sys_acl_permission_code , del_flag , sys_acl_seq , sys_acl_type , sys_acl_frame , disabled_flag , create_time , update_time , #{id,jdbcType=BIGINT} , #{sysAclParentId,jdbcType=BIGINT} , #{uuid,jdbcType=VARCHAR} , #{remark,jdbcType=VARCHAR} , #{createBy,jdbcType=VARCHAR} , #{updateBy,jdbcType=VARCHAR} , #{sysAclUrl,jdbcType=VARCHAR} , #{sysAclCode,jdbcType=VARCHAR} , #{sysAclIcon,jdbcType=VARCHAR} , #{sysAclName,jdbcType=VARCHAR} , #{sysAclLevel,jdbcType=VARCHAR} , #{sysAclAction,jdbcType=VARCHAR} , #{sysAclRouter,jdbcType=VARCHAR} , #{sysAclComponentName,jdbcType=VARCHAR} , #{sysAclPermissionCode,jdbcType=VARCHAR} , #{delFlag,jdbcType=INTEGER} , #{sysAclSeq,jdbcType=INTEGER} , #{sysAclType,jdbcType=INTEGER} , #{sysAclFrame,jdbcType=TINYINT} , #{disabledFlag,jdbcType=INTEGER} , #{createTime,jdbcType=TIMESTAMP} , #{updateTime,jdbcType=TIMESTAMP} , 
     * @param entity entity
     * @return int
     */
    int insertSelect(AclDO entity);
    /**
     * desc:插入表:sys_acl.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO sys_acl id , sys_acl_parent_id , uuid , remark , create_by , update_by , sys_acl_url , sys_acl_code , sys_acl_icon , sys_acl_name , sys_acl_level , sys_acl_action , sys_acl_router , sys_acl_component_name , sys_acl_permission_code , del_flag , sys_acl_seq , sys_acl_type , sys_acl_frame , disabled_flag , create_time , update_time , #{id,jdbcType=BIGINT} , #{sysAclParentId,jdbcType=BIGINT} , #{uuid,jdbcType=VARCHAR} , #{remark,jdbcType=VARCHAR} , #{createBy,jdbcType=VARCHAR} , #{updateBy,jdbcType=VARCHAR} , #{sysAclUrl,jdbcType=VARCHAR} , #{sysAclCode,jdbcType=VARCHAR} , #{sysAclIcon,jdbcType=VARCHAR} , #{sysAclName,jdbcType=VARCHAR} , #{sysAclLevel,jdbcType=VARCHAR} , #{sysAclAction,jdbcType=VARCHAR} , #{sysAclRouter,jdbcType=VARCHAR} , #{sysAclComponentName,jdbcType=VARCHAR} , #{sysAclPermissionCode,jdbcType=VARCHAR} , #{delFlag,jdbcType=INTEGER} , #{sysAclSeq,jdbcType=INTEGER} , #{sysAclType,jdbcType=INTEGER} , #{sysAclFrame,jdbcType=TINYINT} , #{disabledFlag,jdbcType=INTEGER} , #{createTime,jdbcType=TIMESTAMP} , #{updateTime,jdbcType=TIMESTAMP} , 
     * @param entity entity
     * @return int
     */
    int insertSelectReturnId(AclDO entity);
    /**
     * desc:批量插入表:sys_acl.<br/>
     * descSql =  INSERT INTO sys_acl id , sys_acl_parent_id , uuid , remark , create_by , update_by , sys_acl_url , sys_acl_code , sys_acl_icon , sys_acl_name , sys_acl_level , sys_acl_action , sys_acl_router , sys_acl_component_name , sys_acl_permission_code , del_flag , sys_acl_seq , sys_acl_type , sys_acl_frame , disabled_flag , create_time , update_time , #{item.id,jdbcType=BIGINT} , #{item.sysAclParentId,jdbcType=BIGINT} , #{item.uuid,jdbcType=VARCHAR} , #{item.remark,jdbcType=VARCHAR} , #{item.createBy,jdbcType=VARCHAR} , #{item.updateBy,jdbcType=VARCHAR} , #{item.sysAclUrl,jdbcType=VARCHAR} , #{item.sysAclCode,jdbcType=VARCHAR} , #{item.sysAclIcon,jdbcType=VARCHAR} , #{item.sysAclName,jdbcType=VARCHAR} , #{item.sysAclLevel,jdbcType=VARCHAR} , #{item.sysAclAction,jdbcType=VARCHAR} , #{item.sysAclRouter,jdbcType=VARCHAR} , #{item.sysAclComponentName,jdbcType=VARCHAR} , #{item.sysAclPermissionCode,jdbcType=VARCHAR} , #{item.delFlag,jdbcType=INTEGER} , #{item.sysAclSeq,jdbcType=INTEGER} , #{item.sysAclType,jdbcType=INTEGER} , #{item.sysAclFrame,jdbcType=TINYINT} , #{item.disabledFlag,jdbcType=INTEGER} , #{item.createTime,jdbcType=TIMESTAMP} , #{item.updateTime,jdbcType=TIMESTAMP} , 
     * @param list list
     * @return int
     */
    int insertBatchSelect(List<AclDO> list);
    /**
     * desc:插入表:sys_acl.<br/>
     * descSql =  update sys_acl id=#{item.id,jdbcType=BIGINT} , sys_acl_parent_id=#{item.sysAclParentId,jdbcType=BIGINT} , uuid=#{item.uuid,jdbcType=VARCHAR} , remark=#{item.remark,jdbcType=VARCHAR} , create_by=#{item.createBy,jdbcType=VARCHAR} , update_by=#{item.updateBy,jdbcType=VARCHAR} , sys_acl_url=#{item.sysAclUrl,jdbcType=VARCHAR} , sys_acl_code=#{item.sysAclCode,jdbcType=VARCHAR} , sys_acl_icon=#{item.sysAclIcon,jdbcType=VARCHAR} , sys_acl_name=#{item.sysAclName,jdbcType=VARCHAR} , sys_acl_level=#{item.sysAclLevel,jdbcType=VARCHAR} , sys_acl_action=#{item.sysAclAction,jdbcType=VARCHAR} , sys_acl_router=#{item.sysAclRouter,jdbcType=VARCHAR} , sys_acl_component_name=#{item.sysAclComponentName,jdbcType=VARCHAR} , sys_acl_permission_code=#{item.sysAclPermissionCode,jdbcType=VARCHAR} , del_flag=#{item.delFlag,jdbcType=INTEGER} , sys_acl_seq=#{item.sysAclSeq,jdbcType=INTEGER} , sys_acl_type=#{item.sysAclType,jdbcType=INTEGER} , sys_acl_frame=#{item.sysAclFrame,jdbcType=TINYINT} , disabled_flag=#{item.disabledFlag,jdbcType=INTEGER} , create_time=#{item.createTime,jdbcType=TIMESTAMP} , update_time=#{item.updateTime,jdbcType=TIMESTAMP} , WHERE id=#{item.id,jdbcType=BIGINT} 
     * @param list list
     * @return int
     */
    int updateBatchByPrimaryKeySelective(List<AclDO> list);
    /**
     * desc:插入表:sys_acl.<br/>
     * descSql =  update sys_acl id=#{id,jdbcType=BIGINT}, sys_acl_parent_id=#{sysAclParentId,jdbcType=BIGINT}, uuid=#{uuid,jdbcType=VARCHAR}, remark=#{remark,jdbcType=VARCHAR}, create_by=#{createBy,jdbcType=VARCHAR}, update_by=#{updateBy,jdbcType=VARCHAR}, sys_acl_url=#{sysAclUrl,jdbcType=VARCHAR}, sys_acl_code=#{sysAclCode,jdbcType=VARCHAR}, sys_acl_icon=#{sysAclIcon,jdbcType=VARCHAR}, sys_acl_name=#{sysAclName,jdbcType=VARCHAR}, sys_acl_level=#{sysAclLevel,jdbcType=VARCHAR}, sys_acl_action=#{sysAclAction,jdbcType=VARCHAR}, sys_acl_router=#{sysAclRouter,jdbcType=VARCHAR}, sys_acl_component_name=#{sysAclComponentName,jdbcType=VARCHAR}, sys_acl_permission_code=#{sysAclPermissionCode,jdbcType=VARCHAR}, del_flag=#{delFlag,jdbcType=INTEGER}, sys_acl_seq=#{sysAclSeq,jdbcType=INTEGER}, sys_acl_type=#{sysAclType,jdbcType=INTEGER}, sys_acl_frame=#{sysAclFrame,jdbcType=TINYINT}, disabled_flag=#{disabledFlag,jdbcType=INTEGER}, create_time=#{createTime,jdbcType=TIMESTAMP}, update_time=#{updateTime,jdbcType=TIMESTAMP}, WHERE id=#{id,jdbcType=BIGINT} 
     * @param entity entity
     * @return int
     */
    int updateByPrimaryKeySelective(AclDO entity);
    /**
     * desc:根据主键删除数据:sys_acl.<br/>
     * descSql =  DELETE FROM sys_acl WHERE id = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:sys_acl.<br/>
     * descSql =  SELECT * FROM sys_acl WHERE id = #{id,jdbcType=BIGINT}
     * @param id id
     * @return AclDO
     */
    AclDO getById(Long id);
    /**
     * desc:根据普通索引SysAclLevel获取数据:sys_acl.<br/>
     * descSql =  SELECT * FROM sys_acl WHERE SYS_ACL_LEVEL = #{sysAclLevel,jdbcType=VARCHAR}
     * @param sysAclLevel sysAclLevel
     * @return List<AclDO>
     */
    List<AclDO> queryBySysAclLevel(@Param("sysAclLevel")String sysAclLevel);
    /**
     * desc:根据普通索引SysAclModuleId获取数据:sys_acl.<br/>
     * descSql =  SELECT * FROM sys_acl WHERE SYS_ACL_PARENT_ID = #{sysAclParentId,jdbcType=BIGINT}
     * @param sysAclParentId sysAclParentId
     * @return List<AclDO>
     */
    List<AclDO> queryBySysAclModuleId(Long sysAclParentId);
    /**
     * desc:根据普通索引SysAclUuid获取数据:sys_acl.<br/>
     * descSql =  SELECT * FROM sys_acl WHERE UUID = #{uuid,jdbcType=VARCHAR}
     * @param uuid uuid
     * @return List<AclDO>
     */
    List<AclDO> queryBySysAclUuid(@Param("uuid")String uuid);
}

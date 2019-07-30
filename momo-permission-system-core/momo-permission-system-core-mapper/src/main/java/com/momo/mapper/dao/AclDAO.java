package com.momo.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.momo.mapper.dataobject.AclDO;
import com.momo.mapper.mapper.AclDOMapper;

/**
* The Table SYS_ACL.
* 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
* 权限点表
*/
@Repository
public class AclDAO{

    @Autowired
    private AclDOMapper aclDOMapper;

    /**
     * desc:插入表:SYS_ACL.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_ACL( SYS_ACL_MODULE_ID ,SYS_ACL_PERMISSION_TYPE ,NAME ,REMARK ,DEL_FLAG ,CREATE_BY ,UPDATE_BY ,SYS_ACL_URL ,SYS_ACL_CODE ,SYS_ACL_ICON ,SYS_ACL_NAME ,SYS_ACL_TYPE ,SYS_ACL_UUID ,SYS_ACL_ACTION ,SYS_ACL_ROUTER ,SYS_ACL_STATUS ,SYS_ACL_SEQ ,CREATE_TIME ,UPDATE_TIME )VALUES( #{sysAclModuleId,jdbcType=BIGINT} ,#{sysAclPermissionType,jdbcType=BIGINT} ,#{name,jdbcType=VARCHAR} ,#{remark,jdbcType=VARCHAR} ,#{delFlag,jdbcType=CHAR} ,#{createBy,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{sysAclUrl,jdbcType=VARCHAR} ,#{sysAclCode,jdbcType=VARCHAR} ,#{sysAclIcon,jdbcType=VARCHAR} ,#{sysAclName,jdbcType=VARCHAR} ,#{sysAclType,jdbcType=CHAR} ,#{sysAclUuid,jdbcType=VARCHAR} ,#{sysAclAction,jdbcType=VARCHAR} ,#{sysAclRouter,jdbcType=VARCHAR} ,#{sysAclStatus,jdbcType=CHAR} ,#{sysAclSeq,jdbcType=INTEGER} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    public int insert(AclDO entity){
        return aclDOMapper.insert(entity);
    }
    /**
     * desc:更新表:SYS_ACL.<br/>
     * descSql =  UPDATE SYS_ACL SET SYS_ACL_MODULE_ID = #{sysAclModuleId,jdbcType=BIGINT} ,SYS_ACL_PERMISSION_TYPE = #{sysAclPermissionType,jdbcType=BIGINT} ,NAME = #{name,jdbcType=VARCHAR} ,REMARK = #{remark,jdbcType=VARCHAR} ,DEL_FLAG = #{delFlag,jdbcType=CHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,SYS_ACL_URL = #{sysAclUrl,jdbcType=VARCHAR} ,SYS_ACL_CODE = #{sysAclCode,jdbcType=VARCHAR} ,SYS_ACL_ICON = #{sysAclIcon,jdbcType=VARCHAR} ,SYS_ACL_NAME = #{sysAclName,jdbcType=VARCHAR} ,SYS_ACL_TYPE = #{sysAclType,jdbcType=CHAR} ,SYS_ACL_UUID = #{sysAclUuid,jdbcType=VARCHAR} ,SYS_ACL_ACTION = #{sysAclAction,jdbcType=VARCHAR} ,SYS_ACL_ROUTER = #{sysAclRouter,jdbcType=VARCHAR} ,SYS_ACL_STATUS = #{sysAclStatus,jdbcType=CHAR} ,SYS_ACL_SEQ = #{sysAclSeq,jdbcType=INTEGER} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    public int update(AclDO entity){
        return aclDOMapper.update(entity);
    }
    /**
     * desc:根据主键删除数据:SYS_ACL.<br/>
     * descSql =  DELETE FROM SYS_ACL WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return aclDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:SYS_ACL.<br/>
     * descSql =  SELECT * FROM SYS_ACL WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return AclDO
     */
    public AclDO getById(Long id){
        return aclDOMapper.getById(id);
    }
}

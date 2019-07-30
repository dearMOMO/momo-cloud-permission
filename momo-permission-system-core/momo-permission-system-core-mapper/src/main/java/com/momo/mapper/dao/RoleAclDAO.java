package com.momo.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.momo.mapper.dataobject.RoleAclDO;
import com.momo.mapper.mapper.RoleAclDOMapper;

/**
* The Table SYS_ROLE_ACL.
* 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
* 角色和权限中间表
*/
@Repository
public class RoleAclDAO{

    @Autowired
    private RoleAclDOMapper roleAclDOMapper;

    /**
     * desc:插入表:SYS_ROLE_ACL.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_ROLE_ACL( GROUP_ID ,SYS_ACL_ID ,SYS_ROLE_ID ,SYS_ACL_PERMISSION_TYPE ,DEL_FLAG ,CREATE_BY ,UPDATE_BY ,SYS_ROLE_ACL_UUID ,CREATE_TIME ,UPDATE_TIME )VALUES( #{groupId,jdbcType=BIGINT} ,#{sysAclId,jdbcType=BIGINT} ,#{sysRoleId,jdbcType=BIGINT} ,#{sysAclPermissionType,jdbcType=BIGINT} ,#{delFlag,jdbcType=CHAR} ,#{createBy,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{sysRoleAclUuid,jdbcType=VARCHAR} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    public int insert(RoleAclDO entity){
        return roleAclDOMapper.insert(entity);
    }
    /**
     * desc:更新表:SYS_ROLE_ACL.<br/>
     * descSql =  UPDATE SYS_ROLE_ACL SET GROUP_ID = #{groupId,jdbcType=BIGINT} ,SYS_ACL_ID = #{sysAclId,jdbcType=BIGINT} ,SYS_ROLE_ID = #{sysRoleId,jdbcType=BIGINT} ,SYS_ACL_PERMISSION_TYPE = #{sysAclPermissionType,jdbcType=BIGINT} ,DEL_FLAG = #{delFlag,jdbcType=CHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,SYS_ROLE_ACL_UUID = #{sysRoleAclUuid,jdbcType=VARCHAR} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    public int update(RoleAclDO entity){
        return roleAclDOMapper.update(entity);
    }
    /**
     * desc:根据主键删除数据:SYS_ROLE_ACL.<br/>
     * descSql =  DELETE FROM SYS_ROLE_ACL WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return roleAclDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:SYS_ROLE_ACL.<br/>
     * descSql =  SELECT * FROM SYS_ROLE_ACL WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return RoleAclDO
     */
    public RoleAclDO getById(Long id){
        return roleAclDOMapper.getById(id);
    }
}

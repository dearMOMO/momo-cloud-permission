package com.momo.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.momo.mapper.dataobject.RoleDO;
import com.momo.mapper.mapper.RoleDOMapper;

/**
* The Table SYS_ROLE.
* 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
* 角色
*/
@Repository
public class RoleDAO{

    @Autowired
    private RoleDOMapper roleDOMapper;

    /**
     * desc:插入表:SYS_ROLE.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_ROLE( GROUP_ID ,REMARK ,DEL_FLAG ,CREATE_BY ,UPDATE_BY ,SYS_ROLE_NAME ,SYS_ROLE_TYPE ,SYS_ROLE_UUID ,SYS_ROLE_STATUS ,CREATE_TIME ,UPDATE_TIME )VALUES( #{groupId,jdbcType=BIGINT} ,#{remark,jdbcType=VARCHAR} ,#{delFlag,jdbcType=CHAR} ,#{createBy,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{sysRoleName,jdbcType=VARCHAR} ,#{sysRoleType,jdbcType=CHAR} ,#{sysRoleUuid,jdbcType=VARCHAR} ,#{sysRoleStatus,jdbcType=CHAR} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    public int insert(RoleDO entity){
        return roleDOMapper.insert(entity);
    }
    /**
     * desc:更新表:SYS_ROLE.<br/>
     * descSql =  UPDATE SYS_ROLE SET GROUP_ID = #{groupId,jdbcType=BIGINT} ,REMARK = #{remark,jdbcType=VARCHAR} ,DEL_FLAG = #{delFlag,jdbcType=CHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,SYS_ROLE_NAME = #{sysRoleName,jdbcType=VARCHAR} ,SYS_ROLE_TYPE = #{sysRoleType,jdbcType=CHAR} ,SYS_ROLE_UUID = #{sysRoleUuid,jdbcType=VARCHAR} ,SYS_ROLE_STATUS = #{sysRoleStatus,jdbcType=CHAR} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    public int update(RoleDO entity){
        return roleDOMapper.update(entity);
    }
    /**
     * desc:根据主键删除数据:SYS_ROLE.<br/>
     * descSql =  DELETE FROM SYS_ROLE WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return roleDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:SYS_ROLE.<br/>
     * descSql =  SELECT * FROM SYS_ROLE WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return RoleDO
     */
    public RoleDO getById(Long id){
        return roleDOMapper.getById(id);
    }
}

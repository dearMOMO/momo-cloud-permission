package com.momo.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.momo.mapper.dataobject.RoleUserDO;
import com.momo.mapper.mapper.RoleUserDOMapper;

/**
* The Table SYS_ROLE_USER.
* 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
* 角色和用户中间表
*/
@Repository
public class RoleUserDAO{

    @Autowired
    private RoleUserDOMapper roleUserDOMapper;

    /**
     * desc:插入表:SYS_ROLE_USER.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_ROLE_USER( ROLE_ID ,USER_ID ,GROUP_ID ,DEL_FLAG ,CREATE_BY ,UPDATE_BY ,SYS_ROLE_USER_UUID ,CREATE_TIME ,UPDATE_TIME )VALUES( #{roleId,jdbcType=BIGINT} ,#{userId,jdbcType=BIGINT} ,#{groupId,jdbcType=BIGINT} ,#{delFlag,jdbcType=CHAR} ,#{createBy,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{sysRoleUserUuid,jdbcType=VARCHAR} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    public int insert(RoleUserDO entity){
        return roleUserDOMapper.insert(entity);
    }
    /**
     * desc:更新表:SYS_ROLE_USER.<br/>
     * descSql =  UPDATE SYS_ROLE_USER SET ROLE_ID = #{roleId,jdbcType=BIGINT} ,USER_ID = #{userId,jdbcType=BIGINT} ,GROUP_ID = #{groupId,jdbcType=BIGINT} ,DEL_FLAG = #{delFlag,jdbcType=CHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,SYS_ROLE_USER_UUID = #{sysRoleUserUuid,jdbcType=VARCHAR} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    public int update(RoleUserDO entity){
        return roleUserDOMapper.update(entity);
    }
    /**
     * desc:根据主键删除数据:SYS_ROLE_USER.<br/>
     * descSql =  DELETE FROM SYS_ROLE_USER WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return roleUserDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:SYS_ROLE_USER.<br/>
     * descSql =  SELECT * FROM SYS_ROLE_USER WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return RoleUserDO
     */
    public RoleUserDO getById(Long id){
        return roleUserDOMapper.getById(id);
    }
}

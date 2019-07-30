package com.momo.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.momo.mapper.dataobject.UserDeptDO;
import com.momo.mapper.mapper.UserDeptDOMapper;

/**
* The Table SYS_USER_DEPT.
* 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
* 用户和部门
*/
@Repository
public class UserDeptDAO{

    @Autowired
    private UserDeptDOMapper userDeptDOMapper;

    /**
     * desc:插入表:SYS_USER_DEPT.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_USER_DEPT( SYS_DEPT_ID ,SYS_USER_ID ,CREATE_BY ,UPDATE_BY ,STATE ,DEL_FLAG ,CREATE_TIME ,UPDATE_TIME )VALUES( #{sysDeptId,jdbcType=BIGINT} ,#{sysUserId,jdbcType=BIGINT} ,#{createBy,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{state,jdbcType=INTEGER} ,#{delFlag,jdbcType=INTEGER} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    public int insert(UserDeptDO entity){
        return userDeptDOMapper.insert(entity);
    }
    /**
     * desc:更新表:SYS_USER_DEPT.<br/>
     * descSql =  UPDATE SYS_USER_DEPT SET SYS_DEPT_ID = #{sysDeptId,jdbcType=BIGINT} ,SYS_USER_ID = #{sysUserId,jdbcType=BIGINT} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,STATE = #{state,jdbcType=INTEGER} ,DEL_FLAG = #{delFlag,jdbcType=INTEGER} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    public int update(UserDeptDO entity){
        return userDeptDOMapper.update(entity);
    }
    /**
     * desc:根据主键删除数据:SYS_USER_DEPT.<br/>
     * descSql =  DELETE FROM SYS_USER_DEPT WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return userDeptDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:SYS_USER_DEPT.<br/>
     * descSql =  SELECT * FROM SYS_USER_DEPT WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return UserDeptDO
     */
    public UserDeptDO getById(Long id){
        return userDeptDOMapper.getById(id);
    }
}

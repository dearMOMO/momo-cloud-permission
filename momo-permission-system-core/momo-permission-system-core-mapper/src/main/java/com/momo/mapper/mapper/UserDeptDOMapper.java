package com.momo.mapper.mapper;

import com.momo.mapper.dataobject.UserDeptDO;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table SYS_USER_DEPT.
 * 用户和部门
 */
public interface UserDeptDOMapper{

    /**
     * desc:插入表:SYS_USER_DEPT.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_USER_DEPT( SYS_DEPT_ID ,SYS_USER_ID ,CREATE_BY ,UPDATE_BY ,STATE ,DEL_FLAG ,CREATE_TIME ,UPDATE_TIME )VALUES( #{sysDeptId,jdbcType=BIGINT} ,#{sysUserId,jdbcType=BIGINT} ,#{createBy,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{state,jdbcType=INTEGER} ,#{delFlag,jdbcType=INTEGER} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    int insert(UserDeptDO entity);
    /**
     * desc:更新表:SYS_USER_DEPT.<br/>
     * descSql =  UPDATE SYS_USER_DEPT SET SYS_DEPT_ID = #{sysDeptId,jdbcType=BIGINT} ,SYS_USER_ID = #{sysUserId,jdbcType=BIGINT} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,STATE = #{state,jdbcType=INTEGER} ,DEL_FLAG = #{delFlag,jdbcType=INTEGER} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    int update(UserDeptDO entity);
    /**
     * desc:根据主键删除数据:SYS_USER_DEPT.<br/>
     * descSql =  DELETE FROM SYS_USER_DEPT WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:SYS_USER_DEPT.<br/>
     * descSql =  SELECT * FROM SYS_USER_DEPT WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return UserDeptDO
     */
    UserDeptDO getById(Long id);
}

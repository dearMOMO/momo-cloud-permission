package com.momo.mapper.mapper;

import com.momo.mapper.dataobject.UserDeptDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * The Table sys_user_dept.
 * 用户和部门
 */
public interface UserDeptDOMapper{

    /**
     * desc:插入表:sys_user_dept.<br/>
     * descSql =  SELECT LAST_INSERT_ID() <![CDATA[ INSERT INTO sys_user_dept( ID ,SYS_DEPT_ID ,SYS_USER_ID ,UUID ,CREATE_BY ,UPDATE_BY ,FLAG ,DEL_FLAG ,CREATE_TIME ,UPDATE_TIME )VALUES( null , #{sysDeptId,jdbcType=BIGINT} , #{sysUserId,jdbcType=BIGINT} , #{uuid,jdbcType=VARCHAR} , #{createBy,jdbcType=VARCHAR} , #{updateBy,jdbcType=VARCHAR} , #{flag,jdbcType=INTEGER} , #{delFlag,jdbcType=INTEGER} , #{createTime,jdbcType=TIMESTAMP} , #{updateTime,jdbcType=TIMESTAMP} ) ]]>
     * @param entity entity
     * @return int
     */
    int insert(UserDeptDO entity);
    /**
     * desc:批量插入表:sys_user_dept.<br/>
     * descSql =  INSERT INTO sys_user_dept( ID ,SYS_DEPT_ID ,SYS_USER_ID ,UUID ,CREATE_BY ,UPDATE_BY ,FLAG ,DEL_FLAG ,CREATE_TIME ,UPDATE_TIME )VALUES ( null , #{item.sysDeptId,jdbcType=BIGINT} , #{item.sysUserId,jdbcType=BIGINT} , #{item.uuid,jdbcType=VARCHAR} , #{item.createBy,jdbcType=VARCHAR} , #{item.updateBy,jdbcType=VARCHAR} , #{item.flag,jdbcType=INTEGER} , #{item.delFlag,jdbcType=INTEGER} , #{item.createTime,jdbcType=TIMESTAMP} , #{item.updateTime,jdbcType=TIMESTAMP} ) 
     * @param list list
     * @return int
     */
    int insertBatch(List<UserDeptDO> list);
    /**
     * desc:根据主键删除数据:sys_user_dept.<br/>
     * descSql =  <![CDATA[ DELETE FROM sys_user_dept WHERE ID = #{id,jdbcType=BIGINT} ]]>
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:sys_user_dept.<br/>
     * descSql =  SELECT * FROM sys_user_dept WHERE <![CDATA[ ID = #{id,jdbcType=BIGINT} ]]>
     * @param id id
     * @return UserDeptDO
     */
    UserDeptDO getById(Long id);
}

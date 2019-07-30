package com.momo.mapper.mapper;

import com.momo.mapper.dataobject.DeptDO;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table SYS_DEPT.
 * 部门表
 */
public interface DeptDOMapper{

    /**
     * desc:插入表:SYS_DEPT.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_DEPT( GROUP_ID ,SYS_DEPT_PARENT_ID ,DEL_FLAG ,CREATE_BY ,UPDATE_BY ,SYS_IS_LEAF ,SYS_DEPT_NAME ,SYS_DEPT_UUID ,SYS_DEPT_LEVEL ,SYS_DEPT_REMARK ,SYS_DEPT_STATUS ,SYS_DEPT_SEQ ,CREATE_TIME ,UPDATE_TIME )VALUES( #{groupId,jdbcType=BIGINT} ,#{sysDeptParentId,jdbcType=BIGINT} ,#{delFlag,jdbcType=CHAR} ,#{createBy,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{sysIsLeaf,jdbcType=CHAR} ,#{sysDeptName,jdbcType=VARCHAR} ,#{sysDeptUuid,jdbcType=VARCHAR} ,#{sysDeptLevel,jdbcType=VARCHAR} ,#{sysDeptRemark,jdbcType=VARCHAR} ,#{sysDeptStatus,jdbcType=CHAR} ,#{sysDeptSeq,jdbcType=INTEGER} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    int insert(DeptDO entity);
    /**
     * desc:更新表:SYS_DEPT.<br/>
     * descSql =  UPDATE SYS_DEPT SET GROUP_ID = #{groupId,jdbcType=BIGINT} ,SYS_DEPT_PARENT_ID = #{sysDeptParentId,jdbcType=BIGINT} ,DEL_FLAG = #{delFlag,jdbcType=CHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,SYS_IS_LEAF = #{sysIsLeaf,jdbcType=CHAR} ,SYS_DEPT_NAME = #{sysDeptName,jdbcType=VARCHAR} ,SYS_DEPT_UUID = #{sysDeptUuid,jdbcType=VARCHAR} ,SYS_DEPT_LEVEL = #{sysDeptLevel,jdbcType=VARCHAR} ,SYS_DEPT_REMARK = #{sysDeptRemark,jdbcType=VARCHAR} ,SYS_DEPT_STATUS = #{sysDeptStatus,jdbcType=CHAR} ,SYS_DEPT_SEQ = #{sysDeptSeq,jdbcType=INTEGER} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    int update(DeptDO entity);
    /**
     * desc:根据主键删除数据:SYS_DEPT.<br/>
     * descSql =  DELETE FROM SYS_DEPT WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:SYS_DEPT.<br/>
     * descSql =  SELECT * FROM SYS_DEPT WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return DeptDO
     */
    DeptDO getById(Long id);
}

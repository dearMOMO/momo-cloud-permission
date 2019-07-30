package com.momo.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.momo.mapper.dataobject.DeptDO;
import com.momo.mapper.mapper.DeptDOMapper;

/**
* The Table SYS_DEPT.
* 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
* 部门表
*/
@Repository
public class DeptDAO{

    @Autowired
    private DeptDOMapper deptDOMapper;

    /**
     * desc:插入表:SYS_DEPT.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_DEPT( GROUP_ID ,SYS_DEPT_PARENT_ID ,DEL_FLAG ,CREATE_BY ,UPDATE_BY ,SYS_IS_LEAF ,SYS_DEPT_NAME ,SYS_DEPT_UUID ,SYS_DEPT_LEVEL ,SYS_DEPT_REMARK ,SYS_DEPT_STATUS ,SYS_DEPT_SEQ ,CREATE_TIME ,UPDATE_TIME )VALUES( #{groupId,jdbcType=BIGINT} ,#{sysDeptParentId,jdbcType=BIGINT} ,#{delFlag,jdbcType=CHAR} ,#{createBy,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{sysIsLeaf,jdbcType=CHAR} ,#{sysDeptName,jdbcType=VARCHAR} ,#{sysDeptUuid,jdbcType=VARCHAR} ,#{sysDeptLevel,jdbcType=VARCHAR} ,#{sysDeptRemark,jdbcType=VARCHAR} ,#{sysDeptStatus,jdbcType=CHAR} ,#{sysDeptSeq,jdbcType=INTEGER} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    public int insert(DeptDO entity){
        return deptDOMapper.insert(entity);
    }
    /**
     * desc:更新表:SYS_DEPT.<br/>
     * descSql =  UPDATE SYS_DEPT SET GROUP_ID = #{groupId,jdbcType=BIGINT} ,SYS_DEPT_PARENT_ID = #{sysDeptParentId,jdbcType=BIGINT} ,DEL_FLAG = #{delFlag,jdbcType=CHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,SYS_IS_LEAF = #{sysIsLeaf,jdbcType=CHAR} ,SYS_DEPT_NAME = #{sysDeptName,jdbcType=VARCHAR} ,SYS_DEPT_UUID = #{sysDeptUuid,jdbcType=VARCHAR} ,SYS_DEPT_LEVEL = #{sysDeptLevel,jdbcType=VARCHAR} ,SYS_DEPT_REMARK = #{sysDeptRemark,jdbcType=VARCHAR} ,SYS_DEPT_STATUS = #{sysDeptStatus,jdbcType=CHAR} ,SYS_DEPT_SEQ = #{sysDeptSeq,jdbcType=INTEGER} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    public int update(DeptDO entity){
        return deptDOMapper.update(entity);
    }
    /**
     * desc:根据主键删除数据:SYS_DEPT.<br/>
     * descSql =  DELETE FROM SYS_DEPT WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return deptDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:SYS_DEPT.<br/>
     * descSql =  SELECT * FROM SYS_DEPT WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return DeptDO
     */
    public DeptDO getById(Long id){
        return deptDOMapper.getById(id);
    }
}

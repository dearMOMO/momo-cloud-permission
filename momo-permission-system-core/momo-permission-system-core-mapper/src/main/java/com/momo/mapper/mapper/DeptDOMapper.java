package com.momo.mapper.mapper;

import com.momo.mapper.dataobject.DeptDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * The Table sys_dept.
 * 部门表
 */
public interface DeptDOMapper{

    /**
     * desc:插入表:sys_dept.<br/>
     * descSql =  SELECT LAST_INSERT_ID() <![CDATA[ INSERT INTO sys_dept( ID ,GROUP_ID ,SYS_DEPT_PARENT_ID ,UUID ,REMARK ,CREATE_BY ,UPDATE_BY ,SYS_DEPT_NAME ,SYS_DEPT_LEVEL ,FLAG ,DEL_FLAG ,SYS_IS_LEAF ,SYS_DEPT_SEQ ,CREATE_TIME ,UPDATE_TIME )VALUES( null , #{groupId,jdbcType=BIGINT} , #{sysDeptParentId,jdbcType=BIGINT} , #{uuid,jdbcType=VARCHAR} , #{remark,jdbcType=VARCHAR} , #{createBy,jdbcType=VARCHAR} , #{updateBy,jdbcType=VARCHAR} , #{sysDeptName,jdbcType=VARCHAR} , #{sysDeptLevel,jdbcType=VARCHAR} , #{flag,jdbcType=INTEGER} , #{delFlag,jdbcType=INTEGER} , #{sysIsLeaf,jdbcType=INTEGER} , #{sysDeptSeq,jdbcType=INTEGER} , #{createTime,jdbcType=TIMESTAMP} , #{updateTime,jdbcType=TIMESTAMP} ) ]]>
     * @param entity entity
     * @return int
     */
    int insert(DeptDO entity);
    /**
     * desc:批量插入表:sys_dept.<br/>
     * descSql =  INSERT INTO sys_dept( ID ,GROUP_ID ,SYS_DEPT_PARENT_ID ,UUID ,REMARK ,CREATE_BY ,UPDATE_BY ,SYS_DEPT_NAME ,SYS_DEPT_LEVEL ,FLAG ,DEL_FLAG ,SYS_IS_LEAF ,SYS_DEPT_SEQ ,CREATE_TIME ,UPDATE_TIME )VALUES ( null , #{item.groupId,jdbcType=BIGINT} , #{item.sysDeptParentId,jdbcType=BIGINT} , #{item.uuid,jdbcType=VARCHAR} , #{item.remark,jdbcType=VARCHAR} , #{item.createBy,jdbcType=VARCHAR} , #{item.updateBy,jdbcType=VARCHAR} , #{item.sysDeptName,jdbcType=VARCHAR} , #{item.sysDeptLevel,jdbcType=VARCHAR} , #{item.flag,jdbcType=INTEGER} , #{item.delFlag,jdbcType=INTEGER} , #{item.sysIsLeaf,jdbcType=INTEGER} , #{item.sysDeptSeq,jdbcType=INTEGER} , #{item.createTime,jdbcType=TIMESTAMP} , #{item.updateTime,jdbcType=TIMESTAMP} ) 
     * @param list list
     * @return int
     */
    int insertBatch(List<DeptDO> list);
    /**
     * desc:根据主键删除数据:sys_dept.<br/>
     * descSql =  <![CDATA[ DELETE FROM sys_dept WHERE ID = #{id,jdbcType=BIGINT} ]]>
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:sys_dept.<br/>
     * descSql =  SELECT * FROM sys_dept WHERE <![CDATA[ ID = #{id,jdbcType=BIGINT} ]]>
     * @param id id
     * @return DeptDO
     */
    DeptDO getById(Long id);
    /**
     * desc:根据普通索引SysDeptUuid获取数据:sys_dept.<br/>
     * descSql =  SELECT * FROM sys_dept WHERE <![CDATA[ UUID = #{uuid,jdbcType=VARCHAR} ]]>
     * @param uuid uuid
     * @return List<DeptDO>
     */
    List<DeptDO> queryBySysDeptUuid(@Param("uuid")String uuid);
}

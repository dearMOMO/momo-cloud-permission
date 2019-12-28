package com.momo.mapper.mapper;

import com.momo.mapper.dataobject.DataDictDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * The Table sys_data_dict.
 * 数据字典表
 */
public interface DataDictDOMapper{

    /**
     * desc:插入表:sys_data_dict.<br/>
     * descSql =  SELECT LAST_INSERT_ID() <![CDATA[ INSERT INTO sys_data_dict( ID ,SYS_DICT_CODE_PARENT_ID ,REMARK ,CREATE_BY ,UPDATE_BY ,SYS_DICT_CODE_NAME ,SYS_DICT_CODE_LEVEL ,SYS_DICT_CODE_VALUE ,SYS_DICT_CODE_PARENT_VALUE ,DEL_FLAG ,DISABLED_FLAG ,SYS_DICT_CODE_SEQ ,CREATE_TIME ,UPDATE_TIME )VALUES( null , #{sysDictCodeParentId,jdbcType=BIGINT} , #{remark,jdbcType=VARCHAR} , #{createBy,jdbcType=VARCHAR} , #{updateBy,jdbcType=VARCHAR} , #{sysDictCodeName,jdbcType=VARCHAR} , #{sysDictCodeLevel,jdbcType=VARCHAR} , #{sysDictCodeValue,jdbcType=VARCHAR} , #{sysDictCodeParentValue,jdbcType=VARCHAR} , #{delFlag,jdbcType=INTEGER} , #{disabledFlag,jdbcType=INTEGER} , #{sysDictCodeSeq,jdbcType=INTEGER} , #{createTime,jdbcType=TIMESTAMP} , #{updateTime,jdbcType=TIMESTAMP} ) ]]>
     * @param entity entity
     * @return int
     */
    int insert(DataDictDO entity);
    /**
     * desc:批量插入表:sys_data_dict.<br/>
     * descSql =  INSERT INTO sys_data_dict( ID ,SYS_DICT_CODE_PARENT_ID ,REMARK ,CREATE_BY ,UPDATE_BY ,SYS_DICT_CODE_NAME ,SYS_DICT_CODE_LEVEL ,SYS_DICT_CODE_VALUE ,SYS_DICT_CODE_PARENT_VALUE ,DEL_FLAG ,DISABLED_FLAG ,SYS_DICT_CODE_SEQ ,CREATE_TIME ,UPDATE_TIME )VALUES ( null , #{item.sysDictCodeParentId,jdbcType=BIGINT} , #{item.remark,jdbcType=VARCHAR} , #{item.createBy,jdbcType=VARCHAR} , #{item.updateBy,jdbcType=VARCHAR} , #{item.sysDictCodeName,jdbcType=VARCHAR} , #{item.sysDictCodeLevel,jdbcType=VARCHAR} , #{item.sysDictCodeValue,jdbcType=VARCHAR} , #{item.sysDictCodeParentValue,jdbcType=VARCHAR} , #{item.delFlag,jdbcType=INTEGER} , #{item.disabledFlag,jdbcType=INTEGER} , #{item.sysDictCodeSeq,jdbcType=INTEGER} , #{item.createTime,jdbcType=TIMESTAMP} , #{item.updateTime,jdbcType=TIMESTAMP} ) 
     * @param list list
     * @return int
     */
    int insertBatch(List<DataDictDO> list);
    /**
     * desc:根据主键删除数据:sys_data_dict.<br/>
     * descSql =  <![CDATA[ DELETE FROM sys_data_dict WHERE ID = #{id,jdbcType=BIGINT} ]]>
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:sys_data_dict.<br/>
     * descSql =  SELECT * FROM sys_data_dict WHERE <![CDATA[ ID = #{id,jdbcType=BIGINT} ]]>
     * @param id id
     * @return DataDictDO
     */
    DataDictDO getById(Long id);
}

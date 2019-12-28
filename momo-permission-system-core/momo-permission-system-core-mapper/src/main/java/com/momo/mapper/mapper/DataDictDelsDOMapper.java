package com.momo.mapper.mapper;

import com.momo.mapper.dataobject.DataDictDelsDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * The Table sys_data_dict_dels.
 * 数据字典详情表
 */
public interface DataDictDelsDOMapper{

    /**
     * desc:插入表:sys_data_dict_dels.<br/>
     * descSql =  SELECT LAST_INSERT_ID() <![CDATA[ INSERT INTO sys_data_dict_dels( ID ,SYS_DICT_CODE_ID ,REMARK ,CREATE_BY ,UPDATE_BY ,SYS_DICT_DELS_NAME ,SYS_DICT_CODE_VALUE ,SYS_DICT_DELS_VALUE ,DEL_FLAG ,DISABLED_FLAG ,SYS_DICT_DELS_SEQ ,CREATE_TIME ,UPDATE_TIME )VALUES( null , #{sysDictCodeId,jdbcType=BIGINT} , #{remark,jdbcType=VARCHAR} , #{createBy,jdbcType=VARCHAR} , #{updateBy,jdbcType=VARCHAR} , #{sysDictDelsName,jdbcType=VARCHAR} , #{sysDictCodeValue,jdbcType=VARCHAR} , #{sysDictDelsValue,jdbcType=VARCHAR} , #{delFlag,jdbcType=INTEGER} , #{disabledFlag,jdbcType=INTEGER} , #{sysDictDelsSeq,jdbcType=INTEGER} , #{createTime,jdbcType=TIMESTAMP} , #{updateTime,jdbcType=TIMESTAMP} ) ]]>
     * @param entity entity
     * @return int
     */
    int insert(DataDictDelsDO entity);
    /**
     * desc:批量插入表:sys_data_dict_dels.<br/>
     * descSql =  INSERT INTO sys_data_dict_dels( ID ,SYS_DICT_CODE_ID ,REMARK ,CREATE_BY ,UPDATE_BY ,SYS_DICT_DELS_NAME ,SYS_DICT_CODE_VALUE ,SYS_DICT_DELS_VALUE ,DEL_FLAG ,DISABLED_FLAG ,SYS_DICT_DELS_SEQ ,CREATE_TIME ,UPDATE_TIME )VALUES ( null , #{item.sysDictCodeId,jdbcType=BIGINT} , #{item.remark,jdbcType=VARCHAR} , #{item.createBy,jdbcType=VARCHAR} , #{item.updateBy,jdbcType=VARCHAR} , #{item.sysDictDelsName,jdbcType=VARCHAR} , #{item.sysDictCodeValue,jdbcType=VARCHAR} , #{item.sysDictDelsValue,jdbcType=VARCHAR} , #{item.delFlag,jdbcType=INTEGER} , #{item.disabledFlag,jdbcType=INTEGER} , #{item.sysDictDelsSeq,jdbcType=INTEGER} , #{item.createTime,jdbcType=TIMESTAMP} , #{item.updateTime,jdbcType=TIMESTAMP} ) 
     * @param list list
     * @return int
     */
    int insertBatch(List<DataDictDelsDO> list);
    /**
     * desc:根据主键删除数据:sys_data_dict_dels.<br/>
     * descSql =  <![CDATA[ DELETE FROM sys_data_dict_dels WHERE ID = #{id,jdbcType=BIGINT} ]]>
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:sys_data_dict_dels.<br/>
     * descSql =  SELECT * FROM sys_data_dict_dels WHERE <![CDATA[ ID = #{id,jdbcType=BIGINT} ]]>
     * @param id id
     * @return DataDictDelsDO
     */
    DataDictDelsDO getById(Long id);
}

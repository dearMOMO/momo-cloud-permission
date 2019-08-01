package com.momo.mapper.mapper;

import com.momo.mapper.dataobject.BugDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * The Table sys_bug.
 * 产品提优
 */
public interface BugDOMapper{

    /**
     * desc:插入表:sys_bug.<br/>
     * descSql =  SELECT LAST_INSERT_ID() <![CDATA[ INSERT INTO sys_bug( ID ,UUID ,CREATE_BY ,UPDATE_BY ,SYS_PRODUCT_IMG ,SYS_PRODUCT_NAME ,SYS_PRODUCT_REPLY ,SYS_PRODUCT_DETAIL ,FLAG ,CREATE_TIME ,UPDATE_TIME )VALUES( null , #{uuid,jdbcType=VARCHAR} , #{createBy,jdbcType=VARCHAR} , #{updateBy,jdbcType=VARCHAR} , #{sysProductImg,jdbcType=VARCHAR} , #{sysProductName,jdbcType=VARCHAR} , #{sysProductReply,jdbcType=VARCHAR} , #{sysProductDetail,jdbcType=VARCHAR} , #{flag,jdbcType=INTEGER} , #{createTime,jdbcType=TIMESTAMP} , #{updateTime,jdbcType=TIMESTAMP} ) ]]>
     * @param entity entity
     * @return int
     */
    int insert(BugDO entity);
    /**
     * desc:批量插入表:sys_bug.<br/>
     * descSql =  INSERT INTO sys_bug( ID ,UUID ,CREATE_BY ,UPDATE_BY ,SYS_PRODUCT_IMG ,SYS_PRODUCT_NAME ,SYS_PRODUCT_REPLY ,SYS_PRODUCT_DETAIL ,FLAG ,CREATE_TIME ,UPDATE_TIME )VALUES ( null , #{item.uuid,jdbcType=VARCHAR} , #{item.createBy,jdbcType=VARCHAR} , #{item.updateBy,jdbcType=VARCHAR} , #{item.sysProductImg,jdbcType=VARCHAR} , #{item.sysProductName,jdbcType=VARCHAR} , #{item.sysProductReply,jdbcType=VARCHAR} , #{item.sysProductDetail,jdbcType=VARCHAR} , #{item.flag,jdbcType=INTEGER} , #{item.createTime,jdbcType=TIMESTAMP} , #{item.updateTime,jdbcType=TIMESTAMP} ) 
     * @param list list
     * @return int
     */
    int insertBatch(List<BugDO> list);
    /**
     * desc:根据主键删除数据:sys_bug.<br/>
     * descSql =  <![CDATA[ DELETE FROM sys_bug WHERE ID = #{id,jdbcType=BIGINT} ]]>
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:sys_bug.<br/>
     * descSql =  SELECT * FROM sys_bug WHERE <![CDATA[ ID = #{id,jdbcType=BIGINT} ]]>
     * @param id id
     * @return BugDO
     */
    BugDO getById(Long id);
}

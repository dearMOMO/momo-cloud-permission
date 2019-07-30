package com.momo.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.momo.mapper.dataobject.BugDO;
import com.momo.mapper.mapper.BugDOMapper;

/**
* The Table SYS_BUG.
* 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
* 产品提优
*/
@Repository
public class BugDAO{

    @Autowired
    private BugDOMapper bugDOMapper;

    /**
     * desc:插入表:SYS_BUG.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_BUG( CREATE_BY ,UPDATE_BY ,SYS_PRODUCT_IMG ,SYS_PRODUCT_NAME ,SYS_PRODUCT_REPLY ,SYS_PRODUCT_DETAIL ,SYS_PRODUCT_STATUS ,CREATE_TIME ,UPDATE_TIME )VALUES( #{createBy,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{sysProductImg,jdbcType=VARCHAR} ,#{sysProductName,jdbcType=VARCHAR} ,#{sysProductReply,jdbcType=VARCHAR} ,#{sysProductDetail,jdbcType=VARCHAR} ,#{sysProductStatus,jdbcType=CHAR} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    public int insert(BugDO entity){
        return bugDOMapper.insert(entity);
    }
    /**
     * desc:更新表:SYS_BUG.<br/>
     * descSql =  UPDATE SYS_BUG SET CREATE_BY = #{createBy,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,SYS_PRODUCT_IMG = #{sysProductImg,jdbcType=VARCHAR} ,SYS_PRODUCT_NAME = #{sysProductName,jdbcType=VARCHAR} ,SYS_PRODUCT_REPLY = #{sysProductReply,jdbcType=VARCHAR} ,SYS_PRODUCT_DETAIL = #{sysProductDetail,jdbcType=VARCHAR} ,SYS_PRODUCT_STATUS = #{sysProductStatus,jdbcType=CHAR} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    public int update(BugDO entity){
        return bugDOMapper.update(entity);
    }
    /**
     * desc:根据主键删除数据:SYS_BUG.<br/>
     * descSql =  DELETE FROM SYS_BUG WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return bugDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:SYS_BUG.<br/>
     * descSql =  SELECT * FROM SYS_BUG WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return BugDO
     */
    public BugDO getById(Long id){
        return bugDOMapper.getById(id);
    }
}

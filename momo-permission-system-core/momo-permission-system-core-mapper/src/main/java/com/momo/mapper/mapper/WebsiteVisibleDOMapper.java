package com.momo.mapper.mapper;

import com.momo.mapper.dataobject.WebsiteVisibleDO;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table SYS_WEBSITE_VISIBLE.
 * 网站访问总人数
 */
public interface WebsiteVisibleDOMapper{

    /**
     * desc:插入表:SYS_WEBSITE_VISIBLE.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_WEBSITE_VISIBLE( VISIBLE_NUMBER )VALUES( #{visibleNumber,jdbcType=BIGINT} )
     * @param entity entity
     * @return int
     */
    int insert(WebsiteVisibleDO entity);
    /**
     * desc:更新表:SYS_WEBSITE_VISIBLE.<br/>
     * descSql =  UPDATE SYS_WEBSITE_VISIBLE SET VISIBLE_NUMBER = #{visibleNumber,jdbcType=BIGINT} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    int update(WebsiteVisibleDO entity);
    /**
     * desc:根据主键删除数据:SYS_WEBSITE_VISIBLE.<br/>
     * descSql =  DELETE FROM SYS_WEBSITE_VISIBLE WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:SYS_WEBSITE_VISIBLE.<br/>
     * descSql =  SELECT * FROM SYS_WEBSITE_VISIBLE WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return WebsiteVisibleDO
     */
    WebsiteVisibleDO getById(Long id);
}

package com.momo.mapper.mapper;

import com.momo.mapper.dataobject.WebsiteVisibleDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * The Table sys_website_visible.
 * 网站访问总人数
 */
public interface WebsiteVisibleDOMapper{

    /**
     * desc:插入表:sys_website_visible.<br/>
     * descSql =  SELECT LAST_INSERT_ID() <![CDATA[ INSERT INTO sys_website_visible( ID ,VISIBLE_NUMBER )VALUES( null , #{visibleNumber,jdbcType=BIGINT} ) ]]>
     * @param entity entity
     * @return int
     */
    int insert(WebsiteVisibleDO entity);
    /**
     * desc:批量插入表:sys_website_visible.<br/>
     * descSql =  INSERT INTO sys_website_visible( ID ,VISIBLE_NUMBER )VALUES ( null , #{item.visibleNumber,jdbcType=BIGINT} ) 
     * @param list list
     * @return int
     */
    int insertBatch(List<WebsiteVisibleDO> list);
    /**
     * desc:根据主键删除数据:sys_website_visible.<br/>
     * descSql =  <![CDATA[ DELETE FROM sys_website_visible WHERE ID = #{id,jdbcType=BIGINT} ]]>
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:sys_website_visible.<br/>
     * descSql =  SELECT * FROM sys_website_visible WHERE <![CDATA[ ID = #{id,jdbcType=BIGINT} ]]>
     * @param id id
     * @return WebsiteVisibleDO
     */
    WebsiteVisibleDO getById(Long id);
}

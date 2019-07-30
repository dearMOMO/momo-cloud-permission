package com.momo.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.momo.mapper.dataobject.WebsiteVisibleDO;
import com.momo.mapper.mapper.WebsiteVisibleDOMapper;

/**
* The Table SYS_WEBSITE_VISIBLE.
* 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
* 网站访问总人数
*/
@Repository
public class WebsiteVisibleDAO{

    @Autowired
    private WebsiteVisibleDOMapper websiteVisibleDOMapper;

    /**
     * desc:插入表:SYS_WEBSITE_VISIBLE.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_WEBSITE_VISIBLE( VISIBLE_NUMBER )VALUES( #{visibleNumber,jdbcType=BIGINT} )
     * @param entity entity
     * @return int
     */
    public int insert(WebsiteVisibleDO entity){
        return websiteVisibleDOMapper.insert(entity);
    }
    /**
     * desc:更新表:SYS_WEBSITE_VISIBLE.<br/>
     * descSql =  UPDATE SYS_WEBSITE_VISIBLE SET VISIBLE_NUMBER = #{visibleNumber,jdbcType=BIGINT} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    public int update(WebsiteVisibleDO entity){
        return websiteVisibleDOMapper.update(entity);
    }
    /**
     * desc:根据主键删除数据:SYS_WEBSITE_VISIBLE.<br/>
     * descSql =  DELETE FROM SYS_WEBSITE_VISIBLE WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return websiteVisibleDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:SYS_WEBSITE_VISIBLE.<br/>
     * descSql =  SELECT * FROM SYS_WEBSITE_VISIBLE WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return WebsiteVisibleDO
     */
    public WebsiteVisibleDO getById(Long id){
        return websiteVisibleDOMapper.getById(id);
    }
}

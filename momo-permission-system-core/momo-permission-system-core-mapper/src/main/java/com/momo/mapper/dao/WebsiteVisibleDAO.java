package com.momo.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.momo.mapper.dataobject.WebsiteVisibleDO;
import java.util.List;
import com.momo.mapper.mapper.WebsiteVisibleDOMapper;

/**
* The Table sys_website_visible.
* 网站访问总人数
*/
@Repository
public class WebsiteVisibleDAO{

    @Autowired
    private WebsiteVisibleDOMapper websiteVisibleDOMapper;

    /**
     * desc:插入表:sys_website_visible.<br/>
     * @param entity entity
     * @return int
     */
    public int insert(WebsiteVisibleDO entity){
        return websiteVisibleDOMapper.insert(entity);
    }
    /**
     * desc:批量插入表:sys_website_visible.<br/>
     * @param list list
     * @return int
     */
    public int insertBatch(List<WebsiteVisibleDO> list){
        return websiteVisibleDOMapper.insertBatch(list);
    }
    /**
     * desc:根据主键删除数据:sys_website_visible.<br/>
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return websiteVisibleDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:sys_website_visible.<br/>
     * @param id id
     * @return WebsiteVisibleDO
     */
    public WebsiteVisibleDO getById(Long id){
        return websiteVisibleDOMapper.getById(id);
    }
}

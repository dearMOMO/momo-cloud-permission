package com.momo.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.momo.mapper.dataobject.BugDO;
import java.util.List;
import com.momo.mapper.mapper.BugDOMapper;

/**
* The Table sys_bug.
* 产品提优
*/
@Repository
public class BugDAO{

    @Autowired
    private BugDOMapper bugDOMapper;

    /**
     * desc:插入表:sys_bug.<br/>
     * @param entity entity
     * @return int
     */
    public int insert(BugDO entity){
        return bugDOMapper.insert(entity);
    }
    /**
     * desc:批量插入表:sys_bug.<br/>
     * @param list list
     * @return int
     */
    public int insertBatch(List<BugDO> list){
        return bugDOMapper.insertBatch(list);
    }
    /**
     * desc:根据主键删除数据:sys_bug.<br/>
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return bugDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:sys_bug.<br/>
     * @param id id
     * @return BugDO
     */
    public BugDO getById(Long id){
        return bugDOMapper.getById(id);
    }
}

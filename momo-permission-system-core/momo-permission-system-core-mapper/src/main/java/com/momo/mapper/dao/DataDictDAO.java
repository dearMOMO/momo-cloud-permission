package com.momo.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.momo.mapper.dataobject.DataDictDO;
import java.util.List;
import com.momo.mapper.mapper.DataDictDOMapper;

/**
* The Table sys_data_dict.
* 数据字典表
*/
@Repository
public class DataDictDAO{

    @Autowired
    private DataDictDOMapper dataDictDOMapper;

    /**
     * desc:插入表:sys_data_dict.<br/>
     * @param entity entity
     * @return int
     */
    public int insert(DataDictDO entity){
        return dataDictDOMapper.insert(entity);
    }
    /**
     * desc:批量插入表:sys_data_dict.<br/>
     * @param list list
     * @return int
     */
    public int insertBatch(List<DataDictDO> list){
        return dataDictDOMapper.insertBatch(list);
    }
    /**
     * desc:根据主键删除数据:sys_data_dict.<br/>
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return dataDictDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:sys_data_dict.<br/>
     * @param id id
     * @return DataDictDO
     */
    public DataDictDO getById(Long id){
        return dataDictDOMapper.getById(id);
    }
}

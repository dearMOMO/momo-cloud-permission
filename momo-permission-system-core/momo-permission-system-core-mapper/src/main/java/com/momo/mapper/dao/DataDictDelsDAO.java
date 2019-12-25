package com.momo.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.momo.mapper.dataobject.DataDictDelsDO;
import java.util.List;
import com.momo.mapper.mapper.DataDictDelsDOMapper;

/**
* The Table sys_data_dict_dels.
* 数据字典详情表
*/
@Repository
public class DataDictDelsDAO{

    @Autowired
    private DataDictDelsDOMapper dataDictDelsDOMapper;

    /**
     * desc:插入表:sys_data_dict_dels.<br/>
     * @param entity entity
     * @return int
     */
    public int insert(DataDictDelsDO entity){
        return dataDictDelsDOMapper.insert(entity);
    }
    /**
     * desc:批量插入表:sys_data_dict_dels.<br/>
     * @param list list
     * @return int
     */
    public int insertBatch(List<DataDictDelsDO> list){
        return dataDictDelsDOMapper.insertBatch(list);
    }
    /**
     * desc:根据主键删除数据:sys_data_dict_dels.<br/>
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return dataDictDelsDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:sys_data_dict_dels.<br/>
     * @param id id
     * @return DataDictDelsDO
     */
    public DataDictDelsDO getById(Long id){
        return dataDictDelsDOMapper.getById(id);
    }
}

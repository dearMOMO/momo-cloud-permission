package com.momo.mapper.mapper.manual;

import com.momo.mapper.dataobject.DataDictDelsDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: DataDictDelsMapper
 * @Author: Jie Li
 * @Date 2019-12-28 16:59
 * @Description: TODO
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
public interface DataDictDelsMapper {


    int insertSelective(DataDictDelsDO dataDictDelsDO);

    DataDictDelsDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DataDictDelsDO dataDictDelsDO);

    List<DataDictDelsDO> dictDelsPageList(@Param("sys_dict_dels_name")String dels_name,@Param("sys_dict_dels_value")String dels_value);

}

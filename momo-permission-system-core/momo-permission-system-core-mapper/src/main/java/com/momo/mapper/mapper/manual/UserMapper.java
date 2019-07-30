package com.momo.mapper.mapper.manual;

import com.momo.mapper.dataobject.UserDO;
import org.apache.ibatis.annotations.Param;


public interface UserMapper {


    UserDO getById(@Param("id") Long id);
}

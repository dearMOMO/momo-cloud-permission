package com.momo.mapper.mapper.manual;

import com.momo.mapper.dataobject.UserGroupDO;
import org.apache.ibatis.annotations.Param;


public interface UserGroupMapper {


    UserGroupDO getUserGroupById(@Param("id") Long id);
}

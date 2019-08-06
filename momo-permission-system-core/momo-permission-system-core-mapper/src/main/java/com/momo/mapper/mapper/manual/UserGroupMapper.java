package com.momo.mapper.mapper.manual;

import com.momo.mapper.dataobject.UserGroupDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserGroupMapper {


    UserGroupDO getUserGroupById(@Param("id") Long id);

    List<UserGroupDO> getUserGroupPage(@Param("userGroupName")String userGroupName,@Param("flag")Integer flag);
}

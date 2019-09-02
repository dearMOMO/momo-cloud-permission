package com.momo.mapper.mapper.manual;

import com.momo.mapper.dataobject.UserGroupDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserGroupMapper {


    UserGroupDO getUserGroupById(@Param("id") Long id);

    UserGroupDO uuid(@Param("uuid") String uuid);

    List<UserGroupDO> getUserGroupPage(@Param("userGroupName") String userGroupName, @Param("disabledFlag") Integer disabledFlag);

    int insertSelective(UserGroupDO userGroupDO);

    int updateByPrimaryKeySelective(UserGroupDO userGroupDO);

    int checkNameExists(@Param("user_group_name") String user_group_name, @Param("id") Long id);
}

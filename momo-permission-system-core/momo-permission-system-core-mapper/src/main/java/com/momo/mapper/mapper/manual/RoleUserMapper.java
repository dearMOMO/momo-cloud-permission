package com.momo.mapper.mapper.manual;

import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface RoleUserMapper {
    /**
     * 根据用户id得到角色id
     *
     * @param userId
     * @return
     */
    List<Long> userToRoleIds(@Param("userId") Long userId);

}

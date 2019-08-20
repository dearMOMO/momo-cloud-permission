package com.momo.mapper.mapper.manual;

import com.momo.mapper.dataobject.UserAccountPwdDO;
import org.apache.ibatis.annotations.Param;


public interface UserAccountPwdMapper {


    UserAccountPwdDO sysUserAccountLogin(@Param("sysUserLoginName") String sysUserLoginName);

    UserAccountPwdDO sysUserAccountByUserId(@Param("userId") Long userId);

    int insertSelective(UserAccountPwdDO userAccountPwdDO);

    int updateByPrimaryKeySelective(UserAccountPwdDO userAccountPwdDO);
}

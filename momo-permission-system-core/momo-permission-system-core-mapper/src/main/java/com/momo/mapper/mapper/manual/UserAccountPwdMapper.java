package com.momo.mapper.mapper.manual;

import com.momo.mapper.dataobject.UserAccountPwdDO;
import org.apache.ibatis.annotations.Param;


public interface UserAccountPwdMapper {


    UserAccountPwdDO sysUserAccountLogin(@Param("sysUserLoginName") String sysUserLoginName);
    int insertSelective(UserAccountPwdDO userAccountPwdDO);
}

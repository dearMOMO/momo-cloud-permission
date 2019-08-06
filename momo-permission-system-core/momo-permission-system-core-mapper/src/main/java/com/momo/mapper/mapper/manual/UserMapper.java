package com.momo.mapper.mapper.manual;

import com.momo.mapper.dataobject.UserDO;
import com.momo.mapper.dataobject.manual.SysUserListDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserMapper {


    UserDO getById(@Param("id") Long id);

    UserDO uuid(@Param("uuid") String uuid);

    List<SysUserListDO> pageSysUserList(@Param("groupId")Long groupId,@Param("sysUserName") String sysUserName, @Param("flag") Integer flag);

    UserDO getUserInfo(@Param("id") Long id);

    int insertSelective(UserDO record);
}

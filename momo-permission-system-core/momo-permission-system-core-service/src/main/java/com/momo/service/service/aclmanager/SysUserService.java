package com.momo.service.service.aclmanager;

import com.github.pagehelper.PageInfo;
import com.momo.mapper.req.aclmanager.SysUserListReq;
import com.momo.mapper.res.aclmanager.SysUserListRes;

/**
 * @program: momo-cloud-permission
 * @description: 用户管理
 * @author: Jie Li
 * @create: 2019-08-02 17:20
 **/
public interface SysUserService {

    /**
     * 用户列表分页
     * @param sysUserListReq
     * @return
     */
    public PageInfo<SysUserListRes> sysUserList(SysUserListReq sysUserListReq);
}

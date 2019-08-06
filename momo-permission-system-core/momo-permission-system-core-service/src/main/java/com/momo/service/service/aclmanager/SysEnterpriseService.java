package com.momo.service.service.aclmanager;

import com.github.pagehelper.PageInfo;
import com.momo.mapper.dataobject.UserGroupDO;
import com.momo.mapper.req.aclmanager.UserGroupPageReq;

/**
 * @program: momo-cloud-permission
 * @description: 企业管理
 * @author: Jie Li
 * @create: 2019-08-06 13:04
 **/
public interface SysEnterpriseService {
    //企业分页
    public PageInfo<UserGroupDO> getUserGroupPage(UserGroupPageReq userGroupPageReq);
}

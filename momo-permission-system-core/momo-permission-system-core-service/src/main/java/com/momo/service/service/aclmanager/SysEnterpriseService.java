package com.momo.service.service.aclmanager;

import com.github.pagehelper.PageInfo;
import com.momo.mapper.dataobject.RoleDO;
import com.momo.mapper.dataobject.UserGroupDO;
import com.momo.mapper.req.aclmanager.SysEnterpriseRoleReq;
import com.momo.mapper.req.aclmanager.SysUserGroupReq;
import com.momo.mapper.req.aclmanager.UserGroupPageReq;
import com.momo.mapper.res.aclmanager.SysEnterpriseRoleRes;
import com.momo.mapper.res.aclmanager.SysUserGroupPageRes;
import com.momo.mapper.res.authority.AclTreeRes;

/**
 * @program: momo-cloud-permission
 * @description: 企业管理
 * @author: Jie Li
 * @create: 2019-08-06 13:04
 **/
public interface SysEnterpriseService {
    //企业分页
    public PageInfo<SysUserGroupPageRes> getUserGroupPage(UserGroupPageReq userGroupPageReq);

    UserGroupDO detail(UserGroupPageReq userGroupPageReq);

    AclTreeRes aclDetail(UserGroupPageReq userGroupPageReq);

    String aclsToEnterprise(UserGroupPageReq userGroupPageReq);

    String modify(SysUserGroupReq sysUserGroupReq);

    SysEnterpriseRoleRes roleList(SysEnterpriseRoleReq sysEnterpriseRoleReq);

    String save(SysUserGroupReq sysUserGroupReq);

    String status(SysUserGroupReq sysUserGroupReq);

    String roleAdd(SysEnterpriseRoleReq sysEnterpriseRoleReq);

    RoleDO roleDetail(SysEnterpriseRoleReq sysEnterpriseRoleReq);

    String roleModify(SysEnterpriseRoleReq sysEnterpriseRoleReq);

    AclTreeRes roleHaveAclTree(SysEnterpriseRoleReq sysEnterpriseRoleReq);
}

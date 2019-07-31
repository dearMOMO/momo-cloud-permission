package com.momo.service.service.authority;

import com.google.common.collect.Lists;
import com.momo.mapper.dataobject.AclDO;
import com.momo.mapper.mapper.manual.AuthorityMapper;
import com.momo.mapper.req.sysmain.RedisUser;
import com.momo.mapper.res.authority.LoginAuthReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @program: momo-cloud-permission
 * @description: 总部 权限 核心
 * @author: Jie Li
 * @create: 2019-07-30 22:49
 **/
@Service
public class AdminSysCoreService {
    @Value("${momo.superAdmins}")
    private String superAdmins = "";
    @Autowired
    private AuthorityMapper authorityMapper;

    public List<AclDO> getRoleAclList(Long roleId, Long aclPermissionType) {
        //根据角色id获取权限点ids
        List<Long> aclIdList = authorityMapper.aclsByRoleId(Lists.<Long>newArrayList(roleId), aclPermissionType);
        if (org.apache.commons.collections.CollectionUtils.isEmpty(aclIdList)) {
            return Lists.newArrayList();
        }
        //根据权限点ids获取权限点列表
        return authorityMapper.getAllAcl(null, aclIdList);
    }

    //动态权限菜单
    public List<AclDO> getUserAclList(LoginAuthReq loginAuthReq, RedisUser redisUser) {

        //如果是超级管理员，则获取所有权限点
        if (isSuperAdmin(redisUser)) {
            return authorityMapper.getAllAcl(loginAuthReq.getAclPermissionType(), null);
        }
        //根据用户id获取角色ids
        List<Long> userRoleIdList = authorityMapper.rolesByUserId(redisUser.getBaseId());
        if (CollectionUtils.isEmpty(userRoleIdList)) {
            return Lists.newArrayList();
        }
        List<Long> finalRoleIds = Lists.newArrayList();
        //根据角色ids获取角色列表 临时启用和禁用角色
        //是否被禁用  0否 1禁用
        List<Long> roleIds = authorityMapper.rolesByRoleId(userRoleIdList, "0");
        if (CollectionUtils.isEmpty(roleIds)) {
            return Lists.newArrayList();
        }
        roleIds.forEach(aLong -> {
            if (roleIds.contains(aLong)) {
                finalRoleIds.add(aLong);
            }
        });
        //根据角色ids获取权限点ids
        List<Long> userAclIdList = authorityMapper.aclsByRoleId(finalRoleIds, loginAuthReq.getAclPermissionType());
        if (CollectionUtils.isEmpty(userAclIdList)) {
            return Lists.newArrayList();
        }
        //根据权限点ids获取权限点列表
        return authorityMapper.getAllAcl(loginAuthReq.getAclPermissionType(), userAclIdList);
    }

    //为角色授权 权限 之前， 需要查看该角色拥有哪些权限点，以及当前登录用户可以操作哪些权限
    public List<AclDO> getUserHavingAclList(LoginAuthReq loginAuthReq, RedisUser redisUser) {
        //如果是超级管理员，则获取所有权限点
        if (isSuperAdmin(redisUser)) {
            return authorityMapper.getAllAcl(loginAuthReq.getAclPermissionType(), null);
        }
        //根据用户id获取角色ids
        List<Long> userRoleIdList = authorityMapper.rolesByUserId(redisUser.getBaseId());
        if (org.apache.commons.collections.CollectionUtils.isEmpty(userRoleIdList)) {
            return Lists.newArrayList();
        }
        //根据角色ids获取权限点ids
        List<Long> userAclIdList = authorityMapper.aclsByRoleId(userRoleIdList, loginAuthReq.getAclPermissionType());
        if (org.apache.commons.collections.CollectionUtils.isEmpty(userAclIdList)) {
            return Lists.newArrayList();
        }
        //根据权限点ids获取权限点列表
        return authorityMapper.getAllAcl(loginAuthReq.getAclPermissionType(), userAclIdList);
    }

    public boolean isSuperAdmin(RedisUser redisUser) {
        // 这里是我自己定义了一个假的超级管理员规则，实际中要根据项目进行修改
        // 可以是配置文件获取，可以指定某个用户，也可以指定某个角色
        if (superAdmins.trim().contains(redisUser.getSysUserPhone())) {
            return true;
        }
        return false;
    }
}

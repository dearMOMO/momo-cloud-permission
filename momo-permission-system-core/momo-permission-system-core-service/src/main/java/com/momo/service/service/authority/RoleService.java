package com.momo.service.service.authority;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.momo.common.common.JSONResult;
import com.momo.common.error.BizException;
import com.momo.common.util.DateUtil;
import com.momo.common.util.StrUtil;
import com.momo.common.util.snowFlake.SnowFlake;
import com.momo.mapper.dataobject.RoleAclDO;
import com.momo.mapper.dataobject.RoleDO;
import com.momo.mapper.dataobject.RoleUserDO;
import com.momo.mapper.dataobject.UserDO;
import com.momo.mapper.mapper.manual.AuthorityMapper;
import com.momo.mapper.mapper.manual.RoleMapper;
import com.momo.mapper.mapper.manual.UserMapper;
import com.momo.mapper.req.authority.BatchRoleUserReq;
import com.momo.mapper.req.authority.RoleReq;
import com.momo.mapper.req.sysmain.LoginAuthReq;
import com.momo.mapper.req.sysmain.RedisUser;
import com.momo.mapper.res.authority.AclLevelRes;
import com.momo.service.service.BaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @program: momo-cloud-permission
 * @description: 角色设置
 * @author: Jie Li
 * @create: 2019-07-31 14:59
 **/
@Service
public class RoleService extends BaseService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AuthorityMapper authorityMapper;
    @Autowired
    private AdminAuthorityService adminAuthorityService;
    @Autowired
    private CommonAuthorityService commonAuthorityService;
    private SnowFlake snowFlake = new SnowFlake(1, 1);

    public String rolesToUser(BatchRoleUserReq batchRoleUserReq) {
        UserDO userDO = userMapper.uuid(batchRoleUserReq.getUserUuid());
        if (null == userDO) {
            throw BizException.fail("待授权的用户不存在");
        }
        List<Long> roles = batchRoleUserReq.getRoles();
        List<Long> originAclIdList = authorityMapper.rolesByUserId(userDO.getId());
        if (originAclIdList.size() == roles.size()) {
            Set<Long> originAclIdSet = Sets.newHashSet(originAclIdList);
            Set<Long> aclIdSet = Sets.newHashSet(roles);
            originAclIdSet.removeAll(aclIdSet);
            if (CollectionUtils.isEmpty(originAclIdSet)) {
                return "为用户授权角色成功";
            }
        }
        RedisUser redisUser = this.redisUser();
        updateUserRoles(userDO.getId(), roles, redisUser, userDO.getGroupId());
        return "为用户授权角色成功";
    }

    public String aclsToRole(BatchRoleUserReq batchRoleUserReq) {
        RoleDO roleDO = roleMapper.selectByPrimaryUuid(batchRoleUserReq.getRoleUuid());
        if (null == roleDO) {
            throw BizException.fail("待授权的角色不存在");
        }
        RedisUser redisUser = this.redisUser();
        //屏蔽非总部操作第三方管理员角色
        if (!redisUser.getGroupId().equals(1L) && "0".equals(roleDO.getSysRoleType())) {
            throw BizException.fail("您无权限操作");
        }
        List<RoleAclDO> roleAclDOS = batchRoleUserReq.getAcls();
        List<Long> acls = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(roleAclDOS)) {
            roleAclDOS.forEach(roleAclDO -> {
                acls.add(roleAclDO.getSysAclId());
            });
        }
        List<Long> originAclIdList = authorityMapper.aclsByRoleId(Arrays.asList(roleDO.getId()), null);
        List<Long> alcIds = Lists.newArrayList();
        if (originAclIdList.size() == acls.size()) {
            Set<Long> originAclIdSet = Sets.newHashSet(originAclIdList);
            Set<Long> aclIdSet = Sets.newHashSet(acls);
            originAclIdSet.removeAll(aclIdSet);
            alcIds.addAll(originAclIdSet);
            if (CollectionUtils.isEmpty(originAclIdSet)) {
                return "为角色授权权限成功";
            }
        }
        updateRoleAcls(roleDO.getId(), roleAclDOS, redisUser, roleDO.getGroupId(), acls, roleDO.getSysRoleType(), alcIds);
        return "为角色授权权限成功";
    }

    public List<AclLevelRes>  roleHaveAclTree(LoginAuthReq loginAuthReq) {
        RoleDO roleDO = roleMapper.selectByPrimaryUuid(loginAuthReq.getSysRoleUuid());
        if (null == roleDO) {
            throw BizException.fail("待授权的角色不存在");
        }
        loginAuthReq.setRoleId(roleDO.getId());
        RedisUser redisUser = this.redisUser();
        if (redisUser.getGroupId().equals(1L)) {
            List<AclLevelRes> list = adminAuthorityService.roleTree(loginAuthReq, redisUser);
            return list;
        } else {
            List<AclLevelRes> list = commonAuthorityService.roleTree(loginAuthReq, redisUser);
            return list;
        }
    }

    public List<RoleDO> rolesByCurrentId(RedisUser redisUser) {
        return roleMapper.rolesByCurrentId(redisUser.getBaseId());
    }

    public PageInfo<RoleDO> roleList(RoleReq roleReq) {
        PageHelper.startPage(roleReq.getPageNum(), roleReq.getPageSize(), "id desc");
        RoleDO selfRoleDO = new RoleDO();
        BeanUtils.copyProperties(roleReq, selfRoleDO);
        RedisUser redisUser = this.redisUser();
        selfRoleDO.setGroupId(redisUser.getGroupId());
        //角色的类型，0：管理员角色，1：普通用户 2其他
        //剔除第三方管理员角色
        selfRoleDO.setSysRoleType(1);
        List<RoleDO> selfRoleDOS = roleMapper.roleList(selfRoleDO);
        PageInfo<RoleDO> pageInfo = new PageInfo<>(selfRoleDOS);
        return pageInfo;
    }

    public RoleDO showRole(RoleReq roleReq) {
        RoleDO roleDO = roleMapper.selectByPrimaryUuid(roleReq.getUuid());
        if (null == roleDO) {
            throw BizException.fail("待编辑的角色不存在");
        }
        return roleDO;
    }

    public List<RoleDO> selectRole() {
        RoleDO selfRoleDO = new RoleDO();
        RedisUser redisUser = this.redisUser();
        selfRoleDO.setGroupId(redisUser.getGroupId());
        List<RoleDO> selfRoleDOS = roleMapper.roleList(selfRoleDO);
        return selfRoleDOS;
    }

    @Transactional
    public String insertSelective(RoleReq roleReq) {
        RedisUser redisUser = this.redisUser();
        if (checkRoleName(roleReq.getSysRoleName(), null, redisUser.getGroupId())) {
            throw BizException.fail("角色名称已存在");
        }
        //角色的类型，0：管理员角色，1：普通用户 2其他
        if (roleReq.getSysRoleType().equals(0)) {
            if (checkAdminRole("0", null, redisUser.getGroupId())) {
                throw BizException.fail("管理员角色已存在");
            }
        }

        RoleDO record = new RoleDO();
        BeanUtils.copyProperties(roleReq, record);
        record.setCreateBy(redisUser.getSysUserName());
        record.setUpdateBy(redisUser.getSysUserName());
        record.setCreateTime(DateUtil.getDateTime());
        record.setUpdateTime(DateUtil.getDateTime());
        record.setUuid(StrUtil.genUUID());
        record.setGroupId(redisUser.getGroupId());
        record.setId(snowFlake.nextId());
        roleMapper.insertSelective(record);

        return "新增角色成功";
    }

    @Transactional
    public String updateByPrimaryKeySelective(RoleReq roleReq) {
        RoleDO selfRoleDO = roleMapper.selectByPrimaryUuid(roleReq.getUuid());
        if (null == selfRoleDO) {
            throw BizException.fail("待编辑的角色不存在");
        }
        RedisUser redisUser = this.redisUser();
        if (checkRoleName(roleReq.getSysRoleName(), selfRoleDO.getId(), redisUser.getGroupId())) {
            throw BizException.fail("角色名称已存在");
        }
        //角色的类型，0：管理员角色，1：普通用户 2其他
        if (roleReq.getSysRoleType().equals(0)) {
            if (checkAdminRole("0", null, redisUser.getGroupId())) {
                throw BizException.fail("管理员角色已存在");
            }
        }
        //非总部，不可以操作管理员敏感权限
        if (!redisUser.getGroupId().equals(1L)) {
            if (!redisUser.getGroupId().equals(1L)) {
                //角色的类型，0：管理员角色，1：普通用户 2其他
                //屏蔽非总部操作第三方管理员角色
                if (selfRoleDO.getSysRoleType().equals(0) && roleReq.getSysRoleType().equals(1)) {
                    throw BizException.fail("您无权限操作管理员角色类型");
                }
                //屏蔽非总部操作第三方管理员角色状态
                //状态 0启用  1禁用
                if ((selfRoleDO.getSysRoleType().equals(0) && roleReq.getFlag().equals(1))) {
                    throw BizException.fail("您无权限操作管理员角色状态");
                }
            }
        }
        RoleDO record = new RoleDO();
        BeanUtils.copyProperties(roleReq, record);
        record.setUpdateBy(redisUser.getSysUserName());
        record.setUpdateTime(DateUtil.getDateTime());
        record.setId(selfRoleDO.getId());
        roleMapper.updateByPrimaryKeySelective(record);
        return "编辑角色成功";
    }

    @Transactional
    public String updateState(RoleReq roleReq) {
        RoleDO selfRoleDO = roleMapper.selectByPrimaryUuid(roleReq.getUuid());
        if (null == selfRoleDO) {
            throw BizException.fail("待编辑的角色不存在");
        }
        RedisUser redisUser = this.redisUser();
        //非总部，不可以操作管理员敏感权限
        if (!redisUser.getGroupId().equals(1L) && selfRoleDO.getSysRoleType().equals(0)) {
            throw BizException.fail("您无权限操作");
        }
        RoleDO record = new RoleDO();
        //状态 0启用  1禁用
        if (roleReq.getFlag().equals(0)) {
            record.setFlag(1);
        } else if (roleReq.getFlag().equals(1)) {
            record.setFlag(0);
        }
        record.setUpdateBy(redisUser.getSysUserName());
        record.setUpdateTime(DateUtil.getDateTime());
        record.setId(selfRoleDO.getId());
        roleMapper.updateByPrimaryKeySelective(record);
        return "变更角色状态成功";
    }

    @Transactional
    public void updateRoleAcls(Long roleId, List<RoleAclDO> aclIdList, RedisUser redisUser, Long groupId, List<Long> acls, Integer roleType, List<Long> alcIds) {
        roleMapper.deleteRoleAclsByRoleId(roleId);
        //角色的类型，0：管理员角色，1：普通用户 2其他
        if (roleType.equals(0)) {
            roleMapper.deleteRoleAclsAdmin(groupId, alcIds);
        }

        if (CollectionUtils.isEmpty(aclIdList)) {
            return;
        }
        List<RoleAclDO> roleUserList = Lists.newArrayList();
        for (RoleAclDO aclId : aclIdList) {
            RoleAclDO roleUserDO = new RoleAclDO();
            roleUserDO.setId(snowFlake.nextId());
            roleUserDO.setDelFlag(0);
            roleUserDO.setGroupId(groupId);
            roleUserDO.setSysAclId(aclId.getSysAclId());
            roleUserDO.setSysRoleId(roleId);
            roleUserDO.setSysAclPermissionType(aclId.getSysAclPermissionType());
            roleUserDO.setUuid(StrUtil.genUUID());
            roleUserDO.setCreateBy(redisUser.getSysUserName());
            roleUserDO.setUpdateBy(redisUser.getSysUserName());
            roleUserDO.setUpdateTime(DateUtil.getDateTime());
            roleUserDO.setCreateTime(DateUtil.getDateTime());
            roleUserList.add(roleUserDO);
        }
        roleMapper.batchInsertRoleAcls(roleUserList);
    }

    @Transactional
    public void updateUserRoles(Long userId, List<Long> roleIdList, RedisUser redisUser, Long groupId) {
        roleMapper.deleteUserRolesByUserId(userId);

        if (CollectionUtils.isEmpty(roleIdList)) {
            return;
        }
        List<RoleUserDO> roleUserList = Lists.newArrayList();
        for (Long aclId : roleIdList) {
            RoleUserDO roleUserDO = new RoleUserDO();
            roleUserDO.setId(snowFlake.nextId());
            roleUserDO.setRoleId(aclId);
            roleUserDO.setGroupId(groupId);
            roleUserDO.setUserId(userId);
            roleUserDO.setCreateTime(DateUtil.getDateTime());
            roleUserDO.setCreateBy(redisUser.getSysUserName());
            roleUserDO.setUpdateBy(redisUser.getSysUserName());
            roleUserDO.setUpdateTime(DateUtil.getDateTime());
            roleUserList.add(roleUserDO);
        }
        roleMapper.batchInsertUserRoles(roleUserList);
    }

    public boolean checkRoleName(String roleName, Long id, Long compId) {
        return roleMapper.checkRoleName(roleName, id, compId) > 0 ? true : false;
    }

    public boolean checkAdminRole(String roleType, Long id, Long compId) {
        return roleMapper.checkAdminRole(id, roleType, compId) > 0 ? true : false;
    }
}

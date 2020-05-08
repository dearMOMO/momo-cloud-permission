/**
 * Copyright (c) 2018-2019, Jie Li 李杰 (mqgnsds@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.momo.service.service.aclmanager.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.momo.common.core.entity.RedisUser;
import com.momo.common.core.error.BizException;
import com.momo.common.core.util.DateUtils;
import com.momo.common.core.util.Encrypt;
import com.momo.common.core.util.StrUtil;
import com.momo.common.core.util.snowFlake.SnowFlake;
import com.momo.mapper.dataobject.*;
import com.momo.mapper.dataobject.manual.SysUserListDO;
import com.momo.mapper.enums.DelFlagEnum;
import com.momo.mapper.enums.DisabledFlagEnum;
import com.momo.mapper.enums.RoleTypeEnum;
import com.momo.mapper.mapper.manual.*;
import com.momo.mapper.req.aclmanager.SysEnterpriseRoleReq;
import com.momo.mapper.req.aclmanager.SysEnterpriseUserReq;
import com.momo.mapper.req.aclmanager.SysUserGroupReq;
import com.momo.mapper.req.aclmanager.UserGroupPageReq;
import com.momo.mapper.req.authority.BatchRoleUserReq;
import com.momo.mapper.req.sysmain.DynamicMenuAuthorReq;
import com.momo.mapper.res.aclmanager.*;
import com.momo.mapper.res.authority.AclTreeRes;
import com.momo.service.async.RoleRedisCacheServiceAsync;
import com.momo.service.service.BaseService;
import com.momo.service.service.SuperAdminsService;
import com.momo.service.service.aclmanager.SysEnterpriseService;
import com.momo.service.service.authority.AdminAuthorityService;
import com.momo.service.service.authority.CommonAuthorityService;
import com.momo.service.service.authority.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @program: momo-cloud-permission
 * @description: 企业管理
 * @author: Jie Li
 * @create: 2019-08-06 13:04
 **/
@Service
@Slf4j
public class SysEnterpriseServiceImpl extends BaseService implements SysEnterpriseService {
    @Autowired
    private UserGroupMapper userGroupMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private AclMapper aclMapper;
    @Autowired
    private AdminAuthorityService adminAuthorityService;
    @Autowired
    private CommonAuthorityService commonAuthorityService;
    @Autowired
    private AuthorityMapper authorityMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserAccountPwdMapper userAccountPwdMapper;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleRedisCacheServiceAsync roleRedisCacheServiceAsync;
    private static final SnowFlake snowFlake = new SnowFlake(1L, 1L);
    @Autowired
    private SuperAdminsService superAdminsService;


    public PageInfo<SysUserGroupPageRes> getUserGroupPage(UserGroupPageReq userGroupPageReq) {
        PageHelper.startPage(userGroupPageReq.getPageNum(), userGroupPageReq.getPageSize(), "id desc");
        List<UserGroupDO> getUserGroupPage = userGroupMapper.getUserGroupPage(userGroupPageReq.getUserGroupName(), userGroupPageReq.getDisabledFlag());
        PageInfo<UserGroupDO> pageInfo = new PageInfo<>(getUserGroupPage);
        List<UserGroupDO> userGroupDOS = pageInfo.getList();
        List<SysUserGroupPageRes> sysUserGroupPageRes = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(userGroupDOS)) {
            userGroupDOS.forEach(userGroupDO -> {
                SysUserGroupPageRes sysUserGroupPag = new SysUserGroupPageRes();
                BeanUtils.copyProperties(userGroupDO, sysUserGroupPag);
                if (null != userGroupDO.getSysAccountEndTime() && null != userGroupDO.getSysAccountStartTime()) {
                    ////第一个大于第二个的情况小返回false
                    //相等 false
                    // 未过期
                    if (DateUtils.compareDate(userGroupDO.getSysAccountStartTime(), userGroupDO.getSysAccountEndTime())) {
                        String expireDtStr = DateUtils.getDatePoorTwo(userGroupDO.getSysAccountEndTime(), userGroupDO.getSysAccountStartTime());
                        sysUserGroupPag.setExpireDtStr(expireDtStr);
                    } else {
                        sysUserGroupPag.setExpireIs(true);
                        String expireDtStr = DateUtils.getDatePoorTwo(userGroupDO.getSysAccountStartTime(), userGroupDO.getSysAccountEndTime());
                        sysUserGroupPag.setExpireDtStr(expireDtStr);
                    }

                } else {
                    sysUserGroupPag.setExpireIs(true);
                }

                sysUserGroupPageRes.add(sysUserGroupPag);
            });
        }
        PageInfo<SysUserGroupPageRes> sysUserGroupPageResPageInfo = new PageInfo<SysUserGroupPageRes>(sysUserGroupPageRes);
        sysUserGroupPageResPageInfo.setTotal(pageInfo.getTotal());
        sysUserGroupPageResPageInfo.setPageNum(pageInfo.getPageNum());
        sysUserGroupPageResPageInfo.setPageSize(pageInfo.getPageSize());
        return sysUserGroupPageResPageInfo;
    }

    @Override
    public UserGroupDO detail(UserGroupPageReq userGroupPageReq) {
        UserGroupDO uuid = userGroupMapper.uuid(userGroupPageReq.getUuid());
        if (uuid == null) {
            throw BizException.fail("待编辑的企业不存在");
        }
        return uuid;
    }

    @Override
    public AclTreeRes aclDetail(UserGroupPageReq userGroupPageReq) {
        UserGroupDO uuid = userGroupMapper.uuid(userGroupPageReq.getUuid());
        if (uuid == null) {
            throw BizException.fail("待授权的企业不存在");
        }
        //角色的类型，0：管理员(老板)，1：管理员(员工) 2其他
        RoleDO getVipAdminRole = roleMapper.getVipAdminRole(uuid.getId(), RoleTypeEnum.admin.type);
        if (null == getVipAdminRole) {
            throw BizException.fail("请先为企业设置一个管理员用户");
        }
        RedisUser redisUser = this.redisUser();
        DynamicMenuAuthorReq loginAuthReq = new DynamicMenuAuthorReq();
        loginAuthReq.setRoleId(getVipAdminRole.getId());
        if (redisUser.getTenantId().equals(superAdminsService.getTeantId())) {
            return adminAuthorityService.roleTree(loginAuthReq, redisUser);
        } else {
            return commonAuthorityService.roleTree(loginAuthReq, redisUser);
        }
    }

    @Transactional
    @Override
    public String aclsToEnterprise(UserGroupPageReq userGroupPageReq) {
        UserGroupDO uuid = userGroupMapper.uuid(userGroupPageReq.getUuid());
        if (uuid == null) {
            throw BizException.fail("待授权的企业不存在");
        }
        //角色的类型，0：管理员(老板)，1：管理员(员工) 2其他
        RoleDO roleDO = roleMapper.getVipAdminRole(uuid.getId(), RoleTypeEnum.admin.type);
        if (null == roleDO) {
            throw BizException.fail("请先为企业设置一个管理员用户");
        }
        RedisUser redisUser = this.redisUser();
        //屏蔽非总部操作第三方管理员角色
        //角色的类型，0：管理员(老板)，1：管理员(员工) 2其他
        if (!redisUser.getTenantId().equals(superAdminsService.getTeantId()) && roleDO.getSysRoleType().equals(RoleTypeEnum.admin.type)) {
            throw BizException.fail("您无权限操作");
        }
        List<AclDO> getAcls = userGroupPageReq.getAcls();
        List<AclDO> redisAcls = Lists.newArrayList();
        roleService.computeAclsToRole(getAcls, roleDO, redisUser, redisAcls);
        roleRedisCacheServiceAsync.aclsToRoleToRedis(roleDO.getId(), redisAcls);
        return "为企业授权成功";
    }

    @Transactional
    @Override
    public String modify(SysUserGroupReq sysUserGroupReq) {
        UserGroupDO uuid = userGroupMapper.uuid(sysUserGroupReq.getUuid());
        if (uuid == null) {
            throw BizException.fail("待编辑的企业不存在");
        }
        checkNameExists(uuid.getUserGroupName(), uuid.getId());
        RedisUser redisUser = this.redisUser();
        Date startTime = DateUtils.stringToDate(sysUserGroupReq.getSysAccountStartTime());
        Date endTime = DateUtils.stringToDate(sysUserGroupReq.getSysAccountEndTime());
        UserGroupDO userGroupDO = new UserGroupDO();
        BeanUtils.copyProperties(sysUserGroupReq, userGroupDO);
        userGroupDO.setId(uuid.getId());
        userGroupDO.setSysAccountStartTime(startTime);
        userGroupDO.setSysAccountEndTime(endTime);
        userGroupDO.setUpdateBy(redisUser.getSysUserName());
        userGroupDO.setUpdateTime(DateUtils.getDateTime());
        userGroupMapper.updateByPrimaryKeySelective(userGroupDO);

        return "编辑企业信息成功";
    }

    public void checkNameExists(String user_group_name, Long id) {
        int checkNameExists = userGroupMapper.checkNameExists(user_group_name, id);
        if (checkNameExists > 0) {
            throw BizException.fail("企业已被注册");
        }
    }

    @Override
    public SysEnterpriseRoleRes roleList(SysEnterpriseRoleReq sysEnterpriseRoleReq) {
        UserGroupDO userGroupDO = userGroupMapper.uuid(sysEnterpriseRoleReq.getEnterpriseUuid());
        if (userGroupDO == null) {
            throw BizException.fail("企业不存在");
        }
        PageHelper.startPage(sysEnterpriseRoleReq.getPageNum(), sysEnterpriseRoleReq.getPageSize(), "id desc");
        List<RoleDO> getRoleListByEnterpriseId = roleMapper.getRoleListByEnterpriseId(userGroupDO.getId(), sysEnterpriseRoleReq.getSysRoleType(), sysEnterpriseRoleReq.getDisabledFlag(), sysEnterpriseRoleReq.getSysRoleName());
        PageInfo<RoleDO> pageInfo = new PageInfo<>(getRoleListByEnterpriseId);
        SysEnterpriseRoleRes sysEnterpriseRoleResFinal = new SysEnterpriseRoleRes();
        sysEnterpriseRoleResFinal.setSysEnterpriseName(userGroupDO.getUserGroupName());
        PageInfo<SysEnterpriseRoleRes> sysRolePageListResPageInfo = new PageInfo<>();
        sysRolePageListResPageInfo.setPageSize(pageInfo.getPageSize());
        sysRolePageListResPageInfo.setPageNum(pageInfo.getPageNum());
        List<RoleDO> roleDOS = pageInfo.getList();

        if (CollectionUtils.isEmpty(roleDOS)) {
            sysEnterpriseRoleResFinal.setSysEnterpriseRoleResPageInfo(sysRolePageListResPageInfo);
            return sysEnterpriseRoleResFinal;
        }
        RedisUser redisUser = this.redisUser();
        //是否被禁用  0否 1禁用
        List<RoleDO> roleDOList = roleMapper.getRolesByUserId(redisUser.getBaseId(), DisabledFlagEnum.start.type);
        Set<Long> roleIds = Sets.newHashSet();
        //当前登录用户是否是管理员(老板)
        boolean checkAdminRole = false;
        if (CollectionUtils.isNotEmpty(roleDOList)) {
            for (RoleDO roleDO : roleDOList) {
                roleIds.add(roleDO.getId());
                if (roleDO.getSysRoleType() == 0) {
                    checkAdminRole = true;
                }
            }

        }
        List<SysEnterpriseRoleRes> enterpriseRoleResList = Lists.newArrayList();
        for (RoleDO roleDO : roleDOS) {
            SysEnterpriseRoleRes sysEnterpriseRoleRes = new SysEnterpriseRoleRes();
            BeanUtils.copyProperties(roleDO, sysEnterpriseRoleRes);

            //管理员类型 隐藏
            //角色的类型，0：管理员(老板)，1：管理员(员工) 2其他
            if (roleDO.getSysRoleType().equals(RoleTypeEnum.admin.type)) {
                sysEnterpriseRoleRes.setEditButtonShow(false);
                sysEnterpriseRoleRes.setAuthorButtonShow(false);
                sysEnterpriseRoleRes.setDisabledFlagButtonShow(false);
            }
            if (roleDO.getSysRoleType().equals(RoleTypeEnum.admin.type)) {
                sysEnterpriseRoleRes.setEditButtonShow(false);
                sysEnterpriseRoleRes.setAuthorButtonShow(false);
                sysEnterpriseRoleRes.setDisabledFlagButtonShow(false);
            }
            // 角色列表包含自己角色则显示
            if (roleIds.contains(roleDO.getId())) {
                sysEnterpriseRoleRes.setEditButtonShow(true);
                sysEnterpriseRoleRes.setAuthorButtonShow(true);
                sysEnterpriseRoleRes.setDisabledFlagButtonShow(true);
            }
            //如果是老板，则显示自己
            if (checkAdminRole) {
                sysEnterpriseRoleRes.setEditButtonShow(true);
                sysEnterpriseRoleRes.setAuthorButtonShow(true);
                sysEnterpriseRoleRes.setDisabledFlagButtonShow(true);
            }
            //超级管理员，则显示全部
            if (superAdminsService.checkIsSuperAdmin(redisUser.getSysUserPhone())) {
                sysEnterpriseRoleRes.setEditButtonShow(true);
                sysEnterpriseRoleRes.setAuthorButtonShow(true);
                sysEnterpriseRoleRes.setDisabledFlagButtonShow(true);
            }
            enterpriseRoleResList.add(sysEnterpriseRoleRes);
        }
        sysRolePageListResPageInfo.setList(enterpriseRoleResList);
        sysEnterpriseRoleResFinal.setSysEnterpriseRoleResPageInfo(sysRolePageListResPageInfo);
        return sysEnterpriseRoleResFinal;
    }

    @Transactional
    @Override
    public String save(SysUserGroupReq sysUserGroupReq) {
        checkNameExists(sysUserGroupReq.getUserGroupName(), null);
        RedisUser redisUser = this.redisUser();
        Date startTime = DateUtils.stringToDate(sysUserGroupReq.getSysAccountStartTime());
        Date endTime = DateUtils.stringToDate(sysUserGroupReq.getSysAccountEndTime());
        UserGroupDO userGroupDO = new UserGroupDO();
        BeanUtils.copyProperties(sysUserGroupReq, userGroupDO);
        userGroupDO.setUuid(StrUtil.genUUID());
        userGroupDO.setId(snowFlake.nextId());
        userGroupDO.setSysAccountStartTime(startTime);
        userGroupDO.setSysAccountEndTime(endTime);
        userGroupDO.setUpdateBy(redisUser.getSysUserName());
        userGroupDO.setUpdateTime(DateUtils.getDateTime());
        userGroupDO.setCreateBy(redisUser.getSysUserName());
        userGroupDO.setCreateTime(DateUtils.getDateTime());
        userGroupMapper.insertSelective(userGroupDO);

        return "新增企业信息成功";
    }

    @Transactional
    @Override
    public String status(SysUserGroupReq sysUserGroupReq) {
        UserGroupDO userGroupDO = userGroupMapper.uuid(sysUserGroupReq.getUuid());
        if (userGroupDO == null) {
            throw BizException.fail("待编辑的企业不存在");
        }
        RedisUser redisUser = this.redisUser();
        userGroupDO.setDisabledFlag(sysUserGroupReq.getDisabledFlag());
        userGroupDO.setUpdateBy(redisUser.getSysUserName());
        userGroupDO.setUpdateTime(DateUtils.getDateTime());
        userGroupMapper.updateByPrimaryKeySelective(userGroupDO);
        return "状态设置成功";
    }

    @Transactional
    @Override
    public String roleAdd(SysEnterpriseRoleReq sysEnterpriseRoleReq) {
        UserGroupDO uuid = userGroupMapper.uuid(sysEnterpriseRoleReq.getEnterpriseUuid());
        if (uuid == null) {
            throw BizException.fail("角色所在的企业不存在");
        }
        RedisUser redisUser = this.redisUser();
        if (checkRoleName(sysEnterpriseRoleReq.getSysRoleName(), null, uuid.getId())) {
            throw BizException.fail("角色名称已存在");
        }
        if (!redisUser.getTenantId().equals(superAdminsService.getTeantId())) {
            //角色的类型，0：管理员角色，1：普通用户 2其他
            if (sysEnterpriseRoleReq.getSysRoleType().equals(RoleTypeEnum.superAdmin.type)) {
                if (checkAdminRole(RoleTypeEnum.superAdmin.type, null, redisUser.getTenantId())) {
                    throw BizException.fail("管理员角色已存在");
                }
            }
        }

        RoleDO record = new RoleDO();
        BeanUtils.copyProperties(sysEnterpriseRoleReq, record);
        record.setCreateBy(redisUser.getSysUserName());
        record.setUpdateBy(redisUser.getSysUserName());
        record.setCreateTime(DateUtils.getDateTime());
        record.setUpdateTime(DateUtils.getDateTime());
        record.setUuid(StrUtil.genUUID());
        record.setTenantId(uuid.getId());
        record.setId(snowFlake.nextId());
        roleMapper.insertSelective(record);

        return "新增角色成功";
    }

    @Transactional
    @Override
    public String roleStatus(SysEnterpriseRoleReq sysEnterpriseRoleReq) {
        RedisUser redisUser = this.redisUser();
        if (!redisUser.getTenantId().equals(superAdminsService.getTeantId())) {
            throw BizException.fail("您无权限操作");
        }
        UserGroupDO uuid = userGroupMapper.uuid(sysEnterpriseRoleReq.getEnterpriseUuid());
        if (uuid == null) {
            throw BizException.fail("角色所在的企业不存在");
        }
        RoleDO roleDO = roleMapper.selectByPrimaryUuid(sysEnterpriseRoleReq.getUuid());
        if (null == roleDO) {
            throw BizException.fail("待编辑的角色不存在");
        }
        RoleDO record = new RoleDO();
        BeanUtils.copyProperties(sysEnterpriseRoleReq, record);
        record.setTenantId(null);
        //是否被禁用  0否 1禁用
        record.setDisabledFlag(sysEnterpriseRoleReq.getDisabledFlag().equals(DisabledFlagEnum.start.type) ? DisabledFlagEnum.disabled.type : DisabledFlagEnum.start.type);
        record.setUpdateBy(redisUser.getSysUserName());
        record.setUpdateTime(DateUtils.getDateTime());
        record.setId(roleDO.getId());
        roleMapper.updateByPrimaryKeySelective(record);
        return "设置角色状态成功";
    }

    @Override
    public RoleDO roleDetail(SysEnterpriseRoleReq sysEnterpriseRoleReq) {
        UserGroupDO uuid = userGroupMapper.uuid(sysEnterpriseRoleReq.getEnterpriseUuid());
        if (uuid == null) {
            throw BizException.fail("角色所在的企业不存在");
        }
        RoleDO roleDO = roleMapper.selectByPrimaryUuid(sysEnterpriseRoleReq.getUuid());
        if (null == roleDO) {
            throw BizException.fail("待编辑的角色不存在");
        }
        return roleDO;
    }

    public boolean checkRoleName(String roleName, Long id, Long tenantId) {
        return roleMapper.checkRoleName(roleName, id, tenantId) > 0;
    }

    public boolean checkAdminRole(Integer roleType, Long id, Long compId) {
        return roleMapper.checkAdminRole(id, roleType, compId) > 0;
    }

    @Transactional
    @Override
    public String roleModify(SysEnterpriseRoleReq sysEnterpriseRoleReq) {
        UserGroupDO uuid = userGroupMapper.uuid(sysEnterpriseRoleReq.getEnterpriseUuid());
        if (uuid == null) {
            throw BizException.fail("角色所在的企业不存在");
        }
        RoleDO roleDO = roleMapper.selectByPrimaryUuid(sysEnterpriseRoleReq.getUuid());
        if (null == roleDO) {
            throw BizException.fail("待编辑的角色不存在");
        }
        RedisUser redisUser = this.redisUser();
        if (checkRoleName(sysEnterpriseRoleReq.getSysRoleName(), roleDO.getId(), uuid.getId())) {
            throw BizException.fail("角色名称已存在");
        }

        //角色的类型，0：管理员(老板)，1：管理员(员工) 2其他
        if (sysEnterpriseRoleReq.getSysRoleType().equals(RoleTypeEnum.superAdmin.type)) {
            if (checkAdminRole(0, roleDO.getId(), uuid.getId())) {
                throw BizException.fail("管理员角色已存在");
            }
        }

        //非总部，不可以操作管理员敏感权限
        if (!redisUser.getTenantId().equals(superAdminsService.getTeantId())) {
            //角色的类型，0：管理员角色，1：普通用户 2其他
            //屏蔽非总部操作第三方管理员角色
            if (roleDO.getSysRoleType().equals(RoleTypeEnum.superAdmin.type) && !sysEnterpriseRoleReq.getSysRoleType().equals(RoleTypeEnum.superAdmin.type)) {
                throw BizException.fail("您无权限操作管理员角色类型");
            }
            //屏蔽非总部操作第三方管理员角色状态
            //状态 0启用  1禁用
            if (roleDO.getSysRoleType().equals(RoleTypeEnum.superAdmin.type) && sysEnterpriseRoleReq.getDisabledFlag().equals(DisabledFlagEnum.disabled.type)) {
                throw BizException.fail("您无权限操作管理员角色状态");
            }
        }
        RoleDO record = new RoleDO();
        BeanUtils.copyProperties(sysEnterpriseRoleReq, record);
        record.setTenantId(null);
        record.setUpdateBy(redisUser.getSysUserName());
        record.setUpdateTime(DateUtils.getDateTime());
        record.setId(roleDO.getId());
        roleMapper.updateByPrimaryKeySelective(record);
        return "编辑角色成功";
    }

    @Override
    public AclTreeRes roleHaveAclTree(SysEnterpriseRoleReq sysEnterpriseRoleReq) {
        UserGroupDO uuid = userGroupMapper.uuid(sysEnterpriseRoleReq.getEnterpriseUuid());
        if (uuid == null) {
            throw BizException.fail("角色所在的企业不存在");
        }
        RoleDO roleDO = roleMapper.selectByPrimaryUuid(sysEnterpriseRoleReq.getUuid());
        if (null == roleDO) {
            throw BizException.fail("待编辑的角色不存在");
        }
        DynamicMenuAuthorReq loginAuthReq = new DynamicMenuAuthorReq();
        loginAuthReq.setRoleId(roleDO.getId());
        RedisUser redisUser = this.redisUser();
        if (redisUser.getTenantId().equals(superAdminsService.getTeantId())) {
            AclTreeRes aclTreeRes = adminAuthorityService.roleTree(loginAuthReq, redisUser);
            return aclTreeRes;
        } else {
            AclTreeRes aclTreeRes = commonAuthorityService.roleTree(loginAuthReq, redisUser);
            return aclTreeRes;
        }
    }

    @Override
    public String aclsToRole(BatchRoleUserReq batchRoleUserReq) {
        UserGroupDO uuid = userGroupMapper.uuid(batchRoleUserReq.getEnterpriseUuid());
        if (uuid == null) {
            throw BizException.fail("角色所在的企业不存在");
        }
        RoleDO roleDO = roleMapper.selectByPrimaryUuid(batchRoleUserReq.getRoleUuid());
        if (null == roleDO) {
            throw BizException.fail("待授权的角色不存在");
        }
        RedisUser redisUser = this.redisUser();
        //屏蔽非总部操作第三方管理员角色
        //角色的类型，0：管理员(老板)，1：管理员(员工) 2其他
        if (!redisUser.getTenantId().equals(superAdminsService.getTeantId()) && roleDO.getSysRoleType().equals(RoleTypeEnum.superAdmin.type)) {
            throw BizException.fail("您无权限操作");
        }
        //当前登录用户所拥有的 角色
        //是否被禁用  0否 1禁用
        List<RoleDO> currentRoleDOList = roleMapper.getRolesByUserId(redisUser.getBaseId(), DisabledFlagEnum.start.type);
        if (CollectionUtils.isNotEmpty(currentRoleDOList)) {
            currentRoleDOList.forEach(currentRole -> {
                if (currentRole.getId().equals(roleDO.getId())) {
                    throw BizException.fail("您无法变更自己的权限");
                }
            });
        }
        List<AclDO> getAcls = batchRoleUserReq.getAcls();

        computeAclsToRole(getAcls, roleDO, redisUser);
        return "为角色授权权限成功";
    }

    @Transactional
    public void computeAclsToRole(List<AclDO> getAcls, RoleDO roleDO, RedisUser redisUser) {
        List<AclDO> aclDOS = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(getAcls)) {
            Set<String> aclsUuid = getAcls.stream().map(AclDO::getUuid).collect(Collectors.toSet());
            aclDOS.addAll(aclMapper.aclUuids(aclsUuid));
        }
        List<Long> acls = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(aclDOS)) {
            aclDOS.forEach(roleAclDO -> {
                acls.add(roleAclDO.getId());
            });
        }
        List<Long> originAclIdList = authorityMapper.aclsByRoleId(Sets.newHashSet(roleDO.getId()), null);
        List<Long> alcIds = Lists.newArrayList();
        if (originAclIdList.size() == acls.size()) {
            Set<Long> originAclIdSet = Sets.newHashSet(originAclIdList);
            Set<Long> aclIdSet = Sets.newHashSet(acls);
            originAclIdSet.removeAll(aclIdSet);
            alcIds.addAll(originAclIdSet);
            if (CollectionUtils.isEmpty(originAclIdSet)) {
                return;
            }
        }
        updateRoleAcls(roleDO.getId(), aclDOS, redisUser, roleDO.getTenantId(), acls, roleDO.getSysRoleType(), alcIds);
    }

    @Transactional
    public void updateRoleAcls(Long roleId, List<AclDO> aclIdList, RedisUser redisUser, Long groupId, List<Long> acls, Integer roleType, List<Long> alcIds) {
        roleMapper.deleteRoleAclsByRoleId(roleId);
        if (CollectionUtils.isEmpty(aclIdList)) {
            return;
        }
        List<RoleAclDO> roleUserList = Lists.newArrayList();
        for (AclDO aclId : aclIdList) {
            RoleAclDO roleUserDO = new RoleAclDO();
            roleUserDO.setId(snowFlake.nextId());
            roleUserDO.setDelFlag(DelFlagEnum.ok.type);
            roleUserDO.setTenantId(groupId);
            roleUserDO.setSysAclId(aclId.getId());
            roleUserDO.setSysRoleId(roleId);
            roleUserDO.setSysAclPermissionCode(aclId.getSysAclPermissionCode());
            roleUserDO.setUuid(StrUtil.genUUID());
            roleUserDO.setCreateBy(redisUser.getSysUserName());
            roleUserDO.setUpdateBy(redisUser.getSysUserName());
            roleUserDO.setUpdateTime(DateUtils.getDateTime());
            roleUserDO.setCreateTime(DateUtils.getDateTime());
            roleUserList.add(roleUserDO);
        }
        roleMapper.batchInsertRoleAcls(roleUserList);
    }

    @Override
    public SysUserListRes userList(SysEnterpriseUserReq sysEnterpriseUserReq) {
        UserGroupDO uuid = userGroupMapper.uuid(sysEnterpriseUserReq.getEnterpriseUuid());
        if (uuid == null) {
            throw BizException.fail("用户所在的企业不存在");
        }
        SysUserListRes sysUserListResFianl = new SysUserListRes();
        sysUserListResFianl.setSysEnterpriseName(uuid.getUserGroupName());
        RedisUser redisUser = this.redisUser();
        PageHelper.startPage(sysEnterpriseUserReq.getPageNum(), sysEnterpriseUserReq.getPageSize(), "id desc");
        List<SysUserListDO> pageSysUserList = userMapper.pageSysUserList(uuid.getId(), sysEnterpriseUserReq.getSysUserName(), sysEnterpriseUserReq.getDisabledFlag());
        PageInfo<SysUserListDO> pageInfo = new PageInfo<>(pageSysUserList);
        List<SysUserListRes> resList = Lists.newArrayList();
        List<SysUserListDO> doList = pageInfo.getList();

        PageInfo<SysUserListRes> pageInfoRes = new PageInfo<>();
        pageInfoRes.setPageNum(pageInfo.getPageNum());
        pageInfoRes.setPageSize(pageInfo.getPageSize());
        pageInfoRes.setTotal(pageInfo.getTotal());
        if (CollectionUtils.isNotEmpty(doList)) {
            doList.forEach(sysUserListDO -> {
                SysUserListRes sysUserListRes = new SysUserListRes();
                BeanUtils.copyProperties(sysUserListDO, sysUserListRes);
                //管理员按钮是否显示
                List<RoleDO> roles = sysUserListDO.getRoles();
                Set<Integer> rolesSet = roles.stream().map(RoleDO::getSysRoleType).collect(Collectors.toSet());
                //角色的类型，0：管理员(老板)，1：管理员(员工) 2其他
                if (rolesSet.contains(RoleTypeEnum.superAdmin.type)) {
                    sysUserListRes.setEditButtonShow(false);
                    sysUserListRes.setPwdButtonShow(false);
                    sysUserListRes.setDisabledFlagButtonShow(false);
                    sysUserListRes.setRoleButtonShow(false);
                }
                if (rolesSet.contains(RoleTypeEnum.admin.type)) {
                    sysUserListRes.setEditButtonShow(false);
                    sysUserListRes.setPwdButtonShow(false);
                    sysUserListRes.setDisabledFlagButtonShow(false);
                    sysUserListRes.setRoleButtonShow(false);
                }
                //用户是自己登陆，则显示自己
                if (sysUserListDO.getId().equals(redisUser.getBaseId())) {
                    sysUserListRes.setEditButtonShow(true);
                    sysUserListRes.setPwdButtonShow(true);
                    sysUserListRes.setDisabledFlagButtonShow(true);
                    sysUserListRes.setRoleButtonShow(true);
                }
                //超级管理员，则显示全部
                if (superAdminsService.checkIsSuperAdmin(redisUser.getSysUserPhone())) {
                    sysUserListRes.setEditButtonShow(true);
                    sysUserListRes.setPwdButtonShow(true);
                    sysUserListRes.setDisabledFlagButtonShow(true);
                    sysUserListRes.setRoleButtonShow(true);
                }
                UserAccountPwdDO userAccountPwdDO = sysUserListDO.getUserAccountPwdDO();
                //密码绑定
                if (null != userAccountPwdDO) {
                    sysUserListRes.setPwdBinding(true);
                    sysUserListRes.setPwdBindingName(userAccountPwdDO.getSysUserLoginName());
                    sysUserListRes.setPwdBindingFlag(userAccountPwdDO.getDisabledFlag());
                    sysUserListRes.setPwdBindingDate(userAccountPwdDO.getCreateTime());
                }
                resList.add(sysUserListRes);
            });
            pageInfoRes.setList(resList);

            sysUserListResFianl.setSysUserListResPageInfo(pageInfoRes);
            return sysUserListResFianl;
        }
        sysUserListResFianl.setSysUserListResPageInfo(pageInfoRes);
        return sysUserListResFianl;
    }

    @Transactional
    @Override
    public String userAdd(SysEnterpriseUserReq sysEnterpriseUserReq) {
        UserGroupDO uuid = userGroupMapper.uuid(sysEnterpriseUserReq.getEnterpriseUuid());
        if (uuid == null) {
            throw BizException.fail("用户所在的企业不存在");
        }
        UserAccountPwdDO exitsUserAccountPwdDO = userAccountPwdMapper.sysUserAccountLogin(sysEnterpriseUserReq.getSysUserLoginName());
        if (exitsUserAccountPwdDO != null) {
            throw BizException.fail("登录账号已存在");
        }
        RedisUser redisUser = this.redisUser();
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(sysEnterpriseUserReq, userDO);
        Long id = snowFlake.nextId();
        userDO.setId(id);
        userDO.setSysUserEmail(sysEnterpriseUserReq.getSysUserLoginName());
        userDO.setUuid(StrUtil.genUUID());
        userDO.setTenantId(uuid.getId());
        userDO.setCreateBy(redisUser.getSysUserName());
        userDO.setUpdateBy(redisUser.getSysUserName());
        userDO.setCreateTime(DateUtils.getDateTime());
        userDO.setUpdateTime(DateUtils.getDateTime());
        userMapper.insertSelective(userDO);
        UserAccountPwdDO userAccountPwdDO = new UserAccountPwdDO();
        BeanUtils.copyProperties(sysEnterpriseUserReq, userAccountPwdDO);
        userAccountPwdDO.setId(snowFlake.nextId());
        String salt = StrUtil.genUUID();
        userAccountPwdDO.setSysUserAuthSalt(salt);
        String pwd = Encrypt.SHA512AndSHA256(sysEnterpriseUserReq.getSysUserPwd(), salt);
        userAccountPwdDO.setSysUserPwd(pwd);
        userAccountPwdDO.setTenantId(uuid.getId());
        userAccountPwdDO.setCreateBy(redisUser.getSysUserName());
        userAccountPwdDO.setUpdateBy(redisUser.getSysUserName());
        userAccountPwdDO.setUuid(StrUtil.genUUID());
        userAccountPwdDO.setSysUserId(id);
        userAccountPwdDO.setCreateTime(DateUtils.getDateTime());
        userAccountPwdDO.setUpdateTime(DateUtils.getDateTime());
        userAccountPwdMapper.insertSelective(userAccountPwdDO);
        return "新增企业用户成功";
    }

    @Override
    public UserDO userDetail(SysEnterpriseUserReq sysEnterpriseUserReq) {
        UserGroupDO uuid = userGroupMapper.uuid(sysEnterpriseUserReq.getEnterpriseUuid());
        if (uuid == null) {
            throw BizException.fail("用户所在的企业不存在");
        }
        UserDO userDODetail = userMapper.uuid(sysEnterpriseUserReq.getUuid());
        if (null == userDODetail) {
            throw BizException.fail("待查询的用户不存在");
        }
        userDODetail.setId(null);
        return userDODetail;
    }

    @Override
    public String userModify(SysEnterpriseUserReq sysEnterpriseUserReq) {
        UserGroupDO uuid = userGroupMapper.uuid(sysEnterpriseUserReq.getEnterpriseUuid());
        if (uuid == null) {
            throw BizException.fail("用户所在的企业不存在");
        }
        UserDO userDODetail = userMapper.uuid(sysEnterpriseUserReq.getUuid());
        if (null == userDODetail) {
            throw BizException.fail("待编辑的用户不存在");
        }
        RedisUser redisUser = this.redisUser();
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(sysEnterpriseUserReq, userDO);
        userDO.setSysUserName(sysEnterpriseUserReq.getSysUserName());
        userDO.setDisabledFlag(sysEnterpriseUserReq.getDisabledFlag());
        userDO.setId(userDODetail.getId());
        userDO.setUpdateBy(redisUser.getSysUserName());
        userDO.setUpdateTime(DateUtils.getDateTime());
        //超级管理员 编辑所有
        if (superAdminsService.checkIsSuperAdmin(redisUser.getSysUserPhone())) {
            userMapper.updateByPrimaryKeySelective(userDO);
            return "编辑用户信息成功";
        } else {
            //普通管理员 按需来
            if (superAdminsService.checkIsSuperAdmin(userDODetail.getSysUserPhone())) {
                throw BizException.fail("超级管理员信息不允许编辑");
            }
            //是否被禁用  0否 1禁用
            List<RoleDO> roleDOS = roleMapper.getRolesByUserId(userDODetail.getId(), DisabledFlagEnum.start.type);
            //角色的类型，0：管理员(老板)，1：管理员(员工) 2其他
            Set<Integer> roleTypes = roleDOS.stream().map(RoleDO::getSysRoleType).collect(Collectors.toSet());
            if (roleTypes.contains(0) && !userDODetail.getId().equals(redisUser.getBaseId())) {
                throw BizException.fail("管理员信息不允许编辑");
            }
            userMapper.updateByPrimaryKeySelective(userDO);
            return "编辑用户信息成功";
        }
    }

    @Override
    public SysRoleCheckedRes userCheckRoles(SysEnterpriseUserReq sysEnterpriseUserReq) {
        UserGroupDO uuid = userGroupMapper.uuid(sysEnterpriseUserReq.getEnterpriseUuid());
        if (uuid == null) {
            throw BizException.fail("用户所在的企业不存在");
        }
        UserDO userDO = userMapper.uuid(sysEnterpriseUserReq.getUuid());
        if (null == userDO) {
            throw BizException.fail("带授权的用户不存在");
        }
        RoleDO roleDODb = new RoleDO();
        RedisUser redisUser = this.redisUser();
        roleDODb.setTenantId(uuid.getId());
        //企业下所有角色
        List<RoleDO> selfRoleDOS = roleMapper.roleList(roleDODb);

        //选择用户所拥有的角色
        List<RoleDO> roleDOList = roleMapper.getRolesByUserId(userDO.getId(), null);
        Set<String> roleSet = Sets.newHashSet();
        //已选中列表
        if (CollectionUtils.isNotEmpty(roleDOList)) {
            roleSet = roleDOList.stream().map(roleDO -> String.valueOf(roleDO.getId())).collect(Collectors.toSet());
        }
        SysRoleCheckedRes roleCheckedRes = new SysRoleCheckedRes();
        List<SysRoleCheckedRes> sysRoleCheckedRes = Lists.newArrayList();
        for (RoleDO roleDO : selfRoleDOS) {
            SysRoleCheckedRes sysRoleChecke = new SysRoleCheckedRes();
            BeanUtils.copyProperties(roleDO, sysRoleChecke);
            sysRoleChecke.setIdStr(String.valueOf(roleDO.getId()));
            //是否被禁用  0否 1禁用
            if (roleDO.getDisabledFlag().equals(DisabledFlagEnum.disabled.type)) {
                sysRoleChecke.setDisabled(true);
            }
            sysRoleCheckedRes.add(sysRoleChecke);
        }
        roleCheckedRes.setRoles(sysRoleCheckedRes);
        roleCheckedRes.setCheckList(roleSet);
        return roleCheckedRes;
    }

    @Override
    public String rolesToUser(SysEnterpriseUserReq sysEnterpriseUserReq) {
        UserGroupDO uuid = userGroupMapper.uuid(sysEnterpriseUserReq.getEnterpriseUuid());
        if (uuid == null) {
            throw BizException.fail("用户所在的企业不存在");
        }
        UserDO userDO = userMapper.uuid(sysEnterpriseUserReq.getUuid());
        if (null == userDO) {
            throw BizException.fail("待授权的用户不存在");
        }
        List<Long> roles = sysEnterpriseUserReq.getRoleIds();
        List<Long> originAclIdList = authorityMapper.rolesByUserId(userDO.getId());
        if (originAclIdList.size() == roles.size()) {
            Set<Long> originAclIdSet = Sets.newHashSet(originAclIdList);
            Set<Long> aclIdSet = Sets.newHashSet(roles);
            originAclIdSet.removeAll(aclIdSet);
            if (CollectionUtils.isEmpty(originAclIdSet)) {
                return "为企业用户授权角色成功";
            }
        }
        RedisUser redisUser = this.redisUser();
        updateUserRoles(userDO.getId(), roles, redisUser, userDO.getTenantId());
        return "为企业用户授权角色成功";
    }

    @Override
    public String userStatus(SysEnterpriseUserReq sysEnterpriseUserReq) {
        UserGroupDO uuid = userGroupMapper.uuid(sysEnterpriseUserReq.getEnterpriseUuid());
        if (uuid == null) {
            throw BizException.fail("用户所在的企业不存在");
        }
        UserDO userDODetail = userMapper.uuid(sysEnterpriseUserReq.getUuid());
        if (null == userDODetail) {
            throw BizException.fail("待编辑的用户信息不存在");
        }
        RedisUser redisUser = this.redisUser();
        UserDO userDO = new UserDO();
        userDO.setDisabledFlag(sysEnterpriseUserReq.getDisabledFlag());
        userDO.setId(userDODetail.getId());
        userDO.setUpdateBy(redisUser.getSysUserName());
        userDO.setUpdateTime(DateUtils.getDateTime());
        //超级管理员 编辑所有
        if (superAdminsService.checkIsSuperAdmin(redisUser.getSysUserPhone())) {
            userMapper.updateByPrimaryKeySelective(userDO);
            return "用户状态设置成功";
        } else {
            //普通管理员 按需来
            if (superAdminsService.checkIsSuperAdmin(userDODetail.getSysUserPhone())) {
                throw BizException.fail("超级管理员状态不允许编辑");
            }
            List<RoleDO> roleDOS = roleMapper.getRolesByUserId(userDODetail.getId(), DisabledFlagEnum.start.type);
            //角色的类型，0：管理员(老板)，1：管理员(员工) 2其他
            Set<Integer> roleTypes = roleDOS.stream().map(RoleDO::getSysRoleType).collect(Collectors.toSet());
            if (roleTypes.contains(0) && !userDODetail.getId().equals(redisUser.getBaseId())) {
                throw BizException.fail("管理员状态不允许编辑");
            }
            userMapper.updateByPrimaryKeySelective(userDO);
            return "用户状态设置成功";
        }
    }

    @Override
    public String sysUserPwd(SysEnterpriseUserReq sysEnterpriseUserReq) {
        UserGroupDO uuid = userGroupMapper.uuid(sysEnterpriseUserReq.getEnterpriseUuid());
        if (uuid == null) {
            throw BizException.fail("用户所在的企业不存在");
        }
        UserDO userDODetail = userMapper.uuid(sysEnterpriseUserReq.getUuid());
        if (null == userDODetail) {
            throw BizException.fail("待编辑的用户不存在");
        }
        UserAccountPwdDO sysUserAccountByUserId = userAccountPwdMapper.sysUserAccountByUserId(userDODetail.getId());
        UserAccountPwdDO userAccountPwdDO = new UserAccountPwdDO();
        userAccountPwdDO.setSysUserPwd(sysEnterpriseUserReq.getSysUserPwd());
        String salt = StrUtil.genUUID();
        userAccountPwdDO.setSysUserAuthSalt(salt);
        String pwd = Encrypt.SHA512AndSHA256(sysEnterpriseUserReq.getSysUserPwd(), salt);
        userAccountPwdDO.setSysUserPwd(pwd);
        userAccountPwdDO.setId(sysUserAccountByUserId.getId());
        RedisUser redisUser = this.redisUser();
        //超级管理员 编辑所有
        if (superAdminsService.checkIsSuperAdmin(redisUser.getSysUserPhone())) {
            userAccountPwdMapper.updateByPrimaryKeySelective(userAccountPwdDO);
            return "修改密码成功";
        } else {
            //普通管理员 按需来
            if (superAdminsService.checkIsSuperAdmin(userDODetail.getSysUserPhone())) {
                throw BizException.fail("超级管理员密码不允许编辑");
            }
            List<RoleDO> roleDOS = roleMapper.getRolesByUserId(userDODetail.getId(), DisabledFlagEnum.start.type);
            //角色的类型，0：管理员(老板)，1：管理员(员工) 2其他
            Set<Integer> roleTypes = roleDOS.stream().map(RoleDO::getSysRoleType).collect(Collectors.toSet());
            if (roleTypes.contains(RoleTypeEnum.superAdmin.type) && !userDODetail.getId().equals(redisUser.getBaseId())) {
                throw BizException.fail("管理员密码不允许编辑");
            }
            userAccountPwdMapper.updateByPrimaryKeySelective(userAccountPwdDO);
            return "修改密码成功";
        }
    }

    @Transactional
    public void updateUserRoles(Long userId, List<Long> roleIdList, RedisUser redisUser, Long tenantId) {
        roleMapper.deleteUserRolesByUserId(userId);

        if (CollectionUtils.isEmpty(roleIdList)) {
            return;
        }
        List<RoleUserDO> roleUserList = Lists.newArrayList();
        for (Long aclId : roleIdList) {
            RoleUserDO roleUserDO = new RoleUserDO();
            roleUserDO.setId(snowFlake.nextId());
            roleUserDO.setUuid(StrUtil.genUUID());
            roleUserDO.setRoleId(aclId);
            roleUserDO.setTenantId(tenantId);
            roleUserDO.setUserId(userId);
            roleUserDO.setCreateTime(DateUtils.getDateTime());
            roleUserDO.setCreateBy(redisUser.getSysUserName());
            roleUserDO.setUpdateBy(redisUser.getSysUserName());
            roleUserDO.setUpdateTime(DateUtils.getDateTime());
            roleUserList.add(roleUserDO);
        }
        roleMapper.batchInsertUserRoles(roleUserList);
    }
}

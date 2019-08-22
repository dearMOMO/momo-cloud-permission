package com.momo.service.service.aclmanager.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.momo.common.util.DateUtils;
import com.momo.common.error.BizException;
import com.momo.common.util.StrUtil;
import com.momo.common.util.snowFlake.SnowFlake;
import com.momo.mapper.dataobject.AclDO;
import com.momo.mapper.dataobject.RoleDO;
import com.momo.mapper.dataobject.UserGroupDO;
import com.momo.mapper.mapper.manual.RoleMapper;
import com.momo.mapper.mapper.manual.UserGroupMapper;
import com.momo.mapper.req.aclmanager.SysEnterpriseRoleReq;
import com.momo.mapper.req.aclmanager.SysUserGroupReq;
import com.momo.mapper.req.aclmanager.UserGroupPageReq;
import com.momo.mapper.req.sysmain.LoginAuthReq;
import com.momo.mapper.req.sysmain.RedisUser;
import com.momo.mapper.res.aclmanager.SysEnterpriseRoleRes;
import com.momo.mapper.res.aclmanager.SysRolePageListRes;
import com.momo.mapper.res.aclmanager.SysUserGroupPageRes;
import com.momo.mapper.res.authority.AclTreeRes;
import com.momo.service.service.BaseService;
import com.momo.service.service.aclmanager.SysEnterpriseService;
import com.momo.service.service.authority.AdminAuthorityService;
import com.momo.service.service.authority.CommonAuthorityService;
import com.momo.service.service.authority.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
    private AdminAuthorityService adminAuthorityService;
    @Autowired
    private CommonAuthorityService commonAuthorityService;
    @Autowired
    private RoleService roleService;
    private SnowFlake snowFlake = new SnowFlake(1L, 1L);
    @Value("${momo.superAdmins}")
    private String superAdmins = "";


    public PageInfo<SysUserGroupPageRes> getUserGroupPage(UserGroupPageReq userGroupPageReq) {
        PageHelper.startPage(userGroupPageReq.getPageNum(), userGroupPageReq.getPageSize(), "id desc");
        List<UserGroupDO> getUserGroupPage = userGroupMapper.getUserGroupPage(userGroupPageReq.getUserGroupName(), userGroupPageReq.getFlag());
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
        RoleDO getVipAdminRole = roleMapper.getVipAdminRole(uuid.getId(), 0);
        if (null == getVipAdminRole) {
            throw BizException.fail("请先为企业设置一个管理员用户");
        }
        RedisUser redisUser = this.redisUser();
        LoginAuthReq loginAuthReq = new LoginAuthReq();
        loginAuthReq.setRoleId(getVipAdminRole.getId());
        if (redisUser.getTenantId().equals(1L)) {
            AclTreeRes aclTreeRes = adminAuthorityService.roleTree(loginAuthReq, redisUser);
            return aclTreeRes;
        } else {
            AclTreeRes aclTreeRes = commonAuthorityService.roleTree(loginAuthReq, redisUser);
            return aclTreeRes;
        }
    }

    @Override
    public String aclsToEnterprise(UserGroupPageReq userGroupPageReq) {
        UserGroupDO uuid = userGroupMapper.uuid(userGroupPageReq.getUuid());
        if (uuid == null) {
            throw BizException.fail("待授权的企业不存在");
        }
        //角色的类型，0：管理员(老板)，1：管理员(员工) 2其他
        RoleDO roleDO = roleMapper.getVipAdminRole(uuid.getId(), 0);
        if (null == roleDO) {
            throw BizException.fail("请先为企业设置一个管理员用户");
        }
        RedisUser redisUser = this.redisUser();
        //屏蔽非总部操作第三方管理员角色
        //角色的类型，0：管理员(老板)，1：管理员(员工) 2其他
        if (!redisUser.getTenantId().equals(1L) && roleDO.getSysRoleType().equals(0)) {
            throw BizException.fail("您无权限操作");
        }
        List<AclDO> getAcls = userGroupPageReq.getAcls();
        roleService.computeAclsToRole(getAcls, roleDO, redisUser);
        return "未企业授权成功";
    }

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
        UserGroupDO userGroupDO = userGroupMapper.uuid(sysEnterpriseRoleReq.getUuid());
        if (userGroupDO == null) {
            throw BizException.fail("企业不存在");
        }
        PageHelper.startPage(sysEnterpriseRoleReq.getPageNum(), sysEnterpriseRoleReq.getPageSize(), "id desc");
        List<RoleDO> getRoleListByEnterpriseId = roleMapper.getRoleListByEnterpriseId(userGroupDO.getId(), sysEnterpriseRoleReq.getSysRoleType(), sysEnterpriseRoleReq.getFlag(), sysEnterpriseRoleReq.getSysRoleName());
        PageInfo<RoleDO> pageInfo = new PageInfo<>(getRoleListByEnterpriseId);
        SysEnterpriseRoleRes sysEnterpriseRoleResFinal = new SysEnterpriseRoleRes();
        sysEnterpriseRoleResFinal.setSysEnterpriseName(userGroupDO.getUserGroupName());
        PageInfo<SysEnterpriseRoleRes> sysRolePageListResPageInfo = new PageInfo<>();
        sysRolePageListResPageInfo.setPageSize(pageInfo.getPageSize());
        sysRolePageListResPageInfo.setPageNum(pageInfo.getPageNum());
        List<RoleDO> roleDOS = pageInfo.getList();

        if (org.apache.commons.collections.CollectionUtils.isEmpty(roleDOS)) {
            return sysEnterpriseRoleResFinal;
        }
        RedisUser redisUser = this.redisUser();
        List<RoleDO> roleDOList = roleMapper.getRolesByUserId(redisUser.getBaseId());
        Set<Long> roleIds = Sets.newHashSet();
        //当前登录用户是否是管理员(老板)
        boolean checkAdminRole = false;
        if (org.apache.commons.collections.CollectionUtils.isNotEmpty(roleDOList)) {
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
            if (roleDO.getSysRoleType().equals(0)) {
                sysEnterpriseRoleRes.setEditButton(false);
                sysEnterpriseRoleRes.setAuthorButton(false);
                sysEnterpriseRoleRes.setFlagButton(false);
            }
            if (roleDO.getSysRoleType().equals(1)) {
                sysEnterpriseRoleRes.setEditButton(false);
                sysEnterpriseRoleRes.setAuthorButton(false);
                sysEnterpriseRoleRes.setFlagButton(false);
            }
            // 角色列表包含自己角色则显示
            if (roleIds.contains(roleDO.getId())) {
                sysEnterpriseRoleRes.setEditButton(true);
                sysEnterpriseRoleRes.setAuthorButton(true);
                sysEnterpriseRoleRes.setFlagButton(true);
            }
            //如果是老板，则显示自己
            if (checkAdminRole) {
                sysEnterpriseRoleRes.setEditButton(true);
                sysEnterpriseRoleRes.setAuthorButton(true);
                sysEnterpriseRoleRes.setFlagButton(true);
            }
            //超级管理员，则显示全部
            if (superAdmins.contains(redisUser.getSysUserPhone())) {
                sysEnterpriseRoleRes.setEditButton(true);
                sysEnterpriseRoleRes.setAuthorButton(true);
                sysEnterpriseRoleRes.setFlagButton(true);
            }
            enterpriseRoleResList.add(sysEnterpriseRoleRes);
        }
        sysRolePageListResPageInfo.setList(enterpriseRoleResList);
        sysEnterpriseRoleResFinal.setSysEnterpriseRoleResPageInfo(sysRolePageListResPageInfo);
        return sysEnterpriseRoleResFinal;
    }

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

    @Override
    public String status(SysUserGroupReq sysUserGroupReq) {
        UserGroupDO uuid = userGroupMapper.uuid(sysUserGroupReq.getUuid());
        if (uuid == null) {
            throw BizException.fail("待编辑的企业不存在");
        }
        RedisUser redisUser = this.redisUser();
        Date startTime = DateUtils.stringToDate(sysUserGroupReq.getSysAccountStartTime());
        Date endTime = DateUtils.stringToDate(sysUserGroupReq.getSysAccountEndTime());
        UserGroupDO userGroupDO = new UserGroupDO();
        userGroupDO.setId(uuid.getId());
        userGroupDO.setFlag(sysUserGroupReq.getFlag());
        userGroupDO.setUpdateBy(redisUser.getSysUserName());
        userGroupDO.setUpdateTime(DateUtils.getDateTime());
        userGroupMapper.insertSelective(userGroupDO);
        return "状态设置成功";
    }

    @Override
    public String roleAdd(SysEnterpriseRoleReq sysEnterpriseRoleReq) {
        UserGroupDO uuid = userGroupMapper.uuid(sysEnterpriseRoleReq.getEnterpriseUuid());
        if (uuid == null) {
            throw BizException.fail("角色所在的企业不存在");
        }
        RedisUser redisUser = this.redisUser();
        if (checkRoleName(sysEnterpriseRoleReq.getSysRoleName(), null, redisUser.getTenantId())) {
            throw BizException.fail("角色名称已存在");
        }
        if (!redisUser.getTenantId().equals(1L)) {
            //角色的类型，0：管理员角色，1：普通用户 2其他
            if (sysEnterpriseRoleReq.getSysRoleType().equals(0)) {
                if (checkAdminRole("0", null, redisUser.getTenantId())) {
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
        return roleMapper.checkRoleName(roleName, id, tenantId) > 0 ? true : false;
    }

    public boolean checkAdminRole(String roleType, Long id, Long compId) {
        return roleMapper.checkAdminRole(id, roleType, compId) > 0 ? true : false;
    }

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
        if (checkRoleName(sysEnterpriseRoleReq.getSysRoleName(), roleDO.getId(), redisUser.getTenantId())) {
            throw BizException.fail("角色名称已存在");
        }

        //非总部，不可以操作管理员敏感权限
        if (!redisUser.getTenantId().equals(1L)) {
            //角色的类型，0：管理员角色，1：普通用户 2其他
            if (sysEnterpriseRoleReq.getSysRoleType().equals(0)) {
                if (checkAdminRole("0", null, redisUser.getTenantId())) {
                    throw BizException.fail("管理员角色已存在");
                }
            }

            //角色的类型，0：管理员角色，1：普通用户 2其他
            //屏蔽非总部操作第三方管理员角色
            if (roleDO.getSysRoleType().equals(0) && sysEnterpriseRoleReq.getSysRoleType().equals(1)) {
                throw BizException.fail("您无权限操作管理员角色类型");
            }
            //屏蔽非总部操作第三方管理员角色状态
            //状态 0启用  1禁用
            if ((roleDO.getSysRoleType().equals(0) && sysEnterpriseRoleReq.getFlag().equals(1))) {
                throw BizException.fail("您无权限操作管理员角色状态");
            }
        }
        RoleDO record = new RoleDO();
        BeanUtils.copyProperties(sysEnterpriseRoleReq, record);
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
        LoginAuthReq loginAuthReq = new LoginAuthReq();

        RedisUser redisUser = this.redisUser();
        if (redisUser.getTenantId().equals(1L)) {
            AclTreeRes aclTreeRes = adminAuthorityService.roleTree(loginAuthReq, redisUser);
            return aclTreeRes;
        } else {
            AclTreeRes aclTreeRes = commonAuthorityService.roleTree(loginAuthReq, redisUser);
            return aclTreeRes;
        }
    }
}

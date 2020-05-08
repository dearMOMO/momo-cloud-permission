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
package com.momo.service.service.authority;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.momo.common.core.entity.RedisUser;
import com.momo.common.core.error.BizException;
import com.momo.common.core.util.DateUtils;
import com.momo.common.core.util.StrUtil;
import com.momo.common.core.util.snowFlake.SnowFlake;
import com.momo.mapper.dataobject.*;
import com.momo.mapper.enums.DelFlagEnum;
import com.momo.mapper.enums.DisabledFlagEnum;
import com.momo.mapper.enums.RoleTypeEnum;
import com.momo.mapper.mapper.manual.AclMapper;
import com.momo.mapper.mapper.manual.AuthorityMapper;
import com.momo.mapper.mapper.manual.RoleMapper;
import com.momo.mapper.mapper.manual.UserMapper;
import com.momo.mapper.req.authority.BatchRoleUserReq;
import com.momo.mapper.req.authority.RoleReq;
import com.momo.mapper.req.sysmain.DynamicMenuAuthorReq;
import com.momo.mapper.res.aclmanager.DisabledAdminRoleButtonRes;
import com.momo.mapper.res.aclmanager.SysRoleCheckedRes;
import com.momo.mapper.res.aclmanager.SysRolePageListRes;
import com.momo.mapper.res.authority.AclTreeRes;
import com.momo.mapper.res.authority.CheckTwoSetSizeRes;
import com.momo.service.async.RoleRedisCacheServiceAsync;
import com.momo.service.service.BaseService;
import com.momo.service.service.SuperAdminsService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    private AclMapper aclMapper;
    @Autowired
    private AdminAuthorityService adminAuthorityService;
    @Autowired
    private CommonAuthorityService commonAuthorityService;
    @Autowired
    private SuperAdminsService superAdminsService;
    @Autowired
    private RoleRedisCacheServiceAsync roleRedisCacheServiceAsync;
    private static final SnowFlake snowFlake = new SnowFlake(1, 1);

    /**
     * 角色给用户(授权)
     *
     * @param batchRoleUserReq
     * @return
     */
    public String rolesToUser(BatchRoleUserReq batchRoleUserReq) {
        RedisUser redisUser = this.redisUser();
        //当前登录用户所拥有的 角色
        //是否被禁用  0否 1禁用
        List<RoleDO> currentRoleDOList = roleMapper.getRolesByUserId(redisUser.getBaseId(), DisabledFlagEnum.start.type);
        UserDO userDO = userMapper.uuid(batchRoleUserReq.getUserUuid());
        if (null == userDO) {
            throw BizException.fail("待授权的用户不存在");
        }
        boolean superAdmin = superAdminsService.checkIsSuperAdmin(redisUser.getSysUserPhone());
        //除去超级管理员
        if (!superAdmin && userDO.getId().equals(redisUser.getBaseId())) {
            throw BizException.fail("您无法为自己授权角色");
        }
        //当前登录用户所拥有的 角色类型
        Set<Integer> currentLoginRoleType = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(currentRoleDOList)) {
            currentLoginRoleType = currentRoleDOList.stream().map(RoleDO::getSysRoleType).collect(Collectors.toSet());
        }
        Set<Long> roleIdList = Sets.newHashSet();
        //为用户授权所拥有的 角色
        List<RoleDO> roleDOList = batchRoleUserReq.getRoles();
        //为用户授权所拥有的 角色类型
        Set<Integer> authorRoleType = Sets.newHashSet();

        if (CollectionUtils.isNotEmpty(roleDOList)) {
            Set<String> roleUuids = roleDOList.stream().map(RoleDO::getUuid).collect(Collectors.toSet());
            authorRoleType = roleDOList.stream().map(RoleDO::getSysRoleType).collect(Collectors.toSet());
            List<RoleDO> selectByPrimaryUuids = roleMapper.selectByPrimaryUuids(roleUuids);
            if (CollectionUtils.isNotEmpty(selectByPrimaryUuids)) {
                roleIdList = selectByPrimaryUuids.stream().map(RoleDO::getId).collect(Collectors.toSet());
            }
        }
        if (!superAdmin) {
            if (CheckTwoSetSizeRes.CheckTwoSetSize(currentLoginRoleType, authorRoleType)) {
                throw BizException.fail("您当前拥有的角色类型较低，请联系管理员进行授权");
            }
        }

        List<Long> originAclIdList = authorityMapper.rolesByUserId(userDO.getId());
        if (originAclIdList.size() == roleIdList.size()) {
            Set<Long> originAclIdSet = Sets.newHashSet(originAclIdList);
            Set<Long> aclIdSet = Sets.newHashSet(roleIdList);
            originAclIdSet.removeAll(aclIdSet);
            if (CollectionUtils.isEmpty(originAclIdSet)) {
                return "为用户授权角色成功";
            }
        }
        updateUserRoles(userDO.getId(), roleIdList, redisUser, userDO.getTenantId());
        roleRedisCacheServiceAsync.rolesToUserToRedis(userDO.getId(), roleIdList);
        return "为用户授权角色成功";
    }

    /**
     * 为角色授权权限
     *
     * @param batchRoleUserReq
     * @return
     */
    public String aclsToRole(BatchRoleUserReq batchRoleUserReq) {
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
        List<AclDO> redisAcls = Lists.newArrayList();
        computeAclsToRole(getAcls, roleDO, redisUser, redisAcls);
        roleRedisCacheServiceAsync.aclsToRoleToRedis(roleDO.getId(), redisAcls);
        return "为角色授权权限成功";
    }

    @Transactional
    public void computeAclsToRole(List<AclDO> getAcls, RoleDO roleDO, RedisUser redisUser, List<AclDO> redisAcls) {
        List<AclDO> aclDOS = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(getAcls)) {
            Set<String> aclsUuid = getAcls.stream().map(AclDO::getUuid).collect(Collectors.toSet());
            List<AclDO> aclUuids = aclMapper.aclUuids(aclsUuid);
            if (CollectionUtils.isNotEmpty(aclUuids)) {
                aclDOS.addAll(aclUuids);
                redisAcls.addAll(aclUuids);
            }
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

    /**
     * 为角色授权 权限 之前， 需要查看该角色拥有哪些权限点，以及当前登录用户可以操作哪些权限
     *
     * @param loginAuthReq
     * @return
     */
    public AclTreeRes roleHaveAclTree(DynamicMenuAuthorReq loginAuthReq) {
        RoleDO roleDO = roleMapper.selectByPrimaryUuid(loginAuthReq.getUuid());
        if (null == roleDO) {
            throw BizException.fail("待授权的角色不存在");
        }
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

    public List<RoleDO> rolesByCurrentId(RedisUser redisUser) {
        return roleMapper.rolesByCurrentId(redisUser.getBaseId(), DisabledFlagEnum.start.type);
    }

    /**
     * 角色列表
     *
     * @param roleReq
     * @return
     */
    public PageInfo<SysRolePageListRes> roleList(RoleReq roleReq) {
        RoleDO roleDO = new RoleDO();
        BeanUtils.copyProperties(roleReq, roleDO);
        RedisUser redisUser = this.redisUser();
        roleDO.setTenantId(redisUser.getTenantId());
        PageHelper.startPage(roleReq.getPageNum(), roleReq.getPageSize(), "id desc");
        List<RoleDO> roleList = roleMapper.roleList(roleDO);
        PageInfo<RoleDO> pageInfo = new PageInfo<>(roleList);
        PageInfo<SysRolePageListRes> sysRolePageListResPageInfo = new PageInfo<>();
        sysRolePageListResPageInfo.setPageSize(pageInfo.getPageSize());
        sysRolePageListResPageInfo.setPageNum(pageInfo.getPageNum());
        List<RoleDO> roleDOS = pageInfo.getList();

        if (CollectionUtils.isEmpty(roleDOS)) {
            return sysRolePageListResPageInfo;
        }
        //是否被禁用  0否 1禁用
        List<RoleDO> roleDOList = roleMapper.getRolesByUserId(redisUser.getBaseId(), DisabledFlagEnum.start.type);

        //当前登录用户所拥有的 角色类型
        Set<Integer> currentLoginRoleType = Sets.newHashSet();
        Set<Long> roleIds = Sets.newHashSet();
        //当前登录用户是否是管理员(老板)
        boolean checkAdminRole = false;
        if (CollectionUtils.isNotEmpty(roleDOList)) {
            for (RoleDO aDo : roleDOList) {
                roleIds.add(aDo.getId());
                currentLoginRoleType.add(aDo.getSysRoleType());
                if (aDo.getSysRoleType().equals(RoleTypeEnum.superAdmin.type)) {
                    checkAdminRole = true;
                }
            }

        }
        List<SysRolePageListRes> sysRolePageListRes = Lists.newArrayList();
        if (redisUser.getTenantId().equals(superAdminsService.getTeantId())) {
            boolean superAdmin = superAdminsService.checkIsSuperAdmin(redisUser.getSysUserPhone());
            momoRoleList(sysRolePageListRes, roleDOS, roleIds, checkAdminRole, superAdmin, currentLoginRoleType);
        } else {
            vipRoleList(sysRolePageListRes, roleDOS, roleIds, checkAdminRole, currentLoginRoleType);
        }

        sysRolePageListResPageInfo.setList(sysRolePageListRes);
        return sysRolePageListResPageInfo;
    }

    private void vipRoleList(List<SysRolePageListRes> sysRolePageListRes, List<RoleDO> roleDOS, Set<Long> roleIds, boolean checkAdminRole, Set<Integer> currentLoginRoleType) {
        for (RoleDO aDo : roleDOS) {
            SysRolePageListRes rolePageListRes = new SysRolePageListRes();
            BeanUtils.copyProperties(aDo, rolePageListRes);
            //管理员类型 隐藏
            //角色的类型，0：管理员(老板)，1：管理员(员工) 2其他
            if (aDo.getSysRoleType().equals(RoleTypeEnum.superAdmin.type)) {
                rolePageListRes.setEditButtonShow(false);
                rolePageListRes.setAuthorButtonShow(false);
                rolePageListRes.setDisabledFlagButtonShow(false);
            }
            if (aDo.getSysRoleType().equals(RoleTypeEnum.admin.type)) {
                rolePageListRes.setEditButtonShow(false);
                rolePageListRes.setAuthorButtonShow(false);
                rolePageListRes.setDisabledFlagButtonShow(false);
            }
            //当前登录用户所拥有的角色类型大于 角色列表  则显示
            if (!CheckTwoSetSizeRes.CheckTwoSetSize(currentLoginRoleType, Sets.newHashSet(aDo.getSysRoleType()))) {
                rolePageListRes.setEditButtonShow(true);
                rolePageListRes.setAuthorButtonShow(true);
                rolePageListRes.setDisabledFlagButtonShow(true);
            }
            // 角色列表包含自己角色则显示
            if (roleIds.contains(aDo.getId())) {
                rolePageListRes.setEditButtonShow(true);
                rolePageListRes.setAuthorButtonShow(true);
                rolePageListRes.setDisabledFlagButtonShow(true);
            }
            //如果是老板，则全部显示
            if (checkAdminRole) {
                rolePageListRes.setEditButtonShow(true);
                rolePageListRes.setAuthorButtonShow(true);
                rolePageListRes.setDisabledFlagButtonShow(true);
            }
            //屏蔽管理员(老板)角色 和状态  按钮
            if (aDo.getSysRoleType().equals(RoleTypeEnum.superAdmin.type)) {
                rolePageListRes.setAuthorButtonShow(false);
                rolePageListRes.setDisabledFlagButtonShow(false);
            }
            sysRolePageListRes.add(rolePageListRes);
        }
    }

    private void momoRoleList(List<SysRolePageListRes> sysRolePageListRes, List<RoleDO> roleDOS, Set<Long> roleIds, boolean checkAdminRole, boolean superAdmin, Set<Integer> currentLoginRoleType) {
        for (RoleDO aDo : roleDOS) {
            SysRolePageListRes rolePageListRes = new SysRolePageListRes();
            BeanUtils.copyProperties(aDo, rolePageListRes);
            //管理员类型 隐藏
            //角色的类型，0：管理员(老板)，1：管理员(员工) 2其他
            if (aDo.getSysRoleType().equals(RoleTypeEnum.superAdmin.type)) {
                rolePageListRes.setEditButtonShow(false);
                rolePageListRes.setAuthorButtonShow(false);
                rolePageListRes.setDisabledFlagButtonShow(false);
            }
            if (aDo.getSysRoleType().equals(RoleTypeEnum.admin.type)) {
                rolePageListRes.setEditButtonShow(false);
                rolePageListRes.setAuthorButtonShow(false);
                rolePageListRes.setDisabledFlagButtonShow(false);
            }
            //当前登录用户所拥有的角色类型大于 角色列表  则显示
            if (!CheckTwoSetSizeRes.CheckTwoSetSize(currentLoginRoleType, Sets.newHashSet(aDo.getSysRoleType()))) {
                rolePageListRes.setEditButtonShow(true);
                rolePageListRes.setAuthorButtonShow(true);
                rolePageListRes.setDisabledFlagButtonShow(true);
            }
            // 角色列表包含自己角色则显示
            if (roleIds.contains(aDo.getId())) {
                rolePageListRes.setEditButtonShow(true);
                rolePageListRes.setAuthorButtonShow(true);
                rolePageListRes.setDisabledFlagButtonShow(true);
            }
            //如果是老板，则显示自己
            if (checkAdminRole) {
                rolePageListRes.setEditButtonShow(true);
                rolePageListRes.setAuthorButtonShow(true);
                rolePageListRes.setDisabledFlagButtonShow(true);
            }
            //超级管理员，则显示全部
            if (superAdmin) {
                rolePageListRes.setEditButtonShow(true);
                rolePageListRes.setAuthorButtonShow(true);
                rolePageListRes.setDisabledFlagButtonShow(true);
            }
            sysRolePageListRes.add(rolePageListRes);
        }
    }

    /**
     * 角色详情
     *
     * @param roleReq
     * @return
     */
    public RoleDO showRole(RoleReq roleReq) {
        RoleDO roleDO = roleMapper.selectByPrimaryUuid(roleReq.getUuid());
        if (null == roleDO) {
            throw BizException.fail("待编辑的角色不存在");
        }
        return roleDO;
    }

    /**
     * 是否禁用管理员按钮
     *
     * @return
     */
    public DisabledAdminRoleButtonRes disabledAdminRoleButton() {
        RedisUser redisUser = this.redisUser();
        DisabledAdminRoleButtonRes disabledAdminRoleButtonRes = new DisabledAdminRoleButtonRes();
        if (redisUser.getTenantId().equals(superAdminsService.getTeantId())) {
            disabledAdminRoleButtonRes.setDisabledAdminRoleButton(false);
            return disabledAdminRoleButtonRes;
        }
        return disabledAdminRoleButtonRes;
    }

    /**
     * 角色给用户(回显)
     *
     * @param roleReq
     * @return
     */
    public SysRoleCheckedRes userCheckedRoles(RoleReq roleReq) {
        UserDO userDO = userMapper.uuid(roleReq.getUuid());
        if (null == userDO) {
            throw BizException.fail("带授权的用户不存在");
        }
        RoleDO roleDODb = new RoleDO();
        RedisUser redisUser = this.redisUser();
        roleDODb.setTenantId(redisUser.getTenantId());
        //企业下所有角色
        List<RoleDO> selfRoleDOS = roleMapper.roleList(roleDODb);

        //当前登录用户所拥有的角色
        //是否被禁用  0否 1禁用
        List<RoleDO> currentRoleDOList = roleMapper.getRolesByUserId(redisUser.getBaseId(), DisabledFlagEnum.start.type);
        Set<Integer> currentRoleSet = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(currentRoleDOList)) {
            currentRoleSet = currentRoleDOList.stream().map(RoleDO::getSysRoleType).collect(Collectors.toSet());
        }
        //选择用户所拥有的角色
        //是否被禁用  0否 1禁用
        List<RoleDO> roleDOList = roleMapper.getRolesByUserId(userDO.getId(), DisabledFlagEnum.start.type);
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
            //非总部为企业授权，则屏蔽管理员(老板)角色
            //角色的类型，0：管理员(老板)，1：管理员(员工)  2:普通员工 3:其他
            //防止将 管理员(老板)角色 授权给多个用户，造成系统混乱
            if (!redisUser.getTenantId().equals(superAdminsService.getTeantId())) {
                if (roleDO.getSysRoleType().equals(RoleTypeEnum.superAdmin.type)) {
                    sysRoleChecke.setDisabled(true);
                }
                if (currentRoleSet.contains(0)) {
                    sysRoleCheckedRes.add(sysRoleChecke);
                } else if (currentRoleSet.contains(1) && (roleDO.getSysRoleType().equals(1) || roleDO.getSysRoleType().equals(2) || roleDO.getSysRoleType().equals(3))) {
                    sysRoleCheckedRes.add(sysRoleChecke);
                } else if ((currentRoleSet.contains(2) || currentRoleSet.contains(3)) && (roleDO.getSysRoleType().equals(2) || roleDO.getSysRoleType().equals(3))) {
                    sysRoleCheckedRes.add(sysRoleChecke);
                } else if (CollectionUtils.isEmpty(currentRoleSet) && (roleDO.getSysRoleType().equals(2) || roleDO.getSysRoleType().equals(3))) {
                    sysRoleCheckedRes.add(sysRoleChecke);
                }
            } else {
                sysRoleChecke.setDisabled(true);
                sysRoleCheckedRes.add(sysRoleChecke);
                //超级管理员，则显示全部
                if (superAdminsService.checkIsSuperAdmin(redisUser.getSysUserPhone())) {
                    sysRoleChecke.setDisabled(false);
                    continue;
                }
                //角色类型为 0：管理员(老板) 或者 1：管理员(员工)
                //角色的类型，0：管理员(老板)，1：管理员(员工)  2:普通员工 3:其他
                if (currentRoleSet.contains(RoleTypeEnum.superAdmin.type)) {
                    sysRoleChecke.setDisabled(false);
                    continue;
                }
                if (currentRoleSet.contains(RoleTypeEnum.admin.type)) {
                    sysRoleChecke.setDisabled(false);
                    continue;
                }
                if ((currentRoleSet.contains(RoleTypeEnum.common.type) || currentRoleSet.contains(RoleTypeEnum.other.type)) && (roleDO.getSysRoleType().equals(RoleTypeEnum.common.type) || roleDO.getSysRoleType().equals(RoleTypeEnum.other.type))) {
                    sysRoleChecke.setDisabled(false);
                    continue;
                }
                if (CollectionUtils.isEmpty(currentRoleSet) && (roleDO.getSysRoleType().equals(RoleTypeEnum.common.type) || roleDO.getSysRoleType().equals(RoleTypeEnum.other.type))) {
                    sysRoleChecke.setDisabled(false);
                }
            }
        }
        roleCheckedRes.setRoles(sysRoleCheckedRes);
        roleCheckedRes.setCheckList(roleSet);
        return roleCheckedRes;
    }

    /**
     * 角色新增
     *
     * @param roleReq
     * @return
     */
    @Transactional
    public String insertSelective(RoleReq roleReq) {
        RedisUser redisUser = this.redisUser();
        if (checkRoleName(roleReq.getSysRoleName(), null, redisUser.getTenantId())) {
            throw BizException.fail("角色名称已存在");
        }
        if (!redisUser.getTenantId().equals(superAdminsService.getTeantId())) {
            //角色的类型，0：管理员角色，1：普通用户 2其他
            if (roleReq.getSysRoleType().equals(RoleTypeEnum.superAdmin.type)) {
                if (checkAdminRole(RoleTypeEnum.superAdmin.type, null, redisUser.getTenantId())) {
                    throw BizException.fail("管理员角色已存在");
                }
            }
        }


        RoleDO record = new RoleDO();
        BeanUtils.copyProperties(roleReq, record);
        record.setCreateBy(redisUser.getSysUserName());
        record.setUpdateBy(redisUser.getSysUserName());
        record.setCreateTime(DateUtils.getDateTime());
        record.setUpdateTime(DateUtils.getDateTime());
        record.setUuid(StrUtil.genUUID());
        record.setTenantId(redisUser.getTenantId());
        record.setId(snowFlake.nextId());
        record.setDelFlag(DelFlagEnum.ok.type);
        roleMapper.insertSelective(record);
        roleRedisCacheServiceAsync.roleSaveToRedis(record);
        return "新增角色成功";
    }

    /**
     * 角色编辑
     *
     * @param roleReq
     * @return
     */
    @Transactional
    public String updateByPrimaryKeySelective(RoleReq roleReq) {
        RoleDO roleDO = roleMapper.selectByPrimaryUuid(roleReq.getUuid());
        if (null == roleDO) {
            throw BizException.fail("待编辑的角色不存在");
        }
        RedisUser redisUser = this.redisUser();
        if (checkRoleName(roleReq.getSysRoleName(), roleDO.getId(), redisUser.getTenantId())) {
            throw BizException.fail("角色名称已存在");
        }


        //非总部，不可以操作管理员敏感权限
        if (!redisUser.getTenantId().equals(superAdminsService.getTeantId())) {
            //角色的类型，0：管理员(老板)，1：管理员(员工) 2其他
            if (roleReq.getSysRoleType().equals(RoleTypeEnum.superAdmin.type)) {
                if (checkAdminRole(RoleTypeEnum.superAdmin.type, roleDO.getId(), redisUser.getTenantId())) {
                    throw BizException.fail("管理员角色已存在");
                }
            }
            //角色的类型，0：管理员角色，1：普通用户 2其他
            //屏蔽非总部操作第三方管理员角色
            if (roleDO.getSysRoleType().equals(RoleTypeEnum.superAdmin.type) && !roleReq.getSysRoleType().equals(RoleTypeEnum.superAdmin.type)) {
                throw BizException.fail("您无权限操作管理员角色类型");
            }
            //屏蔽非总部操作第三方管理员角色状态
            //状态 0启用  1禁用
            if (roleDO.getSysRoleType().equals(RoleTypeEnum.superAdmin.type) && roleReq.getDisabledFlag().equals(RoleTypeEnum.admin.type)) {
                throw BizException.fail("您无权限操作管理员角色状态");
            }
        }
        RoleDO record = new RoleDO();
        BeanUtils.copyProperties(roleReq, record);
        record.setUpdateBy(redisUser.getSysUserName());
        record.setTenantId(roleDO.getTenantId());
        record.setUpdateTime(DateUtils.getDateTime());
        record.setId(roleDO.getId());
        roleMapper.updateByPrimaryKeySelective(record);
        roleRedisCacheServiceAsync.roleModifyToRedis(record, roleDO);
        return "编辑角色成功";
    }

    /**
     * 角色状态
     *
     * @param roleReq
     * @return
     */
    @Transactional
    public String updateState(RoleReq roleReq) {
        RoleDO roleDO = roleMapper.selectByPrimaryUuid(roleReq.getUuid());
        if (null == roleDO) {
            throw BizException.fail("待编辑的角色不存在");
        }
        RedisUser redisUser = this.redisUser();
        //非总部，不可以操作管理员敏感权限
        if (!redisUser.getTenantId().equals(superAdminsService.getTeantId()) && roleDO.getSysRoleType().equals(0)) {
            throw BizException.fail("您无权限操作");
        }
        RoleDO record = new RoleDO();
        //状态 0启用  1禁用
        if (roleReq.getDisabledFlag().equals(DisabledFlagEnum.start.type)) {
            record.setDisabledFlag(DisabledFlagEnum.start.type);
        } else if (roleReq.getDisabledFlag().equals(DisabledFlagEnum.disabled.type)) {
            record.setDisabledFlag(DisabledFlagEnum.disabled.type);
        }
        record.setUpdateBy(redisUser.getSysUserName());
        record.setUpdateTime(DateUtils.getDateTime());
        record.setTenantId(roleDO.getTenantId());
        record.setId(roleDO.getId());
        roleMapper.updateByPrimaryKeySelective(record);
        roleRedisCacheServiceAsync.roleStatusToRedis(roleDO, record.getDisabledFlag());
        return "变更角色状态成功";
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

    @Transactional
    public void updateUserRoles(Long userId, Set<Long> roleIdList, RedisUser redisUser, Long groupId) {
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
            roleUserDO.setTenantId(groupId);
            roleUserDO.setUserId(userId);
            roleUserDO.setCreateTime(DateUtils.getDateTime());
            roleUserDO.setCreateBy(redisUser.getSysUserName());
            roleUserDO.setUpdateBy(redisUser.getSysUserName());
            roleUserDO.setUpdateTime(DateUtils.getDateTime());
            roleUserList.add(roleUserDO);
        }
        roleMapper.batchInsertUserRoles(roleUserList);
    }

    public boolean checkRoleName(String roleName, Long id, Long tenantId) {
        return roleMapper.checkRoleName(roleName, id, tenantId) > 0;
    }

    public boolean checkAdminRole(Integer roleType, Long id, Long compId) {
        return roleMapper.checkAdminRole(id, roleType, compId) > 0;
    }
}

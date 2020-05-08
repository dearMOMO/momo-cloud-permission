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

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.*;
import com.momo.common.core.entity.RedisUser;
import com.momo.common.core.error.RedisKeyEnum;
import com.momo.common.core.error.BizException;
import com.momo.common.core.util.DateUtils;
import com.momo.common.core.util.LevelUtil;
import com.momo.common.core.util.RedisUtil;
import com.momo.common.core.util.StrUtil;
import com.momo.common.core.util.snowFlake.SnowFlake;
import com.momo.mapper.dataobject.AclDO;
import com.momo.mapper.dataobject.RoleAclDO;
import com.momo.mapper.dataobject.RoleDO;
import com.momo.mapper.dataobject.UserDO;
import com.momo.mapper.enums.AclTypeEnum;
import com.momo.mapper.enums.DelFlagEnum;
import com.momo.mapper.enums.DisabledFlagEnum;
import com.momo.mapper.enums.RoleTypeEnum;
import com.momo.mapper.mapper.manual.*;
import com.momo.mapper.req.authority.AclReq;
import com.momo.mapper.req.sysmain.DynamicMenuAuthorReq;
import com.momo.mapper.res.authority.AclDetailRes;
import com.momo.mapper.res.authority.AclLevelRes;
import com.momo.mapper.res.authority.AclTreeRes;
import com.momo.service.async.AclRedisCacheServiceAsync;
import com.momo.service.service.BaseService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @program: momo-cloud-permission
 * @description: 权限设置
 * @author: Jie Li
 * @create: 2019-07-31 13:52
 **/
@Service
public class AclService extends BaseService {

    @Autowired
    private AdminAuthorityService adminAuthorityService;
    @Autowired
    private AclMapper aclMapper;
    @Autowired
    private AdminSysCoreService sysCoreService;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private AuthorityMapper authorityMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleUserMapper roleUserMapper;
    @Autowired
    private RoleAclMapper roleAclMapper;
    @Autowired
    private AclRedisCacheServiceAsync aclRedisCacheServiceAsync;
    private static final SnowFlake snowFlake = new SnowFlake(1, 1);

    /**
     * 权限菜单树
     *
     * @return
     */
    public AclTreeRes aclTree() {
        RedisUser redisUser = this.redisUser();
        // 1、当前用户已分配的权限点
        List<AclDO> userAclList = sysCoreService.getUserHavingAclList(new DynamicMenuAuthorReq(), redisUser);
        //是否被禁用  0否 1禁用
        List<RoleDO> getRolesByUserId = roleMapper.getRolesByUserId(redisUser.getBaseId(), DisabledFlagEnum.start.type);
        Set<Long> roleIds = getRolesByUserId.stream().map(RoleDO::getId).collect(Collectors.toSet());
        // 2、当前角色分配的权限点
        List<AclDO> roleAclList = sysCoreService.getRoleAclList(roleIds, null);
        // 3、当前系统所有权限点
        List<AclLevelRes> aclDtoList = Lists.newArrayList();
        Set<Long> userAclIdSet = userAclList.stream().map(AclDO::getId).collect(Collectors.toSet());
        Set<Long> roleAclIdSet = roleAclList.stream().map(AclDO::getId).collect(Collectors.toSet());
        List<AclDO> allAclList = authorityMapper.getAllAcl(null, null);
        List<String> defaultexpandedKeys = Lists.newArrayList();
        for (AclDO acl : allAclList) {
            //状态 0启用  1禁用
//            if ("0".equals(acl.getSysAclState())){
            AclLevelRes dto = AclLevelRes.adapt(acl);
            if (userAclIdSet.contains(acl.getId())) {
                dto.setHasAcl(true);
                dto.setDisabled(false);
            }
            if (roleAclIdSet.contains(acl.getId())) {
                dto.setChecked(true);
            }
            //类型，-1系统 0:目录 1：菜单，2：按钮，3：其他
            if (acl.getSysAclType().equals(AclTypeEnum.catalogue.type)) {
                defaultexpandedKeys.add(String.valueOf(acl.getId()));
            }
            aclDtoList.add(dto);
//            }
        }
        AclTreeRes aclTreeRes = new AclTreeRes();

        List<AclLevelRes> aclListToTree = adminAuthorityService.aclListToTree(aclDtoList);
        aclTreeRes.setAclLevelRes(aclListToTree);
        aclTreeRes.setDefaultexpandedKeys(defaultexpandedKeys);
        return aclTreeRes;
    }

    /**
     * 新增权限
     *
     * @param aclReq
     * @return
     */
    @Transactional
    public String insertSelective(AclReq aclReq) {
        if (checkUrl(aclReq.getSysAclUrl(), aclReq.getSysAclPermissionCode(), null)) {
            throw BizException.fail("url重复");
        }

        String level = LevelUtil.calculateLevel(getLevel(aclReq.getSysAclParentIdStr()), aclReq.getSysAclParentIdStr());
        checkAclSysName(null, aclReq.getSysAclName(), level);
        RedisUser redisUser = this.redisUser();
        AclDO record = new AclDO();
        BeanUtils.copyProperties(aclReq, record);
        record.setSysAclParentId(aclReq.getSysAclParentIdStr());
        record.setSysAclLevel(level);
        record.setCreateBy(redisUser.getSysUserName());
        record.setUpdateBy(redisUser.getSysUserName());
        record.setCreateTime(DateUtils.getDateTime());
        record.setUpdateTime(DateUtils.getDateTime());
        record.setUuid(StrUtil.genUUID());
        record.setId(snowFlake.nextId());
        record.setDisabledFlag(DisabledFlagEnum.start.type);
        record.setDelFlag(DelFlagEnum.ok.type);
        aclMapper.insertSelective(record);
        aclRedisCacheServiceAsync.aclSaveToRedis(record);
        return "新增权限成功";
    }

    /**
     * 新增权限系统
     *
     * @param aclReq
     * @return
     */
    @Transactional
    public String saveSys(AclReq aclReq) {
        int checkAclPermissionType = aclMapper.checkAclPermissionType(aclReq.getSysAclPermissionCode());
        if (checkAclPermissionType > 0) {
            throw BizException.fail("菜单系统类型 值 已存在");
        }
        checkAclSysName(null, aclReq.getSysAclName(), "0");
        RedisUser redisUser = this.redisUser();
        AclDO record = new AclDO();
        BeanUtils.copyProperties(aclReq, record);
        record.setSysAclParentId(0L);
        //类型，-1系统 0:目录 1：菜单，2：按钮，3：其他
        record.setSysAclType(AclTypeEnum.system.type);
        record.setSysAclLevel(LevelUtil.calculateLevel(getLevel(aclReq.getSysAclParentIdStr()), aclReq.getSysAclParentIdStr()));
        record.setCreateBy(redisUser.getSysUserName());
        record.setUpdateBy(redisUser.getSysUserName());
        record.setCreateTime(DateUtils.getDateTime());
        record.setUpdateTime(DateUtils.getDateTime());
        record.setUuid(StrUtil.genUUID());
        record.setId(snowFlake.nextId());
        record.setDisabledFlag(DisabledFlagEnum.start.type);
        record.setDelFlag(DelFlagEnum.ok.type);
        aclMapper.insertSelective(record);
        aclRedisCacheServiceAsync.aclSaveToRedis(record);
        return "新增权限系统成功";
    }

    /**
     * 编辑权限
     *
     * @param aclReq
     * @return
     */
    @Transactional
    public String updateByPrimaryKeySelective(AclReq aclReq) {
        AclDO before = aclMapper.selectByPrimaryUuid(aclReq.getUuid());
        if (null == before) {
            throw BizException.fail("待编辑的权限不存在");
        }
        if (before.getId().equals(aclReq.getSysAclParentIdStr())) {
            throw BizException.fail("无法将当前模块挂在自己模块下");
        }
        AclDO after = new AclDO();
        BeanUtils.copyProperties(aclReq, after);
        RedisUser redisUser = this.redisUser();
        AclDO aclDO = aclMapper.selectByPrimaryKey(aclReq.getSysAclParentIdStr());
        if (!aclReq.getSysAclParentIdStr().equals(0L)) {
            if (null == aclDO) {
                throw BizException.fail("父权限不存在");
            }
            if (!before.getSysAclPermissionCode().equals(aclDO.getSysAclPermissionCode())) {
                throw BizException.fail("无法跨模块编辑");
            }
            after.setSysAclLevel(LevelUtil.calculateLevel(aclDO.getSysAclLevel(), aclReq.getSysAclParentIdStr()));
        } else {
            after.setSysAclLevel("0");
        }

        if (checkUrl(aclReq.getSysAclUrl(), aclReq.getSysAclPermissionCode(), before.getId())) {
            throw BizException.fail("url重复");
        }
        if (!aclReq.getSysAclParentIdStr().equals(0L) && !before.getSysAclPermissionCode().equals(aclReq.getSysAclPermissionCode())) {
            throw BizException.fail("权限码不允许编辑");
        }
        after.setUpdateBy(redisUser.getSysUserName());
        after.setUpdateTime(DateUtils.getDateTime());
        after.setId(before.getId());
        after.setSysAclParentId(aclReq.getSysAclParentIdStr());
        after.setSysAclPermissionCode(before.getSysAclPermissionCode());

        updateWithChild(before, after);

        return "编辑权限成功";
    }

    /**
     * 查询菜单权限详情
     *
     * @param aclReq
     * @return
     */
    public AclDetailRes detail(AclReq aclReq) {
        AclDO aclDO = aclMapper.selectByPrimaryUuid(aclReq.getUuid());
        if (null == aclDO) {
            throw BizException.fail("待查询的权限不存在");
        }
        AclDetailRes aclDetailRes = new AclDetailRes();
        BeanUtils.copyProperties(aclDO, aclDetailRes);
        //类型，-1系统 0:目录 1：菜单，2：按钮，3：其他
        if (aclDO.getSysAclType().equals(AclTypeEnum.system.type)) {
            //是否有孩子
            int count = aclMapper.checkChildAcl(LevelUtil.calculateLevel(aclDO.getSysAclLevel(), aclDO.getId()));
            if (count == 0) {
                aclDetailRes.setDisabledAclSysCode(false);
            }
        }
        aclDetailRes.setIdStr(String.valueOf(aclDO.getId()));
        aclDetailRes.setSysAclParentIdStr(String.valueOf(aclDO.getSysAclParentId()));

        return aclDetailRes;
    }

    /**
     * 更新权限状态
     *
     * @param aclReq
     * @return
     */
    @Transactional
    public String updateStatus(AclReq aclReq) {
        AclDO selfAclDO = aclMapper.selectByPrimaryUuid(aclReq.getUuid());
        if (null == selfAclDO) {
            throw BizException.fail("待编辑的权限不存在");
        }
        AclDO record = new AclDO();
        BeanUtils.copyProperties(selfAclDO, record);
        //状态 0启用  1禁用
        if (aclReq.getDisabledFlag().equals(DisabledFlagEnum.start.type)) {
            record.setDisabledFlag(DisabledFlagEnum.disabled.type);
        } else if (aclReq.getDisabledFlag().equals(DisabledFlagEnum.disabled.type)) {
            record.setDisabledFlag(DisabledFlagEnum.start.type);
        }
        RedisUser redisUser = this.redisUser();

        record.setUpdateBy(redisUser.getSysUserName());
        record.setUpdateTime(DateUtils.getDateTime());
        record.setId(selfAclDO.getId());
        aclMapper.updateByPrimaryKeySelective(record);
        aclRedisCacheServiceAsync.modifyAclToRedis(record, null);
        return "更新权限状态成功";
    }

    @Transactional
    public void updateWithChild(AclDO before, AclDO after) {
        String newLevelPrefix = after.getSysAclLevel();
        String oldLevelPrefix = before.getSysAclLevel();
        List<AclDO> aclModuleList = null;
        if (!after.getSysAclLevel().equals(before.getSysAclLevel())) {
            aclModuleList = aclMapper.getChildAclModuleListByLevel(LevelUtil.calculateLevel(before.getSysAclLevel(), before.getId()));
            if (CollectionUtils.isNotEmpty(aclModuleList)) {
                for (AclDO aclModule : aclModuleList) {
                    String level = aclModule.getSysAclLevel();
                    if (level.indexOf(oldLevelPrefix) == 0) {
                        level = newLevelPrefix + level.substring(oldLevelPrefix.length());
                        aclModule.setSysAclLevel(level);
                    }
                }
                aclMapper.batchUpdateLevel(aclModuleList);

            }
        }
        aclMapper.updateByPrimaryKeySelective(after);
        aclRedisCacheServiceAsync.modifyAclToRedis(after, aclModuleList);
    }

    private boolean checkUrl(String url, String moduleType, Long id) {
        return aclMapper.checkUrl(url, moduleType, id) > 0;
    }

    private String getLevel(Long aclModuleId) {
        AclDO aclModule = aclMapper.selectByPrimaryKey(aclModuleId);
        if (aclModule == null) {
            return null;
        }
        return aclModule.getSysAclLevel();
    }

    private void checkAclSysName(Long id, String sys_acl_name, String sys_acl_level) {
        int checkAclSysName = aclMapper.checkAclSysName(id, sys_acl_name, sys_acl_level);
        if (checkAclSysName > 0) {
            throw BizException.fail("菜单名称已存在");
        }
    }

    /**
     * 一键同步用户和角色关系到Redis
     *
     * @return
     */
    public String userToRolesToAcls() {
        //用户相关
        int pageNumUser = 1;
        int pageSizeUser = 200;
        int totalUserUser = userMapper.getAllUserForCount();
        int forCountUser = 1;
        forCountUser(forCountUser, totalUserUser, pageSizeUser);
        for (int i = 0; i < forCountUser; i++) {
            PageHelper.startPage(pageNumUser, pageSizeUser);
            List<UserDO> pageSysUserList = userMapper.getAllUserForPage();
            PageInfo<UserDO> pageInfo = new PageInfo<>(pageSysUserList);
            pageSizeUser += pageSizeUser;
            pageNumUser++;
            //用户id,tenantId
            List<UserDO> userIds = pageInfo.getList();
            if (CollectionUtils.isEmpty(userIds)) {
                break;
            }
            //根据用户id查询用户与角色关系
            userIds.forEach(userDO -> {
                List<Long> roleIds = roleUserMapper.userToRoleIds(userDO.getId());
                if (CollectionUtils.isNotEmpty(roleIds)) {
                    redisUtil.set(RedisKeyEnum.REDIS_USER_ROLES_STR.getKey() + userDO.getId(), JSONObject.toJSONString(roleIds));
                }
            });
        }
        //角色相关
        int pageNumRole = 1;
        int pageSizeRole = 200;
        int totalRole = roleMapper.getAllRoleForCount();
        int forCountRole = 1;
        forCountRole(forCountRole, totalRole, pageSizeRole);
        for (int i = 0; i < forCountRole; i++) {
            PageHelper.startPage(pageNumRole, pageSizeRole);
            List<RoleDO> pageSysUserList = roleMapper.getAllRoleForPage();
            PageInfo<RoleDO> pageInfo = new PageInfo<>(pageSysUserList);
            pageSizeRole += pageSizeRole;
            pageNumRole++;
            //角色id,角色类型
            List<RoleDO> userDos = pageInfo.getList();
            if (CollectionUtils.isNotEmpty(userDos)) {
                userDos.forEach(roleDO -> {
                    //根据角色id查询角色与权限关系
                    String redisKey = RedisKeyEnum.REDIS_ROLE_STR.getKey() + roleDO.getId();
                    String redisAdminKey = RedisKeyEnum.REDIS_ADMIN_ROLE_STR.getKey() + roleDO.getTenantId();
                    String roleStr = JSONObject.toJSONString(roleDO, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteDateUseDateFormat);
                    redisUtil.set(redisKey, roleStr);
                    //角色的类型，0：管理员(老板)，1：管理员(员工)  2:普通员工 3:其他
                    if (roleDO.getSysRoleType().equals(RoleTypeEnum.superAdmin.type)) {
                        redisUtil.set(redisAdminKey, roleStr);
                    }
                    //根据角色id查询角色与权限关系
                    List<RoleAclDO> getRoleAclByRoleId = roleAclMapper.getRoleAclByRoleId(roleDO.getId());
                    if (CollectionUtils.isNotEmpty(getRoleAclByRoleId)) {
                        String roleIdToAclIdsRedisKey = RedisKeyEnum.REDIS_ROLE_ACLS_MAP.getKey() + roleDO.getId();
                        Map<String, Object> map = Maps.newHashMap();
                        getRoleAclByRoleId.forEach(roleAclDO -> {
                            Set<String> sysAclPermissionCode = Sets.newHashSet();
                            Multimap<String, Long> multimap = ArrayListMultimap.create();
                            getRoleAclByRoleId.forEach(aclDO -> {
                                sysAclPermissionCode.add(aclDO.getSysAclPermissionCode());
                                multimap.put(aclDO.getSysAclPermissionCode(), aclDO.getSysAclId());
                            });
                            sysAclPermissionCode.forEach(s -> {
                                List<Long> aclIds = (List<Long>) multimap.get(s);
                                if (CollectionUtils.isNotEmpty(aclIds)) {
                                    map.put(s, JSONObject.toJSONString(aclIds));
                                }
                            });
                        });
                        redisUtil.hmset(roleIdToAclIdsRedisKey, map);
                    }
                });
            }

        }

        return "一键同步用户和角色关系到Redis成功";
    }

    private void forCountUser(int forCountUser, int totalUser, int pageSizeUser) {
        for (; ; ) {
            int moduluscount = totalUser / pageSizeUser;
            if (moduluscount != 0) {
                forCountUser++;
                pageSizeUser += pageSizeUser;
                forCountUser(forCountUser, totalUser, pageSizeUser);
            } else {
                break;
            }
        }
    }

    private void forCountRole(int forCountRole, int totalRole, int pageSizeRole) {
        for (; ; ) {
            int moduluscount = totalRole / pageSizeRole;
            if (moduluscount != 0) {
                forCountRole++;
                pageSizeRole += pageSizeRole;
                forCountUser(forCountRole, totalRole, pageSizeRole);
            } else {
                break;
            }
        }
    }

    /**
     * 一键同步权限到Redis
     *
     * @return
     */
    public String aclsToRedis() {
        List<AclDO> getAllAcl = authorityMapper.getAllAcl(null, null);
        Set<String> redisMapKey = Sets.newHashSet();
        Multimap<String, AclDO> aclDOMultimap = ArrayListMultimap.create();
        if (CollectionUtils.isNotEmpty(getAllAcl)) {
            getAllAcl.forEach(aclDO -> {
                String redisKey = RedisKeyEnum.REDIS_ACL_MAP.getKey() + aclDO.getSysAclPermissionCode();
                redisMapKey.add(redisKey);

                aclDOMultimap.put(redisKey, aclDO);
            });
        }
        if (CollectionUtils.isNotEmpty(redisMapKey)) {
            redisMapKey.forEach(s -> {
                List<AclDO> aclDOS = (List<AclDO>) aclDOMultimap.get(s);
                Map<String, String> aclMap = Maps.newHashMap();
                if (CollectionUtils.isNotEmpty(aclDOS)) {
                    aclDOS.forEach(aclDO -> {
                        String aclMapStr = JSONObject.toJSONString(aclDO, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteDateUseDateFormat);
                        aclMap.put(String.valueOf(aclDO.getId()), aclMapStr);
                    });
                }
                redisUtil.hsetputAll(s, aclMap);
            });
        }
        return "一键同步权限到Redis成功";
    }
}

package com.momo.service.service.authority;

import com.google.common.collect.Lists;
import com.momo.common.util.DateUtils;
import com.momo.common.error.BizException;
import com.momo.common.util.LevelUtil;
import com.momo.common.util.StrUtil;
import com.momo.common.util.snowFlake.SnowFlake;
import com.momo.mapper.dataobject.AclDO;
import com.momo.mapper.dataobject.RoleDO;
import com.momo.mapper.mapper.manual.AclMapper;
import com.momo.mapper.mapper.manual.AuthorityMapper;
import com.momo.mapper.mapper.manual.RoleMapper;
import com.momo.mapper.req.authority.AclReq;
import com.momo.mapper.req.sysmain.LoginAuthReq;
import com.momo.mapper.req.sysmain.RedisUser;
import com.momo.mapper.res.authority.AclDetailRes;
import com.momo.mapper.res.authority.AclLevelRes;
import com.momo.mapper.res.authority.AclTreeRes;
import com.momo.service.service.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    private SnowFlake snowFlake = new SnowFlake(1, 1);

    public AclTreeRes aclTree() {
        RedisUser redisUser = this.redisUser();
        // 1、当前用户已分配的权限点
        List<AclDO> userAclList = sysCoreService.getUserHavingAclList(new LoginAuthReq(), redisUser);
        List<RoleDO> getRolesByUserId = roleMapper.getRolesByUserId(redisUser.getBaseId());
        Set<Long> roleIds = getRolesByUserId.stream().map(roleDO -> roleDO.getId()).collect(Collectors.toSet());
        // 2、当前角色分配的权限点
        List<AclDO> roleAclList = sysCoreService.getRoleAclList(roleIds, null);
        // 3、当前系统所有权限点
        List<AclLevelRes> aclDtoList = Lists.newArrayList();
        Set<Long> userAclIdSet = userAclList.stream().map(sysAcl -> sysAcl.getId()).collect(Collectors.toSet());
        Set<Long> roleAclIdSet = roleAclList.stream().map(sysAcl -> sysAcl.getId()).collect(Collectors.toSet());
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
            if (acl.getSysAclType().equals(0)) {
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

    @Transactional
    public String insertSelective(AclReq aclReq) {
        if (checkUrl(aclReq.getSysAclUrl(), aclReq.getSysAclPermissionCode(), null)) {
            throw BizException.fail("url重复");
        }
        String level = LevelUtil.calculateLevel(getLevel(aclReq.getSysAclParentId()), aclReq.getSysAclParentId());
        RedisUser redisUser = this.redisUser();
        AclDO record = new AclDO();
        BeanUtils.copyProperties(aclReq, record);
        record.setSysAclLevel(level);
        record.setCreateBy(redisUser.getSysUserName());
        record.setUpdateBy(redisUser.getSysUserName());
        record.setCreateTime(DateUtils.getDateTime());
        record.setUpdateTime(DateUtils.getDateTime());
        record.setUuid(StrUtil.genUUID());
        record.setId(snowFlake.nextId());
        aclMapper.insertSelective(record);
        return "新增权限成功";
    }

    @Transactional
    public String saveSys(AclReq aclReq) {
        int checkAclPermissionType = aclMapper.checkAclPermissionType(aclReq.getSysAclPermissionCode());
        if (checkAclPermissionType > 0) {
            throw BizException.fail("菜单系统类型 值 已存在");
        }
        int checkAclSysName = aclMapper.checkAclSysName(null, aclReq.getSysAclName(), "0");
        if (checkAclSysName > 0) {
            throw BizException.fail("菜单名称已存在");
        }
        RedisUser redisUser = this.redisUser();
        AclDO record = new AclDO();
        BeanUtils.copyProperties(aclReq, record);
        record.setSysAclParentId(0L);
        //类型，-1系统 0:目录 1：菜单，2：按钮，3：其他
        record.setSysAclType(-1);
        record.setSysAclLevel(LevelUtil.calculateLevel(getLevel(aclReq.getSysAclParentId()), aclReq.getSysAclParentId()));
        record.setCreateBy(redisUser.getSysUserName());
        record.setUpdateBy(redisUser.getSysUserName());
        record.setCreateTime(DateUtils.getDateTime());
        record.setUpdateTime(DateUtils.getDateTime());
        record.setUuid(StrUtil.genUUID());
        record.setId(snowFlake.nextId());
        aclMapper.insertSelective(record);
        return "新增权限系统成功";
    }

    @Transactional
    public String updateByPrimaryKeySelective(AclReq aclReq) {
        AclDO before = aclMapper.selectByPrimaryUuid(aclReq.getUuid());
        if (null == before) {
            throw BizException.fail("待编辑的权限不存在");
        }
        if (before.getId().equals(aclReq.getSysAclParentId())) {
            throw BizException.fail("无法将当前模块挂在自己模块下");
        }
        if (!before.getSysAclPermissionCode().equals(aclReq.getSysAclPermissionCode())) {
            throw BizException.fail("无法跨模块编辑");
        }
        if (checkUrl(aclReq.getSysAclUrl(), aclReq.getSysAclPermissionCode(), before.getId())) {
            throw BizException.fail("url重复");
        }
        AclDO after = new AclDO();
        BeanUtils.copyProperties(aclReq, after);
        RedisUser redisUser = this.redisUser();
        after.setSysAclLevel(LevelUtil.calculateLevel(getLevel(aclReq.getSysAclParentId()), aclReq.getSysAclParentId()));
        after.setUpdateBy(redisUser.getSysUserName());
        after.setUpdateTime(DateUtils.getDateTime());
        after.setId(before.getId());
        updateWithChild(before, after);

        return "编辑权限成功";
    }

    public AclDetailRes detail(AclReq aclReq) {
        AclDO aclDO = aclMapper.selectByPrimaryUuid(aclReq.getUuid());
        if (null == aclDO) {
            throw BizException.fail("待查询的权限不存在");
        }
        AclDetailRes aclDetailRes = new AclDetailRes();
        BeanUtils.copyProperties(aclDO, aclDetailRes);
        //类型，-1系统 0:目录 1：菜单，2：按钮，3：其他
        if (aclDO.getSysAclType().equals(-1)) {
            //是否有孩子
            int count = aclMapper.checkChildAcl(LevelUtil.calculateLevel(aclDO.getSysAclLevel(), aclDO.getId()));
            if (count == 0) {
                aclDetailRes.setDisabledAclSysCode(false);
            }
        }
        aclDetailRes.setIdStr(String.valueOf(aclDO.getId()));
        aclDetailRes.setSetSysAclParentIdStr(String.valueOf(aclDO.getSysAclParentId()));

        return aclDetailRes;
    }

    public String updateStatus(AclReq aclReq) {
        AclDO selfAclDO = aclMapper.selectByPrimaryUuid(aclReq.getUuid());
        if (null == selfAclDO) {
            throw BizException.fail("待编辑的权限不存在");
        }
        AclDO record = new AclDO();
        //状态 0启用  1禁用
        if (aclReq.getFlag().equals(0)) {
            record.setFlag(1);
        } else if (aclReq.getFlag().equals(1)) {
            record.setFlag(0);
        }
        RedisUser redisUser = this.redisUser();
        record.setUpdateBy(redisUser.getSysUserName());
        record.setUpdateTime(DateUtils.getDateTime());
        record.setId(selfAclDO.getId());
        aclMapper.updateByPrimaryKeySelective(record);
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
        after.setSysAclPermissionCode(null);
        aclMapper.updateByPrimaryKeySelective(after);
    }

    private boolean checkUrl(String url, String moduleType, Long id) {
        return aclMapper.checkUrl(url, moduleType, id) > 0 ? true : false;
    }

    private String getLevel(Long aclModuleId) {
        AclDO aclModule = aclMapper.selectByPrimaryKey(aclModuleId);
        if (aclModule == null) {
            return null;
        }
        return aclModule.getSysAclLevel();
    }
}

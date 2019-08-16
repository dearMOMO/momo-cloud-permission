package com.momo.service.service.aclmanager.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.momo.common.util.DateUtils;
import com.momo.common.error.BizException;
import com.momo.mapper.dataobject.RoleDO;
import com.momo.mapper.dataobject.UserGroupDO;
import com.momo.mapper.mapper.manual.RoleMapper;
import com.momo.mapper.mapper.manual.UserGroupMapper;
import com.momo.mapper.req.aclmanager.SysUserGroupReq;
import com.momo.mapper.req.aclmanager.UserGroupPageReq;
import com.momo.mapper.req.sysmain.LoginAuthReq;
import com.momo.mapper.req.sysmain.RedisUser;
import com.momo.mapper.res.aclmanager.SysUserGroupPageRes;
import com.momo.mapper.res.authority.AclTreeRes;
import com.momo.service.service.BaseService;
import com.momo.service.service.aclmanager.SysEnterpriseService;
import com.momo.service.service.authority.AdminAuthorityService;
import com.momo.service.service.authority.CommonAuthorityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

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
        if (redisUser.getGroupId().equals(1L)) {
            AclTreeRes aclTreeRes = adminAuthorityService.roleTree(loginAuthReq, redisUser);
            return aclTreeRes;
        } else {
            AclTreeRes aclTreeRes = commonAuthorityService.roleTree(loginAuthReq, redisUser);
            return aclTreeRes;
        }
    }

    @Override
    public String modify(SysUserGroupReq sysUserGroupReq) {
        UserGroupDO uuid = userGroupMapper.uuid(sysUserGroupReq.getUuid());
        if (uuid == null) {
            throw BizException.fail("待编辑的企业不存在");
        }
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
        userGroupMapper.insertSelective(userGroupDO);

        return "编辑企业信息成功";
    }

    @Override
    public String save(SysUserGroupReq sysUserGroupReq) {
        UserGroupDO uuid = userGroupMapper.uuid(sysUserGroupReq.getUuid());
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
}

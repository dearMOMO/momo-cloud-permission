package com.momo.service.service.aclmanager.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.momo.common.common.DateUtils;
import com.momo.common.error.BizException;
import com.momo.common.util.DateUtil;
import com.momo.mapper.dataobject.UserGroupDO;
import com.momo.mapper.mapper.manual.UserGroupMapper;
import com.momo.mapper.req.aclmanager.SysUserGroupReq;
import com.momo.mapper.req.aclmanager.UserGroupPageReq;
import com.momo.mapper.req.sysmain.RedisUser;
import com.momo.service.service.BaseService;
import com.momo.service.service.aclmanager.SysEnterpriseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public PageInfo<UserGroupDO> getUserGroupPage(UserGroupPageReq userGroupPageReq) {
        PageHelper.startPage(userGroupPageReq.getPageNum(), userGroupPageReq.getPageSize(), "id desc");
        List<UserGroupDO> getUserGroupPage = userGroupMapper.getUserGroupPage(userGroupPageReq.getUserGroupName(), userGroupPageReq.getFlag());
        PageInfo<UserGroupDO> pageInfo = new PageInfo<>(getUserGroupPage);
        return pageInfo;
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
        userGroupDO.setUpdateTime(DateUtil.getDateTime());
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
        userGroupDO.setUpdateTime(DateUtil.getDateTime());
        userGroupDO.setCreateBy(redisUser.getSysUserName());
        userGroupDO.setCreateTime(DateUtil.getDateTime());
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
        userGroupDO.setUpdateTime(DateUtil.getDateTime());
        userGroupMapper.insertSelective(userGroupDO);
        return "状态设置成功";
    }
}

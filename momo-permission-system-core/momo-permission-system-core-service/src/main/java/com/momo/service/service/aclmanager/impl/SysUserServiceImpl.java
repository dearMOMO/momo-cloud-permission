package com.momo.service.service.aclmanager.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.momo.common.util.DateUtils;
import com.momo.common.error.BizException;
import com.momo.common.util.Encrypt;
import com.momo.common.util.StrUtil;
import com.momo.common.util.snowFlake.SnowFlake;
import com.momo.mapper.dataobject.RoleDO;
import com.momo.mapper.dataobject.UserAccountPwdDO;
import com.momo.mapper.dataobject.UserDO;
import com.momo.mapper.dataobject.manual.SysUserListDO;
import com.momo.mapper.mapper.manual.RoleMapper;
import com.momo.mapper.mapper.manual.UserAccountPwdMapper;
import com.momo.mapper.mapper.manual.UserMapper;
import com.momo.mapper.req.aclmanager.SysUserAddReq;
import com.momo.mapper.req.aclmanager.SysUserListReq;
import com.momo.mapper.req.sysmain.RedisUser;
import com.momo.mapper.res.aclmanager.SysUserListRes;
import com.momo.service.service.BaseService;
import com.momo.service.service.SuperAdminsService;
import com.momo.service.service.aclmanager.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @program: momo-cloud-permission
 * @description: 用户管理
 * @author: Jie Li
 * @create: 2019-08-02 17:20
 **/
@Service
@Slf4j
public class SysUserServiceImpl extends BaseService implements SysUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserAccountPwdMapper userAccountPwdMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private SuperAdminsService superAdminsService;
    private SnowFlake snowFlake = new SnowFlake(1, 1);

    @Transactional
    @Override
    public String sysUserAdd(SysUserAddReq sysUserAddReq) {
        UserAccountPwdDO exitsUserAccountPwdDO = userAccountPwdMapper.sysUserAccountLogin(sysUserAddReq.getSysUserLoginName());
        if (exitsUserAccountPwdDO != null) {
            throw BizException.fail("登录账号已存在");
        }
        RedisUser redisUser = this.redisUser();
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(sysUserAddReq, userDO);
        Long id = snowFlake.nextId();
        userDO.setId(id);
        userDO.setSysUserEmail(sysUserAddReq.getSysUserLoginName());
        userDO.setUuid(StrUtil.genUUID());
        userDO.setTenantId(redisUser.getTenantId());
        userDO.setCreateBy(redisUser.getSysUserName());
        userDO.setUpdateBy(redisUser.getSysUserName());
        userDO.setCreateTime(DateUtils.getDateTime());
        userDO.setUpdateTime(DateUtils.getDateTime());
        userMapper.insertSelective(userDO);
        UserAccountPwdDO userAccountPwdDO = new UserAccountPwdDO();
        BeanUtils.copyProperties(sysUserAddReq, userAccountPwdDO);
        userAccountPwdDO.setId(snowFlake.nextId());
        String salt = StrUtil.genUUID();
        userAccountPwdDO.setSysUserAuthSalt(salt);
        String pwd = Encrypt.SHA512AndSHA256(sysUserAddReq.getSysUserPwd(), salt);
        userAccountPwdDO.setSysUserPwd(pwd);
        userAccountPwdDO.setTenantId(redisUser.getTenantId());
        userAccountPwdDO.setCreateBy(redisUser.getSysUserName());
        userAccountPwdDO.setUpdateBy(redisUser.getSysUserName());
        userAccountPwdDO.setUuid(StrUtil.genUUID());
        userAccountPwdDO.setSysUserId(id);
        userAccountPwdDO.setCreateTime(DateUtils.getDateTime());
        userAccountPwdDO.setUpdateTime(DateUtils.getDateTime());
        userAccountPwdMapper.insertSelective(userAccountPwdDO);
        return "新增用户成功";
    }

    @Override
    public UserDO sysUserDetail(SysUserAddReq sysUserAddReq) {
        UserDO userDODetail = userMapper.uuid(sysUserAddReq.getUuid());
        if (null == userDODetail) {
            throw BizException.fail("待查询的用户不存在");
        }
        userDODetail.setId(null);
        return userDODetail;
    }

    @Transactional
    @Override
    public String sysUserModify(SysUserAddReq sysUserAddReq) {
        UserDO userDODetail = userMapper.uuid(sysUserAddReq.getUuid());
        if (null == userDODetail) {
            throw BizException.fail("待编辑的用户不存在");
        }
        RedisUser redisUser = this.redisUser();
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(sysUserAddReq, userDO);
        userDO.setSysUserName(sysUserAddReq.getSysUserName());
        userDO.setDisabledFlag(sysUserAddReq.getDisabledFlag());
        userDO.setId(userDODetail.getId());
        userDO.setUpdateBy(redisUser.getSysUserName());
        userDO.setUpdateTime(DateUtils.getDateTime());
        //超级管理员 编辑所有
        if (superAdminsService.checkIsSuperAdmin(redisUser.getSysUserPhone())) {
            userMapper.updateByPrimaryKeySelective(userDO);
            return "编辑用户信息成功";
        } else {
            if (userDODetail.getId().equals(redisUser.getBaseId()) && !userDODetail.getDisabledFlag().equals(sysUserAddReq.getDisabledFlag())) {
                throw BizException.fail("您无法更改自己的用户状态");
            }
            //普通管理员 按需来
            if (superAdminsService.checkIsSuperAdmin(userDODetail.getSysUserPhone())) {
                throw BizException.fail("超级管理员信息不允许编辑");
            }
            //是否被禁用  0否 1禁用
            List<RoleDO> roleDOS = roleMapper.getRolesByUserId(userDODetail.getId(), 0);
            //角色的类型，0：管理员(老板)，1：管理员(员工) 2其他
            Set<Integer> roleTypes = roleDOS.stream().map(roleDO -> roleDO.getSysRoleType()).collect(Collectors.toSet());
            if (roleTypes.contains(0) && !userDODetail.getId().equals(redisUser.getBaseId())) {
                throw BizException.fail("管理员信息不允许编辑");
            }
            userMapper.updateByPrimaryKeySelective(userDO);
            return "编辑用户信息成功";
        }
    }

    @Transactional
    @Override
    public String sysUserStatus(SysUserAddReq sysUserAddReq) {
        UserDO userDODetail = userMapper.uuid(sysUserAddReq.getUuid());
        if (null == userDODetail) {
            throw BizException.fail("待编辑的用户信息不存在");
        }
        RedisUser redisUser = this.redisUser();
        UserDO userDO = new UserDO();
        userDO.setDisabledFlag(sysUserAddReq.getDisabledFlag());
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
            //是否被禁用  0否 1禁用
            List<RoleDO> roleDOS = roleMapper.getRolesByUserId(userDODetail.getId(), 0);
            //角色的类型，0：管理员(老板)，1：管理员(员工) 2其他
            Set<Integer> roleTypes = roleDOS.stream().map(roleDO -> roleDO.getSysRoleType()).collect(Collectors.toSet());
            if (roleTypes.contains(0) && !userDODetail.getId().equals(redisUser.getBaseId())) {
                throw BizException.fail("管理员状态不允许编辑");
            }
            userMapper.updateByPrimaryKeySelective(userDO);
            return "用户状态设置成功";
        }
    }

    @Transactional
    @Override
    public String sysUserPwd(SysUserAddReq sysUserAddReq) {
        UserDO userDODetail = userMapper.uuid(sysUserAddReq.getUuid());
        if (null == userDODetail) {
            throw BizException.fail("待编辑的用户不存在");
        }
        UserAccountPwdDO sysUserAccountByUserId = userAccountPwdMapper.sysUserAccountByUserId(userDODetail.getId());
        UserAccountPwdDO userAccountPwdDO = new UserAccountPwdDO();
        userAccountPwdDO.setSysUserPwd(sysUserAddReq.getSysUserPwd());
        String salt = StrUtil.genUUID();
        userAccountPwdDO.setSysUserAuthSalt(salt);
        String pwd = Encrypt.SHA512AndSHA256(sysUserAddReq.getSysUserPwd(), salt);
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
            //是否被禁用  0否 1禁用
            List<RoleDO> roleDOS = roleMapper.getRolesByUserId(userDODetail.getId(), 0);
            //角色的类型，0：管理员(老板)，1：管理员(员工) 2其他
            Set<Integer> roleTypes = roleDOS.stream().map(roleDO -> roleDO.getSysRoleType()).collect(Collectors.toSet());
            if (roleTypes.contains(0) && !userDODetail.getId().equals(redisUser.getBaseId())) {
                throw BizException.fail("管理员密码不允许编辑");
            }
            userAccountPwdMapper.updateByPrimaryKeySelective(userAccountPwdDO);
            return "修改密码成功";
        }
    }

    @Override
    public PageInfo<SysUserListRes> sysUserList(SysUserListReq sysUserListReq) {
        RedisUser redisUser = this.redisUser();
        PageHelper.startPage(sysUserListReq.getPageNum(), sysUserListReq.getPageSize(), "id desc");
        List<SysUserListDO> pageSysUserList = userMapper.pageSysUserList(redisUser.getTenantId(), sysUserListReq.getSysUserName(), sysUserListReq.getDisabledFlag());
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
                Set<Integer> rolesSet = roles.stream().map(roleDO -> roleDO.getSysRoleType()).collect(Collectors.toSet());
                //角色的类型，0：管理员(老板)，1：管理员(员工) 2其他
                if (rolesSet.contains(0)) {
                    sysUserListRes.setEditButton(false);
                    sysUserListRes.setPwdButton(false);
                    sysUserListRes.setFlagButton(false);
                    sysUserListRes.setRoleButton(false);
                }
                if (rolesSet.contains(1)) {
                    sysUserListRes.setEditButton(false);
                    sysUserListRes.setPwdButton(false);
                    sysUserListRes.setFlagButton(false);
                    sysUserListRes.setRoleButton(false);
                }
                //用户是自己登陆，则显示自己
                if (sysUserListDO.getId().equals(redisUser.getBaseId())) {
                    sysUserListRes.setEditButton(true);
                    sysUserListRes.setPwdButton(true);
                    sysUserListRes.setFlagButton(true);
                    sysUserListRes.setRoleButton(true);
                }
                //超级管理员，则显示全部
                if (superAdminsService.checkIsSuperAdmin(redisUser.getSysUserPhone())) {
                    sysUserListRes.setEditButton(true);
                    sysUserListRes.setPwdButton(true);
                    sysUserListRes.setFlagButton(true);
                    sysUserListRes.setRoleButton(true);
                }
                UserAccountPwdDO userAccountPwdDO = sysUserListDO.getUserAccountPwdDO();
                //密码绑定
                if (null != userAccountPwdDO) {
                    sysUserListRes.setPwdBinding(true);
                    sysUserListRes.setPwdBindingName(userAccountPwdDO.getSysUserLoginName());
                    sysUserListRes.setPwdBindingFlag(userAccountPwdDO.getDisabledFlag());
                    sysUserListRes.setPwdBindingDate(userAccountPwdDO.getCreateTime());
                }
                //用户是自己登陆，则显示自己
                if (sysUserListDO.getId().equals(redisUser.getBaseId())) {
                    //屏蔽自己状态 按钮
                    sysUserListRes.setFlagButton(false);
                }
                resList.add(sysUserListRes);
            });
            pageInfoRes.setList(resList);

            return pageInfoRes;
        }
        return pageInfoRes;
    }
}

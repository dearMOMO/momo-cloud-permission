package com.momo.service.service.sysmain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.momo.common.common.DateUtils;
import com.momo.common.common.JSONResult;
import com.momo.common.error.BizException;
import com.momo.common.error.RedisKeyEnum;
import com.momo.common.util.Encrypt;
import com.momo.common.util.JwtTokenUtil;
import com.momo.common.util.RedisUtil;
import com.momo.common.util.StrUtil;
import com.momo.common.util.snowFlake.SnowFlake;
import com.momo.common.util.verificationCode.captcha.SpecCaptcha;
import com.momo.mapper.dataobject.*;
import com.momo.mapper.mapper.manual.UserAccountPwdMapper;
import com.momo.mapper.mapper.manual.UserGroupMapper;
import com.momo.mapper.mapper.manual.UserMapper;
import com.momo.mapper.req.sysmain.RedisUser;
import com.momo.mapper.req.sysmain.SysUserLoginReq;
import com.momo.mapper.res.sysmain.UserInfoRes;
import com.momo.service.async.LoginLogAsync;
import com.momo.service.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @program: momo-cloud-permission
 * @description: 系统入口
 * @author: Jie Li
 * @create: 2019-07-30 15:10
 **/
@Service
@Slf4j
public class SysMainService extends BaseService {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserAccountPwdMapper userAccountPwdMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LoginLogAsync loginLog;
    @Autowired
    private UserGroupMapper userGroupMapper;
    private SnowFlake snowFlake = new SnowFlake(1, 1);
    @Value("${momo.superAdmins}")
    private String superAdmins = "";

    public String userLogin(SysUserLoginReq sysUserLoginReq, HttpServletRequest request) {
        //todo 验证码
//        checkVerificationCode(sysUserLoginReq);
        UserAccountPwdDO userAccountPwdDO = userAccountPwdMapper.sysUserAccountLogin(sysUserLoginReq.getSysUserLoginName());
        if (null == userAccountPwdDO) {
            throw BizException.fail("用户名或者密码错误");
        }
        //剔除超级管理员限制
        if (!superAdmins.contains(userAccountPwdDO.getSysUserLoginName())) {
            //删除状态(0-正常，1-删除)
            if (1 == userAccountPwdDO.getDelFlag()) {
                throw BizException.fail("您的账户已被删除，请联系管理员");
            }
            //状态 0启用  1禁用
            if (1 == userAccountPwdDO.getFlag()) {
                throw BizException.fail("您的账户已被禁用，请联系管理员");
            }
        }
        String pwd = Encrypt.SHA512AndSHA256(sysUserLoginReq.getSysUserPwd(), userAccountPwdDO.getSysUserAuthSalt());
        if (!userAccountPwdDO.getSysUserPwd().equals(pwd)) {
            throw BizException.fail("用户名或者密码错误");
        }
        //剔除超级管理员限制
        if (!superAdmins.contains(userAccountPwdDO.getSysUserLoginName())) {
            UserGroupDO userGroupDO = userGroupMapper.getUserGroupById(userAccountPwdDO.getGroupId());
            if (null == userGroupDO) {
                throw BizException.fail("您所在的用户组不存在");
            }
            if (1 == userGroupDO.getFlag()) {
                throw BizException.fail("您所在的用户组已被禁用");
            }
            if (1 == userGroupDO.getDelFlag()) {
                throw BizException.fail("您所在的用户组已被删除");
            }
            if (1 != userGroupDO.getId() && DateUtils.timeDifference(userGroupDO.getSysAccountEndTime())) {
                throw BizException.fail("您所在的用户组时间已到期,请续约后在次登录");
            }
        }

        UserDO userDO = userMapper.getById(userAccountPwdDO.getSysUserId());
        String redisUserKey = StrUtil.genUUID();
        RedisUser redisUser = RedisUser.builder().redisUserKey(redisUserKey).loginType(1)
                .sysUserLoginName(userAccountPwdDO.getSysUserLoginName()).sysUserPhone(userDO.getSysUserPhone())
                .groupId(userAccountPwdDO.getGroupId()).sysUserName(userDO.getSysUserName())
                .baseId(userDO.getId()).pwdId(userAccountPwdDO.getId())
                .sysLoginNumber(userAccountPwdDO.getSysLoginNumber()).sysUserName(userDO.getSysUserName()).build();

        String jsonStr = JSONObject.toJSONString(redisUser);
        //生成token
        // randomKey和token已经生成完毕
        final String randomKey = jwtTokenUtil.getRandomKey();
        final String token = jwtTokenUtil.generateToken(jsonStr, randomKey);
        //限制登录次数 -1 则不进行限制
        Integer loginNumber = userAccountPwdDO.getSysLoginNumber();
        //限制登录次数
        if (loginNumber != -1) {
            //redis  key--》用户id     v----》 List<String> String=uuid
            //redis  key--》uuid     v----》 token
            Object listUuid = redisUtil.get(RedisKeyEnum.REDIS_KEY_USER_ID.getKey() + userDO.getId());
            //首次登录
            if (listUuid == null) {
                List<String> list = Lists.newArrayList();
                list.add(redisUserKey);
                String listStr = JSONObject.toJSONString(list);
                //redis  key--》用户id     v----》 List<String> String=uuid
                loginNumber(userDO.getId(), listStr, redisUserKey, token);
            } else {//已经登录过了
                List<String> list = JSON.parseObject((String) listUuid, new TypeReference<List<String>>() {
                });
                //登录次数已满，需要剔除最先登录的用户
                if (list.size() >= loginNumber) {
                    int count = list.size() - loginNumber + 1;
                    String[] str = new String[count];
                    for (int i = 0; i < count; i++) {
                        str[i] = RedisKeyEnum.REDIS_KEY_USER_INFO.getKey() + list.get(i);
                    }
                    String firstUuid = list.get(0);
                    redisUtil.del(str);
                    list.remove(0);
                    list.add(redisUserKey);
                    String listStr = JSONObject.toJSONString(list);
                    loginNumber(userDO.getId(), listStr, redisUserKey, token);
                } else {//登录次数未满
                    list.add(redisUserKey);
                    String listStr = JSONObject.toJSONString(list);
                    loginNumber(userDO.getId(), listStr, redisUserKey, token);
                }
            }
        } else if (loginNumber.equals(-1)) {//不限制登录次数
            //token放入 redis
            redisUtil.set(RedisKeyEnum.REDIS_KEY_USER_INFO.getKey() + redisUserKey, token, RedisKeyEnum.REDIS_KEY_USER_INFO.getExpireTime());
        }
        LoginLogDO entity = new LoginLogDO();
        entity.setGroupId(redisUser.getGroupId());
        entity.setUserLoginName(redisUser.getSysUserLoginName());
        entity.setUserUserName(userDO.getSysUserName());
        entity.setUserLoginType(1);
        entity.setUserId(userDO.getId());
        entity.setId(snowFlake.nextId());
        entity.setUuid(redisUserKey);
        loginLog.loginLog(entity, request);
        //登录成功删除验证码
        redisUtil.del("verUUidCode:" + sysUserLoginReq.getVerUUidCode());
        return redisUserKey;
    }

    public UserInfoRes getUserInfo() {
        RedisUser redisUser = this.redisUser();
        UserDO userDO = userMapper.getUserInfo(redisUser.getBaseId());
        UserInfoRes userInfoRes = UserInfoRes.builder().sysUserName(userDO.getSysUserName()).build();
        return userInfoRes;
    }

    public JSONResult createVerificationCode() {
        try {
            SpecCaptcha specCaptcha = new SpecCaptcha();
            String verUUidCode = specCaptcha.outaaaa(null);
            String key = StrUtil.genUUID();
            String verificationCode = specCaptcha.text();
            redisUtil.set(RedisKeyEnum.REDIS_KEY_IMG_UUID_CODE.getKey() + key, verificationCode, RedisKeyEnum.REDIS_KEY_IMG_UUID_CODE.getExpireTime());
            Map<String, Object> map = Maps.newHashMap();
            //base64
            map.put(RedisKeyEnum.REDIS_KEY_IMG_BASE.getKey(), verUUidCode);
            //一个verUUidCode对应当前请求用户
            map.put(RedisKeyEnum.REDIS_KEY_IMG_UUID_CODE_HEADER.getKey(), key);
            map.put(RedisKeyEnum.REDIS_KEY_IMG_TYPE.getKey(), "data:image/png;base64,");
            return JSONResult.ok(map);
        } catch (Exception e) {
            log.error("生成验证码异常:{},{}", e.getMessage(), e.toString());
            throw BizException.fail("获取验证码base64异常");
        }
    }

    /**
     * @param userId   用户id
     * @param listUuid List<String> String=uuid
     * @param uuid
     * @param token
     */
    private void loginNumber(Long userId, String listUuid, String uuid, String token) {
        //redis  key--》用户id     v----》 List<String> String=uuid
        redisUtil.set(RedisKeyEnum.REDIS_KEY_USER_ID.getKey() + userId, listUuid, RedisKeyEnum.REDIS_KEY_USER_ID.getExpireTime());
        redisUtil.set(RedisKeyEnum.REDIS_KEY_USER_INFO.getKey() + uuid, token, RedisKeyEnum.REDIS_KEY_USER_INFO.getExpireTime());
    }

    private void checkVerificationCode(SysUserLoginReq sysUserLoginReq) {
        String key = "verUUidCode:" + sysUserLoginReq.getVerUUidCode();
        boolean existsKey = redisUtil.hasKey(key);
        if (!existsKey) {//
            throw BizException.fail("验证码已失效");
        }
        String verUUidCode = (String) redisUtil.get(key);
        String aa = sysUserLoginReq.getVerificationCode().toLowerCase().trim();
        if (!aa.equals(verUUidCode.toLowerCase().trim())) {
            throw BizException.fail("验证码不正确");
        }
    }
}

package com.momo.common.core.error;


/**
 * @ClassName: RedisKeyEnum
 * @Author: Jie Li
 * @Date 2019-11-14 15:41
 * @Description: 错误码
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
public enum RedisKeyEnum {

    //
    REDIS_KEY_IMG_UUID_CODE("verUUidCode:", 60 * 2, "图片验证码"),
    //
    REDIS_KEY_IMG_BASE("baseImg", 60, "base64图片"),
    REDIS_KEY_IMG_TYPE("imgType", 60, "t图片类型"),
    //
    REDIS_KEY_IMG_UUID_CODE_HEADER("verUUidCode", 60, "图片验证码唯一uuid放在header里"),
    //
    REDIS_KEY_USER_INFO("userInfo:", 60 * 60 * 3, "用户登录"),
    //
    REDIS_KEY_USER_ID("userId:", 60 * 60 * 3, "用户登录"),
    //
    REDIS_KEY_USER_HEADER_CODE("userHeader", -1, "用户信息Token"),
    //
    REDIS_KEY_USER_LIST_INFO("userList:", 60 * 60 * 3 + 2, "用户登录，限制登录次数"),
    //
    REDIS_KEY_HEADER_INFO("X-Token", 60 * 60 * 15, "给前端的header"),
    REDIS_KEY_WEBSITE_VISIBLE_TOTAL("webTotalPeople", 60 * 60 * 15, "网站访问总人数"),
    //
    REDIS_USER_INFO("user:", -1, "用户信息,实时更新"),
    REDIS_ROLE_STR("roleStr:", -1, "所有单个角色 "),
    REDIS_ADMIN_ROLE_STR("adminRoleStr:", -1, "管理员角色 "),

    REDIS_ACL_MAP("aclMap:", -1, "权限点 Map"),
    REDIS_USER_ROLES_STR("userRoleStr:", -1, "用户和角色关系 "),
    REDIS_ROLE_ACLS_MAP("roleAclsMap:", -1, "角色和权限关系 "),
    //
    REDIS_KEY_USER_CODE("user:", -1, "用户信息"),
    ;
    private final String key;
    private final int expireTime;
    private final String keyInfo;


    RedisKeyEnum(String key, int expireTime, String keyInfo) {
        this.key = key;
        this.expireTime = expireTime;
        this.keyInfo = keyInfo;
    }


    public String getKey() {
        return key;
    }

    public int getExpireTime() {
        return expireTime;
    }

    public String getKeyInfo() {
        return keyInfo;
    }
}

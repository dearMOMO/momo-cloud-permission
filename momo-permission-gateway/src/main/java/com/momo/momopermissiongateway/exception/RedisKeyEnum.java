package com.momo.momopermissiongateway.exception;

/**
 * Created by MOMO on 2018/9/14.
 */
public enum RedisKeyEnum {

    //
    REDIS_KEY_IMG_UUID_CODE("verUUidCode:", 60*2, "图片验证码"),
    //
    REDIS_KEY_IMG_UUID_CODE_HEADER("verUUidCode", 60, "图片验证码唯一uuid放在header里"),
    //
    REDIS_KEY_USER_CODE("user:",-1,"用户信息"),
    //
    REDIS_KEY_USER_HEADER_CODE("userHeader",-1,"用户信息"),
    //
    REDIS_KEY_USER_INFO("userInfo:", 60 * 60*3, "用户登录"),
    //
    REDIS_KEY_USER_ID("userId:", 60 * 60*3, "用户登录"),
    //
    REDIS_KEY_USER_LIST_INFO("userList:", 60 * 60*3+2, "用户登录，限制登录次数"),
    //
    REDIS_KEY_HEADER_INFO("X-Token", 60 * 60*15, "给前端的header"),
    REDIS_KEY_WEBSITE_VISIBLE_TOTAL("webTotalPeople", 60 * 60*15, "网站访问总人数"),
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

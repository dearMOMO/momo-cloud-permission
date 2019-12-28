package com.momo.mapper.enums;

/**
 * @ClassName: LoginTypeEnum
 * @Author: Jie Li
 * @Date 2019-12-27 17:24
 * @Description: 登陆类型
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
public enum LoginTypeEnum {
    pwd(0, "账号密码登陆", "账号密码登陆");

    public final Integer type;
    public final String value;
    public final String description;

    LoginTypeEnum(Integer type, String value, String description) {
        this.type = type;
        this.value = value;
        this.description = description;
    }
}

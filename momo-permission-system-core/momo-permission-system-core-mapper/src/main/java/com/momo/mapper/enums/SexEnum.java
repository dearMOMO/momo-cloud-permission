package com.momo.mapper.enums;

/**
 * @ClassName: Sex
 * @Author: Jie Li
 * @Date 2019-12-21 21:47
 * @Description: 性别 枚举
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
public enum SexEnum {
    woman(0, "女", ""),
    man(1, "男", ""),
    secret(2, "保密", "");

    public final Integer type;
    public final String value;
    public final String description;

    SexEnum(Integer type, String value, String description) {
        this.type = type;
        this.value = value;
        this.description = description;
    }
}


package com.momo.mapper.enums;

/**
 * @ClassName: Status
 * @Author: Jie Li
 * @Date 2019-12-27 15:00
 * @Description: TODO
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
public enum DisabledStatusEnum {
    start(0, "已启用",""),
    disabled(1, "已禁用","");

    public final Integer type;
    public final String value;
    public final String description;

    DisabledStatusEnum(Integer type, String value, String description) {
        this.type = type;
        this.value = value;
        this.description = description;
    }
}

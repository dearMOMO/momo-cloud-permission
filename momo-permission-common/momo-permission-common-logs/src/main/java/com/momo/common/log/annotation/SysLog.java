package com.momo.common.log.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: SysLog
 * @Author: Jie Li
 * @Date 2019-11-14 14:03
 * @Description: 表明日志注解
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    /**
     * 描述
     *
     * @return {String}
     */
    String value();
}

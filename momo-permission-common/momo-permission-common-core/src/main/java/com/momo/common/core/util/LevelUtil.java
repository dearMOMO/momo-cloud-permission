package com.momo.common.core.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName: LevelUtil
 * @Author: Jie Li
 * @Date 2019-11-14 14:39
 * @Description: TODO
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
public class LevelUtil {
    public final static String SEPARATOR = ".";
    public final static String ROOT = "0";

    //0
    //0.1
    //0.1.1
    //0.1.3
    //0.4


    public static String calculateLevel(String parentLevel, long parentId) {
        if (StringUtils.isBlank(parentLevel)) {
            return ROOT;
        } else {
            return StringUtils.join(parentLevel, SEPARATOR, parentId);
        }
    }

    public static String calculateLevelString(String parentLevel, long parentId) {
        if (StringUtils.isBlank(parentLevel)) {
            return ROOT;
        } else {
            return StringUtils.join(parentLevel, SEPARATOR, parentId);
        }
    }
}

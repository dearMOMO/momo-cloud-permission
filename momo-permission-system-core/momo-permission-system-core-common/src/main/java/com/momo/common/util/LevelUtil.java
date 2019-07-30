package com.momo.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by kanshunfu on 2019/01/07.
 */
public class LevelUtil {
    public final static String SEPARATOR = ".";
    public final static String ROOT = "0";

    //0
    //0.1
    //0.1.1
    //0.1.3
    //0.4


    public static String  calculateLevel(String parentLevel, long parentId) {
        if (StringUtils.isBlank(parentLevel)) {
            return ROOT;
        } else {
            return StringUtils.join(parentLevel,SEPARATOR,parentId);
        }
    }
    public static String calculateLevelString(String parentLevel, long parentId) {
        if (StringUtils.isBlank(parentLevel)) {
            return ROOT;
        } else {
            return StringUtils.join(parentLevel,SEPARATOR,parentId);
        }
    }
}

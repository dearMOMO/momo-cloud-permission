package com.momo.momopermissiongateway.utils;

import java.util.Date;

public class DateUtil {

    public static Date getCurrentTime(){
        return new Date();
    }

    /**
     * 计算两个 时间差  分钟
     * @param endDate
     * @param nowDate
     * @return
     */
    public static long getMinuteDifference(Date endDate, Date nowDate) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
    /*    // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;*/
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long second = diff / ns;
        return second;
    }

}

package com.yw.colliery.sdk.utils;

/**
 * @Author: ignore1992
 * @Description: 日期工具类
 * @Date: 2019/1/12
 */
public final class DateUtils {
    public static final long ONE_DAY_MILLS =  24 * 60 * 60 * 1000L;
    /**一年的毫秒数(按365天来算)**/
    public static final long ONE_YEAR_MILLS =  365 * 24 * 60 * 60 * 1000L;
    /**一年的秒数(按365天来算)**/
    public static final int ONE_YEAR_SECODNS =  365 * 24 * 60 * 60;

    /**
     * 与几天前的时间进行比较，小于等于返回false，大于返回true
     * @param left
     * @param right
     * @param beforeDay
     * @return
     */
    public static boolean compare(long left, long right, int beforeDay) {
        long compareTime = right - beforeDay * ONE_DAY_MILLS;
        if (left <= compareTime) {
            return false;
        } else {
            return true;
        }
    }

}

package com.yw.colliery.sdk.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    public static Date getMonthStartTime(String dataFormat) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-mm");
            Date date = format.parse(dataFormat);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            // 设置为1号,当前日期既为本月第一天
            cal.set(Calendar.DAY_OF_MONTH, 1);
            cal.set(Calendar.HOUR, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            return cal.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getMonthEndTime(String dataFormat) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-mm");
            Date date = format.parse(dataFormat);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            // 设置为1号,当前日期既为本月第一天
            cal.set(Calendar.DAY_OF_MONTH, 1);
            cal.set(Calendar.HOUR, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            //向后推一个月
            cal.roll(Calendar.MONTH, -1);
            return cal.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getYearStartTime(String dataFormat) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy");
            Date date = format.parse(dataFormat);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            // 设置为1号,当前日期既为本月第一天
            cal.set(Calendar.DAY_OF_YEAR, 1);
            cal.set(Calendar.HOUR, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            return cal.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getYearEndTime(String dataFormat) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy");
            Date date = format.parse(dataFormat);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            // 设置为1号,当前日期既为本月第一天
            cal.set(Calendar.DAY_OF_YEAR, 1);
            cal.set(Calendar.HOUR, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            //向后推一个月
            cal.roll(Calendar.YEAR, -1);
            return cal.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

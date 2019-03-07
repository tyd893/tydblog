package com.my.tydblog.util;


import java.util.Date;

public class DateUtil {
    public static long getTimeDifference(Date now, Date date) {
        long l=now.getTime()-date.getTime();       //获取时间差
        long day=l/(24*60*60*1000);
        long hour=(l/(60*60*1000)-day*24);
        long min=((l/(60*1000))-day*24*60-hour*60);
        long s=(l/1000-day*24*60*60-hour*60*60-min*60);
        return day; // 返回相差得天数
    }
}

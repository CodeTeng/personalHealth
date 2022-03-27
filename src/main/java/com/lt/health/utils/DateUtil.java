package com.lt.health.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @description: 事件格式转换工具类
 * @author: 狂小腾
 * @date: 2022/3/22 9:06
 */
public class DateUtil {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String getNowDate() {
        return format.format(new Date());
    }

    public static String getNowDateTime() {
        return format2.format(new Date());
    }

    /**
     * 获取半年前时间
     */
    public static String getHalfYearAgo() {
        //创建一个日历对象
        Calendar c = Calendar.getInstance();
        //设置当前时间
        c.setTime(new Date());
        //设置6个月前
        c.add(Calendar.MONTH, -6);
        Date time = c.getTime();
        return format.format(time);
    }

    /**
     * 获取1年前时间
     */
    public static String getOneYearAgo() {
        //创建一个日历对象
        Calendar c = Calendar.getInstance();
        //设置当前时间
        c.setTime(new Date());
        //设置1年前
        c.add(Calendar.YEAR, -1);
        Date time = c.getTime();
        return format.format(time);
    }

    /**
     * 获取3年前时间
     */
    public static String getThreeYearAgo() {
        //创建一个日历对象
        Calendar c = Calendar.getInstance();
        //设置当前时间
        c.setTime(new Date());
        //设置1年前
        c.add(Calendar.YEAR, -3);
        Date time = c.getTime();
        return format.format(time);
    }

    /**
     * 将时间戳转换为时间
     */
    public static String stampToDate(Long s) {
        Date date = new Date(s);
        return format.format(date);
    }


    /**
     * 传入时间字符串和天数
     * 返回该日期一周前的时间
     *
     * @param date 时间字符串
     * @param day  天数
     */
    public static String getWeekBeforeDate(String date, int day) throws ParseException {
        Date parse = format.parse(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(parse);
        cal.add(Calendar.DATE, -day);
        Date time = cal.getTime();
        return format.format(time);
    }

    /**
     * 根据时间得到本周是星期几
     */
    public static int getWeekOfDate(String date) throws ParseException {
        int[] week = {7, 1, 2, 3, 4, 5, 6};
        Calendar cal = Calendar.getInstance();
        cal.setTime(format.parse(date));
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return week[w];
    }

    public static void main(String[] args) throws ParseException {
        //System.out.println(getWeekBeforeDate("2021-6-22", -1));
        //System.out.println(getWeekOfDate("2021-6-22"));
        String date = getNowDate();
        int week = getWeekOfDate(date);
        String date1 = getWeekBeforeDate(date, week - 1);
        System.out.println("第一周周" + week + ":" + date + "-->周一" + date1);
        //第二周
        String date2 = getWeekBeforeDate(date, week);
        String week2 = getWeekBeforeDate(date2, 6);
        System.out.println("第二周周天:" + date2 + "-->周一：" + week2);

        String date3 = getWeekBeforeDate(week2, 1);
        String week3 = getWeekBeforeDate(date3, 6);
        System.out.println("第三周周天:" + date3 + "-->周一：" + week3);

        String date4 = getWeekBeforeDate(week3, 1);
        String week4 = getWeekBeforeDate(date4, 6);
        System.out.println("第四周周天:" + date4 + "-->周一：" + week4);
    }

}

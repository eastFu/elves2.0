package cn.gyyx.elves.console.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @ClassName : DateUtil
 * @Author : weiyao@gyyx.cn
 * @Date 20:21 2018/9/21
 * @Do : 时间、日期工具类
 **/
public class DateUtil {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
    private static Calendar calendar = Calendar.getInstance(Locale.CHINA);

    public static String getNowDate(){
        Date d = new Date();
        return sdf.format(d);
    }

    /**上周周一 和 本周周一
     * */
    public static String[] getLastWeekBeginAndEnd(){
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(new Date());

        calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
        String end = sdf2.format(calendar.getTime());

        calendar.add(Calendar.WEEK_OF_MONTH,-1);
        calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
        String start = sdf2.format(calendar.getTime());
        return new String[]{start,end};
    }

    /**获取指定月份第一天 和下月第一天
     * */
    public static String[] getMonthBeginAndEnd(Date date){
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);

        calendar.set(Calendar.DAY_OF_MONTH,1);
        String start = sdf2.format(calendar.getTime());

        calendar.add(Calendar.MONTH,1);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        String end =  sdf2.format(calendar.getTime());
        return new String[]{start,end};
    }
}

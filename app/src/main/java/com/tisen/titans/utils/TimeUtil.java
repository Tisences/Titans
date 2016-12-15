package com.tisen.titans.utils;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**时间工具类
 * Created by tisen on 2016/12/14 21:12.
 * Email tisences@qq.com
 */
public class TimeUtil {
    public final static String FORMAT_DATE = "yyyy-MM-dd";
    public final static String FORMAT_TIME = "hh:mm";
    public final static String FORMAT_DATE_TIME = "yyyy-MM-dd hh::mm";
    public final static String FORMAT_MONTH_DAY_TIME = "MM月dd日 hh:mm";

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
    private static final int YEAR = 365*24*60*60;
    private static final int MONTH = 30*24*60*60;
    private static final int DAY = 24*60*60;
    private static final int HOUR = 60*60;
    private static final int MINUTE = 60;

    /**
     * 获取描述性的时间间隔
     * @param timeStamp
     * @return
     */
    public static String getDescriptionTimeFromTimeStamp(long timeStamp){
        long currentTime = System.currentTimeMillis();
        long timeGap = (currentTime-timeStamp)/1000;

        String timeStr = null;
        if(timeGap>YEAR){
            timeStr = timeGap/YEAR+"年前";
        }else if (timeGap>MONTH){
            timeStr = timeGap/MONTH+"个月前";
        }else if (timeGap>DAY){
            timeStr = timeGap/DAY+"天前";
        }else if (timeGap>HOUR){
            timeStr = timeGap/HOUR+"小时前";
        }else if(timeGap>MINUTE){
            timeStr = timeGap/MINUTE+"分钟前";
        }else {
            timeStr = "刚刚";
        }
        return timeStr;
    }

    /**
     * 从时间戳获取时间字符串
     * 若为当前年份，省略年份
     * @param timeStamp
     * @param format
     * @return
     */

    public static String getFormatTimeFromTime(long timeStamp,String format){

        if(TextUtil.isEmpty(format)){
            simpleDateFormat.applyPattern(FORMAT_DATE);
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            int year = Integer.valueOf(simpleDateFormat.format(new Date(timeStamp)).substring(0,4));
            if(currentYear == year){
                simpleDateFormat.applyPattern(FORMAT_MONTH_DAY_TIME);
            }else {
                simpleDateFormat.applyPattern(FORMAT_DATE_TIME);
            }
        }else {
            simpleDateFormat.applyPattern(format);
        }
        return simpleDateFormat.format(new Date(timeStamp));
    }

    /**
     * 获取当前时间字符串
     * @param format
     * @return
     */

    public static String getCurrentTime(String format){
        if(TextUtil.isEmpty(format)){
            simpleDateFormat.applyPattern(FORMAT_DATE_TIME);
        }else {
            simpleDateFormat.applyPattern(format);
        }
        return simpleDateFormat.format(new Date());
    }

    /**
     * 获取给定的时间字符串
     * @param time
     * @param format
     * @return
     */
    public static String getDateTime(Date time,String format){
        if(TextUtil.isEmpty(format)){
            simpleDateFormat.applyPattern(FORMAT_DATE_TIME);
        }else {
            simpleDateFormat.applyPattern(format);
        }
        return simpleDateFormat.format(time);
    }













}

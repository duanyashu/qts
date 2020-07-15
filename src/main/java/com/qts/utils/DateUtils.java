package com.qts.utils;

/**
 * @description: 日期工具类
 * @author: duanyashu
 * @time: 2020-07-07 14:08
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    /**
     * 一天中一共的毫秒数
     */
    public static long millionSecondsOfDay = 86400000;

    /**
     *年月日时分秒格式
     */
    public final static String YEAR_MONTH_DAY_HOUR_MINUTE_SECOND 			 = "yyyyMMddHHmmss";
    /**
     * 年月日格式
     */
    public final static String YEAR_MONTH_DAY 								 = "yyyyMMdd";
    /**
     * 年月日时分秒格式
     */
    public final static String STANDARD_DATE 			 					 = "yyyy-MM-dd HH:mm:ss";
    /**
     * 年-月-日格式
     */
    public final static String YEAR_MONTH_DAY_ 								 = "yyyy-MM-dd";
    /**
     * 年-月格式
     */
    public final static String YEAR_MONTH_ 									 = "yyyy-MM";
    /**
     * 标准时间
     */
    public final static String STANDARD_TIME 								 = "HH:mm:ss";

    /**
     * 将Date类型转换为指定格式
     * @param date
     * @param formatStr
     * @return
     */
    public static String getDateTimeFormat(Date date,String formatStr){
        DateFormat dateFormat = new SimpleDateFormat(formatStr);
        return dateFormat.format(date);

    }

    /**
     * 获取当前时间字符串
     * @return
     */
    public  static  String getCurrentDate(){
        return getDateTimeChars(STANDARD_DATE);
    }
    /**
     * <p>获得时间不同格式的时间字符串</p>
     */
    public static String getDateTimeChars(String formatString)
    {
//        Calendar cal = Calendar.getInstance();
//        DateFormat dateFormat = new SimpleDateFormat(formatString);
//        return dateFormat.format(cal.getTime());
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern(formatString);
        return localDateTime.format(pattern);
    }
    /**
     * <p>获得时间对应的星期数</p>
     * @param dateString
     */
    public static String getWeek(String dateString,String formatString)
    {
        final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五","星期六" };
        try {
            SimpleDateFormat sdfInput = new SimpleDateFormat(formatString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdfInput.parse(dateString));
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)-1;
            if(dayOfWeek<0){dayOfWeek=0;}
            return  dayNames[dayOfWeek];
        } catch (ParseException e) {
        e.printStackTrace();
        }
        return null;
    }


    /**
     * <p>返回指定格式的第n天后的时间串</p>
     * @param date
     * @param n
     * @param dateFormatStr
     * @return n天后的时间串
     */
    public static String afterNDay(Date date,int n,String dateFormatStr)
    {
        Calendar c=Calendar.getInstance();
        DateFormat df=new SimpleDateFormat(dateFormatStr);
        c.setTime(date);
        c.add(Calendar.DATE,n);
        Date d=c.getTime();
        String s=df.format(d);
        return s;
    }

    /**
     * 取得当月天数
     * */
    public static int getCurrentMonthLastDay()
    {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 得到指定月的天数
     * */
    public static int getMonthLastDay(int year, int month)
    {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }
    /**
     * add by mengye 2016-1-19
     * 得到两个日期之间相差的天数,两头不算,取出数据后，可以根据需要再加
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getDifferDay(String date1, String date2) {
        Date dateTime1_tmp = parse(date1, "yyyy-MM-dd");
        Date dateTime2_tmp = parse(date2, "yyyy-MM-dd");
        Long d1 = dateTime1_tmp.getTime();
        Long d2 = dateTime2_tmp.getTime();
        return (int) ((d2 - d1) / millionSecondsOfDay);
    }
    /**
     * add by mengye 2016-1-19
     * 根据指定日期字符串格式格式化日期为Date型
     *
     * @param date
     * @param formater
     * @return
     */
    public static Date parse(String date, String formater) {
        SimpleDateFormat sdf = new SimpleDateFormat(formater);
        Date result = null;
        try {
            result = sdf.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static  String dateToStr(Date date,String formatStr) {
        String dateStr = "";
        SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
        if(date != null) {
            dateStr = formatter.format(date);
        }

        return dateStr;
    }

    /**
     *  两个时间相差距离多少天多少小时多少分多少秒
     * @author gaodengke
     * @date 2016-08-09
     * @param beginDate 时间参数 1 格式：1990-01-01 12:00:00
     * @param endDate 时间参数 2 格式：2009-01-01 12:00:00
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static String getDistanceTime(Date beginDate, Date endDate) {
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        long time1 = beginDate.getTime();
        long time2 = endDate.getTime();
        long diff ;
        if(time1<time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }
        day = diff / (24 * 60 * 60 * 1000);
        hour = (diff / (60 * 60 * 1000) - day * 24);
        min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
        return day + "天" + hour + "小时" + min + "分" + sec + "秒";
    }

    /**
     * 生成合样码时间
     * @return
     */
    public static String getMargeCodeTime() {
        String standardDate = getDateTimeChars(YEAR_MONTH_DAY_HOUR_MINUTE_SECOND);
        return  getShifts() + standardDate.substring(4,8);
    }

    /**
     * 获取当前班次
     * @return
     */
    public static Integer getShifts() {
        String standardDate = getDateTimeChars(YEAR_MONTH_DAY_HOUR_MINUTE_SECOND);
        String yearMonthDay = standardDate.substring(0,8);
        //20190107000000
        String zoreTime=yearMonthDay+"000000";
        String eightTime=yearMonthDay+"080000";
        String fourTime=yearMonthDay+"160000";
        Integer t;
        if(standardDate.compareTo(zoreTime)>=0 && standardDate.compareTo(eightTime)<0){
            t=0;
        }else if(standardDate.compareTo(eightTime)>=0 && standardDate.compareTo(fourTime)<0){
            t=8;
        }else{
            t=4;
        }
        return  t;
    }

    public static long getOpenHyBtnTime(){
        LocalDateTime localDateTime = LocalDateTime.now();
        /**
         * dateTime格式化模板
         */
        DateTimeFormatter df = DateTimeFormatter.ofPattern(YEAR_MONTH_DAY_HOUR_MINUTE_SECOND);
        LocalDateTime endTime;
        switch (getShifts()){
            case 0:
                String  endDateStr8 = getDateTimeChars(DateUtils.YEAR_MONTH_DAY)+"080000";
                endTime = LocalDateTime.parse(endDateStr8,df);
                break;
            case 8:
                String endDateStr4 = getDateTimeChars(DateUtils.YEAR_MONTH_DAY)+"160000";
                endTime = LocalDateTime.parse(endDateStr4,df);
                break;
            case 4:
                /**
                 * 获取当天最大时间
                 */
                LocalDateTime maxTime = localDateTime.with(LocalTime.MAX);
                endTime =maxTime;
                break;
            default:
                endTime= localDateTime;
                break;
        }
        // 获取合样按钮提前开启时间(分钟)
        long openAdvanceTime = 60*60;
        // 距离开启按钮时间 = 下班时间 - 提前开启时间 - 当前时间
        long intervalSecond =  endTime.toEpochSecond(ZoneOffset.of("+8"))-openAdvanceTime-localDateTime.toEpochSecond(ZoneOffset.of("+8"));
        return intervalSecond;
    }

    public static void main(String[] args)
    {
        System.out.println("getO = " + getDateTimeChars(DateUtils.STANDARD_DATE));

    }

}

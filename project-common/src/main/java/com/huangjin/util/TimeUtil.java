package com.huangjin.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by huang on 2016-12-13.
 */
public class TimeUtil {

    /**
     * 将时间转换为时间戳
     * @return
     * @throws ParseException
     */
    public static Long toLong(String date, String format, boolean isMil) {
        try {
            if (isMil) {
                return new SimpleDateFormat(format).parse(date).getTime();
            }else{
                return new SimpleDateFormat(format).parse(date).getTime()/1000;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 将时间戳转换为时间
     * @param s
     * @return
     */
    public static String stampToDate(String s, String format) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 校验String是否为指定的时间格式
     * @param str
     * @return
     */
    public static boolean isValidDate(String str, String format) {
        boolean convertSuccess = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy-MM-dd区分大小写；
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            // 设置lenient为false.
            // 否则SimpleDateFormat会比较宽松地验证日期，比如2007-02-29会被接受，并转换成2007-03-01
            sdf.setLenient(false);
            sdf.parse(str);
        } catch (ParseException e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }


    public static Date getToday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);
        return cal.getTime();
    }

    public static Date getYesterDay() {
        return dateAddDays(new Date(), -1);
    }

    public static Date getBeforeYesterDay() {
        return dateAddDays(new Date(), -2);
    }

    public static Date dateAddDays(Date date, int addDays) {

        Date newDate = null;
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, addDays);
            newDate = cal.getTime();
        } catch (Exception e) {
            return null;
        }
        return newDate;
    }



}

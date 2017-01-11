package com.huangjin.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public static Long toLong(String date,boolean isMil){
        try {
            if (isMil) {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date).getTime();
            }else{
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date).getTime()/1000;
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
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}

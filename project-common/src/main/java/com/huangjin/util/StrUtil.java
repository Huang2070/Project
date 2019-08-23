package com.huangjin.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author: 黄金
 * @Description:
 * @Date: Create in 11:41 2019-07-29
 */
public class StrUtil {


    /**
     * 是否是gb2312兼容的字符串。
     * 判断标准：字符串里的字符是否全部是gb2312或者anscii字符集中的可见字符。
     *
     * @param str
     * @return
     */
    public static boolean isGB2312Compatible(String str) {
        char[] chars = str.toCharArray();
        boolean isGB2312 = true;
        for (int i = 0; i < chars.length; i++) {
            // 可视的ansciall码字符
            if (isVisibleAscii(chars[i])) {
                continue;
            }
            // 是否是gb2312的字符。
            if (!isGB2312Character(chars[i])) {
                return false;
            }
        }
        return isGB2312;
    }

    /**
     * 判断字符是否为ascii 可见字符。
     *
     * @param ch
     * @return
     */
    public static boolean isVisibleAscii(char ch) {
        return ch >= 32 && ch <= 126;
    }

    /**
     * 判断字符是否在gb2312字符集里被收录。
     *
     * @param c
     * @return
     */
    public static boolean isGB2312Character(char c) {
        byte[] bytes = null;
        try {
            bytes = ("" + c).getBytes("GB2312");
        } catch (Exception e) {
            return false;
        }
        if (bytes == null || bytes.length != 2) {
            return false;
        } else {
            int[] ints = new int[2];
            ints[0] = bytes[0] & 0xff;
            ints[1] = bytes[1] & 0xff;
            if (!(ints[0] >= 0xA1 && ints[0] <= 0xFE // GB2312编码范围：A1A1－FEFE
                && ints[1] >= 0xA1 && ints[1] <= 0xFE
                && !(ints[0] >= 0xAA && ints[0] <= 0xAF) // 10-15区为空白区，没有使用。(170 - 175)
                && !(ints[0] >= 0xF8)) // 88-94区为空白区，没有使用。(248 - 254)
            ) {
                return false;
            }
        }
        return true;
    }


    /**
     * 字符是否是英文字母或数字。
     *
     * @param ch
     * @return
     */
    public static boolean isDigitOrEnglishCharacter(char ch) {
        return isDigitCharacter(ch) || isEnglishCharacter(ch);
    }

    /**
     * 字符是否为数字。
     *
     * @param ch
     * @return
     */
    public static boolean isDigitCharacter(char ch) {
        return ch >= 48 && ch <= 57; // 数字
    }

    /**
     * 字符是否为英文字母
     *
     * @param ch
     * @return
     */
    public static boolean isEnglishCharacter(char ch) {
        return ch >= 65 && ch <= 90 // 大写字母
            || ch >= 97 && ch <= 122; //  小写字母
    }


    public static ArrayList<Long> explode2Long(String str, String token) {
        String[] array = str.split(token);
        ArrayList<Long> list = new ArrayList<>();
        try {
            if (array != null && array.length > 0) {
                for (String strTmp : array) {
                    if (strTmp == null) {
                        continue;
                    }
                    strTmp = strTmp.trim();
                    if (strTmp.length() > 0) {
                        list.add(Long.parseLong(strTmp));
                    }
                }
            }
        } catch (NumberFormatException e) {
            return null;
        }
        return list;

    }

    public static String implode(List<String> list, Integer maxCount) {
        if (list == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        int count = 0;
        for (String str : list) {
            if (maxCount != null && count >= maxCount) {
                break;
            }
            sb.append(",").append(str);
            count++;
        }
        if (sb.length() >=1) {
            sb.deleteCharAt(0);
        }
        return  sb.toString();

    }

    public static List<String> explode(String str, String token) {
        if (str == null || str.trim().length() == 0) {
            return null;
        }
        str = str.trim();
        String[] array = str.split(token);
        ArrayList<String> list = new ArrayList<>();
        if (array != null && array.length > 0) {
            for (String strTmp : array) {
                if (StringUtils.isNotEmpty(str)) {
                    list.add(strTmp);
                }
            }
        }
        return list;
    }

    /**
     * 字符串转date
     *
     * @param timeStr
     * @param format
     * @return
     */
    public static Date str2Date(String timeStr, String format) {
        if (format == null) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        ParsePosition pos = new ParsePosition(0);
        try {
            return formatter.parse(timeStr, pos);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 字符串转date
     *
     * @param date
     * @param format
     * @return
     */
    public static String date2Str(Date date, String format) {
        if (format == null) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try {
            return formatter.format(date);
        } catch (Exception e) {
            return null;
        }
    }
}

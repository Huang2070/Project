package com.huangjin.util;

/**
 * Created by wb-tianxiaoxi on 2015/11/16.
 */
public class CharacterUtil {

    // 将数字转换成字母
    public static String numToLetter(String input) {
        StringBuilder str = new StringBuilder();
        for (byte b : input.getBytes()) {
            int num = (int)b;
            if(num%2==0) {
                str.append((char) (b + 52));
            }else{
                str.append((char) (b + 58));
            }
        }
        return str.toString();
    }

    //将字母转换成数字
    public static String letterToNum(String input) {
        StringBuilder str = new StringBuilder();
        for (byte b : input.getBytes()) {
            int num = (int)b;
            if(num%2==0) {
                str.append((b - 100));
            }else{
                str.append((b - 106));
            }
        }
        return str.toString();
    }

    /**
     * 计算字符串长度,中文字符长度为2
     * @param value
     * @return
     */
    public static int getChineseCharCount(String value) {

        int strLength = 0;
        String chinese = "[^\\x00-\\xff]";
        for (int i = 0; i < value.length(); i++) {
            // 获取一个字符
            String temp = value.substring(i, i + 1);
            // 判断是否为中文字符
            if (temp.matches(chinese)) {
                // 中文字符长度为2
                strLength += 2;
            } else {
                // 其他字符长度为1
                strLength += 1;
            }
        }
        return strLength;
    }



    //Unicode转中文方法
    private String unicodeToCn(String unicode) {
        /** 以 \ u 分割，因为java注释也能识别unicode，因此中间加了一个空格*/
        String[] strs = unicode.split("\\\\u");
        String returnStr = "";
        // 由于unicode字符串以 \ u 开头，因此分割出的第一个字符是""。
        for (int i = 1; i < strs.length; i++) {
            returnStr += (char) Integer.valueOf(strs[i], 16).intValue();
        }
        return returnStr;
    }

    //中文转Unicode
    private String cnToUnicode(String cn) {
        char[] chars = cn.toCharArray();
        String returnStr = "";
        for (int i = 0; i < chars.length; i++) {
            returnStr += "\\u" + Integer.toString(chars[i], 16);
        }
        return returnStr;
    }

}


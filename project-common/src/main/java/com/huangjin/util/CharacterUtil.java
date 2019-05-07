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

}


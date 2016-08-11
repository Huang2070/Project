package com.huangjin.util;

/**
 * Created by wb-tianxiaoxi on 2015/11/16.
 */
public class AppNumToLetter {

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

}


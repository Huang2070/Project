package com.huangjin.interview;

/**
 * Created by huang on 2017-5-19.
 * 请实现一个函数，将一个字符串中的空格替换成“%20”。
 * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */
public class QuestionB {
    public static void main(String[] args) {
        StringBuffer str = new StringBuffer("123 222");
        System.out.println(str.toString().replaceAll("\\s", "%20"));

        String s = "1 2 3";
        System.out.println(s.replaceAll("\\s", "%20"));
    }
}

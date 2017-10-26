package com.huangjin.testjava;


/**
 * Created by huang on 2017-6-21.
 */
public class TestJava {
    public static void main(String[] args) {
        System.out.println(sortType(null));
    }


    private static String sortType(Integer sort) {
        if(sort != null) {
            switch (sort) {
                case 0:
                    return "00"; //更新时间
                case 1:
                    return "11"; //权重降序
                case 2:
                    return "22"; //权重升序
                default:
                    return "00";
            }
        } else {
            return "00";
        }

    }
}

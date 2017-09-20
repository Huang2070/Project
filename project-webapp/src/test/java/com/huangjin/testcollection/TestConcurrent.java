package com.huangjin.testcollection;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by huang on 2017-5-10.
 */
public class TestConcurrent {

    private static ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();

    @Test
    public void test() {
//
//        new Thread("Thread1"){
//            @Override
//            public void run() {
//                map.put(3, 33);
//            }
//        }.run();
//
//        new Thread("Thread2"){
//            @Override
//            public void run() {
//                map.put(4, 44);
//            }
//        }.run();
//
//        new Thread("Thread3"){
//            @Override
//            public void run() {
//                map.put(7, 77);
//            }
//        }.run();
//        System.out.println(map);


        String[] aa = "aaa,bbb,ccc".split(",");

        String[] a = "aaa|bbb|ccc".split("|"); //这样才能得到正确的结果

        for (int i = 0 ; i <aa.length ; i++ ) {

            System.out.println(aa[i]);

        }

        for (int i = 0 ; i <a.length ; i++ ) {

            System.out.println(a[i]);

        }
    }
}

package com.huangjin.testcollection;

import org.junit.Test;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by huang on 2017-5-10.
 */
public class TestConcurrent {

    private static ConcurrentHashMap<Integer, Integer> cmap = new ConcurrentHashMap<>();

    private static HashMap<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) {

    }


    @Test
    public void test() throws Exception {

        ConcurrentHashMap<String, Boolean> map = new ConcurrentHashMap<>();

        Thread a = new Thread() {
            public void run() {
                map.put("first", true);
                map.put("second", true);
            }
        };

        Thread b = new Thread() {
            public void run() {
                map.clear();
            }
        };

        a.start();
        System.out.println(map);

        b.start();
        System.out.println(map);

        a.join();
        System.out.println(map);

        b.join();
        System.out.println(map);

    }
}

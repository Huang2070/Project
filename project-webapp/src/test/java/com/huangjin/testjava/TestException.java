package com.huangjin.testjava;

import com.huangjin.util.TimeUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by huang on 2016-8-25.
 */
public class TestException {

    public static void main(String[] args) throws RuntimeException {
        try {
            System.out.println(1);
            throw new RuntimeException();
        } catch(Exception e) {
            System.out.println(3);
        } finally {
            System.out.println(4);
        }
        System.out.println(5);

    }

}

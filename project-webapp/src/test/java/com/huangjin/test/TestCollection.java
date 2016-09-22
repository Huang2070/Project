package com.huangjin.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by huang on 2016-8-25.
 */
public class TestCollection {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        list.add("abc");
        list.add("cba");
        list.add("aac");

        Collections.sort(list);
        System.out.println(list);
    }
}

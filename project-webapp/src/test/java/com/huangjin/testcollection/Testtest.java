package com.huangjin.testcollection;

import org.junit.Test;

import java.util.*;

/**
 * Created by huang on 2016-12-14.
 */
public class Testtest {
    @Test
    public void test() {
        List<String> linkedList = new LinkedList<>();

        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");

        ListIterator<String> liter = linkedList.listIterator();

        liter.next();
        liter.add("1");
        System.out.println(liter.next());


        liter.add("2");

        System.out.println(liter.next());

        Iterator iter = linkedList.iterator();
        while(iter.hasNext()) {
            System.out.println(iter.next());
        }

    }
}

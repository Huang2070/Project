package com.huangjin.testthread;

import org.junit.Test;

/**
 * Created by huang on 2018-11-15.
 */
public class AtomicTest {

    public static volatile int count = 0;

    //olatile仅仅保证变量在线程间保持可见性，却依然不能保证非原子性的操作, count++是非原子操作
    @Test
    public void test() {
        for (int i = 0; i < 100; i++) {
            new Thread() {
                public void run() {
                    count++;
                }
            }.start();
        }
        System.out.println("count: " + count);
    }
}

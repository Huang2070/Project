package com.huangjin.testthread;

import org.junit.Test;

/**
 * Created by huang on 2016-11-30.
 */
public class testThread {
    public static void main(String[] args) {

    }

    @Test
    public void test() {
        for(int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "  " + i);
            if(i == 10) {
                new FirstThread().start();
                new FirstThread().start();
            }
            if(i == 60) {
                System.exit(0);
            }
        }
    }
}

class FirstThread extends Thread {
    private int i = 0;

    public void run() {
        for(; i < 100; i++) {
            System.out.println(getName() + "  " + i);
        }
    }
}

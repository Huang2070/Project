package com.huangjin.testthread;

/**
 * Created by huang on 2017-5-27.
 * volatile适用于结果不依赖于当前值，并且单一线程改变这个变量
 */
public class TestVolatile {
    public static volatile int race = 0;

    public static void increase() {
        race++;
    }

    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) throws Throwable {

        for(int i = 0; i < THREADS_COUNT; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0; i < 1000; i++) {
                        increase();
                    }
                }
            }).start();
        }

        while (Thread.activeCount() > 2) {
            System.out.println(Thread.activeCount());
            Thread.yield();
        }
        System.out.println(race);
    }
}

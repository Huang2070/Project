package com.huangjin.testthread;

/**
 * Created by huang on 2019-3-7.
 * 重排序
 */
public class Test1 {

    private static boolean ready;

    private static int number;

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            while(!ready) {
                Thread.yield();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(number);
        }).start();

        number = 100;
        ready = true;

    }
}

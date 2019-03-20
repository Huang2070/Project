package com.huangjin.testthread;

/**
 * Created by huang on 2019-3-7.
 * synchronized是锁住的对象, 同一个对象的两个synchronized执行的时候, 也会发生阻塞
 */
public class SynchroninzedTest {

    public synchronized void method1() throws InterruptedException {
        System.out.println("method1 process start");
        Thread.sleep(5000);
        System.out.println("method1 process over");
    }


    public synchronized void method2() throws InterruptedException {
        System.out.println("method2 process start");
        Thread.sleep(1000);
        System.out.println("method2 process over");
    }

    public static void main(String[] args) {
        SynchroninzedTest test = new SynchroninzedTest();
//        SynchroninzedTest test2 = new SynchroninzedTest();

        Thread1 thread1 = new Thread1(test);
        Thread2 thread2 = new Thread2(test);
        thread1.start();
        thread2.start();
    }



    public static class Thread1 extends Thread {
        private SynchroninzedTest test;

        public Thread1(SynchroninzedTest test) {
            this.test = test;
        }

        @Override
        public void run() {
            try {
                test.method1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class Thread2 extends Thread {
        private SynchroninzedTest test;

        public Thread2(SynchroninzedTest test) {
            this.test = test;
        }

        @Override
        public void run() {
            try {
                test.method2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

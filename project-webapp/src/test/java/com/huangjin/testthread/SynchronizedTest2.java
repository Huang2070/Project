package com.huangjin.testthread;

/**
 * Created by huang on 2019-3-7.
 */
public class SynchronizedTest2 {
    public static void main(String[] args) throws InterruptedException {
        final SynchronizedTest2Child t = new SynchronizedTest2Child();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t.doSomething();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t.doSomethingElse();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t.doSomethingElseChild();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public synchronized void doSomething() throws InterruptedException {
        System.out.println("something father");
        Thread.sleep(2000);
        System.out.println("something father end");
    }

    public synchronized void doSomethingElse() throws InterruptedException {
        System.out.println("something else father");
        Thread.sleep(2000);
        System.out.println("something else father end");
    }

    private static class SynchronizedTest2Child extends SynchronizedTest2 {
        public synchronized void doSomething() throws InterruptedException {
//            super.doSomething();
            System.out.println("aaaaaaaaa");
            Thread.sleep(5000);
        }

        public synchronized void doSomethingElseChild() throws InterruptedException {
            System.out.println("something else child");
            Thread.sleep(2000);
            System.out.println("something else child end");
        }
    }
}

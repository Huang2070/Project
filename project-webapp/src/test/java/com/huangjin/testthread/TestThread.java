package com.huangjin.testthread;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;


public class TestThread {
    public static void main(String[] args) {
        FirstThread ft = new FirstThread();
        try {
            for(int i = 100; i > 0; i--) {
                new Thread(ft, "线程" + i).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


class FirstThread implements Runnable {
    private Account account = new Account(100);
    public void run() {
        try {
            account.costMoney(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Account {
    private final ReentrantLock lock = new ReentrantLock();
    private int money;

    public Account(int money) {
        this.money = money;
    }

    public void costMoney(int num) {
        lock.lock();
        try {
            money = money - num;
            Thread.sleep(5);
            System.out.println(Thread.currentThread().getName() + ": " + money);

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

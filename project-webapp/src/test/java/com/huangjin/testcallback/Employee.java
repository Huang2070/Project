package com.huangjin.testcallback;

/**
 * Created by huang on 2016-9-7.
 */
public class Employee {
    private Callback callback;

    public Employee(Callback callback) {
        this.callback = callback;
    }

    public void doWork() {
        System.out.println("Employee do work!");

        callback.excute();
    }
}

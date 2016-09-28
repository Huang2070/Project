package com.huangjin.test.testcallback;

/**
 * Created by huang on 2016-9-7.
 */
public class Boss implements Callback {
    @Override
    public void excute() {
        System.out.println("Boss send order");
    }
}

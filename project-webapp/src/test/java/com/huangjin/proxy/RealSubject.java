package com.huangjin.proxy;

/**
 * Created by huang on 2017-5-26.
 */
public class RealSubject implements Subject {
    @Override
    public void rent() {
        System.out.println("I want to rent my house");
    }
    @Override
    public void hello(String str) {
        System.out.println("hello: " + str);
    }
}

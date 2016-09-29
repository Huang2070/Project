package com.huangjin.testJava;

import org.junit.Test;

import java.lang.reflect.Field;

/**
 * Created by huang on 2016-8-25.
 */
public class TestReflect {

    private String para1 = "123";
    public String para2 = "321";
    protected String para3;
    String para4;

    public static void main(String[] args) {
        Class<?> reflect = null;
        try {
            reflect = Class.forName("com.huangjin.TestReflect");
            //取得类的所有属性

            TestReflect demo = new TestReflect();
            Field[] fields = reflect.getDeclaredFields();
            for(Field field : fields) {
                System.out.println(field.get(demo));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void test() {
        Class<?> reflect = null;
        try {
            reflect = Class.forName("com.huangjin.TestReflect");
            //取得类的所有属性

            Field[] fields = reflect.getDeclaredFields();
            for(Field field : fields) {
                System.out.println(field.get(this));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }


    }
}

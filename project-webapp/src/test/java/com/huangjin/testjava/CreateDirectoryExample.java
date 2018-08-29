package com.huangjin.testjava;

import java.io.File;

/**
 * Created by huang on 2018-4-10.
 */
public class CreateDirectoryExample {
    public static void main(String[] args) {

        File file = new File("C:\\Directory1");
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("创建单个目录成功");
            }else {
                System.out.println("创建目录失败.....");
            }
        }


        File files = new File("C:\\Directory2\\Sub2\\Sub-Sub2");
        if (!files.exists()) {
            if (files.mkdirs()) {
                System.out.println("创建多个目录成功");
            }else {
                System.out.println("创建多个目录失败.....");
            }
        }

        File files1 = new File("C:\\Directory2\\Sub2\\Sub-Sub2\\hehe");
        if (!files1.exists()) {
            if (files1.mkdirs()) {
                System.out.println("创建多个目录成功");
            }else {
                System.out.println("创建多个目录失败.....");
            }
        }
    }
}

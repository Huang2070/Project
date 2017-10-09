package com.huangjin.testjava;

/**
 * Created by huang on 2017-6-8.
 */
public class Openfile {

    /**
     * @param args
     */
    public static void main(String[] args) {

        String cmd = "cmd /C c:\\files\\text.txt";
        Process p;
        try {
            p = Runtime.getRuntime().exec(cmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

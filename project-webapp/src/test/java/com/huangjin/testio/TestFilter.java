package com.huangjin.testio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by huang on 2016-11-8.
 */
public class TestFilter {
    public static void main(String[] args) {
        PrintStream ps = null;
        try {
            FileOutputStream fos = new FileOutputStream("C:\\Users\\huang\\Desktop\\sdf.txt");
            ps = new PrintStream(fos);
            ps.println("123");
            ps.println(new TestFilter());
            ps.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}

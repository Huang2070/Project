package com.huangjin.testio;

import org.apache.ibatis.annotations.Param;

import java.io.FileReader;
import java.io.IOException;

/**
 * Created by huang on 2016-11-8.
 */
public class TestTest {
    public static void main(String[] args) {
        FileReader f = null;
        try {
            StringBuffer str = new StringBuffer();
            char[] buf = new char[1024];
            f = new FileReader("C:\\Users\\huang\\Desktop\\sdf.txt");
            while(f.read(buf) > 0) {
                str.append(buf);
            }
            str.toString();
            System.out.println(str);
            f.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}

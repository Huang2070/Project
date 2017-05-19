package com.huangjin.testio;

import org.apache.activemq.protobuf.BufferInputStream;
import org.junit.Test;

import java.io.*;

/**
 * Created by huang on 2016-11-8.
 */
public class TestTest {
    public static void main(String[] args) {

    }

    @Test
    public void testFileReader() {
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


    @Test
    public void TestInputStream() throws Exception {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("C:\\Users\\huang\\Desktop\\sdf.txt"));

        byte[] bytes = new byte[10];
        int bytesReads = in.read(bytes);

        while(bytesReads != -1) {
            for(byte b : bytes) {
                System.out.print((char)b);

            }
            bytesReads = in.read(bytes);
        }
        in.close();
    }

    @Test
    public void testFilter() {
        PrintStream ps = null;
        try {
            FileOutputStream fos = new FileOutputStream("C:\\Users\\huang\\Desktop\\sdf.txt");
            ps = new PrintStream(fos);
            ps.println("123");
            ps.println(new TestTest());
            ps.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}

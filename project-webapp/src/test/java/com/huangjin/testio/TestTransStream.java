package com.huangjin.testio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by huang on 2016-11-8.
 */
public class TestTransStream {
    public static void main(String[] args) {
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            String buffer = null;
            while((buffer = br.readLine()) != null) {
                if(buffer.equals("exit")) {
                    System.exit(1);
                }
                System.out.println(buffer);
            }
            br.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}

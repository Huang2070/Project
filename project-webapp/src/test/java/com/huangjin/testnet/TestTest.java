package com.huangjin.testnet;

import java.net.InetAddress;

/**
 * Created by huang on 2016-11-8.
 */
public class TestTest {
    public static void main(String[] args) {
        try {
            InetAddress ip = InetAddress.getByName("www.baidu.com");
            System.out.println(ip.getHostAddress());
            System.out.println();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}

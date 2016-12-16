package com.huangjin.testnet;

import org.junit.Test;

import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;


/**
 * Created by huang on 2016-11-8.
 */
public class TestTest {
    public static void main(String[] args) {

    }

    @Test
    public void urlToString() throws Exception {
        // 将application/x-www-form-urlencoded字符串
        // 转换成普通字符串
        String keyWord = URLDecoder.decode("%E7%96%AF%E7%8B%82java", "utf-8");
        System.out.println(keyWord);
        // 将普通字符串转换成
        // application/x-www-form-urlencoded字符串
        String urlStr = URLEncoder.encode("疯狂Android讲义", "GBK");
        System.out.println(urlStr);
    }

    @Test
    public void stringToUrl() throws Exception {
        // 根据主机名来获取对应的InetAddress实例
        InetAddress ip = InetAddress.getByName("www.baidu.com");
        // 判断是否可达
        System.out.println("是否可达：" + ip.isReachable(2000));
        // 获取该InetAddress实例的IP字符串
        System.out.println(ip.getHostAddress());
        // 根据原始IP地址来获取对应的InetAddress实例
        InetAddress local = InetAddress.getByAddress(new byte[]{127,0,0,1});
        System.out.println("本机是否可达：" + local.isReachable(5000));
        // 获取该InetAddress实例对应的全限定域名
        System.out.println(local.getCanonicalHostName());
    }

}

package com.huangjin.testjava;


import com.huangjin.util.HttpUtil;

import java.io.*;
import java.net.HttpURLConnection;

/**
 * Created by huang on 2017-6-21.
 */
public class TestJava {
    public static void main(String[] args) {
        getImgFile("http://nos.netease.com/haitao/ac02d6a5d78e43d5ae8bf9f76e9664171416220031422i2lou9bh10005.jpg");
    }


    public static File getImgFile(String url)  {
        HttpURLConnection conn = null;
        File file = null;
        try {
            conn = HttpUtil.getURLConnection(url);

            // 连接失败，或连接存在异常
            if ((conn == null) || (conn.getResponseCode() != HttpURLConnection.HTTP_OK)) {
                if (conn != null) {
                    conn.disconnect();
                }
                return null;
            }

            byte[] buffer = new byte[1024];
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            int len = 0;

            InputStream is = conn.getInputStream();
            while (true) {
                len = is.read(buffer, 0, buffer.length);
                if (len == -1) {
                    break;
                }
                bos.write(buffer, 0, len);
            }
            OutputStream outputStream = new FileOutputStream("C:\\Users\\huang\\Desktop\\ddd.jpg");
            bos.writeTo(outputStream);

            bos.close();
            conn.disconnect();

        } catch (Exception e) {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return file;
    }
}

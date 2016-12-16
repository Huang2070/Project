package com.huangjin.testnet;

import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by huang on 2016-12-13.
 */
public class MultiThreadDownload {

    private String path;
    private String targetFile;
    private int threadNum;
    private long fileSize;

    public MultiThreadDownload(String path, String targetFile, int threadNum, long fileSize) {
        this.path = path;
        this.targetFile = targetFile;
        this.threadNum = threadNum;
        this.fileSize = fileSize;
    }

    public void downLoad() throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();

        conn.setConnectTimeout(5 * 1000);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept",
                "image/gif, image/jpeg, image/pjpeg, image/pjpeg, "
                        + "application/x-shockwave-flash, application/xaml+xml, "
                        + "application/vnd.ms-xpsdocument, application/x-ms-xbap, "
                        + "application/x-ms-application, application/vnd.ms-excel, "
                        + "application/vnd.ms-powerpoint, application/msword, */*");
        conn.setRequestProperty("Accept-Language", "zh-CN");
        conn.setRequestProperty("Charset", "UTF-8");
        conn.setRequestProperty("Connection", "Keep-Alive");

        fileSize = conn.getContentLength();
        conn.disconnect();

        RandomAccessFile file = new RandomAccessFile(targetFile, "rw");

        file.setLength(fileSize);
        file.close();

        for(int i = 0; i < threadNum; i++) {
            long partSize = fileSize / threadNum;
            long otherSize = fileSize % threadNum;

            if(i != threadNum - 1) {

            } else { //最后一部分

            }
        }
    }
}

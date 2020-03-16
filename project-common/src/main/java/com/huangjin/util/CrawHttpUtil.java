package com.huangjin.util;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.nutz.http.Header;
import org.nutz.http.Http;
import org.nutz.http.Response;

/**
 * @Author: 黄金
 * @Description:
 * @Date: Create in 11:17 2019-10-31
 */
public class CrawHttpUtil {

    private CloseableHttpClient client;
    private static final int SUCCESS_STATUS = 200;
    private static final int REDIRECT_STATUS = 302;
    private static final String CHARSET = "utf-8";

    private static CrawHttpUtil ourInstance = new CrawHttpUtil();

    public static CrawHttpUtil getInstance() {
        return ourInstance;
    }

    private CrawHttpUtil() {
        client = HttpClients.custom().build();
    }

    public CloseableHttpClient getClient() {
        return client;
    }

    public void release() {
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUrl(String url, String cookie) {
        return getByNut(url,cookie);
    }


    public String getByNut(String url, String cookie) {
        Header header = Header.create();
        if(StringUtils.isNotBlank(cookie)) {
            cookie = cookie.replaceAll("\u0000", "");
            header.set("Cookie",cookie);
        }

        header.set("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36");
        header.set("Cache-Control","no-cache");
        Response response = Http.get(url,header,120000);
        String resp=response.getContent();
        return resp;
    }


}

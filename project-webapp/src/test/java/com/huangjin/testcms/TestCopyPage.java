package com.huangjin.testcms;

import com.huangjin.util.HttpClientUtils;
import com.huangjin.util.Md5Util;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by huang on 2016-12-14.
 */
public class TestCopyPage {

    @Test
    public void test1() {
        String token = Md5Util.MD5("/frame/copyAllAppPage/" + 123);

        String url = "http://api.m.cms.lecloud.com/frame/copyAllAppPage/123/1/" + token;
        //String url = "http://localhost:90/frame/copyAllAppPage/123/1/" + token;

        Map<String, String> param = new HashMap<>();
        try {
            Thread.sleep(5000);
        } catch (Exception e) {

        }
        String result = HttpClientUtils.post(url, param, "UTF-8");
        System.out.println(result);
    }

    @Test
    public void test2() {
        String token = Md5Util.MD5("/levalley/getFrameInfo/" + 400097);

        //String url = "http://api.m.cms.lecloud.com/levalley/getFrameInfo/" + 400097 + "/" + token;
        String url = "http://localhost:90/levalley/getFrameInfo/" + 400097 + "/" + token;

        Map<String, String> param = new HashMap<>();

        String result = HttpClientUtils.post(url, param, "UTF-8");
        System.out.println(result);
    }

}

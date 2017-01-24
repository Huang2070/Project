package com.huangjin.testcms;

import com.huangjin.util.HttpClientUtils;
import com.huangjin.util.Md5Util;
import org.junit.Test;

/**
 * Created by huang on 2017-1-20.
 */
public class TestMcmsPage {

    //config
    @Test
    public void test() {
        String userId = "400667";
        String interfaceName = "/frame/getCmptInfo/%s/LECOpenConfig";
        String token = Md5Util.MD5(String.format(interfaceName, userId));

        String url = "http://api.m.cms.lecloud.com/frame/getCmptInfo/" + userId + "/" + token;
        String result = HttpClientUtils.get(url);

        System.out.println("result:" + result);
    }

    //content
    @Test
    public void test1() {
        String userId = "400717";
        String interfaceName = "/mcms/api/page/token/userid/%s";
        String token = Md5Util.MD5(String.format(interfaceName, userId));

        String url = "http://localhost:90/page/getDataByPage/" + userId + "/26190/1/10/" + token;
        String result = HttpClientUtils.get(url);

        System.out.println("result:" + result);
    }

    //themeContent
    @Test
    public void test2() {
        String userId = "400667";
        String interfaceName = "/frame/getCmptInfo/%s/LECOpenConfig";
        String token = Md5Util.MD5(String.format(interfaceName, userId));

        String url = "http://api.m.cms.lecloud.com/resource/getThemeSource/" + userId + "/1/" + token;
        //String url = "http://localhost:90/resource/getThemeSource/" + userId + "/1/" + token;
        String result = HttpClientUtils.get(url);

        System.out.println("result:" + result);
    }
}

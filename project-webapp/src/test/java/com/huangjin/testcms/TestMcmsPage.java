package com.huangjin.testcms;

import com.huangjin.util.HttpClientUtils;
import com.huangjin.util.Md5Util;
import org.junit.Test;

/**
 * Created by huang on 2017-1-20.
 */
public class TestMcmsPage {

    //框架
    @Test
    public void test() {
        String userId = "864650";
        String interfaceName = "/frame/getCmptInfo/%s/LECOpenConfig";
        String token = Md5Util.MD5(String.format(interfaceName, userId));

        String url = "http://api.m.cms.lecloud.com/frame/getCmptInfo/" + userId + "/" + token;
        //String url = "http://localhost:90/frame/getCmptInfo/" + userId + "/" + token;
        String result = HttpClientUtils.get(url);

        System.out.println("result:" + result);
    }

    //内容
    @Test
    public void test1() {
        String userId = "400004";
        String interfaceName = "/mcms/api/page/token/userid/%s";
        String token = Md5Util.MD5(String.format(interfaceName, userId));

        String url = "http://api.m.cms.lecloud.com/page/getDataByPage/" + userId + "/2133/1/20/" + token;
        //String url = "http://localhost:90/page/getDataByPage/" + userId + "/28877/1/10/" + token;
        String result = HttpClientUtils.get(url);

        System.out.println("result:" + result);
    }

    //资源
    @Test
    public void test2() {
        String userId = "400525";
        String interfaceName = "/frame/getCmptInfo/%s/LECOpenConfig";
        String token = Md5Util.MD5(String.format(interfaceName, userId));

        String url = "http://api.m.cms.lecloud.com/resource/getThemeSource/" + userId + "/1/" + token;
        //String url = "http://localhost:90/resource/getThemeSource/" + userId + "/1/" + token;
        String result = HttpClientUtils.get(url);

        System.out.println("result:" + result);
    }

    //频道导航
    @Test
    public void test3() {
        String userId = "864650";
        String interfaceName = "/mcms/api/page/token/userid/%s";
        String token = Md5Util.MD5(String.format(interfaceName, userId));

        String url = "http://api.m.cms.lecloud.com/page/getChannelIndexData/" + userId + "/3575/" + token;
        //String url = "http://localhost:90/page/getChannelIndexData/" + userId + "/2412/" + token;
        String result = HttpClientUtils.get(url);

        System.out.println("result:" + result);
    }

    //LeValley框架接口
    @Test
    public void test4() {
        String userId = "400001";
        String interfaceName = "/levalley/getFrameInfo/%s";
        String token = Md5Util.MD5(String.format(interfaceName, userId));

        String url = "http://api.m.cms.lecloud.com/levalley/getFrameInfo/" + userId + "/" + token;
        //String url = "http://localhost:90/levalley/getFrameInfo/" + userId + "/" + token;
        String result = HttpClientUtils.get(url);

        System.out.println("result:" + result);
    }
}

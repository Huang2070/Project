package com.huangjin.testcms;

import com.huangjin.util.HttpClientUtils;
import com.huangjin.util.Md5Util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by huangjin on 2016/8/4.
 */
public class TestMlive {
    public static void main(String[] args) {

        /**
         * 获取app列表
         */
        //正式环境
        String url = "http://api.open.lecloud.com/live/execute";
        String method = "lecloud.mobileLive.app.list";
        String timestamp = System.currentTimeMillis() + "";
        String ver = "1.0";
        String userid = "895335";
        String secretkey = "59ed48ce490d5d5259d6664da39d8bb2";

        String buf = "method" + method + "timestamp" + timestamp + "userid" + userid  + "ver" + ver + secretkey;
        String sign = Md5Util.MD5(buf);

        Map<String, String> param = new HashMap<>();
        param.put("method", method);
        param.put("timestamp", timestamp);
        param.put("ver", ver);
        param.put("userid", userid);
        param.put("sign", sign);



//        //T1
//        String url = "http://api.open.lecloud.com/live/execute";
//        String method = "lecloud.mobileLive.app.list";
//        String timestamp = System.currentTimeMillis() + "";
//        String ver = "1.0";
//        String userid = "1111111532";
//        String secretkey = "6bd7b3fcf5cda8d880ed8cc36f462714";


//        //T2
//        String url = "http://api.open.lecloud.com/live/execute";
//        String method = "lecloud.mobileLive.app.list";
//        String timestamp = System.currentTimeMillis() + "";
//        String ver = "1.0";
//        String userid = "400001";
//        String secretkey = "b89cebfb083f286c27d786992069613e";



        /**
         * 获取移动直播流列表
         */
        //T1
//        String url = "http://api.open.lecloud.com/live/execute";
//        String method = "lecloud.mobileLive.stream.list";
//        String timestamp = System.currentTimeMillis() + "";
//        String ver = "1.1";
//        String userid = "1111111532";
//        String pushDomain = "359.mpushtest.live.lecloud.com";
//
//        String secretkey = "6bd7b3fcf5cda8d880ed8cc36f462714";


//        //线上
//        String url = "http://api.open.lecloud.com/live/execute";
//        String method = "lecloud.mobileLive.stream.list";
//        String timestamp = System.currentTimeMillis() + "";
//        String ver = "1.1";
//        String userid = "864650";
//        String pushDomain = "12921.mpush.live.lecloud.com";
//        String size = "1000";
//        String secretkey = "7bc461b03a6096e14b067a4440c54e8c";
//
//        String buf = "method" + method + "pushDomain" + pushDomain + "timestamp" + timestamp + "userid" + userid  + "ver" + ver + secretkey;
//        String sign = Md5Util.MD5(buf);
//
//        Map<String, String> param = new HashMap<>();
//        param.put("method", method);
//        param.put("timestamp", timestamp);
//        param.put("ver", ver);
//        param.put("userid", userid);
//        param.put("sign", sign);
//        param.put("pushDomain", pushDomain);


        String result = HttpClientUtils.post(url, param, "UTF-8");

        System.out.println("result:" + result);


    }
}

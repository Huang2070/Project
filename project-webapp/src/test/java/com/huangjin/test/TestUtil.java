package com.huangjin.test;

import com.huangjin.util.HttpClientUtils;
import com.huangjin.util.HttpUtil;
import com.huangjin.util.Md5Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huangjin on 2016/8/4.
 */
public class TestUtil {
    public static void main(String[] args) {

        //正式环境
//        String url = "http://api.open.lecloud.com/live/execute";
//        String method = "lecloud.mobileLive.app.list";
//        String timestamp = System.currentTimeMillis() + "";
//        String ver = "1.0";
//        String userid = "847695";
//
//        String secretkey = "4e31820c275a532b6707fc73efe40df3";

        //T1
//        String url = "http://api.open.lecloud.com/live/execute";
//        String method = "lecloud.mobileLive.app.list";
//        String timestamp = System.currentTimeMillis() + "";
//        String ver = "1.0";
//        String userid = "1111111532";
//
//        String secretkey = "6bd7b3fcf5cda8d880ed8cc36f462714";

        //T1
//        String url = "http://api.open.lecloud.com/live/execute";
//        String method = "lecloud.mobileLive.stream.list";
//        String timestamp = System.currentTimeMillis() + "";
//        String ver = "1.1";
//        String userid = "1111111532";
//        String pushDomain = "359.mpushtest.live.lecloud.com";
//
//        String secretkey = "6bd7b3fcf5cda8d880ed8cc36f462714";

        //T2
        String url = "http://api.open.lecloud.com/live/execute";
        String method = "lecloud.mobileLive.app.list";
        String timestamp = System.currentTimeMillis() + "";
        String ver = "1.0";
        String userid = "400001";

        String secretkey = "b89cebfb083f286c27d786992069613e";


        String buf = "method" + method + "timestamp" + timestamp + "userid" + userid  + "ver" + ver + secretkey;

        //String buf = "method" + method + "pushDomain" + pushDomain + "timestamp" + timestamp + "userid" + userid  + "ver" + ver + secretkey;
        String sign = Md5Util.MD5(buf);



        Map<String, String> param = new HashMap<>();
        param.put("method", method);
        param.put("timestamp", timestamp);
        param.put("ver", "1.0");
        param.put("userid", userid);
        param.put("sign", sign);

        //param.put("pushDomain", pushDomain);


        String result = HttpClientUtils.post(url, param, "UTF-8");

        System.out.println("result:" + result);


    }
}

package com.huangjin.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.huangjin.util.HttpClientUtils;
import com.huangjin.util.Md5Util;
import org.apache.commons.collections.MapUtils;


/**
 * Created by huang on 2016-9-5.
 */
public class Testlalala {
    public static void main(String[] args) {
//        String url = "http://i.api.cms.lecloud.com/live/liveInfo/modify";
//
//        String ts = System.currentTimeMillis() + "";
//
//        Map<String, String> map = new HashMap<>();
//
//        map.put("userid", "1111111532");
//        map.put("uid", "49561");
//        map.put("ts", ts);
//        map.put("liveName", "hehe");
//
//        String buff = "/live/liveInfo/modify" + "uc" + "6789" + ts;
//        String token = Md5Util.MD5(buff);
//        map.put("token", token);
//
//        String result = HttpClientUtils.post(url, map, "UTF-8");
//
//        System.out.println("result:" + result);



        String url = "http://i.api.cms.lecloud.com/live/liveInfo/updateState";

        String ts = System.currentTimeMillis() + "";

        Map<String, String> map = new HashMap<>();

        map.put("userid", "1111111532");
        map.put("uid", "49561");
        map.put("ts", ts);
        map.put("state", 1 + "");

        String buff = "/live/liveInfo/updateState" + "uc" + "6789" + ts;
        String token = Md5Util.MD5(buff);
        map.put("token", token);

        String result = HttpClientUtils.post(url, map, "UTF-8");

        System.out.println("result:" + result);

    }
}

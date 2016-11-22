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
public class TestCms {
    public static void main(String[] args) {
  //      String url = "http://i.api.cms.lecloud.com/live/liveInfo/modify";
//        String url = "http://localhost:83/live/liveInfo/modify";
//
//        String ts = System.currentTimeMillis() + "";
//
//        Map<String, String> map = new HashMap<>();
//
//        map.put("userid", "400001");
//        map.put("uid", "56838");
//        map.put("ts", ts);
//        map.put("liveName", "陶志娇18500852581aaa");
//
//        String buff = "/live/liveInfo/modify" + "proxy" + "1234" + ts;
//        String token = Md5Util.MD5(buff);
//        map.put("token", token);
//
//        String result = HttpClientUtils.post(url, map, "UTF-8");
//
//        System.out.println("result:" + result);



        String url = "http://localhost:811/channel/programa/get";

        Map<String, String> map = new HashMap<>();
        map.put("userId", "1111111567");
        map.put("id", "974");
        map.put("name", "huangjin");
        map.put("playControlPlatform", "huangjin");

        String result = HttpClientUtils.post(url, map, "UTF-8");
        System.out.println("result:" + result);


//        String url = "http://localhost:811/channel/programa/content/create";
//        Map<String, String> map = new HashMap<>();
//        map.put("userId", "1111111532");
//        map.put("id", "1837");
//        map.put("type", "3");
//        map.put("title", "经典车形状");
//        map.put("priority", "3");
//        map.put("bid", "876");
//
//        String result = HttpClientUtils.post(url, map, "UTF-8");
//        System.out.println("result:" + result);


    }
}

package com.huangjin.testcms;


import com.huangjin.util.HttpClientUtils;
import com.huangjin.util.Md5Util;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by huang on 2016-9-5.
 */
public class TestCms {
    public static void main(String[] args) {
        //api请求 POST方法
        //String url = "http://i.api.cms.lecloud.com/live/liveInfo/modify";
//        String url = "http://localhost:83/live/getMliveThemeList/" + 22222 + ;
//
//        String ts = System.currentTimeMillis() + "";
//
//        Map<String, String> map = new HashMap<>();
//        map.put("userid", "22222");
//        map.put("ts", ts);
//
//        String interfaceName = "/live/getMliveThemeList/{userId}";
//        String buff = interfaceName + "hsea" + "2345" + ts;
//        String token = Md5Util.MD5(buff);
//        map.put("token", token);
//
//        String result = HttpClientUtils.get(url);
//
//        System.out.println("result:" + result);


        //api请求 GET方法
        String interfaceName = "/live/getMliveThemeList/{userId}";
        String ts = System.currentTimeMillis() + "";
        String buff = interfaceName + "hsea" + "2345" + ts;
        String token = Md5Util.MD5(buff);
        //String url = "http://api.cms.lecloud.com/live/getMliveThemeList/" + 400001 + "?token=" + token + "&ts=" + ts;
        String url = "http://localhost:83/live/getMliveThemeList/" + 400001 + "?token=" + token + "&ts=" + ts;
        String result = HttpClientUtils.get(url);

        System.out.println("result:" + result);


        //String url = "http://localhost:83/live/getMliveThemeList/" + 22222 + "?token=" + token + "&ts=" + ts;


        //api请求 POST方法
//        String url = "http://localhost:83/live/modifyThemeState/" + 22222 + "/" + 2;
//
//        String interfaceName = "/live/modifyThemeState/{userId}/{themeType}";
//        String ts = System.currentTimeMillis() + "";
//        String buff = interfaceName + "hsea" + "2345" + ts;
//        String token = Md5Util.MD5(buff);
//
//        Map<String, String> map = new HashMap<>();
//        map.put("token", token);
//        map.put("ts", ts);
//
//        String result = HttpClientUtils.post(url, map, "UTF-8");
//        System.out.println("result:" + result);


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


        //图文发布定时任务
//        String url = "http://api.itms.cms.lecloud.com/dubbo/directPushByScheduler/550";
//
//        Map<String, String> map = new HashMap<>();
//        map.put("userId", "400001");
//
//        String token = Md5Util.MD5("/dubbo/directPushByScheduler/550" + "400001");
//        map.put("token", token);
//
//        String result = HttpClientUtils.post(url, map, "UTF-8");
//        System.out.println("result:" + result);
//
//        JSONObject jsonObject = JSON.parseObject(result);
//
//        if(!jsonObject.get("code").toString().equals("200")) {
//            throw new RuntimeException("执行图文定时发布失败！");
//        } else {
//            System.out.println("发布成功");
//        }

        //获取租户整颗栏目树列表
//        String token = Md5Util.MD5("/channel/getChannelList/" + 806629);
//        String url = "http://i.api.cms.lecloud.com:27080/channel/getChannelList/806629?token=" + token;
//
//        String result = HttpClientUtils.get(url);
//        System.out.println("result:" + result);

    }

}

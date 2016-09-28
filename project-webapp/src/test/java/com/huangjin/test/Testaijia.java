package com.huangjin.test;

import com.huangjin.util.HttpClientUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by huang on 2016-9-26.
 */
public class Testaijia {
    public static void main(String[] args) {
        String url = "http://api.my.lecloud.com/api/anchor/lastAnchorLiveInfo";
        String userId = "50160";
        String tenantId = "400001";

        Map<String, String> param = new HashMap<>();
        param.put("userId", userId);
        param.put("tenantId", tenantId);


        String result = HttpClientUtils.post(url, param, "UTF-8");
        System.out.println(result);
    }
}

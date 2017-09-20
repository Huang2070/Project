package com.huangjin.testkaola;


import com.alibaba.fastjson.JSON;
import com.huangjin.util.HttpClientUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huang on 2016-9-5.
 */
public class TestCms {
    public static void main(String[] args) {

        String url = "http://localhost:8080/property/savePropertyValueWithGroup";

        Map<String, Object> param = new HashMap<>();
        param.put("propertyNameId", 100006);
        param.put("propertyValue", "黄金测试");
        param.put("isFrequent", 1);



        String result = HttpClientUtils.post(url, param, 10000, "utf-8");

        System.out.println("result:" + result);

    }

    @Test
    public void test() {
        String url = "http://localhost:8080/view/testRequest";

        Map<String, Object> param = new HashMap<>();
        param.put("acTitle", "黄金");
        param.put("acDesc", "呵呵");
        param.put("acStartTime", "黄金");
        param.put("acEndTime", "黄金");

        HashMap<String, String> map1 = new HashMap<>();
        map1.put("id", "1");
        map1.put("name", "huangjin");
        map1.put("img", "http://106.38.226.66/operative/wap/images/img-01.jpg");

        HashMap<String, String> map2 = new HashMap<>();
        map2.put("id", "2");
        map2.put("name", "huangjin");
        map2.put("img", "http://106.38.226.66/operative/wap/images/img-01.jpg");

        List<HashMap<String, String>> list = new ArrayList<>();
        list.add(map1);
        list.add(map2);

        String str = JSON.toJSONString(list);
        param.put("prize", str);

        String result = HttpClientUtils.post(url, param, 10000, "utf-8");

        System.out.println("result:" + result);
    }

}

package com.huangjin.alibaba.sem;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.huangjin.util.PidCodeUtile;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Title: ApiSignHepler
 *
 * @author shantao
 * @date 2020/3/12
 */
public class SemLinkTest {

    public static void main(String[] args) {

        String pid="XMTMwMA==";
        String type="json";
        String secretKey = "ED78CFC3C6C4FB";
        String category = "动漫";
        String hour = "2";
        getIncrementAddress(pid,type,secretKey,category,hour);
        Long taskIdLong = PidCodeUtile.decodePid(pid, "X");
        System.out.println("taskId: " + taskIdLong);
        getAllAddress(pid,type,secretKey);
        getIncrementAddress(pid,type,secretKey,category,hour);
        getDelete(pid,secretKey,6);

    }
    public static void getDelete(String pid,String secretKey,Integer timeRange){
        Long tm = System.currentTimeMillis();
        Map<String,String> paramMap = new HashMap<>(8);
        paramMap.put("pid", pid);
        paramMap.put("timeRange", timeRange.toString());
        paramMap.put("tm",tm.toString());
        String sign = generateSign(paramMap,secretKey);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://pre-api-yksem.youku.com/api/out/list/getDeleteVideo.json?")
            .append("pid="+pid+"&")
            .append("timeRange="+timeRange+"&")
            .append("sign="+sign+"&")
            .append("tm="+tm);
        System.out.println("delele: " + stringBuilder.toString());

    }


    public static void getRankList(String pid,String num,String channelId,String orderPro,String startIndex,String endIndex,String secretKey){
        Long aa = System.currentTimeMillis();

        Map<String,String> paramMap = new HashMap<>(8);
        paramMap.put("pid", pid);
        paramMap.put("tm", aa.toString());
        paramMap.put("num", num);
        paramMap.put("channelId", channelId);
        paramMap.put("orderPro", orderPro);
        paramMap.put("startIndex", startIndex);
        paramMap.put("endIndex", endIndex);
        String sign = generateSign(paramMap,secretKey);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://api-yksem.youku.com/api/out/list/getRankList?")
            .append("pid=").append(pid)
            .append("&").append("tm=").append(aa.toString())
            .append("&").append("num=").append(num)
            .append("&").append("channelId=").append(channelId)
            .append("&").append("orderPro=").append(orderPro)
            .append("&").append("startIndex=").append(startIndex)
            .append("&").append("endIndex=").append(endIndex)
            .append("&").append("sign=").append(sign);
        System.out.println("ranked: " + stringBuilder.toString());

    }

    public static void getAllAddress(String pid,String type,String secretKey) {
        Long aa = System.currentTimeMillis();
        Map<String,String> paramMap = ImmutableMap.of(
            "pid", pid,
            "tm", aa.toString(),
            "type", type
        );
        String sign = generateSign(paramMap,secretKey);
        String builder = "";
        if (type.equals("json")) {
            builder = "https://pre-api-yksem.youku.com/api/out/list/getAllAddress.json?"
                + "pid="+pid
                + "&tm=" + aa.toString()
                + "&type=" + type
                + "&sign=" + sign;
        } else {
            builder = "https://pre-api-yksem.youku.com/api/out/list/getAllAddress?"
                + "pid="+pid
                + "&tm=" + aa.toString()
                + "&type=" + type
                + "&sign=" + sign;
        }

        System.out.println("all: " + builder);
    }

    public static void getIncrementAddress(String pid,String type,String secretKey,String category,String hour){

        Long aa = System.currentTimeMillis();
        Map<String,String> paramMap = ImmutableMap.of(
            "pid", pid,
            "tm", aa.toString(),
            "type", type
            ,"category", category,
            "hour", hour
        );
        String sign = generateSign(paramMap,secretKey);
        String builder = "";
        if (type.equals("json")) {
            builder = "https://pre-api-yksem.youku.com/api/out/list/getIncrementAddress.json?"
                + "pid="+pid
                + "&tm=" + aa.toString()
                + "&category=" + category
                + "&type=" + type
                + "&sign=" + sign
                + "&hour=" +hour;
        } else {
            builder = "https://pre-api-yksem.youku.com/api/out/list/getIncrementAddress?"
                + "pid="+pid
                + "&tm=" + aa.toString()
                + "&category=" + category
                + "&type=" + type
                + "&sign=" + sign
                + "&hour=" +hour;
        }
        System.out.println("increment: " + builder);
    }
    public static String generateSign(Map<String, String> params, String secKey ){

        StringBuilder builder = new StringBuilder();
        builder.append(secKey);
        String[] keys = (String[])params.keySet().toArray(new String[0]);
        keys = sortKeys(keys);
        StringBuilder kvBuilder = new StringBuilder();

        for(int i = 0; i < keys.length; ++i) {
            String key = keys[i];
            if (!"sign".equals(key)) {
                String value = (String)params.get(key);
                if(value!=null && value.length() >0){
                    kvBuilder.append(key).append("=").append(value).append("&");
                }
            }
        }

        if (kvBuilder.length() > 0) {
            kvBuilder.deleteCharAt(kvBuilder.length() - 1);
            String kv = null;

            try {
                kv = URLEncoder.encode(kvBuilder.toString(), "utf-8");
            } catch (UnsupportedEncodingException var8) {
                var8.printStackTrace();
            }

            kv = kv.replaceAll("\\+", "%20");
            builder.append("&").append(kv);
        }
        return DigestUtils.md5Hex(builder.toString());
    }

    private static String[] sortKeys(String[] keys) {
        for(int i = 0; i < keys.length - 1; ++i) {
            for(int j = i + 1; j < keys.length; ++j) {
                int intTemp = keys[i].compareTo(keys[j]);
                String strTemp = "";
                if (intTemp > 0) {
                    strTemp = keys[j];
                    keys[j] = keys[i];
                    keys[i] = strTemp;
                }
            }
        }

        return keys;
    }
}


package com.huangjin.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

public class ApiSignHelper {

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

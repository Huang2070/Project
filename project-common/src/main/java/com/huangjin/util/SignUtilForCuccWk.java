package com.huangjin.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.alibaba.fastjson.JSONObject;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

public class SignUtilForCuccWk {

    private static final String KEY_MAC_SHA1 = "HmacSHA1";

    public static String formatUrlJsonObject(JSONObject jsonObject){
        Map paraMap = JSONObject.parseObject(jsonObject.toJSONString(),Map.class);
        return formatUrlObjectMap(paraMap);
    }

    public static String formatUrlObjectMap(Map<String, Object> paraMap) {
        String buff;
        try {
            List<Map.Entry<String, Object>> infoIds = new ArrayList<>(paraMap.entrySet());
            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            infoIds.sort(Comparator.comparing(o -> (o.getKey())));
            // 构造URL 键值对的格式
            StringBuilder buf = new StringBuilder();
            for (Map.Entry<String, Object> item : infoIds) {
                Object val;
                if (!StringUtils.isBlank(item.getKey())) {
                    String key = item.getKey();
                    try {
                        val = item.getValue();

                    } catch (Exception e) {
                        val = Integer.valueOf(item.getValue().toString());

                    }
                    if (!Objects.isNull(val)) {
                        buf.append(key).append("=").append(val);
                        buf.append("&");
                    }
                }

            }
            buff = buf.toString();
            if (!buff.isEmpty()) {
                buff = buff.substring(0, buff.length() - 1);
            }
        } catch (Exception e) {
            return null;
        }
        return buff;
    }

    public static String hmacSha1(String src, String key) {
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes("utf-8"), KEY_MAC_SHA1);
            Mac mac = Mac.getInstance(KEY_MAC_SHA1);
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(src.getBytes("utf-8"));
            return Hex.encodeHexString(rawHmac);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

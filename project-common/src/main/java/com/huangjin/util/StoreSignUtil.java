package com.huangjin.util;

import java.util.Map;
import java.util.SortedMap;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author 清磊
 * @Date 2021/5/24 15:40
 * @Description md5相关工具类
 **/
public class StoreSignUtil {

    /**
     * sign参数名称
     */
    private static final String SIGN="sign";


    /**
     * 参数名ASCII码从小到大排序
     * 返回结构为url参数格式 a=xxx&b=xxx&c=xxx
     *
     * @param parameters
     * @return
     */
    public static StringBuilder orderByAscii(SortedMap<Object, Object> parameters) {
        StringBuilder stringBuilder = new StringBuilder();
        // 所有参与传参的参数按照accsii排序（升序）
        for (Map.Entry<Object, Object> entry : parameters.entrySet()) {
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            // key为sign不参与加密
            // sign区分大小写，这里k就不做.toLowerCase()
            if (!StringUtils.isAnyBlank(value, key) && !SIGN.equals(key)) {
                stringBuilder.append(key).append("=").append(value).append("&");
            }
        }
        return stringBuilder;
    }
}

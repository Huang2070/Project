package com.huangjin.util;

import com.jayway.jsonpath.ReadContext;
import org.nutz.castor.Castors;

/**
 * @Author: 黄金
 * @Description:
 * @Date: Create in 11:24 2019-10-31
 */
public class JsonConvertUtil {

    public static <T> T readJsonPath(ReadContext readContext, String pre, String key, Class<T> clazz) {
        try {
            Object obj = readContext.read(pre + key);
            return Castors.me().castTo(obj, clazz);
        } catch (Exception e) {

        }
        if (clazz.equals(String.class)) {
            return (T) "";
        }
        return null;
    }

    public static Integer getJsonPathInt(Object obj) {
        if (obj instanceof Integer) {
            return (int) obj;
        }
        if (obj instanceof String) {
            return Integer.valueOf((String) obj);
        }
        return null;
    }

    public static Long getJsonPathLong(Object obj) {
        if (obj instanceof Integer) {
            int ret = (int) obj;
            return (long) ret;
        }
        if (obj instanceof String) {
            return Long.valueOf((String) obj);
        }
        return null;
    }

    public static String getJsonPathString(Object obj) {
        try {
            return String.valueOf(obj);
        } catch (Exception e) {

        }
        return null;
    }
}

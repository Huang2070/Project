package com.huangjin.alibaba.aliyun;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class BeanUtil {

    private BeanUtil() {
        //私有构造
    }

    public static <T> Map<String, Object> beanToMap(T bean) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>(10);
        Field[] fields = bean.getClass().getDeclaredFields();
        Field.setAccessible(fields, true);
        for (Field field : fields) {
            if (null != field.get(bean)) {
                map.put(field.getName(), field.get(bean));
            }
        }
        return map;
    }

}

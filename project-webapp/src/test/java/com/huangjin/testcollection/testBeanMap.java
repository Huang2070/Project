package com.huangjin.testcollection;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.huangjin.domain.User;
import net.sf.cglib.beans.BeanMap;

import java.util.List;
import java.util.Map;

/**
 * Created by huang on 2018-3-29.
 */
public class testBeanMap {
    public static void main(String[] args) {
        User user = new User();
        user.setUsername("huangjin");
        user.setPassword("123");
        user.setId(1);

        List<String> phoneNum = Lists.newArrayList();
        phoneNum.add("1");
        phoneNum.add("2");
        phoneNum.add("3");
        user.setPhoneNum(phoneNum);

        Map<String, Object> checkFieldMap = beanToMap(user);

        for(Map.Entry<String, Object> entry : checkFieldMap.entrySet()) {

//            if(entry.getValue() instanceof List) {
//                List<String> list = (List<String>)entry.getValue();
//
//                for(String str : list) {
//                    System.out.println(str);
//                }
//            } else {
//                System.out.println(entry.getValue().toString());
//            }

            System.out.println(entry.getValue().toString());
        }

    }

    /**
     * 把bean转成map
     * @param bean
     * @param <T>
     * @return
     */
    private static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = Maps.newHashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key+"", beanMap.get(key));
            }
        }
        return map;
    }
}

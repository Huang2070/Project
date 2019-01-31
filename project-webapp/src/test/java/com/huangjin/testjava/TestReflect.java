package com.huangjin.testjava;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.huangjin.domain.City;
import com.huangjin.domain.User;
import org.junit.Test;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by huang on 2016-8-25.
 */
public class TestReflect {

    private String para1 = "123";
    public String para2 = "321";
    protected String para3;
    String para4;

    public static void main(String[] args) {
        Class<?> reflect = null;
        try {
            reflect = Class.forName("com.huangjin.testjava.TestReflect");
            //取得类的所有属性

            TestReflect demo = new TestReflect();
            Field[] fields = reflect.getDeclaredFields();
            for(Field field : fields) {
                System.out.println(field.get(demo));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void test() {
        Class<?> reflect = null;
        try {
            reflect = Class.forName(TestReflect.class.getCanonicalName());

            System.out.println(TestReflect.class.getModifiers());
            System.out.println(TestReflect.class.getName());
            System.out.println(TestReflect.class.getAnnotatedInterfaces());
            System.out.println(TestReflect.class.getCanonicalName());
            System.out.println(TestReflect.class.getSimpleName());
            System.out.println(TestReflect.class.getAnnotations());
            System.out.println(TestReflect.class.getClasses());
            System.out.println(TestReflect.class.getClassLoader());

            //取得类的所有属性
            Field[] fields = reflect.getDeclaredFields();
            for(Field field : fields) {
                System.out.println(field.get(this));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test1() throws IllegalAccessException, ClassNotFoundException {
        User user1 = new User();
        user1.setId(1);
        user1.setUsername("123");
        user1.setPassword("123");
        user1.setDate(new Date());

        User user2 = new User();
        user2.setId(2);
        user2.setUsername("123");
        user2.setPassword("123");
        user2.setDate(new Date());

        User user3 = new User();
        user3.setId(3);
        user3.setUsername("123");
        user3.setPassword("123");
        user3.setDate(new Date());

        List<User> users = Lists.newArrayList();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        List<Map<String, Object>> list = this.getExportFieldAndData(User.class, users);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(list.get(0).keySet());

        for(Map<String, Object> map : list) {
            for(Map.Entry<String, Object> entry : map.entrySet()) {
                if(entry.getValue() instanceof Date) {
                    System.out.println(sdf.format(entry.getValue()));
                } else {
                    System.out.println(entry.getValue());
                }

            }
        }
    }


    /**
     * 获取ExcelExportFactory Excel导出类需要的数据格式
     * @param cls
     * @param expertVOList
     * @param <T>
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     */
    private <T> List<Map<String, Object>> getExportFieldAndData(Class cls, List<T> expertVOList) throws ClassNotFoundException, IllegalAccessException {
        List<Map<String, Object>> resultList = Lists.newArrayList();

        Class<T> reflect = (Class<T>) Class.forName(cls.getName());

        Field[] fields = reflect.getDeclaredFields();

        for(T exportVO : expertVOList) {
            Map<String, Object> fieldDataMap = Maps.newLinkedHashMap();

            for(Field field : fields) {
                field.setAccessible(true);//设置私有属性可读
                fieldDataMap.put(field.getName(), field.get(exportVO));
            }

            resultList.add(fieldDataMap);
        }
        return resultList;
    }
}

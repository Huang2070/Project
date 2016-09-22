package com.huangjin.util;

import com.huangjin.domain.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huang on 2016-8-31.
 */
public class ListDiffUtil {

    public static void main(String[] args) {
        List<User> list1 = new ArrayList<>();
        List<User> list2 = new ArrayList<>();
        int num = 5;

        for (int i = 0; i < num; i++) {
            User user = new User();
            user.setId(i);
            user.setUsername("huangjin");
            list1.add(user);

            User user1 = new User();
            user1.setId(i*2);
            user1.setUsername("huangjin");
            list2.add(user1);
        }

//        User user = new User();
//        user.setId(1);
//        user.setUsername("huangjin");
//
//        User user1 = new User();
//        user1.setId(1);
//        user1.setUsername("huangjin");
//
//        System.out.println(user.hashCode());
//        System.out.println(user1.hashCode());
//        System.out.println(user1.equals(user));
//        List<User> diff = new ArrayList<>();
//        for (User str : list1) {
//            if (!list2.contains(str)) {
//                diff.add(str);
//            }
//        }

        list1.removeAll(list2);

        System.out.println(list1);

    }

    /**
     * 获取两个List的不同元素
     *
     * @param list1
     * @param list2
     * @return
     */
    private static List<String> getDiffrent5(List<String> list1, List<String> list2) {
        long st = System.nanoTime();
        List<String> diff = new ArrayList<String>();
        List<String> maxList = list1;
        List<String> minList = list2;
        if (list2.size() > list1.size()) {
            maxList = list2;
            minList = list1;
        }

        // 将List中的数据存到Map中
        Map<String, Integer> maxMap = new HashMap<String, Integer>(maxList.size());
        for (String string : maxList) {
            maxMap.put(string, 1);
        }

        // max:1,2,3,4,5
        // min:2,4,6,8,10

        // 循环minList中的值，标记 maxMap中 相同的 数据2
        for (String string : minList) {
            // 相同的
            if (maxMap.get(string) != null) {
                maxMap.put(string, 2);
                continue;
            }
            // 不相等的
            diff.add(string);
        }

        // 循环maxMap
        for (Map.Entry<String, Integer> entry : maxMap.entrySet()) {
            if (entry.getValue() == 1) {
                diff.add(entry.getKey());
            }
        }
        printf(diff);

        System.out.println("getDiffrent5 total times " + (System.nanoTime() - st));
        return diff;
    }

    /**
     * 获取两个List的不同元素
     *
     * @param list1
     * @param list2
     * @return
     */
    private static List<String> getDiffrent4(List<String> list1, List<String> list2) {
        long st = System.nanoTime();
        Map<String, Integer> map = new HashMap<String, Integer>(list1.size() + list2.size());
        List<String> diff = new ArrayList<String>();
        List<String> maxList = list1;
        List<String> minList = list2;
        if (list2.size() > list1.size()) {
            maxList = list2;
            minList = list1;
        }
        for (String string : maxList) {
            map.put(string, 1);
        }
        for (String string : minList) {
            Integer cc = map.get(string);
            if (cc != null) {
                map.put(string, ++cc);
                continue;
            }
            map.put(string, 1);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                diff.add(entry.getKey());
            }
        }
        System.out.println("getDiffrent4 total times " + (System.nanoTime() - st));
        printf(diff);
        return diff;
    }

    /**
     * 获取两个List的不同元素
     *
     * @param list1
     * @param list2
     * @return
     */
    private static List<String> getDiffrent3(List<String> list1, List<String> list2) {
        long st = System.nanoTime();
        Map<String, Integer> map = new HashMap<String, Integer>(list1.size() + list2.size());
        List<String> diff = new ArrayList<String>();
        for (String string : list1) {
            map.put(string, 1);
        }
        for (String string : list2) {
            Integer cc = map.get(string);
            if (cc != null) {
                map.put(string, ++cc);
                continue;
            }
            map.put(string, 1);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                diff.add(entry.getKey());
            }
        }
        System.out.println("getDiffrent3 total times " + (System.nanoTime() - st));
        printf(diff);
        return diff;
    }

    /**
     * 获取连个List的不同元素
     *
     * @param list1
     * @param list2
     * @return
     */
    private static List<String> getDiffrent2(List<String> list1, List<String> list2) {
        long st = System.nanoTime();
        list1.removeAll(list2);
        System.out.println("getDiffrent2 total times " + (System.nanoTime() - st));
        printf(list1);
        return list1;
    }

    /**
     * 获取两个List的不同元素
     *
     * @param list1
     * @param list2
     * @return
     */
    private static List<String> getDiffrent(List<String> list1, List<String> list2) {
        long st = System.nanoTime();
        List<String> diff = new ArrayList<String>();
        for (String str : list1) {
            if (!list2.contains(str)) {
                diff.add(str);
            }
        }
        System.out.println("getDiffrent total times " + (System.nanoTime() - st));
        printf(diff);
        return diff;
    }

    public static void printf(List<String> list) {
        System.out.println("----------------------------");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }




}

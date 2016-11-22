package com.huangjin.testjava;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huang on 2016-8-25.
 */
public class TestCollection {

    public static void main(String[] args) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        System.out.println("定时任务开始:" + fmt.format(System.currentTimeMillis()));

    }
}

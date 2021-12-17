package com.huangjin.alibaba.sem;

import com.huangjin.util.PidCodeUtile;

/**
 * @Author: 黄金
 * @Description:
 * @Date: Create in 15:17 2021-01-27
 */
public class PidTaskTest {


    public static void main(String[] args) {

        Long taskIdLong = PidCodeUtile.decodePid("XMTExNg==", "X");

        System.out.println(taskIdLong);

    }
}

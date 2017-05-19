package com.huangjin.interview;

import org.junit.Test;

/**
 * Created by huang on 2017-5-19.
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */
public class QuestionA {
    @Test
    public void test() {
        int [][] array = {{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
        int [][] array1 = {{}};

        System.out.println(find(7, array1));
    }


    public boolean find(int target, int [][] array) {

        if(array.length == 1 && array[0].length == 0) {
            return false;
        }

        int height = array.length - 1;
        int length = 0;

        int begin = array[height][length];

        while(true) {
            if(target == begin) {
                System.out.println("位置：array[" + height + "][" + length + "]");
                return true;
            } else if(target < begin) {
                //上移
                height = height - 1;
                if(height < 0) {
                    break;
                }
                begin = array[height][length];
            } else {
                //右移
                length = length + 1;
                if(length >= array[0].length) {
                    break;
                }
                begin = array[height][length];
            }
        }
        return false;
    }
}

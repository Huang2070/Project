package com.huangjin.Alearn;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;
import com.huangjin.util.DateUtil;

/**
 * @Author: 黄金
 * @Description:
 * @Date: Create in 22:53 2022/7/1
 */
public class Lesson1 {

    public static void main(String[] args) {

        /***************** 初始化 start *****************/
        //月份
        int month = 9;

        //第一个值班
        String firstNight = "盛";

        //人员名单
        List<String> columns = Lists.newArrayList("日期","星期","盛","刘","谢","贺","马","王","方");


        /***************** 初始化 end *****************/


        //每月的天数
        int monthCount = getMonthDayCount(month);
        //第一个值班index
        int firstNightIndex = columns.indexOf(firstNight);

        String[][] item = new String[columns.size()][monthCount + 1];

        //框架
        for(int index = 0; index < columns.size(); index++) {

            String column = columns.get(index);

            for(int dayInt = 0; dayInt <= monthCount; dayInt++) {
                if(dayInt == 0) {
                    item[index][dayInt] = column;
                    continue;
                }

                //日期
                String day = dayInt + "";
                if(dayInt < 10) {
                    day = "0" + day;
                }

                //星期
                Calendar cd = Calendar.getInstance();
                cd.set(2022, month - 1, dayInt);
                int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1;
                if(dayOfWeek == 0) {
                    dayOfWeek = 7;
                }

                if(column.equals("日期")) {
                    item[index][dayInt] = day;
                } else if(column.equals("星期")) {
                    item[index][dayInt] = dayOfWeek + "";
                }
            }
        }


        //值班
        int nightEmployee = firstNightIndex;
        for(int dayInt = 1; dayInt <= monthCount; dayInt++) {

            //星期
            Calendar cd = Calendar.getInstance();
            cd.set(2022, month - 1, dayInt);
            int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1;
            if(dayOfWeek == 0) {
                dayOfWeek = 7;
            }

            //值班
            if(dayOfWeek == 6 || dayOfWeek == 7) {

                item[nightEmployee][dayInt] = "白";
                nightEmployee = nightEmployee + 1 >= columns.size() ? 2 : nightEmployee + 1;

                item[nightEmployee][dayInt] = "夜";
                nightEmployee = nightEmployee + 1 >= columns.size() ? 2 : nightEmployee + 1;

            } else {
                item[nightEmployee][dayInt] = "值";
                nightEmployee = nightEmployee + 1 >= columns.size() ? 2 : nightEmployee + 1;
            }
        }


        //查房
        for(int index = 2; index < columns.size(); index++) {
            for(int dayInt = 1; dayInt <= monthCount; dayInt++) {

                //星期
                Calendar cd = Calendar.getInstance();
                cd.set(2022, month - 1, dayInt);
                int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1;
                if(dayOfWeek == 0) {
                    dayOfWeek = 7;
                }

                if(item[index][dayInt] != null) {
                    continue;
                }

                //查房
                if(dayOfWeek == 6 || dayOfWeek == 7) {
                    if(item[index][dayInt - 1] == "夜" || item[index][dayInt - 1] == "值") {
                        item[index][dayInt] = "查";
                    }
                }
            }
        }


        //休息
        for(int index = 2; index < columns.size(); index++) {
            for(int dayInt = 1; dayInt <= monthCount; dayInt++) {

                //星期
                Calendar cd = Calendar.getInstance();
                cd.set(2022, month - 1, dayInt);
                int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1;
                if(dayOfWeek == 0) {
                    dayOfWeek = 7;
                }

                if(item[index][dayInt] != null) {
                    continue;
                }

                //休息
                if(dayOfWeek == 6 || dayOfWeek == 7) {
                    item[index][dayInt] = "休";
                }
            }
        }



        //住
        for(int index = 2; index < columns.size(); index++) {

            for(int dayInt = 1; dayInt <= monthCount; dayInt++) {

                if(item[index][dayInt] != null) {
                    continue;
                }

                //常规
                item[index][dayInt] = "住";

            }
        }


        //打印
        for(String[] rows : item) {
            for(String row : rows) {
                System.out.print(row + "\t");
            }
            System.out.println();
        }
    }




    /**
     * 获取一个月有多少天
     * @param month
     * @return
     */
    private static int getMonthDayCount(int month) {

        switch(month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                String yearStr = DateUtil.getYear(new Date());
                int year = Integer.parseInt(yearStr);

                if(year%4==0 && year%100!=0) {
                    return 29;
                } else if(year%100==0 && year%400==0) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return 0;
        }
    }
}
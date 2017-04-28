package com.huangjin.testjava;

import com.huangjin.enumeration.ShareEnum;

import java.util.EnumSet;

/**
 * Created by huang on 2016-9-28.
 */
public class TestEnum {
    public static void main(String[] args) {
//        EnumSet<ShareEnum> weekSet = EnumSet.allOf(ShareEnum.class);
//        for (ShareEnum item : weekSet) {
//            System.out.println(item.getShareName());
//        }


        for(ShareEnum e : ShareEnum.values()) {
            System.out.println(e.name());

            System.out.println(e.getShareName());
        }
    }
}

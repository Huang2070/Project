package com.huangjin.helper;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

/**
 * @Author: 黄金
 * @Description:
 * @Date: Create in 16:16 2023/4/13
 */
@Component("test")
public class ApplicationNameTest implements BeanNameAware {

    private static String beanName;


    @Override
    public void setBeanName(String beanName) {
        beanName = beanName;
    }

    public static void main(String[] args) {
        System.out.println(beanName);
    }
}

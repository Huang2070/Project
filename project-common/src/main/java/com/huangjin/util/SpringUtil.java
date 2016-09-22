package com.huangjin.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
/**
 * spring 工具类，通过它普通JAVA类能直接获取Spring的bean.
 * @version 1.0
 * @author ZOULEI
 *
 */
public class SpringUtil implements ApplicationContextAware {
    private static ApplicationContext ctx;

    public static <T> T getBean(String name,Class<T> clasz) {
        return ctx.getBean(name,clasz);
    }

    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        ctx = applicationContext;
    }
}

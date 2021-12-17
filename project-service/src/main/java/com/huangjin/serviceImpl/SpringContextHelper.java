package com.huangjin.serviceImpl;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class SpringContextHelper implements ApplicationContextAware {

    private static ApplicationContext context;
    
    
    //提供一个接口，获取容器中的Bean实例，根据名称获取
    public static ApplicationContext getContext() {
        return context;
    }
    
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringContextHelper.context = context;
        
    }

    public static void main(String[] args) {
        System.out.println(context.getId());
    }
}
package com.huangjin.serviceImpl;

import java.util.Map;

import com.huangjin.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author: 黄金
 * @Description:
 * @Date: Create in 17:40 2021-08-09
 */
@Component
public class TestService implements ApplicationContextAware {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() throws BeansException {
        return applicationContext;
    }
}

package com.huangjin.state;

import com.alibaba.fastjson.JSON;

import com.huangjin.domain.User;
import com.huangjin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Author: 黄金
 * @Description:
 * @Date: Create in 20:17 2020-07-21
 */
@Service("distributeStateConfigFinish")
public class DistributeStateConfigFinish implements DistributeState {

    @Autowired
    private UserService userService;

    @Override
    public void save(DistributeContext context) {

        System.out.println(userService);
        userService.selectList(new User());
        System.out.println("configFinish -> save: " + context.getParam());
    }

    @Override
    public String configFinish(DistributeContext context) {
        System.out.println("configFinish -> configFinish: " + context.getParam());
        return "hahaha";
    }
}

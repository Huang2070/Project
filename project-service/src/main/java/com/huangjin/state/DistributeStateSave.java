package com.huangjin.state;

import org.springframework.stereotype.Component;

/**
 * @Author: 黄金
 * @Description:
 * @Date: Create in 20:16 2020-07-21
 */
@Component
public class DistributeStateSave implements DistributeState {

    @Override
    public void save(DistributeContext context) {
        System.out.println("save -> save:" + context.getParam());
    }

    @Override
    public String configFinish(DistributeContext context) {
        System.out.println("save -> configFinish:" + context.getParam());
        return "xixixi";
    }
}

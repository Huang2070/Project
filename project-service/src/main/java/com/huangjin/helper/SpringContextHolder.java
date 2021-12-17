package com.huangjin.helper;

import com.huangjin.state.DistributeStateConfigFinish;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

/**
 * @Author: 黄金
 * @Description:
 * @Date: Create in 18:13 2020-08-06
 */
@Component
public class SpringContextHolder extends ApplicationObjectSupport {

    public DistributeStateConfigFinish getObj(String beanName) {
        return super.getApplicationContext().getBean(beanName , DistributeStateConfigFinish.class);
    }
}

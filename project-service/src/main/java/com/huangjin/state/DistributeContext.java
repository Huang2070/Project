package com.huangjin.state;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: 黄金
 * @Description:
 * @Date: Create in 20:01 2020-07-21
 */
public class DistributeContext {

    private String stateClassName;

    private String stateMethodName;

    private String param;

    public DistributeContext(String stateClassName, String stateMethodName) {
        this.stateClassName = stateClassName;
        this.stateMethodName = stateMethodName;
    }

    public Object invoke()
        throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
        InstantiationException, InvocationTargetException {

        //获取startStatus的状态类实例
        Class<?> startStatusClass = Class.forName("com.huangjin.state.DistributeState" + stateClassName);
        Object startStatusObj = startStatusClass.newInstance();
        DistributeState startStatus = (DistributeState)startStatusObj;

        //获取startStatus里的对应的endStatus方法
        Method endStateMethod = startStatusClass.getDeclaredMethod(stateMethodName, DistributeContext.class);

        endStateMethod.setAccessible(true);

        return endStateMethod.invoke(startStatus, this);
    }

    public String getStateClassName() {
        return stateClassName;
    }

    public void setStateClassName(String stateClassName) {
        this.stateClassName = stateClassName;
    }

    public String getStateMethodName() {
        return stateMethodName;
    }

    public void setStateMethodName(String stateMethodName) {
        this.stateMethodName = stateMethodName;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}

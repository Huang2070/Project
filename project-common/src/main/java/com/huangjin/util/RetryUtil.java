package com.huangjin.util;

import java.lang.reflect.Method;

/**
 * @Author: 黄金
 * @Description:
 * @Date: Create in 12:10 2019-07-29
 */
public class RetryUtil {

    private static ThreadLocal<Integer> retryTimesInThread = new ThreadLocal<>();

    /**
     * 设置当前方法重试次数
     *
     * @param retryTimes
     * @return
     */
    public static RetryUtil setRetryTimes(Integer retryTimes) {
        if (retryTimesInThread.get() == null) {
            retryTimesInThread.set(retryTimes);
        }
        return new RetryUtil();
    }

    /**
     * 重试当前方法
     * <p>按顺序传入调用者方法的所有参数</p>
     *
     * @param args
     * @return
     */
    public Object retry(Class clazz, String methodName, Object... args) {
        try {
            Integer retryTimes = retryTimesInThread.get();
            if(retryTimes <= 0) {
                retryTimesInThread.remove();
                return null;
            }
            retryTimesInThread.set(--retryTimes);

            Object targetObject = clazz.newInstance();
            Method targetMethod = null;
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.getName().equals(methodName)) {
                    targetMethod = method;
                    break;
                }
            }
            if (targetMethod == null) {
                return null;
            }
            targetMethod.setAccessible(true);
            return targetMethod.invoke(targetObject, args);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

package com.huangjin.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger("aspectLog");

    public LogAspect() {
    }

    @Pointcut("execution(public * com.huangjin.controller..*.*(..))")
    public void logPoint() {
    }

    @Before("logPoint()")
    public void beforeMethod(JoinPoint call) {
        String className = call.getTarget().getClass().getName();
        String methodName = call.getSignature().getName();
        logger.info(className + "的" + methodName + "方法开始");
    }

    @AfterReturning("logPoint()")
    public void afterReturningMethod() {
        logger.info("方法结束");
    }
}
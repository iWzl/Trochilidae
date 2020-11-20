package com.upuphub.trochilidae.example.aspect;

import com.upuphub.trochilidae.core.aop.intercept.Interceptor;
import com.upuphub.trochilidae.core.aop.intercept.MethodInvocation;
import com.upuphub.trochilidae.example.controller.TrochilidaeTestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GlobalInterceptor extends Interceptor {
    private final static Logger logger = LoggerFactory.getLogger(GlobalInterceptor.class);
    @Override
    public Object intercept(MethodInvocation methodInvocation) {
        logger.debug(" before method：" + methodInvocation.getTargetMethod().getName());
        Object result = methodInvocation.proceed();
        logger.debug(" after method：" + methodInvocation.getTargetMethod().getName());
        return result;
    }

    @Override
    public boolean supports(Object bean) {
        return bean instanceof TrochilidaeTestController;
    }
}

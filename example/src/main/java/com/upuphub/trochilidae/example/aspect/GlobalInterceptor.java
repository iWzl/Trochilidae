package com.upuphub.trochilidae.example.aspect;

import com.upuphub.trochilidae.core.aop.intercept.Interceptor;
import com.upuphub.trochilidae.core.aop.intercept.MethodInvocation;

/**
 * @author shuang.kou
 * @date  Time 2020年10月09日 21:28:00
 **/
public class GlobalInterceptor extends Interceptor {
    @Override
    public Object intercept(MethodInvocation methodInvocation) {
        System.out.println(GlobalInterceptor.class.getSimpleName() + " before method：" + methodInvocation.getTargetMethod().getName());
        Object result = methodInvocation.proceed();
        System.out.println(GlobalInterceptor.class.getSimpleName() + " after method：" + methodInvocation.getTargetMethod().getName());
        return result;
    }

    @Override
    public boolean supports(Object bean) {
        return true;
    }
}

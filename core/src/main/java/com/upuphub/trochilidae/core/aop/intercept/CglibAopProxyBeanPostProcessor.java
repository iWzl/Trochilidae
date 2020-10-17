package com.upuphub.trochilidae.core.aop.intercept;


import com.upuphub.trochilidae.core.aop.proxy.CglibAspectProxy;

/**
 * @author tom
 * JDK implementation of dynamic proxy
 * @createTime 2020年10月6日10:20:26
 */
public class CglibAopProxyBeanPostProcessor extends AbstractAopProxyBeanPostProcessor {

    @Override
    public Object wrapBean(Object target, Interceptor interceptor) {
        return CglibAspectProxy.wrap(target, interceptor);
    }

}
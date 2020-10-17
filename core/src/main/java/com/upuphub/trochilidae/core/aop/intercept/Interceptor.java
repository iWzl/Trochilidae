package com.upuphub.trochilidae.core.aop.intercept;

/**
 * @author tom & shuang.kou
 * @createTime 2020年10月6日10:20:26
 */

public abstract class Interceptor {
    private int order = -1;

    public boolean supports(Object bean) {
        return false;
    }

    public abstract Object intercept(MethodInvocation methodInvocation);


    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}

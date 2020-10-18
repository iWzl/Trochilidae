package com.upuphub.trochilidae.example;

import com.upuphub.trochilidae.core.aop.factory.InterceptorFactory;
import com.upuphub.trochilidae.core.factory.BeanFactory;
import com.upuphub.trochilidae.core.factory.ClassFactory;
import com.upuphub.trochilidae.core.ioc.DependencyInjection;
import com.upuphub.trochilidae.example.controller.TrochilidaeTestController;

public class TrochilidaeExampleApplication {
    public static void main(String[] args) {
        ClassFactory.loadClass(TrochilidaeExampleApplication.class);
        BeanFactory.loadBeans();
        InterceptorFactory.loadInterceptors(TrochilidaeExampleApplication.class);
        DependencyInjection.dependencyInjection(TrochilidaeExampleApplication.class);
        ((TrochilidaeTestController)BeanFactory.BEANS.get(TrochilidaeTestController.class.getName())).printHello();
    }
}

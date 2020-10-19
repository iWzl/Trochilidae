package com.upuphub.trochilidae.core;

import com.upuphub.trochilidae.core.aop.factory.InterceptorFactory;
import com.upuphub.trochilidae.core.exception.SingleBeanCreateException;
import com.upuphub.trochilidae.core.factory.BeanFactory;
import com.upuphub.trochilidae.core.factory.ClassFactory;
import com.upuphub.trochilidae.core.ioc.DependencyInjection;

/**
 * 应用上下文
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-10-18 15:28
 **/
public final class ApplicationContext {
    private static final ApplicationContext APPLICATION_CONTEXT = new ApplicationContext();

    private ApplicationContext() {
        if(null != APPLICATION_CONTEXT){
            throw new SingleBeanCreateException();
        }
    }

    public static void run(Class<?> bootstrapClazz){
        // Load classes with custom annotation
        ClassFactory.loadClass(bootstrapClazz);
        // Load beans managed by the ioc container
        BeanFactory.loadBeans();
        // Load interceptors
        InterceptorFactory.loadInterceptors(bootstrapClazz);
        // Traverse all the beans in the ioc container and inject instances for all @Autowired annotated attributes.
        DependencyInjection.dependencyInjection(bootstrapClazz);
    }


    public static ApplicationContext getApplicationContext() {
        return APPLICATION_CONTEXT;
    }

}
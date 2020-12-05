package com.upuphub.trochilidae.core;

import com.upuphub.trochilidae.core.annotation.bean.ComponentScan;
import com.upuphub.trochilidae.core.aop.factory.InterceptorFactory;
import com.upuphub.trochilidae.core.banner.Banner;
import com.upuphub.trochilidae.core.exception.SingleBeanCreateException;
import com.upuphub.trochilidae.core.factory.BeanFactory;
import com.upuphub.trochilidae.core.factory.ClassFactory;
import com.upuphub.trochilidae.core.factory.ConfigurationFactory;
import com.upuphub.trochilidae.core.ioc.DependencyInjection;

import java.util.Objects;


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

    public void run(Class<?> bootstrapClass,String... args){
        Banner.print();
        // load class package names
        String[] loadPackageNames = parseLoadedPackageNameByBootstrapClass(bootstrapClass);
        // Load classes with custom annotation
        ClassFactory.loadClass(loadPackageNames);
        // Load resource from yaml or properties
        ConfigurationFactory.loadResourceConfiguration(bootstrapClass.getClassLoader(),loadPackageNames);
        ConfigurationFactory.loadBootstrapConfiguration(args);
        // Load beans managed by the ioc container
        BeanFactory.loadBeans();
        // Load interceptors
        InterceptorFactory.loadInterceptors(loadPackageNames);
        // Traverse all the beans in the ioc container and inject instances for all @Autowired annotated attributes.
        DependencyInjection.inject(loadPackageNames);
        // Applies bean post processors on the classes which are from ClassFactory.
        // For example, the class annotated by @Component
        BeanFactory.applyBeanPostProcessors();
    }

    private String[] parseLoadedPackageNameByBootstrapClass(Class<?> bootstrapClass){
        ComponentScan componentScan = bootstrapClass.getAnnotation(ComponentScan.class);
        return !Objects.isNull(componentScan) ? componentScan.value()
                : new String[]{bootstrapClass.getPackage().getName()};
    }



    public static ApplicationContext getApplicationContext() {
        return APPLICATION_CONTEXT;
    }

}

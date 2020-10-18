package com.upuphub.trochilidae.web;

import com.upuphub.trochilidae.core.ApplicationContext;
import com.upuphub.trochilidae.core.exception.SingleBeanCreateException;
import com.upuphub.trochilidae.core.factory.BeanFactory;
import com.upuphub.trochilidae.core.factory.ClassFactory;
import com.upuphub.trochilidae.web.annotation.RestController;
import com.upuphub.trochilidae.web.factory.RouteMethodMapper;

/**
 * Web的服务应用上下文
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-10-18 15:52
 **/
public final class WebApplicationContext {
    private static final WebApplicationContext WEB_APPLICATION_CONTEXT = new WebApplicationContext();

    private WebApplicationContext() {
        if (null != WEB_APPLICATION_CONTEXT) {
            throw new SingleBeanCreateException();
        }
    }

    public static void run(Class<?> bootstrapClazz){
        ClassFactory.addScanAnnotation(RestController.class);
        BeanFactory.addLoadBeanAnnotation(RestController.class);
        ApplicationContext.run(bootstrapClazz);
        RouteMethodMapper.loadRoutes();
    }

    public static WebApplicationContext getWebApplicationContext() {
        return WEB_APPLICATION_CONTEXT;
    }

}

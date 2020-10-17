package com.upuphub.trochilidae.core.factory;

import com.upuphub.trochilidae.core.annotation.ioc.Component;
import com.upuphub.trochilidae.core.annotation.springmvc.RestController;
import com.upuphub.trochilidae.core.common.util.IocUtil;
import com.upuphub.trochilidae.core.common.util.ReflectionUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class BeanFactory {
    public static final Map<String, Object> BEANS = new ConcurrentHashMap<>(128);

    public static void loadBeans() {
        ClassFactory.CLASSES.get(Component.class).forEach(targetBeanClass -> {
            String beanName = IocUtil.getBeanName(targetBeanClass);
            Object beanInstance = ReflectionUtil.newInstance(targetBeanClass);
            BEANS.put(beanName, beanInstance);
        });
        ClassFactory.CLASSES.get(RestController.class).forEach(targetBeanClass -> {
            Object beanInstance = ReflectionUtil.newInstance(targetBeanClass);
            BEANS.put(targetBeanClass.getName(), beanInstance);
        });
        System.out.println();
    }
}

package com.upuphub.trochilidae.core.common.util;

import com.upuphub.trochilidae.core.annotation.ioc.Component;

public class IocUtil {
    /**
     * get the bean name
     *
     * @param targetBeanClass target class
     * @return the bean name
     */
    public static String getBeanName(Class<?> targetBeanClass) {
        String beanName = targetBeanClass.getName();
        if (targetBeanClass.isAnnotationPresent(Component.class)) {
            Component component = targetBeanClass.getAnnotation(Component.class);
            beanName = "".equals(component.name()) ? targetBeanClass.getName() : component.name();
        }
        return beanName;
    }
}

package com.upuphub.trochilidae.core.factory;

import com.upuphub.trochilidae.core.annotation.ioc.Component;
import com.upuphub.trochilidae.core.common.util.IocUtil;
import com.upuphub.trochilidae.core.common.util.ReflectionUtil;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class BeanFactory {
    public static final Map<String, Object> BEANS = new ConcurrentHashMap<>(128);
    public static final Set<Class<? extends Annotation>> LOAD_BEAN_ANNOTATION = new HashSet<>();

    static {
        LOAD_BEAN_ANNOTATION.add(Component.class);
    }

    public static void addLoadBeanAnnotation(Class<? extends Annotation> annotation){
        LOAD_BEAN_ANNOTATION.add(annotation);
    }

    public static void loadBeans() {
        for (Class<? extends Annotation> loadBeanAnnotation : LOAD_BEAN_ANNOTATION) {
            ClassFactory.CLASSES.get(loadBeanAnnotation).forEach(targetBeanClass -> {
                String beanName = IocUtil.getBeanName(targetBeanClass);
                Object beanInstance = ReflectionUtil.newInstance(targetBeanClass);
                BEANS.put(beanName, beanInstance);
            });
        }
    }
}

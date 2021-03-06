package com.upuphub.trochilidae.core.factory;

import com.upuphub.trochilidae.core.annotation.ioc.Component;
import com.upuphub.trochilidae.core.aop.factory.AopProxyBeanPostProcessorFactory;
import com.upuphub.trochilidae.core.aop.intercept.BeanPostProcessor;
import com.upuphub.trochilidae.core.common.BeanHelper;
import com.upuphub.trochilidae.core.util.ReflectionUtils;

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
                String beanName = BeanHelper.getBeanName(targetBeanClass);
                Object beanInstance = ReflectionUtils.newInstance(targetBeanClass);
                insertBean(beanName, beanInstance);
            });
        }
    }
    public static void applyBeanPostProcessors() {
        BEANS.replaceAll((beanName, beanInstance) -> {
            BeanPostProcessor beanPostProcessor = AopProxyBeanPostProcessorFactory.get(beanInstance.getClass());
            return beanPostProcessor.postProcessAfterInitialization(beanInstance);
        });
    }


    public static void insertBean(String beanName,Object beanInstance){
        BEANS.put(beanName, beanInstance);
    }

    public static Object getInstanceByClazz(Class<?> clazz){
        return BEANS.get(BeanHelper.getBeanName(clazz));
    }
}

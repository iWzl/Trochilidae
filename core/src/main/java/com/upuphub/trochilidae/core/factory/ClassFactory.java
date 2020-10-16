package com.upuphub.trochilidae.core.factory;

import com.upuphub.trochilidae.core.annotation.aop.Aspect;
import com.upuphub.trochilidae.core.annotation.ioc.Component;
import com.upuphub.trochilidae.core.annotation.springmvc.RestController;
import com.upuphub.trochilidae.core.common.util.ReflectionUtil;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author LeoWang
 */
public class ClassFactory {
    public static final Map<Class<? extends Annotation>, Set<Class<?>>> CLASSES = new ConcurrentHashMap<>();

    public static void loadClass(String packageName) {
        Set<Class<?>> restControllers = ReflectionUtil.scanAnnotatedClass(packageName, RestController.class);
        Set<Class<?>> components = ReflectionUtil.scanAnnotatedClass(packageName, Component.class);
        Set<Class<?>> aspects = ReflectionUtil.scanAnnotatedClass(packageName, Aspect.class);
        CLASSES.put(RestController.class, restControllers);
        CLASSES.put(Component.class, components);
        CLASSES.put(Aspect.class, aspects);
        System.out.println();
    }
}

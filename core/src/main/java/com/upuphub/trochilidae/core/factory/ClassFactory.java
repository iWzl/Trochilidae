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
 *
 *
 */
public class ClassFactory {
    public static final Map<Class<? extends Annotation>, Set<Class<?>>> CLASSES = new ConcurrentHashMap<>();


    public static void loadClass(Class<?> bootstrapClass) {
        String packageName = bootstrapClass.getPackage().getName();
        loadClass(packageName);
    }

    public static void loadClass(String packageName) {
        CLASSES.putAll(ReflectionUtil.scanAnnotatedClasses(packageName,RestController.class,Aspect.class,Component.class));
        System.out.println();
    }
}

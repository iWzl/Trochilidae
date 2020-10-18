package com.upuphub.trochilidae.core.factory;

import com.upuphub.trochilidae.core.annotation.aop.Aspect;
import com.upuphub.trochilidae.core.annotation.ioc.Component;
import com.upuphub.trochilidae.core.common.util.ReflectionUtil;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author LeoWang
 *
 *
 */
public class ClassFactory {
    public static final Map<Class<? extends Annotation>, Set<Class<?>>> CLASSES = new ConcurrentHashMap<>();
    private static final Set<Class<? extends Annotation>> SCAN_ANNOTATION = new HashSet<>(16);

    static {
        SCAN_ANNOTATION.add(Aspect.class);
        SCAN_ANNOTATION.add(Component.class);
    }

    public static void addScanAnnotation(Class<? extends Annotation> annotation){
        if(annotation.isAnnotationPresent(Component.class)){
            SCAN_ANNOTATION.add(annotation);
        }
    }

    public static void loadClass(Class<?> bootstrapClass) {
        String packageName = bootstrapClass.getPackage().getName();
        loadClass(packageName);
    }

    public static void loadClass(String packageName) {
        CLASSES.putAll(ReflectionUtil.scanAnnotatedClasses(packageName,SCAN_ANNOTATION));
    }
}

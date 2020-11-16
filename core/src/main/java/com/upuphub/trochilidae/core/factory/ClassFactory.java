package com.upuphub.trochilidae.core.factory;

import com.upuphub.trochilidae.core.annotation.aop.Aspect;
import com.upuphub.trochilidae.core.annotation.bean.ComponentScan;
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
        ComponentScan componentScan = bootstrapClass.getAnnotation(ComponentScan.class);
        String[] packageNames =  !Objects.isNull(componentScan) ? componentScan.value()
                : new String[]{bootstrapClass.getPackage().getName()};
        loadClass(packageNames);
    }

    public static void loadClass(String[] packageNames) {
        CLASSES.putAll(ReflectionUtil.scanAnnotatedClasses(packageNames,SCAN_ANNOTATION));
    }
}

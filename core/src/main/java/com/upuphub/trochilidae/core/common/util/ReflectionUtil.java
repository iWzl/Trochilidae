package com.upuphub.trochilidae.core.common.util;

import com.upuphub.trochilidae.core.exception.CanNotInvokeTargetMethodException;
import com.upuphub.trochilidae.core.exception.CheckClassScanParamsException;
import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 项目中可使用的通用反射工具
 *
 * @author Leo Wang
 **/
public class ReflectionUtil {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);


    /**
     * scan the classes marked by the specified annotation in the specified package
     *
     * @param packageNames specified package name
     * @param annotation  specified annotation
     * @return the classes marked by the specified annotation in the specified package
     */
    public static Set<Class<?>> scanAnnotatedClass(String[] packageNames, Class<? extends Annotation> annotation) {
        Reflections reflections = new Reflections(packageNames, new TypeAnnotationsScanner());
        Set<Class<?>> annotatedClass = reflections.getTypesAnnotatedWith(annotation, true);
        logger.debug("The number of class Annotated with  @{} :[{}]",annotation.getSimpleName(),annotatedClass.size());
        return annotatedClass;
    }


    /**
     * scan the classes marked by the specified annotation in the specified package
     *
     * @param packageNames specified package name
     * @param annotationList  specified annotation
     * @return the classes marked by the specified annotation in the specified package
     */
    public static Map<Class<? extends Annotation>,Set<Class<?>>> scanAnnotatedClasses(String[] packageNames, Set<Class<? extends Annotation>> annotationList) {
        if(null == packageNames || null == annotationList || 0 == packageNames.length || 0 == annotationList.size()){
            throw new CheckClassScanParamsException("Not find scan the classes Params");
        }
        Reflections reflections = new Reflections(packageNames, new TypeAnnotationsScanner());
        Map<Class<? extends Annotation>,Set<Class<?>>> annotatedClassesMap = new ConcurrentHashMap<>(annotationList.size());
        for (Class<? extends Annotation> annotation : annotationList) {
            Set<Class<?>> annotatedClass = reflections.getTypesAnnotatedWith(annotation, true);
            if(!annotatedClass.isEmpty()){
                logger.debug("The number of class Annotated with  @{} :[{}]",annotation.getSimpleName(),annotatedClass.size());
                annotatedClassesMap.put(annotation,annotatedClass) ;
            }
        }
        return annotatedClassesMap;
    }


    /**
     * Get the implementation class of the interface
     *
     * @param packageName    specified package name
     * @param interfaceClass specified interface
     */
    public static <T> Set<Class<? extends T>> getSubClass(String packageName, Class<T> interfaceClass) {
        Reflections reflections = new Reflections(packageName);
        return reflections.getSubTypesOf(interfaceClass);

    }

    /**
     * create object instance through class
     *
     * @param cls target class
     * @return object created by the target class
     */
    public static Object newInstance(Class<?> cls) {
        Object instance = null;
        try {
            instance = cls.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error("new instance failed", e);
        }
        return instance;
    }

    /**
     * set the value of a field in the object
     *
     * @param obj   target object
     * @param field target field
     * @param value the value assigned to the field
     */
    public static void setField(Object obj, Field field, Object value) {

        field.setAccessible(true);
        try {
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            logger.error("set field failed", e);
            e.printStackTrace();
        }

    }

    /**
     * execute the target method
     *
     * @param method target method
     * @param args   method parameters
     * @return the result of method execution
     */
    public static Object executeTargetMethod(Object targetObject, Method method, Object... args) {
        Object result;
        try {
            // invoke target method through reflection
            result = method.invoke(targetObject, args);
            if(Void.class != method.getReturnType()){
                logger.debug("invoke target method successfully");
            }else {
                logger.debug("invoke target method successfully ,result is: [{}]", result.toString());
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new CanNotInvokeTargetMethodException(e.toString());
        }
        return result;
    }

    /**
     * execute the target method
     *
     * @param method target method
     * @param args   method parameters
     */
    public static void executeTargetMethodNoResult(Object targetObject, Method method, Object... args) {
        try {
            // invoke target method through reflection
            method.invoke(targetObject, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new CanNotInvokeTargetMethodException(e.toString());
        }
    }
}

package com.upuphub.trochilidae.core.ioc;

import com.upuphub.trochilidae.core.annotation.ioc.Autowired;
import com.upuphub.trochilidae.core.annotation.ioc.Qualifier;
import com.upuphub.trochilidae.core.aop.factory.BeanPostProcessorFactory;
import com.upuphub.trochilidae.core.aop.intercept.BeanPostProcessor;
import com.upuphub.trochilidae.core.common.util.IocUtil;
import com.upuphub.trochilidae.core.common.util.ReflectionUtil;
import com.upuphub.trochilidae.core.exception.CanNotDetermineTargetBeanException;
import com.upuphub.trochilidae.core.exception.InterfaceNotHaveImplementedClassException;
import com.upuphub.trochilidae.core.factory.BeanFactory;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 依赖注入
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-10-17 18:32
 **/
public class DependencyInjection {


    /**
     * 二级缓存、存储必要的Bean实例
     */
    private static final Map<String, Object> SINGLETON_OBJECTS = new ConcurrentHashMap<>(64);


    /**
     * 遍历ioc容器所有bean的属性, 为所有带@Autowired注解的属性注入实例
     */
    public static void dependencyInjection(Class<?> applicationClazz) {
        Map<String, Object> beans = BeanFactory.BEANS;
        //创建好的bean都放入对象工厂
        if (beans.size() > 0) {
            BeanFactory.BEANS.values().forEach(bean -> prepareBean(bean, applicationClazz.getPackage().getName()));
        }
    }


    /**
     * 遍历ioc容器所有bean的属性, 为所有带@Autowired注解的属性注入实例
     */
    public static void dependencyInjection(String packageName) {
        Map<String, Object> beans = BeanFactory.BEANS;
        //创建好的bean都放入对象工厂
        if (beans.size() > 0) {
            BeanFactory.BEANS.values().forEach(bean -> prepareBean(bean, packageName));
        }
    }

    /**
     * 准备bean
     */
    private static void prepareBean(Object beanInstance, String packageName) {
        Class<?> beanClass = beanInstance.getClass();
        Field[] beanFields = beanClass.getDeclaredFields();
        //遍历bean的属性
        if (beanFields.length > 0) {
            for (Field beanField : beanFields) {
                if (beanField.isAnnotationPresent(Autowired.class)) {
                    //属性类型
                    Class<?> beanFieldClass = beanField.getType();
                    String beanName = IocUtil.getBeanName(beanFieldClass);
                    boolean newSingleton = true;
                    Object beanFieldInstance = null;
                    if (SINGLETON_OBJECTS.containsKey(beanName)) {
                        beanFieldInstance = SINGLETON_OBJECTS.get(beanName);
                        newSingleton = false;
                    }
                    if (beanFieldInstance == null) {
                        beanFieldInstance = BeanFactory.BEANS.get(beanName);
                        if (beanFieldClass.isInterface()) {
                            @SuppressWarnings("unchecked")
                            Set<Class<?>> subClasses = ReflectionUtil.getSubClass(packageName, (Class<Object>) beanFieldClass);
                            if (0 == subClasses.size()) {
                                throw new InterfaceNotHaveImplementedClassException(beanFieldClass.getName() + "is interface and do not have implemented class exception");
                            }
                            if (1 == subClasses.size()) {
                                Class<?> aClass = subClasses.iterator().next();
                                beanFieldInstance = ReflectionUtil.newInstance(aClass);
                            }
                            if (subClasses.size() > 1) {
                                Qualifier qualifier = beanField.getDeclaredAnnotation(Qualifier.class);
                                beanName = qualifier == null ? beanName : qualifier.value();
                                beanFieldInstance = BeanFactory.BEANS.get(beanName);
                            }

                        }
                        if (null == beanFieldInstance) {
                            throw new CanNotDetermineTargetBeanException("can not determine target bean of" + beanFieldClass.getName());
                        }
                        SINGLETON_OBJECTS.put(beanName, beanFieldInstance);
                    }
                    if (newSingleton) {
                        prepareBean(beanFieldInstance, packageName);
                    }
                    BeanPostProcessor beanPostProcessor = BeanPostProcessorFactory.get(beanFieldClass);
                    beanFieldInstance = beanPostProcessor.postProcessAfterInitialization(beanFieldInstance);
                    ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                }
            }
        }
    }
}

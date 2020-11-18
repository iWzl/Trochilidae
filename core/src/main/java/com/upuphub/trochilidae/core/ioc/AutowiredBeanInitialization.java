package com.upuphub.trochilidae.core.ioc;

import com.upuphub.trochilidae.core.annotation.bean.ComponentScan;
import com.upuphub.trochilidae.core.annotation.ioc.Autowired;
import com.upuphub.trochilidae.core.annotation.ioc.Qualifier;
import com.upuphub.trochilidae.core.common.util.BeanHelper;
import com.upuphub.trochilidae.core.common.util.ReflectionUtil;
import com.upuphub.trochilidae.core.exception.CanNotDetermineTargetBeanException;
import com.upuphub.trochilidae.core.exception.InterfaceNotHaveImplementedClassException;
import com.upuphub.trochilidae.core.factory.BeanFactory;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Set;

/**
 * 自动注入的Bean初始化管理器
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-11-13 15:29
 **/
public class AutowiredBeanInitialization {

    private final String[] packageNames;

    public AutowiredBeanInitialization(String[] packageNames) {
       this.packageNames = packageNames;
    }

    public void initialize(Object beanInstance){
        Class<?> beanClass = beanInstance.getClass();
        Field[] beanFields = beanClass.getDeclaredFields();
        // 遍历bean的属性
        if(beanFields.length > 0){
            for (Field beanField : beanFields) {
                if (beanField.isAnnotationPresent(Autowired.class)) {
                    Object beanFieldInstance = processAutowiredAnnotationField(beanField);

                }
            }
        }

    }


    /**
     * 处理被 @Autowired 注解标记的字段
     *
     * @param beanField 目标类的字段
     * @return 目标类的字段对应的对象
     */
    private Object processAutowiredAnnotationField(Field beanField) {
        Class<?> beanFieldClass = beanField.getType();
        String beanFieldName = BeanHelper.getBeanName(beanFieldClass);
        Object beanFieldInstance;
        if (beanFieldClass.isInterface()) {
            @SuppressWarnings("unchecked")
            Set<Class<?>> subClasses = ReflectionUtil.getSubClass(packageNames,(Class<Object>) beanFieldClass);
            if (subClasses.size() == 0) {
                throw new InterfaceNotHaveImplementedClassException(beanFieldClass.getName() + "is interface and do not have implemented class exception");
            }
            if (subClasses.size() == 1) {
                Class<?> subClass = subClasses.iterator().next();
                beanFieldName = BeanHelper.getBeanName(subClass);
            }
            if (subClasses.size() > 1) {
                Qualifier qualifier = beanField.getDeclaredAnnotation(Qualifier.class);
                beanFieldName = qualifier == null ? beanFieldName : qualifier.value();
            }

        }
        beanFieldInstance = BeanFactory.BEANS.get(beanFieldName);
        if (beanFieldInstance == null) {
            throw new CanNotDetermineTargetBeanException("can not determine target bean of" + beanFieldClass.getName());
        }
        return beanFieldInstance;
    }
}

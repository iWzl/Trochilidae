package com.upuphub.trochilidae.web.common.util;

import com.upuphub.trochilidae.web.annotation.RestController;

/**
 * @author Inspiration S.P.A Leo
 * @date create time 2020-11-05 12:59
 **/
public class BeanHelper {
    /**
     * get the bean name
     *
     * @param aClass target class
     * @return the bean name
     */
    public static String getBeanName(Class<?> aClass) {
        String beanName = aClass.getName();
        if (aClass.isAnnotationPresent(RestController.class)) {
            RestController restController = aClass.getAnnotation(RestController.class);
            beanName = "".equals(restController.name()) ? aClass.getName() : restController.name();
        }
        return beanName;
    }
}

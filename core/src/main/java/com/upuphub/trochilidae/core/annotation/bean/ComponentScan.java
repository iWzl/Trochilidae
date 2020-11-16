package com.upuphub.trochilidae.core.annotation.bean;

import java.lang.annotation.*;

/**
 * @author LeoWang
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface ComponentScan {
    String[] value() default {};
}

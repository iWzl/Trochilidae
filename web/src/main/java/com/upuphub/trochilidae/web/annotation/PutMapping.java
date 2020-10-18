package com.upuphub.trochilidae.web.annotation;

import java.lang.annotation.*;


/**
 * Put 请求的相关Map映射
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-10-17 18:33
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PutMapping {
    String value() default "";
}

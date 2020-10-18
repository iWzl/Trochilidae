package com.upuphub.trochilidae.web.annotation;

import java.lang.annotation.*;


/**
 * Post 请求的相关Map映射
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-10-17 18:33
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PostMapping {
    String value() default "";
}

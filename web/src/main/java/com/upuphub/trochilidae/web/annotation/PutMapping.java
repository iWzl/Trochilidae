package com.upuphub.trochilidae.web.annotation;

import com.upuphub.trochilidae.web.common.lang.HttpMethod;

import java.lang.annotation.*;


/**
 * Delete 请求的相关Map映射
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-10-17 18:33
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PutMapping {

    String name() default "";

    String[] value() default {};

    String[] path() default {};

    String[] headers() default {};

    String[] consumes() default {};

    String[] produces() default {};
}

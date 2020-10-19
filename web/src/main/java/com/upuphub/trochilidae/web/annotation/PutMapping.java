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
@RequestMapping(method = HttpMethod.PUT)
public @interface PutMapping {

    @AliasFor(annotation = RequestMapping.class)
    String name() default "";

    @AliasFor(annotation = RequestMapping.class)
    String[] value() default {};

    @AliasFor(annotation = RequestMapping.class)
    String[] path() default {};

    @AliasFor(annotation = RequestMapping.class)
    String[] headers() default {};

    @AliasFor(annotation = RequestMapping.class)
    String[] consumes() default {};

    @AliasFor(annotation = RequestMapping.class)
    String[] produces() default {};
}

package com.upuphub.trochilidae.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * URL路径上的参数属性
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-10-17 18:33
 **/
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PathVariable {
    String value();
}

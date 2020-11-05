package com.upuphub.trochilidae.web.annotation;

import com.upuphub.trochilidae.core.annotation.ioc.Component;

import java.lang.annotation.*;

/**
 * 标记为控制器的入口的Bean
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-10-17 18:33
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RestController {
    String value() default "";
    String name() default "";
}

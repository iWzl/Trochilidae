package com.upuphub.trochilidae.core.annotation.springmvc;



import com.upuphub.trochilidae.core.annotation.ioc.Component;

import java.lang.annotation.*;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RestController {
    String value() default "";
}

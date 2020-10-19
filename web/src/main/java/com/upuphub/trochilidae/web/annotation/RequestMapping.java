package com.upuphub.trochilidae.web.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.upuphub.trochilidae.web.common.lang.HttpMethod;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {

	String name() default "";

	@AliasFor("path")
	String[] value() default {};

	@AliasFor("value")
	String[] path() default {};

	HttpMethod[] method() default {};

	String[] headers() default {};


	String[] consumes() default {};


	String[] produces() default {};

}

package com.next.module.datastorage;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName:数据属性注解
 *
 * @author Afton
 * @time 2024/5/21
 * @auditor
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Data {

    String key() default "";
}
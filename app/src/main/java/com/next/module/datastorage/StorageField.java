package com.next.module.datastorage;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName:存储属性注解
 *
 * @author Afton
 * @time 2024/5/21
 * @auditor
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface StorageField {

    String key() default "";
}
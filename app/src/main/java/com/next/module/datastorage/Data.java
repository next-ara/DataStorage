package com.next.module.datastorage;

/**
 * ClassName:数据属性注解
 *
 * @author Afton
 * @time 2024/5/21
 * @auditor
 */
public @interface Data {

    String key() default "";
}
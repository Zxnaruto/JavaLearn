package com.zhaixin.apisafetoken.annotation;

/**
 * @Program: apisafetoken
 * @Classname: JwtIgnore
 * @Author: Zhai
 * @Description:
 * @Date: 2021/06/09 17:09
 */

import java.lang.annotation.*;

/**
 * JWT验证忽略注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JwtIgnore {
    boolean value() default true;
}


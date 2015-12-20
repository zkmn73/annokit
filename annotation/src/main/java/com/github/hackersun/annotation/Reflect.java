package com.github.hackersun.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Desc:
 * Author:sunguoli@meituan.com
 * Date:15/12/20
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Reflect {

    String name() default "sunguoli";

}

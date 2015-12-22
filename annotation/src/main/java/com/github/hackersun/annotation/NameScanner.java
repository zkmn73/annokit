package com.github.hackersun.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * Desc:
 * Author:sunguoli@meituan.com
 * Date:15/12/11 下午9:22
 */
@Target({ElementType.TYPE,ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
public @interface NameScanner{

}

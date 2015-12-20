package com.github.hackersun.sample;

import com.github.hackersun.annotation.Reflect;
import com.github.hackersun.processor.reflect.ReflectProcessor;

/**
 * Desc:
 * Author:sunguoli@meituan.com
 * Date:15/12/20
 */
public class ReflectTest {

    @Reflect
    public static void sayHello(final String name) {
        System.out.println("==>> Hi, " + name + " [sayHello]");
    }

    @Reflect(name = "AngelaBaby")
    public static void sayHelloToSomeone(final String name) {
        System.out.println("==>> Hi, " + name + " [sayHelloToSomeone]");
    }

    public static void main(final String[] args) throws Exception {
        final ReflectProcessor pm = new ReflectProcessor();
        pm.parseMethod(ReflectTest.class);
    }
}

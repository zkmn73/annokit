package com.github.hackersun.processor.reflect;

import com.github.hackersun.annotation.Reflect;
import java.lang.reflect.Method;

/**
 * Desc:
 * Author:sunguoli@meituan.com
 * Date:15/12/20
 */
public class ReflectProcessor {

    /**
     * 处理定义在方法上的注解
     * @param clazz
     *            为注解所在的目标类
     * @throws Exception
     */
    public void parseMethod(final Class<?> clazz) throws Exception {
        // 生成目标类的一个实例
        final Object obj = clazz.getConstructor(new Class[] {}).newInstance(new Object[] {});
        // 得到目标类的方法集
        final Method[] methods = clazz.getDeclaredMethods();
        for (final Method method : methods) {
            final Reflect my = method.getAnnotation(Reflect.class);
            if (null != my) {
                method.invoke(obj, my.name());
            }
        }
    }
}

package com.github.hackersun.sample.lombok;

import lombok.Getter;
import lombok.Setter;

/**
 * Desc:
 * Author:sunguoli@meituan.com
 * Date:15/12/18
 */
@Getter
public class SetterGetterTest {

    @Setter
    String name;

    @Setter
    int age;

    public static void main(String[] args) {
        SetterGetterTest sgt = new SetterGetterTest();
        sgt.setName("abc");
        sgt.setAge(123);
        System.out.println("Name:" + sgt.getName());
        System.out.println("Age:" + sgt.getAge());
    }
}

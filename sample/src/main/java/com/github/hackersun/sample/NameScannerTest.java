package com.github.hackersun.sample;

import com.github.hackersun.annotation.NameScanner;

/**
 * Desc:
 * Author:sunguoli@meituan.com
 * Date:15/12/11 下午9:47
 */
@NameScanner
public class NameScannerTest {

    @NameScanner
    private String name;
    @NameScanner
    private int age;
    @NameScanner
    public String getName(){
        return this.name;
    }
    @NameScanner
    public void setName(String name){
        this.name = name;
    }
    @NameScanner
    public int getAge() {
        return age;
    }
    @NameScanner
    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args){
        NameScannerTest nst = new NameScannerTest();
        nst.setName("sunguoli");
        nst.setAge(18);
        System.out.println("--starting--");
        System.out.println(nst.getName());
        System.out.println(nst.getAge());
        System.out.println("--finished--");
    }
}

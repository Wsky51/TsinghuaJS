package com.util;

/**
 * Created on 2020/3/23
 *
 * @author WuYi
 */
public class FractionTest {
    // 用法展示
    public static void main(String[] args) {
        Fraction fraction = new Fraction(1,2);
        Fraction f2 = new Fraction(-1, 24);
        System.out.println(fraction);
        System.out.println(fraction.toDouble());
        System.out.println(fraction.add(f2));
        System.out.println(fraction.sub(-1, 24));
        System.out.println(fraction.multiply(f2));
        System.out.println(fraction.divide(-1, 24));
        System.out.println(fraction.add(-10, 12));
    }
}

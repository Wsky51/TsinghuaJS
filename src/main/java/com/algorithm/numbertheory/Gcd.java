package com.algorithm.numbertheory;

/**
 * Created on 2020/3/8
 * 求最大公约数和最小公倍数
 *
 * @author WuYi
 */
public class Gcd {

    /**
     * 求两个数的最大公约数
     *
     * @param a
     * @param b
     * @return
     */
    public int gcd(int a, int b)    /*自定义函数求两数的最大公约数*/ {
        int temp;          /*定义整型变量*/
        if (a < b)             /*通过比较求出两个数中的最大值和最小值*/ {
            temp = a;
            a = b;
            b = temp;
        } /*设置中间变量进行两数交换*/
        while (b != 0)           /*通过循环求两数的余数，直到余数为0*/ {
            temp = a % b;
            a = b;              /*变量数值交换*/
            b = temp;
        }
        return a;            /*返回最大公约数到调用函数处*/
    }

    //gcd的递归实现
    int gcdByRecursion(int a, int b) {
        if (a % b == 0) {
            return b;
        } else {
            return gcd(b, a % b);
        }

    }

    /**
     * 求两个数的最小公倍数
     *
     * @param a
     * @param b
     * @return
     */
    int lcm(int a, int b)         //自定义函数求最小公倍数
    {
        int temp = gcd(a, b);          //再次调用自定义函数，求出最大公约数
        return (a / temp) * b;           //返回最小公倍数到主调函数处进行输出
    }

    public static void main(String[] args) {
        Gcd gcd = new Gcd();
        System.out.println(gcd.gcd(11, 6));


    }
}

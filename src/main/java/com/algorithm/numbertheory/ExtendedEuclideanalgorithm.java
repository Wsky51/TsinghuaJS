package com.algorithm.numbertheory;

/**
 * Created on 2020/4/2
 * 扩展欧几里得算法.
 * <p>
 * 定理：设a,b,c为任意整数。若方程ax+by=c的一组整数解为(x0,y0),则它的任意整数解都可以写成(x0+kb',y0-ka')
 * 其中a'=a/gcd(a,b),b'=b/gcd(a,b)
 * </p>
 *
 * @author WuYi
 */

public class ExtendedEuclideanalgorithm {
    static int x;
    static int y;

    public static void main(String[] args) {
        int d = gcdEx(6, 15);
        System.out.println("x=" + x + ",y=" + y + ",d=" + d);
    }

    //函数返回值为最大公约数，x为使ax+by=gcd(a,b)解的x,y
    static int gcdEx(int a, int b) {
        if (b == 0) {
            x = 1;
            y = 0;
            return a;
        } else {
            int r = gcdEx(b, a % b);
            /* r = GCD(a, b) = GCD(b, a%b) */
            int t = x;
            x = y;
            y = t - a / b * y;
            return r;
        }
    }
}

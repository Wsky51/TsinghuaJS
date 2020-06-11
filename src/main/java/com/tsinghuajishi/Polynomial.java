package com.tsinghuajishi;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created on 2020/3/21
 * 这个题目是2017年清华机试第3题，前两个测试用例过了，第三个测试用例数据量高达2000万，超时，至今未解决大数据超时的问题
 * @author WuYi
 */
public class Polynomial {
    static final int MOD = 1000000007;  //10^9+7
    static final BigInteger MOD_BIG = new BigInteger(String.valueOf(MOD));
    static final BigInteger ONE_BIG = new BigInteger("1");
    static long n, m, a;
    static long[] b;

    public static void main(String[] args) {
        init();//初始化输入数据
        long l = System.currentTimeMillis();
        System.out.println("最终结果：" + s(n));
        System.out.println("耗时："+(System.currentTimeMillis()-l));

    }

    //初始化数据
    public static void init() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextLong();
        m = scanner.nextLong();
        b = new long[new Long(m).intValue() + 1];
        a = scanner.nextLong();
        for (int i = 0; i < m + 1; i++) {
            b[i] = scanner.nextLong();
        }
    }

    public static long checknum(long num) {
        if (num > MOD) {
            System.out.println("注意：当前数字num=" + num + "有溢出");
        }
        return num < MOD ? num : num % MOD;
    }

    //计算f(x)
    public static BigInteger f(long x) {
        BigInteger sum = new BigInteger(String.valueOf(b[0]));
        BigInteger xBig = new BigInteger(String.valueOf(x));
        BigInteger pre = ONE_BIG;
        BigInteger temp;
        for (int i = 1; i < m + 1; i++) {

            BigInteger tarNum = (temp = pre.multiply(xBig).mod(MOD_BIG)).
                    multiply(new BigInteger(String.valueOf(b[i])));
            sum = sum.add(tarNum).mod(MOD_BIG);
            pre = temp;
        }
//        System.out.println("f(" + x + ")" + "=" + sum);
        return sum;
    }

    //计算s(n)
    public static long s(long n) {
        BigInteger sum = f(0);
        BigInteger aBig = new BigInteger(String.valueOf(a));
        BigInteger pre = ONE_BIG;
        BigInteger temp;
        for (int k = 1; k < n + 1; k++) {
            BigInteger curNum = (temp = pre.multiply(aBig).mod(MOD_BIG)).multiply(f(k)).mod(MOD_BIG);
            sum = sum.add(curNum).mod(MOD_BIG);
            pre = temp;
        }
        return sum.longValue();
    }
}

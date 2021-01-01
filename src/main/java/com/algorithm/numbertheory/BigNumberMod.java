package com.algorithm.numbertheory;

import java.math.BigInteger;

/**
 * Created on 2020/4/3
 * 大数的模运算，来自刘汝佳算法
 * (a+b) mod n = ((a mod n)+(b mod n))mod n
 * (a-b) mod n = ((a mod n)-(b mod n)+n)mod n
 * a*b mod n   =  (a mod n)*(b mod n)mod n
 * 注意在减法中，由于a mod n可能小于b mod n,需要在结果上加上n,而在乘法中，需要注意a mod n和b mod n相乘是否会溢出。
 *
 * @author WuYi
 */
public class BigNumberMod {
    public static void main(String[] args) {
//        int m = 3456;
//        long l = System.currentTimeMillis();
//        for (int i = 0; i < 10000; i++) {
//            powMod(32, i, m);
//        }
//        System.out.println(System.currentTimeMillis() - l);
//
//        long l1 = System.currentTimeMillis();
//        for (int i = 0; i < 10000; i++) {
//            powMod2(9, i, m);
//        }
//        System.out.println(System.currentTimeMillis() - l1);
//        int m = 3456;
        long l = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            Math.pow(3, i);
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - l));

        long l1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            fastPow(3, i);
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - l1));

    }

    /**
     * 大整数取模,首先把大整数写成自左至右的形式：1234=(((1*10+2)*10+3)*10+4)
     * 另外如果采用BigInteger计算的话效果一样，但BigInteger计算模效率更低，
     * 作为参考：在数据量达100000下，但BigInteger计算模花费711ms,而此方法只需107ms
     * 其他数据规模有类似结果，效率均是BigInteger求模花费的7-10倍
     */
    public static int bigIntegerMod(String n, int mod) {
        int len = n.length();
        int ans = 0;
        for (int i = 0; i < len; i++) {
            ans = (int) (((long) ans * 10 + n.charAt(i) - '0') % mod);
        }
        return ans;
    }

    //建议采用上面一种方法求效率更高
    public static int bigIntegerMod2(String n, int mod) {
        BigInteger big = new BigInteger(n);
        return big.mod(new BigInteger(mod + "")).intValue();
    }

    /**
     * 幂取模,时间复杂度O(logn)，建议采用
     * 同理，在n=10000条件下，该算法花费42ms,而下一种算法powMod2花费4763ms
     *
     * @param a
     * @param n
     * @param m
     * @return
     */
    public static int powMod(int a, int n, int m) {
        if (n == 0) {
            return 1;
        }
        int x = powMod(a, n / 2, m);
        long ans = (long) x * x % m;
        if ((n & 1) == 1) {
            ans = ans * a % m;
        }
        return (int) ans;
    }

    public static int powModWithoutRecursion(long a, long n, int m) {
        long res = 1;
        while (n != 0) {
            if ((n & 1) == 1) {
                res = res * a % m;
            }
            n /= 2;
            a = a * a % m;
        }
        return (int)(res%m);
    }

    /**
     * 幂取模，时间复杂度为O(n),不建议采用
     */
    public static int powMod2(int a, int n, int m) {
        int ans = 1;
        for (int i = 0; i < n; i++) {
            ans = (int) ((long) ans * n % m);
        }
        return ans;
    }

    public static int fastPow(int a, int n) {
        int res = 1;
        while (n != 0) {
            if ((n & 1) == 1) {
                res = res * a;
            }
            n /= 2;
            a = a * a;
        }
        return res;
    }

}

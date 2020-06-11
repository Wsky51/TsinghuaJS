package com.tsinghuajishi;

import java.util.Scanner;

/**
 * Created on 2020/4/7
 * 总的时间复杂度O(m^2)
 * @author WY
 */
public class Polynomial2 {
    static long n, m, a;
    static long[] b;
    static final long mod = 1000000007l;
    static long[][] c = new long[105][105];//c[i][j]表示组合数从i个物品中拿j个物品有多少种情况
    static long[] pow = new long[105];//pow[i]=(n+1)^i
    static long[] T = new long[105];//t[i]=∑a^k*k^i k从0到n的累加和

    //初始化二项式矩阵
    static {
        c[0][0] = 1;
        c[1][0] = c[1][1] = 1;
        for (int i = 2; i < 105; i++) {
            for (int j = 0; j <= i; j++) {
                c[i][j] = j >= 1 ? (c[i - 1][j - 1] + c[i - 1][j]) % mod : (c[i - 1][j]) % mod;
            }
        }
    }

    public static void main(String[] args) {
        init();
        long l = System.currentTimeMillis();
        calculate();
        System.out.println("time consume:" + (System.currentTimeMillis() - l));
    }

    static void init() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextLong();
        m = scanner.nextLong();
        a = scanner.nextLong();

        b = new long[(int) m + 1];

        for (int i = 0; i <= m; i++) {
            b[i] = scanner.nextLong();
        }

        pow[0] = 1;
        for (int i = 1; i < 105; i++) {
            pow[i] = pow[i - 1] * (n + 1) % mod;
        }
    }

    static void calculate() {
        long aPowNadd1, invAsub1;
        T[0] = ((aPowNadd1 = powMod(a, n + 1)) - 1) * (invAsub1 = inverse(a - 1)) % mod;
        if (a == 1) {//特解
            T[0] = n + 1;//a==1为特殊情况
            for (int i = 1; i <= m; i++) {
                long sum = pow[i + 1];
                for (int t = 0; t < i; t++) {
                    sum = (sum - c[i + 1][t] * T[t]) % mod;
                }
                if (sum < 0) {
                    sum += mod;
                }
                T[i] = sum * inverse(i + 1) % mod;
            }
        } else {//a!=1时
            for (int i = 1; i <= m; i++) {
                long sum = aPowNadd1 * pow[i] % mod;
                long subSum = 0;
                for (int t = 0; t < i; t++) {
                    subSum = (subSum + c[i][t] * T[t]) % mod;
                }
                sum = (sum - (a * subSum % mod)) % mod;
                if (sum < 0) {
                    sum += mod;
                }
                T[i] = sum * invAsub1 % mod;
            }
        }

        long res = 0;
        //计算s(n):
        for (int i = 0; i <= m; i++) {
            res = (res + b[i] * T[i] % mod) % mod;
        }
        System.out.println("ans:" + res);
    }

    //求乘法逆元
    static long inverse(long x) {//mod p的逆元
        return powMod(x, mod - 2);
    }

    //快速幂
    public static long powMod(long x, long pow) { //mod值是定死的
        long res = 1;
        while (pow != 0) {
            if ((pow & 1) == 1) {
                res = res * x % mod;
            }
            pow >>= 1;
            x = x * x % mod;
        }
        return res;
    }
}
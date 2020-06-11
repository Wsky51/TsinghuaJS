package com.csp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created on 2020/3/12
 * 201912-5 魔数 csp
 *
 * @author WuYi
 */

public class Moshu {
    private static final long MOD1 = 2009731336725594113l;
    private static final long MOD2 = 2019;

    static int n, q;
    static long[] A;
    static long[][] data;

    static final long[] U = {314882150829468584l, 427197303358170108l,
            1022292690726729920l, 1698479428772363217l, 2006101093849356424l};

    static long[][] mod = new long[5][2020];
    static final long[] U_MOD_2019 = {32l, 1713l, 1583l, 1029l, 1420l};

    static {
        for (int i = 0; i < 5; i++) {
            mod[i][1] = U[i];

        }
        for (int i = 0; i < 5; i++) {
            for (int j = 2; j < 2020; j++) {
                mod[i][j] = (mod[i][j - 1] + U[i]) % MOD1;
                if (mod[i][j] <= 0) {
                    System.out.println("有问题！！！！");
                }
            }
        }
    }

    public static void init() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] lin = bf.readLine().split(" ");
        n = Integer.parseInt(lin[0]);
        q = Integer.parseInt(lin[1]);

        data = new long[q][2];
        A = new long[n + 1];

        for (int i = 0; i < q; i++) {
            lin = bf.readLine().split(" ");
            data[i][0] = Integer.parseInt(lin[0]);
            data[i][1] = Integer.parseInt(lin[1]);
        }

        for (int i = 1; i <= n; i++) {
            A[i] = i;
        }

    }

    public static void main(String[] args) throws Exception {
        init();
        calculate();
    }

    public static void calculate() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            int l = (int) data[(int) i][0];
            int r = (int) data[(int) i][1];
            long s = 0;
            //计算s并打印结果
            for (int j = l; j <= r; j++) {
                if (A[j] <= 0) {
                    System.out.println("警告：" + fx(A[(int) j]) + ",A[j]:" + A[(int) j] + ",此时l:" + l + ",r:" + r);
                }
                s += A[j];
            }
            sb.append(s + "\r\n");//保存结果
            int t = (int) (s % 5);

            //更新从l到r
            for (int j = l; j <= r; j++) {
                long temp = A[j];
                A[j] = mod[t][(int) A[j]] % 2019;
                if (A[j] <= 0) {
                    System.out.println("第" + (i + 1) + "次操作" + ",此时l:" + l + ",r:" + r + ",A[" + j + "]:" + A[j] + ",更新前：A[" + j + "]=" + temp + ",mod[" + t + "][" + temp + "]=" + mod[t][(int) temp]);
                }

            }

        }
        System.out.println("ans:" + sb);
    }

    public static long fx(long x) {
        return x % 2019l;
    }

//    public static void update(long t, long[] A, long l, long r) {
////        System.out.println("t=" + t + "A=" + Arrays.toString(A) + ",l=" + l + ",r=" + r);
//        for (long i = l; i < r + 1; i++) {
//            A[(int) i] = A[(int) i] * U_MOD_2019[(int) t] % 2019;
//            if (A[(int) i] < 0) {
//                System.out.println("奇怪了：" + A[(int) i]);
//            }
//        }
//
//    }
}


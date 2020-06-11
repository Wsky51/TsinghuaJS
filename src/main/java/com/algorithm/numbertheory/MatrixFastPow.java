package com.algorithm.numbertheory;

import java.util.Arrays;

/**
 * Created on 2020/4/7
 * 矩阵快速幂
 *
 * @author WuYi
 */
public class MatrixFastPow {
    public static void main(String[] args) {
        MatrixFastPow demo = new MatrixFastPow();
        int[][] a = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        int[][] b = {{1, 2, 1}, {1, 1, 2}, {2, 1, 1}};
        System.out.println(Arrays.deepToString(demo.mul(demo.mul(a, a), a)));
        System.out.println(Arrays.deepToString(demo.powMod(a, 3, Integer.MAX_VALUE)));
        System.out.println(Arrays.deepToString(demo.powMod(a, 3, 2)));
    }

    //矩阵乘法,a : m*n, b:n*l
    public int[][] mul(int[][] a, int[][] b) {
        int m = a.length;
        if (m == 0) {
            return null;
        }
        int n = a[0].length;

        int l = b[0].length;

        int[][] c = new int[m][l];
        for (int i = 0; i < m; i++) {
            for (int k = 0; k < n; k++) {
                for (int j = 0; j < l; j++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }

    //矩阵乘法取模,a : m*n, b:n*l
    public int[][] mulMod(int[][] a, int[][] b, int mod) {
        int m = a.length;
        if (m == 0) {
            return null;
        }
        int n = a[0].length;

        int l = b[0].length;

        int[][] c = new int[m][l];
        for (int i = 0; i < m; i++) {
            for (int k = 0; k < n; k++) {
                for (int j = 0; j < l; j++) {
                    c[i][j] = (c[i][j] + a[i][k] * b[k][j] % mod) % mod;
                }
            }
        }
        return c;
    }

    //矩阵快速幂并取模
    public int[][] powMod(int[][] a, int pow, int mod) {
        int res[][] = new int[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            res[i] = Arrays.copyOf(a[i], a[i].length);
        }
        for (pow--; pow != 0; a = mulMod(a, a, mod), pow >>= 1)
            if ((pow & 1) == 1) {
                res = mulMod(res, a, mod);
            }
        return res;
    }

}

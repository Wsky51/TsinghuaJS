package com.algorithm.dynamic;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created on 2020/3/8
 *
 * @author WuYi
 */
public class SquaresNum {
    public static void main(String[] args) {
        System.out.println(numSquares(4));
    }

    public static int numSquares(int n) {
        if (n == 1) {
            return 1;
        }
        int[] res = new int[n + 1];
        for (int i = 1; i * i <= n; i++) {
            res[i * i] = 1;
        }
        if (res[n] != 0) {
            return res[n];
        }

        res[1] = 1;
        res[2] = 2;
        for (int i = 3; i < n + 1; i++) {
            if (res[i] != 0) {
                continue;
            }
            int temp = Integer.MAX_VALUE;
            for (int j = 1; j < i; j++) {
                if (res[j] + res[i - j] < temp) {
                    temp = res[j] + res[i - j];
                }
            }
            res[i] = temp;

        }

        System.out.println(Arrays.toString(res));
        return res[n];

    }
}

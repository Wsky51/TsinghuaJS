package com.algorithm.dynamic;

/**
 * Created on 2020/3/8
 * 爬楼梯 ，Leetcode动态规划问题
 *
 * @author WuYi
 */
public class ClimbingStairs {
    public static void main(String[] args) {
        int n = 10;
        int[] res = new int[n + 1];
        climb(res, n);
        System.out.println(res[n]);
    }

    static void climb(int[] res, int n) {
        res[1] = 1;
        res[2] = 2;
        for (int i = 3; i < n + 1; i++) {
            res[i] = res[i - 1] + res[i - 2];
        }
    }

    public int climbStairs(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int[] res = new int[n + 1];

        res[1] = 1;
        res[2] = 2;
        for (int i = 3; i < n + 1; i++) {
            res[i] = res[i - 1] + res[i - 2];
        }
        return res[n];
    }
}

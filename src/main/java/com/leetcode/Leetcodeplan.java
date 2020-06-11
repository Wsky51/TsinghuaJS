package com.leetcode;

import java.util.Arrays;

/**
 * Created on 2020/4/25
 * https://leetcode-cn.com/contest/season/2020-spring/problems/xiao-zhang-shua-ti-ji-hua/
 *
 * @author WuYi
 */
public class Leetcodeplan {
    public static void main(String[] args) {
        Leetcodeplan demo = new Leetcodeplan();
        int i = demo.minTime(new int[]{1, 2, 3,3}, 3);
        System.out.println("res:" + i);

    }

    public int minTime(int[] time, int m) {
        int len = time.length;
        if (m >= len || len == 1) {
            return 0;
        }

        int[] sum = new int[time.length];
        sum[0] = time[0];
        for (int i = 1; i < len; i++) {
            sum[i] = sum[i - 1] + time[i];
        }

        int[][] dp = new int[len + 1][m + 1];
        dp[0][1] = 0;
        dp[1][1] = 0;
        dp[2][1] = Math.min(time[0], time[1]);
        int max = Math.max(time[0], time[1]);
        for (int i = 3; i < len + 1; i++) {
            max = Math.max(max, time[i - 1]);
            dp[i][1] = sum[i - 1] - max;
        }

        Arrays.fill(dp[0], 0);//处理第一行的数据

        for (int i = 0; i < len + 1; i++) {
            dp[i][0] = Integer.MAX_VALUE;
        }

        int sub = 0;
        for (int i = 1; i < len + 1; i++) {
            for (int j = 2; j < m + 1; j++) {
                if (j >= i) {
                    dp[i][j] = 0;
                } else {
                    int temp = Integer.MAX_VALUE;
                    int max1 = time[i - 1];
                    for (int k = i; k > 1; k--) {
                        max1 = Math.max(max1, time[k - 1]);
                        if (temp > (sub = Math.max(dp[k - 1][j - 1], sum[i - 1] - sum[k - 2] - max1))) {
                            temp = sub;
                        }
//                        System.out.println("k:" + k + ",max1:" + max1 + ",dp:" + dp[k - 1][j - 1]);
                    }
                    dp[i][j] = temp;
//                    System.out.println("-----------");
                }
            }
        }

//        for (int i = 0; i < dp.length; i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }

        return dp[len][m];

    }
}

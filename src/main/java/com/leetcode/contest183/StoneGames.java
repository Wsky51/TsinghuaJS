package com.leetcode.contest183;

import java.util.Arrays;

/**
 * Created on 2020/4/5
 * 用一个数组 dp 来表示“在只剩下第 i 堆到最后一堆石子时，当前玩家最多能拿多少分”。
 * 假如算出了这个 dp 数组，那么最终答案就是判断 dp[0] 和（分数总和-dp[0]）之间的大小关系即可。
 *
 * @author WuYi
 */
public class StoneGames {
    public static void main(String[] args) {
        String s = stoneGameIII(new int[]{-1,-2,-3});
        System.out.println(s);
    }

    static String stoneGameIII(int[] stoneValue) {
        int len = stoneValue.length;
        int[] dp = new int[len + 2];

        dp[len - 1] = stoneValue[len - 1];
        dp[len] = 0;
        dp[len + 1] = 0;

        int sum = stoneValue[len - 1];

        for (int i = len - 2; i >= 0; i--) {
            sum += stoneValue[i];
            int min = Integer.MAX_VALUE;
            for (int j = 1; j <= 3; j++) {
                min = Math.min(min, dp[i + j]);
            }
            dp[i] = sum - min;
        }

//        System.out.println("dp:"+ Arrays.toString(dp));
//        System.out.println("Alice得分："+dp[0]);
//        System.out.println("Bob得分："+(sum-dp[0]));

        if (dp[0]>(sum-dp[0])){
            return "Alice";
        }else if (dp[0]<(sum-dp[0])){
            return "Bob";
        }else {
            return "Tie";
        }
    }
}

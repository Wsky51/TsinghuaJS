package com.leetcode.onetaskperday;

import java.util.Arrays;

/**
 * Created on 2020/4/6
 * https://leetcode-cn.com/problems/edit-distance/ dp 动态规划
 *
 * @author WuYi
 */
public class TypeDistance {
    public static void main(String[] args) {
//        System.out.println(minDistance("intention","execution"));
        System.out.println(minDistance("horse", "rse"));
    }

    public static int minDistance(String word1, String word2) {
//        if (word1.length() == 0) {
//            return word2.length();
//        }
//        if (word2.length() == 0) {
//            return word1.length();
//        }
//        //dp[i][j] 代表 word1 到 i 位置转换成 word2 到 j 位置需要最少步数
//        int[][] dp = new int[word1.length()][word2.length()];
//        dp[0][0] = word1.charAt(0) == word2.charAt(0) ? 0 : 1;
//
//        for (int i = 0; i < word1.length(); i++) {
//            for (int j = 0; j < word2.length(); j++) {
//                if (i == 0 && j == 0) {
//                    continue;
//                }
//                if (i == 0) {
//                    dp[0][j] = dp[0][j - 1] + 1;
//                    continue;
//                }
//                if (j == 0) {
//                    dp[i][0] = dp[i - 1][0] + 1;
//                    continue;
//                }
//                if (word1.charAt(i) == word2.charAt(j)) {
//                    dp[i][j] = dp[i - 1][j - 1];
//                    continue;
//                } else {
//                    dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
//                    continue;
//                }
//
//            }
//        }
//        System.out.println(Arrays.deepToString(dp));
//        return dp[word1.length() - 1][word2.length() - 1];

        int n1 = word1.length();
        int n2 = word2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];
        // 第一行
        for (int j = 1; j <= n2; j++) dp[0][j] = dp[0][j - 1] + 1;
        // 第一列
        for (int i = 1; i <= n1; i++) dp[i][0] = dp[i - 1][0] + 1;

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];
                else dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
            }
        }
        return dp[n1][n2];

    }

}

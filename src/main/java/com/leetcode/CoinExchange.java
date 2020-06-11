package com.leetcode;

import java.util.Arrays;

/**
 * Created on 2020/3/14
 * https://leetcode-cn.com/problems/coin-change/
 * <p>
 * 题目描述：
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 示例 1:
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 * </p>
 *
 * @author WuYi
 */
public class CoinExchange {
    public static void main(String[] args) {
        int[] coins = {1,2,5};
        System.out.println(coinChange(coins, 11));
    }

    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];//定义结果,dp[i]=k表示amount=i时，求得的结果是k
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i < amount + 1; i++) {
            for (int coin : coins) {
                if (i < coin) {//如果当前coin值大于amount,说明这个硬币不可能会被选择到，跳过
                    continue;
                } else {//否则，这枚硬币可能会被选择到
                    if (dp[i] < 0 || dp[i - coin] + 1 < 0) {
                        continue;
                    }
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        System.out.println("DP:" + Arrays.toString(dp));

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
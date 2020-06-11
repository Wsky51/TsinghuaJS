package com.leetcode;

import java.util.Arrays;

/**
 * Created on 2020/3/13
 * https://leetcode-cn.com/problems/coin-change-2/description/?utm_source=LCUS&utm_medium=ip_redirect_q_uns&utm_campaign=transfer2china
 * <p>
 * 零钱交换：
 * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个
 * 示例 1:
 * 输入: amount = 5, coins = [1, 2, 5]
 * 输出: 4
 * 解释: 有四种方式可以凑成总金额:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * </p>
 *
 * @author WuYi
 */
public class CoinExchange2 {

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int res = change2(5, coins);
        System.out.println("res:" + res);

    }

    public static int change(int amount, int[] coins) {
        int[][] dp = new int[amount + 1][coins.length + 1];//dp[i][j]表示coins数组的前0-i个硬币凑成j元可能的结果
        dp[0][0] = 1;
        for (int i = 0; i < coins.length + 1; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < amount + 1; i++) {
            for (int j = 1; j < coins.length + 1; j++) {
                if (i < coins[j - 1]) {
                    dp[i][j] = dp[i][j - 1];//如果第j个硬币的钱数都大于i,那第j个硬币不可能选到
                } else {
                    dp[i][j] = dp[i][j - 1] + dp[i - coins[j - 1]][j];//否则，分两种情况，第一种，不选第j个硬币，第二种，选第j个硬币
                }
            }
        }

        for (int i = 0; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));

        }
        return dp[amount][coins.length];
    }

    //这个方法优化了空间复杂度，如何用一维数组来代表当前的二维数组呢，即dp[i]代表了dp[i-1][j]和dp[i-coins[j]]代替dp[i][j-coins[j]]，
    // 仔细想一下，如果从i=coin开始遍历到amount+1，那如果运行到了dp[i]还没更新，dp[i]=dp[i] + dp[i-coin]中右边的dp[i]其实是上一轮的数据，
    // dp[i-coin]其实是当前轮次之前更新过的数据。
    public static int change2(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        //子问题是:对于硬币从0到k,我们必须使用第k个硬币的时候，当前金额的组合数
        for (int coin : coins) {
            for (int i = 1; i < amount + 1; i++) {
                if (i < coin) {
                    continue;
                }
                dp[i] += dp[i - coin];
            }
            System.out.println("coin=" + coin + "时，此时dp更新为：" + Arrays.toString(dp));
        }
        return dp[amount];
    }
}






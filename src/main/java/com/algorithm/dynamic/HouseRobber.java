package com.algorithm.dynamic;

/**
 * Created on 2020/3/8
 * 题目出自leetcode,打家劫舍，
 * 题目描述：你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
 * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，
 * 系统会自动报警。给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 * Example:Input: [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 *
 * @author WuYi
 */
public class HouseRobber {
    public static void main(String[] args) {
        int[] input = {2,3,2};
        int robber = robber(input);
        System.out.println(robber);
    }

    public static int robber(int[] nums) {
        //典型的动态规划问题，dp[i]=max(dp[i-1],dp[i-2]+nums[i])
        //每个阶段确定一家偷还是不偷，所以决策就是偷和不偷两种
        if(nums==null || nums.length==0)
            return 0;
        int len=nums.length;
        int[] res=new int[len+1];
        res[0]=0;
        res[1]=nums[0];
        for(int i=2;i<=len;i++){
            res[i]=Math.max(res[i-1],res[i-2]+nums[i-1]); //状态转移
        }
        return res[len];

    }
}

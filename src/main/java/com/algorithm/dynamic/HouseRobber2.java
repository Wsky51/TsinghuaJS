package com.algorithm.dynamic;

import java.util.Arrays;

/**
 * Created on 2020/3/8
 *  题目出自leetcode,打家劫舍2，在打家劫舍的基础上变形
 *  题意：你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。
 *  同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * @author WuYi
 */
public class HouseRobber2 {
    public static void main(String[] args) {
        int[] input = {1,2,3,1};
        int a = robber(input,0,input.length-1);
        int b=robber(input,1,input.length);
        System.out.println(Math.max(a,b));

    }

    public static int robber(int[] nums,int from,int to) {
        //典型的动态规划问题，dp[i]=max(dp[i-1],dp[i-2]+nums[i])
        //每个阶段确定一家偷还是不偷，所以决策就是偷和不偷两种
        if(nums==null || nums.length==0)
            return 0;
        int len=nums.length;
        int[] res=new int[len+1];
        res[0]=0;
        res[from+1]=nums[from];
        for(int i=from+2;i<=to;i++){
            res[i]=Math.max(res[i-1],res[i-2]+nums[i-1]); //状态转移
        }
        System.out.println("res:"+ Arrays.toString(res));
        return res[to];

    }
}

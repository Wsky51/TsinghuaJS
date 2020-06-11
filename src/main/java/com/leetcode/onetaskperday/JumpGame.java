package com.leetcode.onetaskperday;

/**
 * Created on 2020/4/17
 * 输入: [2,3,1,1,4]
 * 输出: true
 * [2,4,3,4,8]
 * <p>
 * 输入: [3,2,1,0,4]
 * 输出: false
 * [3,3,3,3,-1]
 *
 * @author WuYi
 */
public class JumpGame {
    public static void main(String[] args) {
        JumpGame demo = new JumpGame();
        System.out.println(canJump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(canJump(new int[]{3, 2, 1, 0, 4}));
        System.out.println(canJump(new int[]{1,2,3}));

    }

    public static boolean canJump(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return true;
        }
        int max = nums[0];

        for (int i = 0; i <= max && i < len - 1; i++) {
            max=Math.max(max,nums[i]+i);
            if (max>=(len-1)){
                return true;
            }
        }
        return false;

    }

}

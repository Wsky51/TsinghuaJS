package com.test;

import com.sun.security.jgss.GSSUtil;

import java.util.*;

/**
 * Created on 2020/3/3
 *
 * @author WuYi
 */
public class Test {
//"testdfat"
    public static final int mod = 1000000007;
    String str="testdfat";

    public static void main(String[] args) {
        Test demo=new Test();
        HashMap<String,String> map=new HashMap();
        int val = demo.numSubseq(new int[]{6,10,12,3,29,21,12,25,

                17,19,16,1,2,24,9,17,25,22,12,22,26,24,24,11,
                3,7,24,5,15,30,23,5,20,10,19,20,9,27,11,4,23,4,4,12
                ,22,27,16,11,26,10,23,26,16,21,24,21,17,13,21,9,16,17,27},
        26);
        System.out.println("val"+val);

//        System.out.println(Integer.toBinaryString(65535));
    }


    //输入：nums = [3,5,6,7], target = 9
    //输出：4
    public int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);
        int[] dp = new int[nums.length];
        dp[0] = nums[0] * 2 <= target ? 1 : 0;
        if (dp[0] == 0) {
            return 0;
        }
        for (int i = 1; i < nums.length; i++) {
            //找出满足nums[j]+nums[i]<=target的最小的下标
            int j = i;
            for (; j >= 0; j--) {
                if (nums[i] + nums[j] > target) {
                    continue;
                } else {
                    ;
                }
            }
            dp[i] = ((dp[i - 1] % mod) + pow2AddMod(j, i, mod) % mod) % mod;
        }
        return dp[dp.length - 1];
    }

    public static int pow2AddMod(int lo, int hi, int m) {
        if (lo==-1){
            return 0;

        }
        if (lo == hi) {
            return (int)(powMod(2, hi, mod)%mod);
        }


        int count = lo + 1;
        long a0=powMod(2,hi,mod)%mod;
        long a1=powMod(2,hi-lo-1,mod)%mod;
        System.out.println("a0:"+a0+",a1:"+a1);
        return (int)((a0+mod-a1)%mod);

    }


    public static int powMod(long a, int n, int m) {
        long res = 1;
        while (n != 0) {
            if ((n & 1) == 1) {
                res = (res%m * a % m)%m;
            }
            n /= 2;
            a = (a%m * a % m)%m;
        }
        return (int)(res%m);
    }



}
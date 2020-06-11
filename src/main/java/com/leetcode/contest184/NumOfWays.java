package com.leetcode.contest184;

/**
 * Created on 2020/4/12
 *
 * @author WuYi
 */
public class NumOfWays {

    public static void main(String[] args) {
        NumOfWays demo=new NumOfWays();
        System.out.println(demo.numOfWays(5000));

//        System.out.println(((-9)%5));
    }

    public int numOfWays(int n) {
        if (n==1){
            return 12;
        }
        int mod=1000000007;
        long[][] dp=new long[n+1][3];
        dp[1][1]=6;
        dp[1][2]=6;
        for (int i =2; i <=n; i++) {
            dp[i][1]=((dp[i-1][1]%mod)*2+(dp[i-1][2]%mod)*2)%mod;
            dp[i][2]=((dp[i-1][1])%mod*2+(dp[i-1][2])%mod*3)%mod;
            if (dp[n-1][1]*4+dp[n-1][2]*5<0){
                System.out.println("有问题dp["+i+"][1]:"+dp[i][1]+",dp["+i+"][2]:"+dp[i][2]);
            }
        }
        return (int)((dp[n-1][1]*4%mod+dp[n-1][2]*5%mod)%mod);
    }
}

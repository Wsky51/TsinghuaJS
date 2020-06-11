package com.leetcode.contest185;

/**
 * Created on 2020/4/18
 * 输入：s = "1317", k = 2000
 * 输出：8
 * 解释：可行的数组方案为 [1317]，[131,7]，[13,17]，[1,317]，[13,1,7]，[1,31,7]，[1,3,17]，[1,3,1,7]
 *
 * @author WuYi
 */
public class StrCombin {

    public static void main(String[] args) {
        String s1 = "455";
        String s2 = "322";
        System.out.println(s1.compareTo(s2));
    }

    public int numberOfArrays(String s, int k) {
        int [] dp=new int[s.length()];
        int i;
        for (i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                continue;
            } else {
                int val = Integer.parseInt(s.substring(i, s.length()));
                if (val > k) {
                    return 0;
                } else if (val == k) {
                    return 1;
                } else {
                    dp[i]=1;
                    break;
                }
            }
        }




        return 0;
    }
}

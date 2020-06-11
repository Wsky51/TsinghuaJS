package com.algorithm.numbertheory;

import java.util.Arrays;

/**
 * Created on 2020/4/5
 * 组合数定理,求C(n,k)
 * @author WuYi
 */
public class CombinatoriaNnumber {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(calculate(6)));
    }

    static int[] calculate(int n){
        int[] C=new int[n+1];
        C[0]=1;//C(n,0)=1;
        for (int i = 1; i <=n; i++) {
            C[i]=C[i-1]*(n-i+1)/i;
        }
        return C;
    }
}

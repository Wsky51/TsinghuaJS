package com.algorithm.dynamic;

import java.util.Arrays;

/**
 * Created on 2020/3/8
 *
 * @author WuYi
 */
public class IntegerBreak {

    public static void main(String[] args) {
        System.out.println(integerBreak(10));
    }

    public static int integerBreak(int n) {
        int []res=new int[n+1];
        if (n==2){
            return 1;
        }
        res[1]=1;
        res[2]=1;
        int c=0;
        for (int i = 3; i <n+1 ; i++) {
            int temp=0;
            for (int j = 1; j < i ; j++) {

                if ((c=Math.max(res[i-j]*j,(i-j)*j))>temp){
                    temp=c;

                }
            }
            res[i]=temp;
        }
        System.out.println(Arrays.toString(res));
        return res[n];
    }
}

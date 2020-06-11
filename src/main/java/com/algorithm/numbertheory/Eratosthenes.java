package com.algorithm.numbertheory;

import java.util.Arrays;

/**
 * Created on 2020/4/2
 * Eratosthenes筛选，求一定范围内的素数
 * @author WuYi
 */
public class Eratosthenes {
    public static void main(String[] args) {
        int x=100;
        boolean[] visit=new boolean[x+1];
        erat(x,visit);
        for (int i = 2; i <100 ; i++) {
            if (!visit[i]){
                System.out.println(i);//输出该素数
            }
        }
    }

    /**
     * 求1-n内的所有素数，visit[i]为true，则说明i不是素数
     * @param n
     * @param visit
     */
    public static void erat(int n,boolean[] visit){
        int m = (int)Math.sqrt(n+0.5);
        Arrays.fill(visit,false);
        for (int i = 2; i <=m; i++) {
            if (!visit[i]){
                for (int j = i*i; j <=n ; j+=i) {
                    visit[j]=true;
                }
            }
        }
    }
}

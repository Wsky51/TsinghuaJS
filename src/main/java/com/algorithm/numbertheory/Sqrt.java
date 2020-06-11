package com.algorithm.numbertheory;

/**
 * Created on 2020/4/22
 * 计算一个数的开方
 * @author WuYi
 */
public class Sqrt {

    public static void main(String[] args) {
        System.out.println(sqrt(16,0.01));//尝试：5.478515625,误差为：0.014133453369140625，一共计算了15次
        System.out.println(sqrtNewton(16,0.01));//一共仅计算了5次

    }

    /**
     * 传统朴素二分法
     * @param n 要开方的数
     * @param e 误差范围
     * @return
     */
    public static double sqrt(double n,double e){
        double e0=Integer.MAX_VALUE;
        double lo=0;
        double hi=n;
        double m=n;
        double temp;
        while (e0>e){
            m=(lo+hi)/2;
            if ((temp=m*m)>n){
                hi=m;
                e0=temp-n;
            }else {
                lo=m;
                e0=n-temp;
            }
            System.out.println("尝试："+m+",误差为："+e0);
        }
        return m;
    }

    /**
     * 牛顿迭代法
     * @param n 待开方的数
     * @param e
     * @return
     */
    public static double sqrtNewton(double n,double e){
        double guess=n/2;
        double e0=n;
        while (e0>e){
            guess=(guess+n/guess)/2;
            e0=guess*guess-n;
            System.out.println("尝试："+guess+",误差："+e0);
        }
        return guess;
    }

}

package com.leetcode.doubleweek23;

/**
 * Created on 2020/4/4
 *
 * @author WuYi
 */
public class NumberCount {
    static int[] val = new int[10];

    public static void main(String[] args) {
        NumberCount demo = new NumberCount();
        System.out.println( demo.countLargestGroup(15));

    }

    void init(int len){
        val=new int[len];
    }

    int countLargestGroup(int n) {
        int len = (n + "").length() * 10;
        init(len);

        for (int i = 1; i <= n; i++) {
            calculate(i);
        }
        int maxCount = 0;
        int time=0;
        for (int i = 1; i <= 9; i++) {
            if (val[i] > maxCount) {
                maxCount = val[i];
                time=1;
            }else if (val[i]==maxCount){
                time++;
            }
        }

        return time;
    }

    void calculate(int x) {
        int sum = 0;
        while (x != 0) {
            sum += x % 10;
            x /= 10;
        }
        val[sum]++;
    }
}

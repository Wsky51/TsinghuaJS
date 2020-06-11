package com.leetcode.onetaskperday;

/**
 * Created on 2020/4/7
 *
 * @author WuYi
 */
public class CollectRain {
    public static void main(String[] args) {
        trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
    }

    public static int trap(int[] height) {
        int n = height.length;
        int[] remain = new int[n];
        int begin = 0, end = n - 1;

        for (int i = 0; i < n; i++) {
            if (height[i] >= height[begin]) {
                begin = i;
                continue;
            } else {
                break;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            if (height[i] >= height[end]) {
                end = i;
                continue;
            } else {
                break;
            }
        }

//        int sumPos = begin;
        int sum = 0;
        while (begin < end) {
            for (int i = begin + 1; i < end; i++) {
                if (height[i] <= height[begin]) {//比begin更低或者一样高
                    remain[i] = height[begin] - height[i];
                } else {//出现更高的
                    for (int j = begin; j < i; j++) {//把这段结果加起来
                        sum += remain[j];
                    }
                    for (int k = i; i < n; i++) {//滑动向更高处
                        if (height[k] >= height[begin]) {
                            begin = k;
                            continue;
                        } else {
                            break;
                        }
                    }
                }

            }
        }

        System.out.println("begin:" + begin + ",end:" + end);
        return -1;
    }

}

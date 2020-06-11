package com.algorithm.dynamic;

import java.util.Arrays;

/**
 * Created on 2020/3/9
 * leetcode 91题，解码方法
 * @author WuYi
 */
public class NumDecodings {
    public static void main(String[] args) {
        int i = numDecoding("30");
        System.out.println("i=" + i);
//        System.out.println(Integer.parseInt("100".substring(1,3)));
    }

    public static int numDecoding(String s) {
        /**
         * 避免开头是0
         */
        if (s.charAt(0) == '0') {
            return 0;
        }
        int length = s.length();
        if (length == 1) {
            return length;
        }
        int[] res = new int[length + 1];
        res[1] = 1;

        /**
         * 解决节点2的情况
         */
        int firstTwo = Integer.parseInt(s.substring(0, 2));
        if (firstTwo == 10 || firstTwo == 20) {
            res[2] = 1;
        } else if (firstTwo < 27) {
            res[2] = 2;
        } else if (firstTwo % 10 == 0) {
            res[2] = 0;
        }else {
            res[2]=1;
        }

        if (length == 2) {
            return res[length];
        }

        for (int i = 3; i < length + 1; i++) {
            char i0 = s.charAt(i - 1);
            char i1 = s.charAt(i - 2);
            char i2 = s.charAt(i - 3);
            if (i1 == '0') {
                if (i2 == '0' || i0 == '0') {
                    return 0;
                } else if (i2 > '2') {
                    return 0;
                } else {
                    res[i] = i == 3 ? 1 : res[i - 3];
                    continue;
                }
            } else {
                int lastTwo = Integer.parseInt(s.substring(i - 2, i));
                if (lastTwo == 10 || lastTwo == 20) {
                    res[i] = res[i - 2];
                } else if (lastTwo < 27) {
                    res[i] = res[i - 2] + res[i - 1];
                } else if (lastTwo % 10 == 0) {
                    return 0;
                } else {
                    res[i] = res[i - 1];
                }
            }

        }
        System.out.println(Arrays.toString(res));
        return res[length];

    }
}

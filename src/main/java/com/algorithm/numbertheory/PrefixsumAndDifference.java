package com.algorithm.numbertheory;

import java.util.Arrays;

/**
 * Created on 2020/4/7
 * 前缀和和差分
 *
 * @author WuYi
 */
public class PrefixsumAndDifference {
    public static void main(String[] args) {
        PrefixsumAndDifference demo = new PrefixsumAndDifference();
        int[] data = {2, 6, 8, 4, 3};
        int[] res = demo.diffDiviAdd(data, 1, 3, 5);
        System.out.println(Arrays.toString(res));
    }

    //现有一个长度为n个数字序列，计算机要连续询问m次，每次问[l,r]区间内所有数之和是多少，时间复杂度为O(m)
    //返回前缀和数组
    public int[] prefixSum(int[] data) {
        int[] sum = new int[data.length];
        sum[0] = data[0];
        for (int i = 1; i < data.length; i++) {
            sum[i] = sum[i - 1] + data[i];
        }
        return sum;
    }

    //需保证l,r不越界且l<r
    public int calPrefixSum(int[] data, int l, int r) {
        int[] ints = prefixSum(data);
        if (l <= 0) {
            return ints[r];
        }
        return ints[r] - ints[l - 1];
    }

    //差分,现有长度为n的数字序列，现需要对其进行m次区间修改（把这个区间所有的数加一个数或者减去一个数），现在需要输出
    //在m次此操作后，得到的序列是什么,且时间复杂度为O(m),返回差分数组
    public int[] differenceDivide(int[] data) {
        int[] sub = new int[data.length];
        sub[0] = data[0];
        for (int i = 1; i < data.length; i++) {
            sub[i] = data[i] - data[i - 1];
        }
        return sub;
    }

    public int[] diffDiviAdd(int[] data, int l, int r, int val) {
        int[] sub = differenceDivide(data);
        sub[l] += val;
        sub[r + 1] -= val;

        int[] res = new int[data.length];
        res[0] = sub[0];
        for (int i = 1; i < data.length; i++) {
            res[i] = res[i - 1] + sub[i];
        }
        return res;

    }

}

package com.leetcode.onetaskperday;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created on 2020/4/24
 *
 * @author WuYi
 */
public class ReversePair {

    public static void main(String[] args) {
        int[] data = new int[]{7,5,6,4};
        int res=mergeSort(data, 0, data.length);
        System.out.println("datA:" + Arrays.toString(data));
        System.out.println("res:"+res);

    }


    public static int mergeSort(int[] data, int lo, int hi) {
        int count = 0;
        if (hi - lo <= 1) {
            return 0;
        }
        int mi = (lo + hi) / 2;
        count += mergeSort(data, lo, mi);
        count += mergeSort(data, mi, hi);
        int i = lo, j = mi;
        int[] temp = new int[hi - lo];
        int index = 0;
        for (; i < mi && j < hi; ) {
            if (data[i] <= data[j]) {
                temp[index] = data[i++];
                count+=(j-mi);
            } else {
                temp[index] = data[j++];
            }
            index++;
        }
        while (i < mi) {
            count += j - mi;
            temp[index++] = data[i++];
        }
        while (j < hi) {
            temp[index++] = data[j++];
        }
        System.arraycopy(temp, 0, data, lo, temp.length);
        return count;
    }
}

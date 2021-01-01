package com.leetcode.shuati;

import java.util.Arrays;
import java.util.Comparator;

public class Q435 {
    public static void main(String[] args) {
        Q435 demo=new Q435();


        Integer [] data=new Integer[]{4,33,62,25,42,9,12};
        Arrays.sort(data,new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        });
    }
//
//    public int eraseOverlapIntervals(int[][] intervals) {
//
//    }
}

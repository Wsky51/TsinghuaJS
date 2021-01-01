package com.leetcode.shuati;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Q4 {
    public static void main(String[] args) {
        Q4 demo=new Q4();
        double res = demo.findMedianSortedArrays(new int[]{1,3}, new int[]{2,7});
        System.out.println(res);
    }


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length==nums2.length){
            if (nums1.length%2==0){
                int lo=nums1.length/2-1;
                int hi=nums1.length/2;
                return ((double)(nums1[lo]+nums1[hi]+nums2[lo]+nums2[hi]))/4;

            }else {
                return ((double)(nums1[nums1.length/2]+nums2[nums1.length/2]))/2;
            }
        }
        int[] data=new int[nums1.length+nums2.length];
        int lo1=0;
        int lo2=0;
        int idx=0;
        while (lo1<nums1.length&&lo2<nums2.length){
            if (nums1[lo1]<nums2[lo2]){
                data[idx]=nums1[lo1];
                lo1++;
            }else {
                data[idx]=nums2[lo2];
                lo2++;
            }
            idx++;
        }
        while(lo1<nums1.length){
            data[idx]=nums1[lo1];
            lo1++;
            idx++;
        }
        while(lo2<nums2.length){
            data[idx]=nums2[lo2];
            lo2++;
            idx++;
        }
        if (data.length%2==0){
            return ((double)(data[data.length/2-1]+data[data.length/2])/2);
        }else {
            return (double)(data[data.length/2]);
        }
    }
}

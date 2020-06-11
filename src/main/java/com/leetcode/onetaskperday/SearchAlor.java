package com.leetcode.onetaskperday;

/**
 * Created on 2020/4/27
 *
 * @author WuYi
 */
public class SearchAlor {

    public static void main(String[] args) {
        int[] data={4,5,6,7,0,1,2};
        for (int i = 0; i < data.length; i++) {
            System.out.println(search(data,data[i]));
        }
        System.out.println("--------");
        System.out.println(search(data,100));
    }

    public static int search(int[] nums, int target) {
        int len = nums.length;
        int lo = 0, hi = len, mi = 0;
        while (lo < hi) {
            mi = (lo + hi) / 2;
            if (nums[lo] <= nums[mi]) {//左侧有序
                if (nums[lo] <= target && target <= nums[mi]) {
                    return search(nums,lo,mi+1,target);
                }else {
                    lo=mi+1;
                }
            }else {//右侧有序
                if (nums[mi]<=target&&target<=nums[hi-1]){
                    return search(nums,mi,hi,target);
                }else {
                    hi=mi;
                }
            }
        }
        return -1;
    }

    public static int search(int[] data, int lo, int hi, int val) {
        int mi = 0;
        while (lo < hi) {
            mi = (lo + hi) / 2;
            if (val < data[mi]) {
                hi = mi;
            } else if (data[mi] < val) {
                lo = mi + 1;
            } else {
                return mi;
            }
        }
        System.out.println("data[" + lo + "]:" + data[lo]);
        return -1;
    }

}

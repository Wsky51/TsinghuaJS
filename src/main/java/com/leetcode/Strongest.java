package com.leetcode;

import java.util.Arrays;

public class Strongest {
    public static void main(String[] args) {
        Strongest demo=new Strongest();
        int[] strongest = demo.getStrongest(new int[]{1,1,3,5,5}, 5);
        System.out.println(Arrays.toString(strongest));
        char[] data =new char[10];
        Arrays.fill(data,'-');

    }
    public int[] getStrongest(int[] arr, int k) {
        Arrays.sort(arr);
        int m=arr[(arr.length-1)/2];
        int lo=0,hi=arr.length-1;//双指针
        int[] res=new int[k];
        for (int i = 0; i < k; i++) {
            if (Math.abs(arr[lo]-m)>Math.abs(arr[hi]-m)){
                res[i]= arr[lo++];
            }
            else if (Math.abs(arr[lo]-m)==Math.abs(arr[hi]-m)){
                res[i]=arr[hi--];
            }else {
                res[i]=arr[hi--];
            }
        }
        return res;

    }

}

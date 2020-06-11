package com.algorithm;

import java.util.Arrays;

/**
 * Created on 2020/4/16
 *
 * @author WuYi
 */
public class BinarySearch {

    public static void main(String[] args) {
        printTest();
    }

    public static void swap(int[] data,int i,int j){
        int temp=data[i];
        data[i]=data[j];
        data[j]=temp;
    }

    public static void printTest(){
        int[] data={3,5,5,5,5,5,5,5,7,8,8,8,10,14,16,20,22,24,26,29,31,32,36,39};
        System.out.println(binarySearchLower(data,0,data.length,0));
        System.out.println(binarySearchUpper(data,0,data.length,0));
        System.out.println(binarySearchLower(data,0,data.length,9));
        System.out.println(binarySearchUpper(data,0,data.length,9));
        System.out.println(binarySearchLower(data,0,data.length,15));
        System.out.println(binarySearchUpper(data,0,data.length,15));
        System.out.println(binarySearchLower(data,0,data.length,5));
        System.out.println(binarySearchUpper(data,0,data.length,5));

        System.out.println(binarySearchLower(data,0,data.length,8));
        System.out.println(binarySearchUpper(data,0,data.length,8));
    }

    /**
     * @param a  待查询的有序数组
     * @param lo 从lo到high
     * @param hi [lo,hi)
     * @param e  待查询的数据
     * @return 查找目标元素e, 返回不大于e且秩最大的元素
     */
    public static int binarySearchUpper(int[] a, int lo, int hi, int e) {
        //假定都满足边界条件
        while (lo < hi) {
            int mi = (lo + hi) >>> 1;
            if (e < a[mi]) {//经比较后确定深入[lo,mi)或(mi,hi)
                hi = mi;
            } else {
                lo = mi + 1;
            }
        }
        return --lo;//循环结束时，lo为大于e的元素的最小秩，故lo-1即不大于e的元素的最大秩
    }//有多个元素命中时，总能保证返回秩的最大者，查找失败时，能够返回失败的位置

    /**
     * @param a  待查询的有序数组
     * @param lo 从lo到high
     * @param hi [lo,hi)
     * @param e  待查询的数据
     * @return 查找目标元素e, 返回不大于e且秩最小的元素
     */
    public static int binarySearchLower(int[] a, int lo, int hi, int e) {
        while (lo < hi) {
            int mi = (lo + hi) >>> 1;
            if (e > a[mi]) {
                lo = mi + 1;
            } else {
                hi = mi;
            }
        }
        return lo;
    }

}

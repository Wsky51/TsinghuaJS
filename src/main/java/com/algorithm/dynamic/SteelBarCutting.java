package com.algorithm.dynamic;

/**
 * Created on 2020/3/8
 * 钢条切割问题，出自算法导论
 * @author WuYi
 */
public class SteelBarCutting {

    static int[] result = {0,0,
            0,0,0,0,0,0,0,0,0};
    public static void main(String[] args) {
        int[] arr = {0,1,5,8,9,10,17,17,20,24,30};//arr[i] 代表当切割的长度为i时，获得的收益为arr[i]
        System.out.println("自顶向下结果");
        for (int i = 0; i < arr.length; i++) {
            System.out.print("r"+ i +"=" + UpDown(i, arr)+"; ");
        }
        System.out.println();
        System.out.println("自底向上结果");
        for (int i = 0; i < arr.length; i++) {
            System.out.print("r"+ i +"=" + DownUp(i, arr)+"; ");
        }

    }

    /**
     *
     * @param num,num代表当前工人有的钢条的总长度，解决的问题是该如何切割钢条工人所得到的收益最大
     * @param arr
     * @return
     */
    static int UpDown(int num, int[] arr) {
        if(num == 0) return 0;
        if(result[num] != 0) return result[num];

        int temp = 0;
        for (int i = 1; i < num+1; i++) {
            int max = arr[i] + UpDown(num-i, arr);
            if(max > temp) {
                temp = max;
            }
        }
        result[num] = temp; //将计算的长度为n的钢条切割的长度用数组保存起来
        return temp;
    }

    static int DownUp(int num, int[] arr) {
        for (int i = 1; i < num + 1; i++) {
            int temp = 0;
            for (int j = 1; j <= i; j++) {
                int max = arr[j] + result[i - j];
                if(max > temp) {
                    temp = max;
                }
            }
            result[i] = temp;
        }
        return result[num];
    }




}

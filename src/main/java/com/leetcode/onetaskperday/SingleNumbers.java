package com.leetcode.onetaskperday;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created on 2020/4/28
 *
 * @author WuYi
 */
public class SingleNumbers {

    public static void main(String[] args) {
        int[] data={1,2,10,4,1,4,3,3};
//        int[] data={4,1,4,6,8,5,8,5};
        SingleNumbers demo=new SingleNumbers();
        int[] ints = demo.singleNumbers(data);
        System.out.println(Arrays.toString(ints));
        HashSet<Integer> set=new HashSet <>();

    }

    public int[] singleNumbers(int[] nums) {
        //用于将所有的数异或起来
        int k = 0;

        for(int num: nums) {
            k ^= num;
        }

        //获得k中最低位的1
        int mask = 1;

        while((k & mask) == 0) {
            mask <<= 1;
        }

        int a = 0;
        int b = 0;

        for(int num: nums) {

            if((num & mask) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }

        return new int[]{a, b};
    }

}

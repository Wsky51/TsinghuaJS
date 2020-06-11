package com.leetcode.contest183;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created on 2020/4/5
 *
 * @author WuYi
 */
public class SubQueue {

    public static void main(String[] args) {
        int[] ab={6};
        minSubsequeue(ab);
    }

    static List<Integer> minSubsequeue(int[] nums){
        int[] datas= Arrays.copyOf(nums,nums.length);
        Arrays.sort(datas);
        int sum=0;
        for (int i = 0; i < datas.length; i++) {
            sum+=datas[i];
        }
        List<Integer> list=new ArrayList <>();

        int temp=0;
        for (int i = datas.length-1; i >=0 ; i--) {
            if ((temp=(temp+datas[i]))>(sum-temp)){
                list.add(datas[i]);
                break;
            }
            list.add(datas[i]);
        }
        System.out.println(list);
        return list;
    }
}



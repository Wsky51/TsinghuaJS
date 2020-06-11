package com.leetcode.onetaskperday;

import java.util.*;

/**
 * Created on 2020/4/25
 * <p>
 * <p>
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 *
 * @author WuYi
 */
public class Permute {
    static List<List <Integer>> res = new LinkedList <>();

    public static void main(String[] args) {
        permute(new int[]{1,2,3});

//        List<Integer> list=new LinkedList <>();
////        list.add(4);
////        list.add(6);
////        list.add(9);
////        list.add(11);
////        List <Integer> temp=new LinkedList (list);
////        System.out.println("temp:"+temp);
////        temp.add(19);
////        System.out.println("temp:"+temp);
////        System.out.println("list："+list);
//        List<Integer> src=new LinkedList <>();
//        List<Integer> dst=new LinkedList <>();
//        src.add(2);
//        src.add(3);
//        dst.add(1);
//        dfs(src,dst);
    }

    public static List <List <Integer>> permute(int[] nums) {
        res.clear();
        dfs(nums,new LinkedList <>(),new boolean[nums.length]);
        return res;
    }

    public static void dfs(int[] nums,List <Integer> des,boolean[] take){
        if (des.size()==nums.length){
            List <Integer> tempRes=new LinkedList <>(des);
            res.add(tempRes);
            return;
        }
        for (int i=0;i<nums.length;i++){
            if (!take[i]){
                des.add(nums[i]);
                take[i]=true;
                dfs(nums, des,take);
                des.remove(des.indexOf(nums[i]));
                take[i]=false;
            }
        }
    }

}

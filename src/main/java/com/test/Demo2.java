package com.test;


import org.apache.hadoop.yarn.webapp.hamlet2.Hamlet;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Demo2 {
    public static void main(String[] args) {
        int[] data=new int[]{7,6,7,6,9};
//        Arrays.sort(data,0,3);
        int res = lastStoneWeight(data);
        System.out.println("res:"+res);
    }

    public static int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq=new PriorityQueue<Integer>((a,b) -> b - a);
        for(int val:stones){
            pq.offer(val);
        }
        while(pq.size()>1){
            int a =pq.poll();
            int b=pq.poll();
            if (a>b){
                pq.offer(a-b);
            }
        }

        return pq.isEmpty() ? 0 : pq.poll();
    }
}




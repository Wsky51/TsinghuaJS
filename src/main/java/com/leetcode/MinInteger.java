package com.leetcode;

import com.sun.security.jgss.GSSUtil;

import java.util.Arrays;

public class MinInteger {
    public static void main(String[] args) {
//        MinInteger demo=new MinInteger();
//        String res = demo.minInteger("22", 22);
//        System.out.println("res:"+res);

    }
    public String minInteger(String num, int k) {
        int length = num.length();
        String res="";
        Arrays.sort(num.toCharArray());

        if ((length+1)*length/2+1<k){
            char[] chars = num.toCharArray();
            Arrays.sort(chars);
            return String.valueOf(chars);
        }

        char minCh='A';
        int minIdx=-1;
        char tempCh='n';

        while(true){
            if (k<=0||num.length()==0){
                break;
            }
            for(int i=0;i<num.length();i++){
                tempCh=num.charAt(i);
                if (i>k){
                    break;
                }else {
                    if (tempCh<minCh){
                        minCh=tempCh;
                        minIdx=i;
                    }
                }
            }
            res+=minCh;
            num=num.replaceFirst(minCh+"","");
            k=k-minIdx;
            minCh='A';
            minIdx=-1;
        }
        res+=num;

        return res;

    }


}

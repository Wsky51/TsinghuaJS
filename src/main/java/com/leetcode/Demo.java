package com.leetcode;

public class Demo {
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
    }

    public static boolean isReverse(String str){
        int len = str.length();
        int mid=len/2;
        for (int i = 0; i < mid; i++) {
            if (str.charAt(i)!=str.charAt(len-i-1)){
                return false;
            }
        }
        return true;
    }

}

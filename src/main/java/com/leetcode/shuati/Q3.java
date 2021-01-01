package com.leetcode.shuati;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Q3 {
    public static void main(String[] args) {
        Q3 demo=new Q3();
        int res = demo.lengthOfLongestSubstring("abcabcbb");
        System.out.println("res:"+res);

    }
    public int lengthOfLongestSubstring(String s) {
        if(s.length()<2){
            return s.length();
        }
        int[] map=new int[128];
        Arrays.fill(map,-1);
        int lo=0;
        int hi=lo;
        int max=hi-lo+1;
        for (int i = 0; i < s.length(); i++) {
            char curChar=s.charAt(i);
            //说明有重复的数字
            if (map[curChar]!=-1){
                int pos = map[curChar];//找到这个重复字符在我们字符串中的位置
                //清洗这前面的所有数据
                for (int j = lo; j <pos+1 ; j++) {
                    map[s.charAt(j)]=-1;
                }
                map[curChar]=i;
                lo=pos+1;
            }else{//是一个新的字符，可以添加进去
                hi=i;
                map[curChar]=i;
                if (hi-lo+1>max){
                    max=hi-lo+1;
                }
            }
        }

        return max;
    }

}


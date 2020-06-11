package com.leetcode.contest185;

/**
 * Created on 2020/4/19
 *
 * @author WuYi
 */
public class Croak {
    public static void main(String[] args) {
        System.out.println(minNumberOfFrogs("croakcroak"));//1
        System.out.println(minNumberOfFrogs("crcoakroak"));//2

    }

    public static int minNumberOfFrogs(String croakOfFrogs) {
        int len = croakOfFrogs.length();

        int count=0;//可供重复使用的青蛙
        int res = 0;
        int c, r, o, a, k;
        c = r = o = a = k = 0;
        for (int i = 0; i < len; i++) {
            char ch = croakOfFrogs.charAt(i);
            switch (ch) {
                case 'c':
                    c++;
                    if (k==0){
                        res++;
                    }else{
                        if (c>k&&count>0){
                            count--;//有哇可用
                        }else {
                            res++;
                        }
                    }
                    break;
                case 'r':
                    if (r < c) {
                        r++;
                        break;
                    } else {
                        return -1;
                    }
                case 'o':
                    if (o < r) {
                        o++;
                        break;
                    } else {
                        return -1;
                    }
                case 'a':
                    if (a < o) {
                        a++;
                        break;
                    } else {
                        return -1;
                    }
                case 'k':
                    if (k < a) {
                        k++;
                        count++;
                        break;
                    } else {
                        return -1;
                    }
            }
        }
        if (c == r && r == o && o == a && a == k) {
            return res;
        } else {
            return -1;
        }
    }
}

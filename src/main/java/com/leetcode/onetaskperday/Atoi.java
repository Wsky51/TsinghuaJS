package com.leetcode.onetaskperday;

/**
 * Created on 2020/4/7
 *
 * @author WuYi
 */
public class Atoi {
    public static void main(String[] args) {
        System.out.println(myAtoi("-42"));
        System.out.println(myAtoi("4193 with my words"));
        System.out.println(myAtoi("word and 798"));
        System.out.println(myAtoi("-654765745634543534543534543"));
    }

    static final long min = 0x80000000l;
    static final long max = 0x7fffffffl;

    public static int myAtoi(String str) {
        str = str.trim();
        if (str.isEmpty()) {
            return 0;
        }
        char c = str.charAt(0);
        if (c != '+' && c != '-' && (c < '0' || c > '9')) {
            return 0;
        }

        int begin;
        if (c == '+' || c == '-') {
            begin = 1;
        } else {
            begin = 0;
        }

        boolean isPost = c == '-' ? false : true;

        long res = 0;

        for (int i = begin; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch < '0' || str.charAt(i) > '9') {//不是数字，直接返回
                break;
            }
            res = res * 10 + (ch - '0');
            if (isPost && res > Integer.MAX_VALUE) {//正数
                return Integer.MAX_VALUE;
            }
            if (!isPost && res > min){//负数
                return Integer.MIN_VALUE;
            }
        }
        return isPost?(int)res:(-1)*(int)res;

    }
}

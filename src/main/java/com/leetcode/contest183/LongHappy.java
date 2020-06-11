package com.leetcode.contest183;

/**
 * Created on 2020/4/5
 *
 * @author WuYi
 */
public class LongHappy {
    public static void main(String[] args) {

    }
    public static String longestDiverseString(int a, int b, int c) {
        if (a + b + c == 0) {
            return "";
        }
        if (a + b == 0) {
            return c > 1 ? "cc" : "c";
        }
        if (a + c == 0) {
            return b > 1 ? "bb" : "b";
        }
        if (b + c == 0) {
            return a > 1 ? "aa" : "a";
        }

        //此时a,b,c至少有2个大于0
        if (a >= b && a >= c) {
            if (a > b + c) {
                if (b > 0) return "aab" + longestDiverseString(a - 2, b - 1, c);
                else return "aac" + longestDiverseString(a - 2, b, c - 1);
            } else {
                if (b > 0) return "ab" + longestDiverseString(a - 1, b - 1, c);
                else return "ac" + longestDiverseString(a - 1, b, c - 1);
            }
        }
        if (b >= a && b >= c) {
            if (b > a + c) {
                if (a > 0) return "bba" + longestDiverseString(a - 1, b - 2, c);
                else return "bbc" + longestDiverseString(a, b - 2, c - 1);
            } else {
                if (a > 0) return "ba" + longestDiverseString(a - 1, b - 1, c);
                else return "bc" + longestDiverseString(a, b - 1, c - 1);
            }
        }
        if (c >= b && c >= a) {
            if (c > b + a) {
                if (b > 0) return "ccb" + longestDiverseString(a, b - 1, c - 2);
                else return "cca" + longestDiverseString(a - 1, b, c - 2);
            } else {
                if (b > 0) return "cb" + longestDiverseString(a, b - 1, c - 1);
                else return "ca" + longestDiverseString(a - 1, b, c - 1);
            }
        }
        return "";

    }
}

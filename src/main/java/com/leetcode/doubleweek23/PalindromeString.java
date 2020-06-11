package com.leetcode.doubleweek23;

/**
 * Created on 2020/4/4
 *
 * @author WuYi
 */
public class PalindromeString {
    static char[] dir = new char[26];

    public static void main(String[] args) {
        System.out.println(palind("yzyzyzyzyzyzyzy",2));
    }

    static boolean palind(String str, int k) {
        if (str.length() < k) {
            return false;
        }
        if (str.length() == k) {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            dir[str.charAt(i) - 'a']++;
        }
        int evenCount = 0;
        int oddCount=0;

        for (int i = 0; i < 26; i++) {
            if (dir[i] == 0) {
                continue;
            }
            if ((dir[i] & 1) == 0) {//为偶数
                evenCount++;
            }else {
                oddCount++;
            }
        }
        if (oddCount>k){//奇数无论怎么放都超过了
            return false;
        }else if (evenCount>=k){
            return true;
        }else if (evenCount+oddCount<k){
            return false;
        }else{
            return true;
        }
    }
}

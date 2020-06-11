package com.leetcode.contest183;

import java.math.BigInteger;
//Line 3: java.lang.NumberFormatException: For input string: "1111110011101010110011100100101110010100101110111010111110110010" under radix 2

/**
 * Created on 2020/4/5
 *
 * @author WuYi
 */
public class NumberSteps {

    public static void main(String[] args) {
        System.out.println(numSteps("10"));
        System.out.println(numSteps("1101"));
        System.out.println(numSteps("1"));
        System.out.println();
    }

    static int numSteps(String s) {
//        if (str=="1"){
//            return 0;
//        }
//        int count0=0;
//        int i;
//        for (i = str.length(); i >=0; i--) {
//            if (str.charAt(i)=='0'){
//                count0++;
//                continue;
//            }
//            break;
//        }
//
//        str=str.substring(0,i)+"1";
//        int count1=0;
//        for (i = str.length(); i >=0; i--) {
//            if (str.charAt(i)=='1'){
//                count1++;
//                continue;
//            }
//            break;
//        }
//        return count0+count1+numSteps(str.su);

        if (s.isEmpty() || s.equals("1")) {
            return 0;
        }
        int count = 0;
        int i;

        if (s.charAt(s.length() - 1) == '0') {
            for (i = s.length()-1; i >= 0; i--) {
                if (s.charAt(i) == '0') {
                    count++;
                    continue;
                }
                break;
            }
            s = s.substring(0, i + 1);
        } else {
            for (i = s.length()-1; i >= 0; i--) {
                if (s.charAt(i) == '1') {
                    count++;
                    continue;
                }
                break;
            }
            count++;
            if (i==-1){
                return count;
            }
            s = s.substring(0, i) + "1";
        }
        return count+numSteps(s);

    }
}

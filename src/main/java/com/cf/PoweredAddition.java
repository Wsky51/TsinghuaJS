////package com.cf;
//
//import java.util.Scanner;
//
///**
// * Created on 2020/4/13
// *
// * @author WuYi
// *         2147483647
// */
//public class PoweredAddition {
//    static long[] pow = new long[60];//pow[i]=2^i
//    static long[] accPow = new long[60];//accPow[i]=pow[0]+pow[1]+...+pow[i];
//
//    static {
//        pow[0] = 1;
//        accPow[0] = 1;
//        for (int i = 1; i < 60; i++) {
//            pow[i] = pow[i - 1] * 2;
//            accPow[i] = accPow[i - 1] + pow[i];
//        }
//
////        System.out.println(Arrays.toString(pow));
////        System.out.println(Arrays.toString(accPow));
//    }
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String res = "";
//        int t = scanner.nextInt();
//        for (int i = 0; i < t; i++) {
//            int n = scanner.nextInt();
//            int[] data = new int[n];
//            for (int j = 0; j < n; j++) {
//                int num = scanner.nextInt();
//                data[j] = num;
//            }
//            int cal = cal(data, n);
//            res = res + cal + "\r\n";
//        }
//        System.out.println(res.trim());
//    }
//
//    public static int cal(int[] data, int n) {
//        if (n <= 1) {
//            return 0;
//        }
//        long dp = 0;
//        long dpNum = data[0];
//
//        for (int i = 1; i < n; i++) {
//            if (data[i] >= dpNum) {
//                dpNum = data[i];
//                continue;
//            } else if ((dp - 1) >= 0 && (data[i] + accPow[(int) dp - 1]) >= dpNum) {
//                continue;
//            } else {//在dp时间内无法达到有序
//                for (int j = (int) dp + 1; ; j++) {
//                    if (data[i] + accPow[j - 1] >= dpNum) {
//                        dp = j;
//                        dpNum = data[i] + accPow[j - 1];
//                        break;
//                    }
//                }
//            }
//        }
//    }
//
//}
//
//
//
//
//
//zxfx PLKUYIJ6ETN5JEVV5TRBT4
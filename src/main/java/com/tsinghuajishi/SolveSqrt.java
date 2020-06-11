//package com.tsinghuajishi;
//
//import java.math.BigDecimal;
//import java.math.BigInteger;
//import java.util.Scanner;
//
///**
// * 清华大学研究生复试2019机试第2题，求n=a^m
// */
//public class SolveSqrt {
//    public static void main(String[] args) {
//        /**
//         * 准备数据
//         */
//        Scanner scanner = new Scanner(System.in);
//        String m = scanner.nextLine();
//        String n = scanner.nextLine();
//
//        System.out.println();
//        BigInteger big=new BigInteger("5");
//
//    }
////
////    public static String sqrt(String n,String m){
////        BigInteger aBig=new BigInteger(n);
////        if (m.equals("1")){
////            return n;
////        }
////        if (n.equals("0")||n.equals("1")){
////            return n;
////        }
////
////
////    }
//
//    public BigInteger sqrt(BigInteger big){
//    }
//
//    public static BigInteger pow(BigInteger big,int pow){
//        BigInteger res=new BigInteger("1");
//        while(pow!=0){
//            if ((pow&1)==1){
//                res=res.multiply(big);
//            }
//            pow/=2;
//            big=big.multiply(big);
//        }
//        return res;
//    }
//}

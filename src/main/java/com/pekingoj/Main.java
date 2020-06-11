//package com.pekingoj;
//
///**
// * Created on 2020/1/26
// *
// * @author WuYi
// */
//
//import java.util.*;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// *
// */
//public class Main {
//    public static int[] mergeArray(int[] a, int[] b) {
//        ConcurrentHashMap<String,String> map=new ConcurrentHashMap <>();
//        map.put("dsadsa", "das");
//        HashMap
//
//        List <Integer> list = new ArrayList();
//        int aIndex = 0, bIndex = 0;
//        for (; aIndex < a.length && bIndex < b.length; ) {
//            if (a[aIndex] <= b[bIndex]) {
//                if (a[aIndex] == b[bIndex]) {
//                    bIndex++;
//                }
//                list.add(a[aIndex++]);
//            } else {
//                list.add(b[bIndex++]);
//            }
//        }
//        while (aIndex < a.length) {
//            list.add(a[aIndex++]);
//        }
//        while (bIndex < b.length) {
//            list.add(b[bIndex++]);
//        }
//        int[] res=new int[list.size()];
//        for (int k=0;k<list.size();k++){
//            res[k]=list.get(k);
//        }
//        return res;
//    }
//
//    public static void main(String[] args) {
//        int[] a = {2, 10, 14, 19, 51, 71};
//        int[] b = {2, 9, 10, 14, 19, 40, 51};
//        int[] ints = mergeArray(a, b);
//        System.out.println(Arrays.toString(ints));
//    }
//}
//

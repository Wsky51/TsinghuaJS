//package com.leetcode.contest185;
//
//import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created on 2020/4/18
// *
// * @author WuYi
// */
//public class StrDacne {
//    static List <String> list = new ArrayList <>();
//
//    public static void main(String[] args) {
//        String happyString = getHappyString(3, 9);
//        System.out.println(list);
//        System.out.println(happyString);
//    }
//
//    public String getHappyString(int n, int k) {
//        dfs(n, "");
//        if (k > list.size()) {
//            return "";
//        }
//        return list.get(k - 1);
//    }
//
//    void dfs(int n, String str) {
//        if (n == 0) {
//            list.add(str);
//            return;
//        }
//        n--;
//        if (str.isEmpty()) {
//            dfs(n, "a");
//            dfs(n, "b");
//            dfs(n, "c");
//        } else {
//            char ch = str.charAt(str.length() - 1);
//
//            if (ch == 'a') {
//                dfs(n, str + "b");
//                dfs(n, str + "c");
//            }
//            if (ch == 'b') {
//                dfs(n, str + "a");
//                dfs(n, str + "c");
//            }
//            if (ch == 'c') {
//                dfs(n, str + "a");
//                dfs(n, str + "b");
//            }
//        }
//
//    }
//}

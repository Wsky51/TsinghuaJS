package com.pekingoj;

import java.util.*;

/**
 * Created on 2020/3/22
 * poj 1002 http://poj.org/problem?id=1002
 * @author WuYi
 */
public class ReflectNum {
    static int n;

    public static void main(String[] args) {
        Map <String, Integer> map = new TreeMap <String, Integer>();
        Scanner scanner = new Scanner(System.in);
        n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String cur = scanner.nextLine();
            String trsStr = getPhoneNum(cur);
            if (map.containsKey(trsStr)) {
                map.put(trsStr, map.get(trsStr) + 1);
            } else {
                map.put(trsStr, 1);
            }
        }
        boolean duplicatedFlag = false;
        for (Map.Entry <String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1 && !entry.getKey().isEmpty()) {
                duplicatedFlag=true;
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        }
        if (!duplicatedFlag){
            System.out.println("No duplicates.");
        }
    }

    public static String getSingleString(char rawChar) {
        //如果是本身就是数字，那么就直接返回
        if (rawChar >= '0' && rawChar <= '9') {
            return rawChar + "";
        }
        //若字符不合法，就直接跳过
        if (rawChar == '-') {
            return "";
        }

        int idx = rawChar - 'A';
        //字符为A-P R-Y
        if (rawChar >= 'A' && rawChar <= 'P') {
            return (idx / 3 + 2) + "";
        } else {
            return ((idx - 1) / 3 + 2) + "";
        }

    }

    public static String getPhoneNum(String rawStr) {
        String res = "";
        int count = 0;
        int length = rawStr.length();
        for (int i = 0; i < length; i++) {
            String trsStr = getSingleString(rawStr.charAt(i));
            if (trsStr.isEmpty()) {
                continue;
            }
            res += trsStr;
            count++;
            if (count == 3) {
                res += "-";
            }
        }
        return res;
    }
}

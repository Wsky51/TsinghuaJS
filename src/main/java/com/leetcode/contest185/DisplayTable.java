package com.leetcode.contest185;

import java.util.*;

/**
 * Created on 2020/4/19
 *
 * @author WuYi
 */
public class DisplayTable {

    public static void main(String[] args) {
        Set <String> set = new TreeSet <>();
        set.add("acfds");
        set.add("ab");
        set.add("ab");
        set.add("ab");

        System.out.println(set);
    }

    public static List <List <String>> displayTable(List <List <String>> orders) {
        Map <Integer, Map <String, Integer>> map = new TreeMap <>();
        Set <String> set = new TreeSet <>();

        for (List <String> list : orders) {
            String dishName = list.get(2);
            set.add(dishName);
        }

        for (List <String> list : orders) {

            int tableId = Integer.parseInt(list.get(1));
            String dishName = list.get(2);

            Map <String, Integer> treeMap;
            if (map.containsKey(tableId)) {
                treeMap = map.get(tableId);
            } else {
                treeMap = new TreeMap <>();
                for (String di : set) {
                    treeMap.put(di, 0);
                }
                map.put(tableId,treeMap);
            }

            treeMap.put(dishName, treeMap.get(dishName) + 1);
        }
        List <List <String>> res = new ArrayList <>();
        List <String> list1 = new ArrayList <>();
        list1.add("Table");
        for (String s : set) {
            list1.add(s);
        }
        res.add(list1);

        for (Map.Entry <Integer, Map <String, Integer>> entry : map.entrySet()) {
            List <String> l = new ArrayList <>();
            l.add(entry.getKey() + "");
            for (Map.Entry <String, Integer> en : entry.getValue().entrySet()) {
                l.add(en.getValue()+"");
            }
            res.add(l);
        }

        return res;

    }
}

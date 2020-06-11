package com.leetcode.onetaskperday;

import java.util.*;

/**
 * Created on 2020/4/26
 *
 * @author WuYi
 */
public class MergeKList {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(4);
        node1.next.next = new ListNode(5);

        ListNode node2 = new ListNode(1);
        node2.next = new ListNode(3);
        node2.next.next = new ListNode(4);

        ListNode node3 = new ListNode(2);
        node3.next = new ListNode(6);

        ListNode node4 = null;

        ListNode[] lists = {node1, node2, node3};
        ListNode res = mergeKLists(lists);
        printNode(res);

    }

    public static void printTest() {
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(4);
        node1.next.next = new ListNode(5);

        ListNode node2 = new ListNode(1);
        node2.next = new ListNode(3);
        node2.next.next = new ListNode(4);

        ListNode node3 = new ListNode(2);
        node3.next = new ListNode(6);

        ListNode node4 = null;

        ListNode[] lists = {node4, node1, node2, node3};

        Arrays.sort(lists, new Comparator <ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                System.out.println("o1:" + o1 + ",o2:" + o2);
                if (o1 == null) {
                    return 1;
                }
                if (o2 == null) {
                    return -1;
                }
                return o1.val - o2.val;
            }
        });

        System.out.println("size:" + lists.length);

        for (ListNode node : lists) {
            System.out.println();
            printNode(node);
        }

    }

    public static void printNode(ListNode val) {
        if (val == null) {
            System.out.println("为空");
            return;
        }
        while (val != null) {
            System.out.print(val.val + "->");
            val = val.next;
        }

    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length==0){
            return null;
        }
        if (lists.length==1){
            return lists[0];
        }
        ListNode res = null;
        Queue <ListNode> pq = new PriorityQueue <>(new Comparator <ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if (o1 == null) {
                    return 1;
                }
                if (o2 == null) {
                    return -1;
                }
                return o1.val - o2.val;
            }
        });
        while (true) {
            boolean empty = true;
            for (int i = 0; i < lists.length; i++) {
                ListNode node = lists[i];
                if (node != null) {
                    empty = false;
                    pq.add(node);
                    lists[i] = node.next;
                }
            }
            if (empty) {
                break;
            }
        }
        res = pq.poll();
        ListNode temp = res;
        while (!pq.isEmpty()) {
            ListNode remove = pq.poll();
            if (remove != null) {
                temp.next = new ListNode(remove.val);
                temp = temp.next;
            }
        }
        return res;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "val=" + val;
        }
    }
}

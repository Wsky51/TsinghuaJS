package com.leetcode.onetaskperday;


import java.math.BigInteger;

/**
 * Created on 2020/4/14
 *
 * @author WuYi
 */
public class AddTwoNum {
    public static void main(String[] args) {
        AddTwoNum demo = new AddTwoNum();
        ListNode l1 = new ListNode(3);
        l1.next = new ListNode(9);
        l1.next.next = new ListNode(9);
        l1.next.next.next = new ListNode(9);
        l1.next.next.next.next = new ListNode(9);
        l1.next.next.next.next.next = new ListNode(9);
        l1.next.next.next.next.next.next = new ListNode(9);
        l1.next.next.next.next.next.next.next = new ListNode(9);
        l1.next.next.next.next.next.next.next.next = new ListNode(9);
        l1.next.next.next.next.next.next.next.next.next = new ListNode(9);
        l1.next.next.next.next.next.next.next.next.next.next = new ListNode(9);

        ListNode l2 = new ListNode(7);
        ListNode listNode = demo.addTwoNumbers(l1, l2);
        demo.printList(listNode);


    }

    public void printList(ListNode node) {
        System.out.println();
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        BigInteger n1 = getNum(l1);
        BigInteger n2 = getNum(l2);
        BigInteger sum = n1.add(n2);
        String str = sum.toString();
        ListNode res = new ListNode(-1);
        ListNode temp = res;
        for (int i = 0; i < str.length(); i++) {
            int i1 = str.charAt(i) - '0';
            ListNode node = new ListNode(i1);
            temp.next = node;
            temp = node;
        }
        return res.next;
    }

    public BigInteger getNum(ListNode l) {
        String s = "";
        while (l != null) {
            s = s + l.val;
            l=l.next;
        }
        return new BigInteger(s);
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

}


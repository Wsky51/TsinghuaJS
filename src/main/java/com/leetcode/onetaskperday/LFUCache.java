//package com.leetcode.onetaskperday;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created on 2020/4/6
// *
// * @author WuYi
// *         <p>
// *         LFUCache cache = new LFUCache( 2 /* capacity (缓存容量)
// */
//
//public class LFUCache {
//    public static void main(String[] args) {
//        LFUCache cach = new LFUCache(3 /* capacity (缓存容量) */);
////
//        cach.put(1, 1);
//        cach.put(2, 2);
////        cach.printInfo();
//        cach.put(3, 3);    // 去除 key 2
////        cach.printInfo();
//        cach.put(4, 4);    // 去除 key 1
////        cach.printInfo();
//
//        System.out.println(cach.get(4));
//        System.out.println(cach.get(3));
//        System.out.println(cach.get(2));
//        System.out.println(cach.get(1));
////        cach.printInfo();
//
//        cach.put(5, 5);
////        cach.printInfo();
//
////        for (int i = 1; i <= 5; i++) {
////            System.out.println("这是打印的信息");
////            System.out.println(cach.get(i));
////
////        }
//
////        cach.put(1,1);
////        cach.put(2,2);
////        System.out.println(cach.get(1));
////        cach.put(3,3);
////        System.out.println(cach.get(2));
//
//    }
//
//    private Map <Integer, Node> cache;
//    private Map <Integer,Node> frequCache;
//    private DoubleLinkList freNodes;//按照频率从小到大的次序依次排列
//    private int capacity;
//    private Node[] nodes;
//    private int clock;//时钟
//
//    public LFUCache(int capacity) {
//        this.capacity = capacity;
//        cache = new HashMap <>(capacity);
//        nodes = new Node[capacity];
//        freNodes = new DoubleLinkList();
//    }
//
//    public int get(int key) {
//        Node node = cache.get(key);
//        if (node == null) {//hash表中不存在此元素，直接返回-1
//            return -1;
//        } else {//存在
//            freNodes.incFreqAndUpdate(node);
//            return node.val;
//        }
//    }
//
//    public int getSize() {
//        return freNodes.getSize();
//    }
//
////    public void printInfo(){
////        freNodes.printListInfo();
////    }
//
//    public void put(int key, int value) {
//        if (capacity == 0) {
//            return;
//        }
//        Node node = cache.get(key);
//        if (node == null) {
//            if (freNodes.getSize() == capacity) {//插入的新元素，且空间已满,进行LFU策略，舍去freNodes链表中的第一个元素
//                Node first = freNodes.removeFirst();
//                Node next = first.next;
//                if (first.frequency==next.frequency){
//                    next.sameFreMoreFreshNode=first.sameFreMoreFreshNode;
//                }
////                System.out.println("移除的点为key:" + first.key + ",val:" + first.val);
//                cache.remove(first.key);
//            }
//            Node newNode = new Node(key, value);
//            cache.put(key, newNode);
//            freNodes.addFirst(newNode);
//        } else {//存在该节点，说明要修改其值
//            node.val = value;
//            freNodes.incFreqAndUpdate(node);
//        }
//
//    }
//
//    class Node {
//        private int key;
//        private int val;
//        private int frequency;//记录LFU
//        private int time;//time,记录穿件时间存活时间，每当被访问过此值将会至为0
//
//        private Node pre;
//        private Node next;
//        private Node sameFreMoreFreshNode;
//
//        public Node(int key, int val) {
//            this.key = key;
//            this.val = val;
//            frequency = 1;
//            time = clock++;
//            sameFreMoreFreshNode = this;
//        }
//
//        @Override
//        public String toString() {
//            return "key=" + key + ",val=" + val + ",frequency=" + frequency+",sameFreLastNode="+sameFreMoreFreshNode.key;
//        }
//    }
//
//    class DoubleLinkList {
//        Node head;
//        Node tail;
//        int size;
//
////        public void printListInfo() {
////            Node node = head.next;
////            while (node != tail) {
////                System.out.print(node + "-->>");
////                node = node.next;
////            }
////            System.out.println();
////        }
//
//        public DoubleLinkList() {
//            head = new Node(-1, -1);
//            tail = new Node(Integer.MAX_VALUE, Integer.MAX_VALUE);
//            tail.frequency = Integer.MAX_VALUE;
//
//            head.pre = head.next = tail;
//            tail.pre = tail.next = head;
//        }
//
//        public void addFirst(Node node) {
//            Node next = head.next;
//            if ((node.frequency < next.frequency)) {
//                head.next = node;
//                next.pre = node;
//                node.pre = head;
//                node.next = next;
//                size++;
//            } else {//那就后移,新加的节点必然是最新节点，必然在同等频率节点下的最后面
//                Node sameFreMoreFreshNode = next.sameFreMoreFreshNode;
////                System.out.println("出现同频次了："+sameFreMoreFreshNode);
//                addNodeAfter(sameFreMoreFreshNode, node);
//                next.sameFreMoreFreshNode = node;
//            }
//        }
//
//        //从尾节点加
//        public void addNode(Node node) {
//            node.pre = tail.pre;
//            node.next = tail;
//            tail.pre.next = node;
//            tail.pre = node;
//            size++;
//        }
//
//        public void addNodeAfter(Node after, Node node) {
////            System.out.println("addNodeAfter,after:"+after+",node:"+node);
//            Node next = after.next;
//
//            after.next = node;
//            next.pre = node;
//
//            node.pre = after;
//            node.next = next;
//            size++;
//        }
//
//        /**
//         * 此处必须要保证node在DoubleLinkList里面
//         *
//         * @param node
//         */
//        public void removeNode(Node node) {
//            Node pre = node.pre;
//            Node next = node.next;
//            pre.next = next;
//            next.pre = pre;
//            size--;
//        }
//
//        public Node removeFirst() {
//            Node first = head.next;
//            removeNode(head.next);
//            return first;
//        }
//
//        public Node removeLast() {
//            Node last = tail.pre;
//            removeNode(last);
//            return last;
//        }
//
//        public int getSize() {
//            return size;
//        }
//
//        public void incFreqAndUpdate(Node node) {//既然是更新节点，若出现相同频率必然也是在最后
//            node.frequency++;//频次先加1
////            System.out.println("要更新了，node.key:" + node.key + ",frequen：" + node.frequency);
//            Node pre = node.pre;
//            Node next = node.next;
//
//            if (node.frequency < next.frequency) {
//                //频率还未达到，直接返回
//                return;
//            } else if (node.frequency == next.frequency) {//若频率相等,则放到同等频率下的最后一个位子
//                Node sameFreMoreFreshNode = next.sameFreMoreFreshNode;
//                removeNode(node);
//                addNodeAfter(sameFreMoreFreshNode, node);
//            } else {//频率更大的话，就要考查下一层次的节点了
//                Node nextLevel = next.sameFreMoreFreshNode.next;
//                if (node.frequency < nextLevel.frequency) {
//                    removeNode(node);
//                    addNodeAfter(nextLevel.pre, node);
//                } else {
//                    Node sameFreMoreFreshNode = next.sameFreMoreFreshNode;
//                    removeNode(node);
//                    addNodeAfter(sameFreMoreFreshNode, node);
//                }
//
//            }
//
//        }
//
//    }
//
//}

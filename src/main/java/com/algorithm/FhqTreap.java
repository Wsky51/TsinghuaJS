package com.algorithm;

import java.util.Random;
import java.util.Scanner;

/**
 * Created on 2020/4/3
 * Treap=Binary Search Tree+Heap（二叉搜索树+堆）
 * 也就是说他的整体结构满足二叉搜索树的性质，然而他的每一个节点有一个随机取的附加的关键词，他们满足堆的性质。
 * 那么他是依靠什么维护平衡性的呢？对就是靠的附加权值，因为附加权值是随机取的，每一个权值取到的概率都是相同的，
 * 那么他的期望高度就是logNlogN也就是说他大概就是平衡的。
 * 然而一般的treap也要通过旋转保持平衡，但是fhq-treap不需要，这得益于他的两个神奇的核心操作MergeMerge和SplitSplit，
 * 也就是合并和分裂。
 *
 * @author WuYi
 */
public class FhqTreap {
    private static final int max = 100005;
    private static final Random random = new Random(10);

    static int cnt = 0, root = 0;

    Node[] fhq;

    public static void main(String[] args) {
        FhqTreap demo = new FhqTreap();
        demo.initFhq();

        demo.insert(106465);
//        int valByRank = demo.getValByRank(1);
//        System.out.println(valByRank);
        System.out.println("root:" + root);
        demo.insert(317721);
        demo.insert(460929);
        demo.insert(644985);
        demo.insert(84185);
        demo.insert(89851);

        System.out.println("root:" + root + "," + demo.fhq[root]);
        Node nodeL = demo.fhq[demo.fhq[root].l];
        Node nodeR = demo.fhq[demo.fhq[root].r];
        System.out.println("l:" + demo.fhq[root].l + "," + demo.fhq[demo.fhq[root].l]);
        System.out.println("r:" + demo.fhq[root].r + "，" + demo.fhq[demo.fhq[root].r]);

        Node nodeLL = demo.fhq[nodeL.l];
        Node nodeLR = demo.fhq[nodeL.r];
        Node nodeRL = demo.fhq[nodeR.l];
        Node nodeRR = demo.fhq[nodeR.r];

        System.out.println("ll:" +nodeLL);
        System.out.println("lr:" +nodeLR);
        System.out.println("rl:" +nodeRL);
        System.out.println("rr:" +nodeRR);

//        int successor = demo.successor(81968);
//        System.out.println(successor);

//        StringBuilder sb=new StringBuilder();

//        Scanner scanner=new Scanner(System.in);
//        int lin = scanner.nextInt();
//        for (int i = 0; i < lin; i++) {
//            int method = scanner.nextInt();
//            int num = scanner.nextInt();
//            switch (method){
//                case 1:
//                    demo.insert(num);
//                    break;
//                case 2:
//                    demo.del(num);
//                    break;
//                case 3:
//                    sb.append(demo.getRankByVal(num)+"\r\n");
//                    break;
//                case 4:
//                    sb.append(demo.getValByRank(num)+"\r\n");
//                    break;
//                case 5:
//                    sb.append(demo.pre(num)+"\r\n");
//                    break;
//                case 6:
//                    sb.append(demo.successor(num)+"\r\n");
//                    break;
//            }
//
//        }
//        System.out.println(sb);
    }

    class Node {
        int l, r;//左右儿子
        int val, rnd;//val为该节点的值，rnd为随机值，也是fhq-treap相较于其他算法的精髓之处
        int size;//此节点的大小

        @Override
        public String toString() {
            return "Node{" +
                    "l=" + l +
                    ", r=" + r +
                    ", val=" + val +
                    ", rnd=" + rnd +
                    ", size=" + size +
                    '}';
        }
    }

    void initFhq() {
        fhq = new Node[max];
        fhq[0] = new Node();
    }

    int newNode(int val) {
        fhq[++cnt] = new Node();
        fhq[cnt].val = val;
        fhq[cnt].rnd = random.nextInt(10);//此随机值为Integer.MIN_VALUE至Integer.MAX_VALUE中的任意值
        fhq[cnt].size = 1;
        return cnt;//返回该节点的计数
    }

    void update(int now) {
        fhq[now].size = fhq[fhq[now].l].size + fhq[fhq[now].r].size + 1;
    }

    /**
     * 分裂操作，对以节点now为根的树按值val进行分裂，分裂成左子树和右子树
     * 其中一棵树必定是以now为根的树，另外一颗树的根节点则为返回值
     *
     * @param now
     * @param val
     */
    int[] split(int now, int val) {
        int[] xy = new int[2];
        if (now == 0) {
            return xy;
        } else {
            if (fhq[now].val <= val) {
                xy[0] = now;
                fhq[now].r = split(fhq[now].r, val)[0];
            } else {
                xy[1] = now;
                fhq[now].l = split(fhq[now].l, val)[1];
            }
            update(now);
        }
        return xy;
    }

    /**
     * 对两颗分别以a,b为根的树进行合并操作
     *
     * @param a
     * @param b
     * @return 合并后的树的树根节点
     */
    int merge(int a, int b) {
        if (a == 0 || b == 0) {
            return a + b;
        }
        if (fhq[a].rnd > fhq[b].rnd)           // > >= < <=
        {
            fhq[a].r = merge(fhq[a].r, b);
            update(a);
            return a;
        } else {
            fhq[b].l = merge(a, fhq[b].l);
            update(b);
            return b;
        }
    }

    /**
     * 插入操作
     *
     * @param val
     */
    void insert(int val) {
        int[] trees = split(root, val);
        root = merge(merge(trees[0], newNode(val)), trees[1]);
    }

    /**
     * 删除操作
     *
     * @param val
     */
    void del(int val) {
//        split(root, val, x, z);
//        split(x, val - 1, x, y);
//        y = merge(fhq[y].l, fhq[y].r);
//        root = merge(merge(x, y), z);

        int[] xz = split(root, val);
        int[] xy = split(xz[0], val - 1);
        int y = merge(fhq[xy[1]].l, fhq[xy[1]].r);
        root = merge(merge(xy[0], y), xz[1]);

    }

    /**
     * 获取值为val的节点的排名
     *
     * @param val
     */
    int getRankByVal(int val) {
        int[] xy = split(root, val - 1);
        int res = fhq[xy[0]].size + 1;
        root = merge(xy[0], xy[1]);
        return res;
    }

    /**
     * 获取排名为rank的节点的值为多少
     *
     * @param rank
     */
    int getValByRank(int rank) {
        int now = root;
        while (now != 0) {
            if (fhq[fhq[now].l].size + 1 == rank)
                break;
            else if (fhq[fhq[now].l].size >= rank)
                now = fhq[now].l;
            else {
                rank -= fhq[fhq[now].l].size + 1;
                now = fhq[now].r;
            }
        }
        return fhq[now].val;
    }

    /**
     * 值为val的节点的前驱
     *
     * @param val
     */
    int pre(int val) {
        int[] xy = split(root, val - 1);
        int now = xy[0];
        while (fhq[now].r != 0) {
            now = fhq[now].r;
        }
        int res = fhq[now].val;
        root = merge(xy[0], xy[1]);
        return res;
    }

    /**
     * 值为val的节点的后继
     *
     * @param val
     */
    int successor(int val) {
        int[] xy = split(root, val);
        int now = xy[1];
        while (fhq[now].l != 0) {
            now = fhq[now].l;
        }
        int res = fhq[now].val;
        root = merge(xy[0], xy[1]);
        return res;
    }

}

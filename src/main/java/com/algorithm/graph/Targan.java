package com.algorithm.graph;

 import java.util.*;

 /**
 * Created on 2020/3/6
 * Targan 算法求强连同图，即求BCC,邓公书171页
 *
 * @author WuYi
 */

/**
 * 割点的定义：去除了这个点之后，连通域个数增加了，该点就是割点，分两种名情况，1，若该节点是根节点，且有两个孩子，则根节点必然是割点
 * 2.叶节点必定不是割点，3.若节点v存在一个孩子节点u，有hca[u]>=dTime[v],则节点v必定是割点
 * 割边的定义：去除该边后，连通域个数增加了，该边就是割边
 */
public class Targan {
    static boolean[] inStack;//保存顶点是否在栈之中
    static int[] dTime;//节点的发现时间，discover time
    static int[] hca;//highest connected ancestor, HCA
    static List <Edge>[] map;//定义点的边集合，map[i]代表第n个节点的边的集合
    static int clock = 0;
    static Stack <Integer> stack = new Stack();
    static List <ArrayList <Integer>> result = new ArrayList();// 保存极大强连通图
    static List <Edge> bridge = new ArrayList <>();
    static boolean[] isCutPoint;//是否是割点
    static int root;//记录根节点

    static int finalRes = Integer.MAX_VALUE;

    public static void init(int n, int m) {
        inStack = new boolean[n + 1];
        dTime = new int[n + 1];
        hca = new int[n + 1];
        map = new ArrayList[n + 1];
        isCutPoint = new boolean[n + 1];

        for (int i = 0; i < n + 1; i++) {
            map[i] = new ArrayList <Edge>();
        }
    }

    /**
     * @param v
     * @param father father 为节点v的父亲
     */
    public static void targan(int v, int father) {
        int child = 0;
        dTime[v] = hca[v] = ++clock;//发现时间
        inStack[v] = true;
        stack.push(v);//入栈
        for (int i = 0; i < map[v].size(); i++) {
            int u = map[v].get(i).y;//v->u的映射
            if (dTime[u] == 0) {//说明u节点暂时没有被发现过
                child++;
                targan(u, v);
                hca[v] = Math.min(hca[v], hca[u]);

                /**
                 * 求割点
                 */
                //  如果当前节点是根节点并且儿子个数大于等于2，则满足第一类节点，为割点
                if (v == root && child >= 2) {
                    isCutPoint[v] = true;
                } else if (v != root && hca[u] >= dTime[v]) {//不为根结点但是满足第二类条件的节点
                    isCutPoint[v] = true;
                }

                /**
                 * 求割边
                 */
                if (hca[u] > dTime[v]) {
                    bridge.add(map[v].get(i));
                    if (map[v].get(i).weight < finalRes) {
                        finalRes = map[v].get(i).weight;
                    }
                }

            } else if (inStack[u] && u != father) {//否则，若当节点u被发现过，而且还在栈中
                hca[v] = Math.min(hca[v], dTime[u]);
            }

        }
        if (dTime[v] == hca[v]) {//构成连通分量
            ArrayList <Integer> temp = new ArrayList <Integer>();
            int j = -1;
            while (j != v) {
                j = stack.pop();
                inStack[j] = false;
                temp.add(j);
                System.out.print(j + " ");
            }
//            result.add(temp);
            System.out.println();
            System.out.println("--------");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();//n个顶点
        int m = scanner.nextInt();//m条边
        init(n, m);

        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
//            int weight = scanner.nextInt();
            map[x].add(new Edge(x, y, 1));
            map[y].add(new Edge(y, x, 1));//针对无向图，有向图这行代码去掉
        }

        root = 1;//将第一个节点设置为根节点
        for (int i = 1; i < n + 1; i++) {
            if (dTime[i] == 0) {
                targan(i, root);
            }
        }

        for (int i = 1; i < n + 1; i++) {
            System.out.println("dTime[" + i + "]:" + dTime[i] + ",hca[" + i + "]:" + hca[i]);

        }

        for (int i = 1; i < n + 1; i++) {
            if (isCutPoint[i]) {
                System.out.println("割点：" + i);
            }
        }
        System.out.println("割边为：" + bridge);

        System.out.println("------------------");
        System.out.println("最终结果：");
        if (finalRes==Integer.MAX_VALUE){
            System.out.println(-1);
        }else {
            System.out.println(finalRes);
        }

    }

    static class Edge {
        int x;//定义边的两个端点，x->y
        int y;
        int weight;

        public Edge(int x, int y) {
            this(x, y, 1);//默认权重为1
        }

        public Edge(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return x + "->" + y + "," + weight + ";";
        }
    }
}

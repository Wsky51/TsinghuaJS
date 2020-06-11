package com.algorithm.graph;

import java.util.*;

/**
 * Created on 2020/3/11
 * SPFA(Shortest Path Faster Algorithm)是Bellman-Ford算法的一种队列优化，减少了不必要的冗余计算。
 * 在稠密图中复杂度比迪杰斯特拉算法差。适用于稀疏图
 * <p>
 * spfa是求单源最短路径的一种算法，它还有一个重要的功能是判负环（在差分约束系统中会得以体现），
 * 在Bellman-ford算法的基础上加上一个队列优化，减少了冗余的松弛操作，是一种高效的最短路算法。
 * </p>
 *
 * @author WuYi
 */
public class SPFA {
    public long[] result;       //用于存放第0个顶点到其它顶点之间的最短距离

    //内部类，表示图的一条加权边
    class edge {
        public int a;   //边的起点
        public int b;   //边的终点
        public int value;  //边的权值

        edge(int a, int b, int value) {
            this.a = a;
            this.b = b;
            this.value = value;
        }

        @Override
        public String toString() {
            return "edge{" +
                    "a=" + a +
                    ", b=" + b +
                    ", value=" + value +
                    '}';
        }
    }

    public static void main(String[] args) {
        SPFA test = new SPFA();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int p = in.nextInt();
        edge[] A = new edge[p];
        for (int i = 0; i < p; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int value = in.nextInt();
            A[i] = test.new edge(a, b, value);
        }
        for (int i = 0; i < p; i++) {
            System.out.println("边的信息" + A[i]);
        }

        if (test.spfa(A, n, 0)) {
            for (int i = 0; i < test.result.length; i++)
                System.out.print(test.result[i] + " ");
        } else
            System.out.println("给定图存在负环，没有最短距离");
    }

    /**
     * @param edges 边集合
     * @param n     顶点数
     * @param s     从哪一点开始
     */
    public boolean spfa(edge[] edges, int n, int s) {
        Queue <Integer> queue = new LinkedList <>();
        result = new long[n];
        boolean[] inQueue = new boolean[n];
        int[] num = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = Integer.MAX_VALUE;
        }
        result[s] = 0;      //第s个顶点到自身距离为0
        num[s] = 1;         //表示第s个顶点已被遍历一次
        queue.add(s);       //第s个顶点入队
        inQueue[s] = true;  //表示第s个顶点进入队列
        while (!queue.isEmpty()) {
            int a = queue.remove();
            for (int i = 0; i < edges.length; i++) {
                //当list数组队的第一个元素等于边A[i]的起点时
                if (a == edges[i].a && result[edges[i].b] > result[edges[i].a] + edges[i].value) {
                    result[edges[i].b] = result[edges[i].a] + edges[i].value;
                    if (!inQueue[edges[i].b]) {//如果不在队列里面就加入到队列中去
                        queue.add(edges[i].b);
                        num[edges[i].b]++;
                        if (num[edges[i].b] > n) {
                            return false;
                        }
                        inQueue[edges[i].b] = true;   //表示边A[i]的终点b已进入队
                    }
                }
            }
            System.out.println("移除的点为："+(a+1)+",当前队列信息为："+queue+",当前result:"+Arrays.toString(result));
            inQueue[a] = false;        //顶点a出数组对
        }
        return true;

    }

}

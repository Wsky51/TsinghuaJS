package com.algorithm.graph;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created on 2020/3/11
 * 最短路径问题，贝尔曼-福特算法的最多运行O(V*E)次，V和E分别是节点和边的数量）
 * <p>
 * Dijkstra算法无法判断含负权边的图的最短路。如果遇到负权，在没有负权回路（回路的权值和为负，即便有负权的边）存在时，
 * 也可以采用Bellman - Ford算法正确求出最短路径。
 * </p>
 * 千万注意：BellmanFord和SPFA要解决负权回路问题，必定是针对有向图，但Dijkstra有向图无向图都可以
 *
 * @author WuYi
 */
public class BellmanFord {
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

    //返回第0个顶点到其它所有顶点之间的最短距离,千万
    public boolean bellmanFord(int n, edge[] edges) {
        result = new long[n];
        for (int i = 1; i < n; i++)
            result[i] = Integer.MAX_VALUE;  //初始化第0个顶点到其它顶点之间的距离为无穷大，此处用Integer型最大值表示,result[0]=0
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < edges.length; j++) {
                if (result[edges[j].a] != Integer.MAX_VALUE && result[edges[j].b] > result[edges[j].a] + edges[j].value) {
                    result[edges[j].b] = result[edges[j].a] + edges[j].value;
                    System.out.println("当前result为：" + Arrays.toString(result) + "：+edge[" + j + "]" + edges[j] + ",有更新");
                }

            }
            System.out.println("第i=" + i + "轮，result:" + Arrays.toString(result));
        }
        boolean judge = true;
        for (int i = 1; i < n; i++) {   //判断给定图中是否存在负环
            if (result[edges[i].b] > result[edges[i].a] + edges[i].value) {
                judge = false;
                break;
            }
        }
        return judge;
    }

    public static void main(String[] args) {
        BellmanFord test = new BellmanFord();
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
        if (test.bellmanFord(n, A)) {
            for (int i = 0; i < test.result.length; i++)
                System.out.print(test.result[i] + " ");
        } else
            System.out.println("给定图存在负环，没有最短距离");
    }

}

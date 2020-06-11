package com.algorithm.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created on 2020/3/29
 *
 * @author WuYi
 */
public class NetWorkFlowMcmfByUsingSpfa {

    final static int UN_REACHABLE = Integer.MAX_VALUE / 2;
    final static int MAX_FLOW = UN_REACHABLE;

    //dist最小花费;prev每个点的前驱；last每个点的所连的前一条边；flow源点到此处的流量
    static Edge[] edges;//保存边集
    static int[] head;//head[i]表示第i个节点在edges数组中所在的编号,注意head[i]=-1表示没有边了
    static boolean[] inq;//保存每个点当前是否在队列中
    static int[] dist;//保存每个点到原点的最短距离
    static int[] prev;//每个点的前驱节点
    static int[] last;//每个点所连的前一条边
    static int[] flow;//原点到此处的流量
    static int cnt = -1;//计数
    static int n, m, s, t;// n为节点数，m为边数，s为计算网络流的起始点，t为终止点
    static int maxFlow, minCost;//保存最终结果，最大流，最小费用

    public static void main(String[] args) {
        init();
        mcmf();
        System.out.println(maxFlow+" "+minCost);
    }

    public static void init() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();//节点数
        m = scanner.nextInt();//边数
        s = scanner.nextInt() - 1;//s为起点
        t = scanner.nextInt() - 1;//t为终点

        edges = new Edge[2 * m];
        head = new int[n];
        inq = new boolean[n];
        dist = new int[n];
        prev = new int[n];
        last = new int[n];
        flow = new int[n];

        for (int i = 0; i < 2 * m; i++) {
            edges[i] = new Edge();
        }

        Arrays.fill(head, -1);

        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt() - 1;//
            int y = scanner.nextInt() - 1;//
            int capacity = scanner.nextInt();//容量
            int cost = scanner.nextInt();//代价
            addEdge(x, y, capacity, cost);
            addEdge(y, x, 0, -cost);
        }
    }

    public static void addEdge(int from, int to, int capacity, int cost) {
        edges[++cnt].to = to;
        edges[cnt].capacity = capacity;
        edges[cnt].cost = cost;
        edges[cnt].next = head[from];
        head[from] = cnt;
    }

    //从s到t做一次spfa
    public static boolean spfa(int s, int t) {

        Arrays.fill(dist, UN_REACHABLE);
        Arrays.fill(flow, MAX_FLOW);
        Arrays.fill(inq, false);
        Arrays.fill(prev, -1);

        Queue <Integer> queue = new LinkedList <>();
        queue.add(s);
        inq[s] = true;
        dist[s] = 0;

        while (!queue.isEmpty()) {
            Integer u = queue.remove();
            inq[u] = false;
            for (int i = head[u]; i != -1; i = edges[i].next) {
                //若该边有容量且边的终点代价大于起点加上边的代价
                if (edges[i].capacity > 0 && dist[edges[i].to] > dist[u] + edges[i].cost) {
                    dist[edges[i].to] = dist[u] + edges[i].cost;
                    prev[edges[i].to] = u;
                    last[edges[i].to] = i;
                    flow[edges[i].to] = Math.min(flow[u], edges[i].capacity);//保存可增加的流量
                    if (!inq[edges[i].to]) {//如果该点不在队列中，则可以加入队列
                        queue.add(edges[i].to);
                        inq[edges[i].to] = true;
                    }
                }
            }
        }
        return prev[t] != -1;
    }

    //最小费用最大流
    public static void mcmf() {
        while (spfa(s, t)) {
            int now = t;
            maxFlow += flow[t];
            minCost += flow[t] * dist[t];
            while (now != s) {
                edges[last[now]].capacity -= flow[t];//flow和dis容易搞混
                edges[last[now] ^ 1].capacity += flow[t];
                now = prev[now];
            }
        }
    }

    static class Edge {
        int to;//边的到达顶点
        int next;//该边的下一条边在edges数组中的位置
        int capacity;//边的最大容量
        int cost;//边的花费
    }
}

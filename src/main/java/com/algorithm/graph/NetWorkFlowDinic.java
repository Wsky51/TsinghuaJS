package com.algorithm.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created on 2020/3/27
 * 网络流算法之Dinic算法，在EK算法的基础上进行的改进
 *
 * @author WuYi
 */
public class NetWorkFlowDinic {
    static Edge[] edges;//保存边集
    static int[] head;//head[i]表示第i个节点在edges数组中所在的编号,注意head[i]=-1表示没有边了
    static int cnt = -1;//计数
    static int[] layer;//layer[i]表示第i个节点的层次
    static int n, m, s, t;// n为节点数，m为边数，s为计算网络流的起始点，t为终止点

    /**
     * 加入一条边到edges数组
     *
     * @param x 边的起始点
     * @param y 边的终止点
     * @param w 边的权重
     */
    public static void addEdge(int x, int y, int w) {
        edges[++cnt].y = y;
        edges[cnt].w = w;
        edges[cnt].next = head[x];
        head[x] = cnt;
    }

    //从节点s开始做一次bfs标记层次
    public static boolean bfs() {
        Arrays.fill(layer, 0);//每次进行bfs时都初始化layer为0
        layer[s] = 1;//初始化起点s为第一层
        Queue <Integer> queue = new LinkedList <>();
        queue.add(s);
        while (!queue.isEmpty()) {
            Integer u = queue.remove();
            for (int i = head[u]; i != -1; i = edges[i].next) {
                int y = edges[i].y;
                if (edges[i].w > 0 && layer[y] == 0) {//如果该边有权值且y节点没有被发现过
                    layer[y] = layer[u] + 1;
                    queue.add(y);
                }
            }
        }
        return layer[t] > 0 ? true : false;//若终点的层次大于0说明此次bfs可行，否则为找不到增广路了
    }

    /**
     * @param u   从哪一点开始做dfs
     * @param low 起始时最少的流量
     * @return 返回从u点到终点t的最小容量，也即是可增加的最小残存流量
     */
    public static int dfs(int u, int low) {

        if (u == t) {
            return low;
        }

        int sum = 0;//记录u节点之后的进行dfs的所有最小残存流量之和
        for (int i = head[u]; i != -1 && low > 0; i = edges[i].next) {
            int y = edges[i].y;
            if (edges[i].w > 0 && layer[y] == layer[u] + 1) {
                int t = dfs(y, Math.min(low, edges[i].w));
                edges[i].w -= t;
                edges[i ^ 1].w += t;
                sum += t;
                low -= t;
                if (low <= 0) {
                    return sum;
                }
            }
        }
        return sum;

    }

    public static int dinic() {
        int sum = 0, tf = 0;
        while (bfs()) {
            while ((tf = dfs(0, Integer.MAX_VALUE)) > 0) {
                sum += tf;
            }

        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();//节点数
        m = scanner.nextInt();//边数
        s = scanner.nextInt();//s为起点
        t = scanner.nextInt();//t为终点

        edges = new Edge[2 * m];
        head = new int[n];
        layer = new int[n];

        for (int i = 0; i < 2 * m; i++) {
            edges[i] = new Edge();
        }

        Arrays.fill(head, -1);

        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt();//
            int y = scanner.nextInt();//
            int capacity = scanner.nextInt();//容量
            addEdge(x, y, capacity);
            addEdge(y, x, 0);
        }

        System.out.println(dinic());

    }

}

class Edge {
    int y;//该边的到达节点y
    int w;//该边的权重
    int next;//该边的下一条边在edges数组中的编号

    @Override
    public String toString() {
        return "Edge{" +
                "y=" + y +
                ", w=" + w +
                ", next=" + next +
                '}';
    }
}
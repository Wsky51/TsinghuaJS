package com.algorithm.graph;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created on 2020/3/3
 * 时间复杂度O(n^2),Dijkstra算法无法判断含负权边的图的最短路。如1->2 代价为5, 1->3代价为4,2->3代价为-3.
 * Dijkstra解决不了这类问题
 *
 * @author WuYi
 */
public class DijkstraDemo {

    public static final int UN_REACHABLE = 100000;
    //    public static final int VISITED = Integer.MAX_VALUE;
    public static int[] res;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();//N为节点个数
        int M = scanner.nextInt();//M为边的个数
        int[][] cost = new int[N + 1][N + 1];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(cost[i], UN_REACHABLE);//初始化，MAX_VALUE为不可达
        }

        //输入数据
        for (int i = 0; i < M; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int len = scanner.nextInt();
            cost[x][y] = len;//注意此处说明是有向图
            cost[x][y] = cost[y][x] = len;//如果上面那一个改成这个，就是无向图了
        }
        dijkstra(cost, N);
    }

    public static void dijkstra(int[][] cost, int n) {
        int[] dist = new int[n + 1];//保存点i到点1的最短距离
        int[] parent = new int[n + 1];//保存节点的父节点信息,parent[i]=k代表第i个节点的父亲是k
        boolean[] visitedFlag = new boolean[n + 1];//保存该节点是否被访问过
        Arrays.fill(dist, UN_REACHABLE);
        Arrays.fill(visitedFlag, false);
        Arrays.fill(parent, -1);//-1代表父节点信息为空

        dist[1] = 0;

        //更新与第一个节点相连的所有节点路径长度
        for (int i = 2; i < n + 1; i++) {
            dist[i] = cost[1][i];
            parent[i] = 1;
        }

        for (int i = 2; i < n + 1; i++) {
            int tarCost = UN_REACHABLE;
            int tarPath = -1;
            //选取距离最小的点
            for (int j = 2; j < n + 1; j++) {
                if (tarCost > dist[j] && !visitedFlag[j]) {
                    tarCost = dist[j];
                    tarPath = j;
                }
            }


            if (tarPath != -1) {
                System.out.println("当前选取的点为："+tarPath);
                visitedFlag[tarPath] = true;
            }

            //更新点
            for (int k = 2; k < (n + 1) && tarPath != -1; k++) {
                if (!visitedFlag[k] && dist[tarPath] + cost[tarPath][k] < dist[k]) {
                    dist[k] = dist[tarPath] + cost[tarPath][k];
                    parent[k] = tarPath;
                }
            }
        }
        System.out.println("路径信息：" + Arrays.toString(dist));//保存最后结果
        System.out.println("父节点信息：" + Arrays.toString(parent));
    }
}
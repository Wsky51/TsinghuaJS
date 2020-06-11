package com.algorithm.graph;

import java.util.Scanner;

/**
 * Created on 2020/3/5
 *
 * @author WuYi
 */
public class Dfs {
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();//n个顶点
        int m = scanner.nextInt();//m条边

        visited = new boolean[n + 1];
        int[][] cost = new int[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            cost[x][y] = 1;//有向图
        }
        for (int i = 1; i < n+1; i++) {
            if (!visited[i]){
                dfs(cost, visited, i);
            }
        }
    }

    public static void dfs(int[][] cost, boolean[] visited, int beginPoint) {
        System.out.println("选取点：" + beginPoint);
        visited[beginPoint] = true;
        for (int i=1;i<visited.length;i++){
            if (!visited[i]&cost[beginPoint][i]>0){
                dfs(cost,visited,i);
            }
        }

    }
}






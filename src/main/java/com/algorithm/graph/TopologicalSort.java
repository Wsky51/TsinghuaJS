package com.algorithm.graph;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created on 2020/3/4
 * http://poj.org/problem?id=2367
 *
 * @author WuYi
 */
public class TopologicalSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] inDegree = new int[N + 1];//记录各个顶点的入度
        boolean[] visited = new boolean[N + 1];//记录各个顶点是否访问完毕
        int[][] map = new int[N + 1][N + 1];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(map[i], Integer.MAX_VALUE);
        }
        Arrays.fill(inDegree, 0);
        Arrays.fill(visited, false);

        //处理输入数据
        for (int i = 1; i < N + 1; i++) {
            int child = scanner.nextInt();
            while (child != 0) {
                map[i][child] = 1;
                inDegree[child]++;//入度+1
                child = scanner.nextInt();
            }
        }

        for (int i = 1; i < N + 1; i++) {

            int targetPoi = -1;
            //选取入度为0的节点
            for (int j = 1; j < N + 1; j++) {
                if (inDegree[j] == 0 && !visited[j]) {
                    targetPoi = j;
                }
            }

            if (targetPoi == -1) {
                System.out.println("找不到入度为0的节点，图有问题(有闭环)，将会退出");
                return;
            }
            System.out.println("选取节点：" + targetPoi);
            visited[targetPoi] = true;
            //更新拓扑结构
            for (int k = 1; k < N + 1; k++) {
                if (map[targetPoi][k] == 1) {
                    inDegree[k]--;
                }
            }
        }
    }
}

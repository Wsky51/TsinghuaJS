package com.algorithm.graph;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created on 2020/3/3
 *  最小生成树算法，prim算法
 * @author WuYi
 */
public class Prim {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();//n个顶点
        int m = scanner.nextInt();//m条边关系，但注意有重复的
        int[][] cost = new int[n + 1][n + 1];//按照从1进行
        for (int i = 1; i < n + 1; i++) {
            Arrays.fill(cost[i], Integer.MAX_VALUE);//进行初始，填充最大值
        }

        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int xycost = scanner.nextInt();
            if (xycost < cost[x][y]) {
                cost[x][y] = cost[y][x] = xycost;
            }
        }

        prim(cost, n);

    }

    /**
     * cost 为二维代价表，n为节点数
     *
     * @param cost
     * @param n
     */
    public static void prim(int[][] cost, int n) {
        int[] lowCost = new int[n + 1];
        int[] pathPoint = new int[n + 1];

        int finalCost = 0;

        //先初始化与1节点的信息
        for (int i = 2; i < n + 1; i++) {
            lowCost[i] = cost[1][i];
            pathPoint[i] = 1;
        }

        //注意，这个地方的i并不是节点信息，而是遍历的次数，因为迭代n-1次必定完
        for (int i = 2; i < n + 1; i++) {

            int poi = -1, minCost = Integer.MAX_VALUE;
            //从lowCost中选取目标
            for (int j = 2; j < n + 1; j++) {
                if (lowCost[j] < minCost && lowCost[j] != 0) {
                    poi = j;
                    minCost = lowCost[j];
                }
            }
            System.out.println("lowCost[]:"+Arrays.toString(lowCost));
            System.out.println("pathPoint[]:"+Arrays.toString(pathPoint));
            System.out.println("poi:"+poi+",min:"+minCost);
            finalCost += minCost;
            //标记该点已经访问完毕
            lowCost[poi] = 0;
            pathPoint[poi] = 0;
            //调整代价
            for (int k = 2; k < n + 1; k++) {
                if (cost[poi][k] < lowCost[k]) {
                    lowCost[k] = cost[poi][k];
                    pathPoint[k] = k;
                }
            }

        }
        System.out.println(finalCost);

    }
}

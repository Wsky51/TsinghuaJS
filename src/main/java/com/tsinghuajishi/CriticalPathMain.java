package com.tsinghuajishi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created on 2020/3/4
 *
 * @author WuYi
 */
public class CriticalPathMain {
    static List <Integer> list = new ArrayList();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();//n个实验(顶点)
        int m = scanner.nextInt();//m个对应关系
        int[] spend = new int[n + 1];
        boolean[] hasChild = new boolean[n + 1];

        int[][] cost = new int[n + 2][n + 2];

        for (int i = 0; i < n + 2; i++) {
            Arrays.fill(cost[i], Integer.MIN_VALUE);
        }

        spend[0] = Integer.MAX_VALUE;

        int[] inDegree = new int[n + 2];//最后一个顶点为n+1,人为加上去的
        Arrays.fill(inDegree, 0);

        boolean[] visited = new boolean[n + 2];
        Arrays.fill(visited, false);

        for (int i = 1; i < n + 1; i++) {
            spend[i] = scanner.nextInt();
        }

        for (int i = 1; i < m + 1; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            cost[x][y] = spend[x];
            inDegree[y]++;
            hasChild[x] = true;
        }

        for (int i = 1; i < n + 1; i++) {
            if (!hasChild[i]) {
                cost[i][n + 1] = spend[i];
                inDegree[n + 1]++;
            }
        }

        int[] ve = new int[n + 2];//事件的最早发生时间
        int[] vl = new int[n + 2];//事件的最晚发生时间

        Arrays.fill(ve, -1);

        //初始化最开始的那些入度为0的点
        for (int j = 1; j < n + 2; j++) {
            if (inDegree[j] == 0) {
                ve[j] = 0;
//                visited[j] = true;
            }
        }

        //开始拓扑排序
        for (int i = 1; i < n + 2; i++) {
            int tarPoint = -1;
            //选取入度为0的点作为起点
            for (int j = 1; j < n + 2; j++) {
                if (!visited[j] && inDegree[j] == 0) {
                    tarPoint = j;
                }
            }
            if (tarPoint == -1) {
                System.out.println("图有闭环，结束");
                return;
            }
            System.out.println("选取点：" + tarPoint);
            list.add(tarPoint);
            int maxCost = -1;
            for (int k = 1; k < n + 2; k++) {
                //更新入度
                if (cost[tarPoint][k] > 0 && !visited[k]) {
                    inDegree[k]--;
                }
                //更新事件的最早发生时间
                if (visited[k] && cost[k][tarPoint] > 0 && ve[k] + cost[k][tarPoint] > maxCost) {
                    maxCost = ve[k] + cost[k][tarPoint];
                }
            }
            visited[tarPoint] = true;
            if (maxCost != -1) {
                ve[tarPoint] = maxCost;
            }
        }

        System.out.println("最后的ve:" + Arrays.toString(ve));
        //n=7，但大小是8

        //至此，事件的最早开始时间已经全部计算完毕，下面计算事件的最晚开始时间
        vl[n + 1] = ve[n + 1];
        boolean[] reVisited = new boolean[n + 2];
        reVisited[n + 1] = true;
        for (int i = 1; i < n + 2; i++) {
            Integer reTarPoint = list.get(n + 1 - i);
            int reMaxcost = Integer.MAX_VALUE;
            for (int k = 1; k < n + 2; k++) {
                if (reVisited[k] && cost[reTarPoint][k] > 0 && vl[k] - cost[reTarPoint][k] < reMaxcost) {
                    reMaxcost = vl[k] - cost[reTarPoint][k];
                }
            }
            if (reMaxcost != Integer.MAX_VALUE) {
                vl[reTarPoint] = reMaxcost;
                reVisited[reTarPoint] = true;
            }
        }
        System.out.println("vl:" + Arrays.toString(vl));

        System.out.println("------------------------------");
        System.out.println("------------------------------");
        System.out.println("------------------------------");
        System.out.println("------------------------------");

        System.out.println("最终结果：");
        System.out.println(ve[n + 1]);
        int chushu = new Double(Math.pow(10d, 9) + 7).intValue();
        long sum = 1;
        for (int k = 1; k < n + 1; k++) {
            sum *= vl[k] - ve[k] + 1;
        }
        System.out.println(sum % chushu);

    }

}

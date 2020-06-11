package com.algorithm.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created on 2020/3/4
 * 关键活动，根据拓扑排序改编而来
 * 默认事件1为第一个发生事件，事件N为最后一个发生的事件
 *
 * @author WuYi
 */
public class CriticalPath {
    public static List <Integer> topo = new ArrayList();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();//顶点数
        int m = scanner.nextInt();//边数

        int[] inDegree = new int[n + 1];//记录各个顶点的入度
        boolean[] visited = new boolean[n + 1];//记录各个顶点是否访问完毕
        Arrays.fill(visited, false);

        int[] ve = new int[n + 1];//记录事件最早发生时间
        int[] vl = new int[n + 1];//记录事件最晚发生时间
        int[] e = new int[m + 1];//记录活动的最早开始时间,这一部分我暂时未实现
        int[] l = new int[m + 1];//记录活动的最迟开始时间,这一部分我暂时未实现

        Arrays.fill(ve, Integer.MIN_VALUE);
        Arrays.fill(vl, Integer.MAX_VALUE);
        Arrays.fill(e, Integer.MAX_VALUE);
        Arrays.fill(l, Integer.MAX_VALUE);
        ve[1] = 0;//第一个点初始化为0

        int[][] cost = new int[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(cost[i], Integer.MIN_VALUE);
        }
        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int length = scanner.nextInt();
            cost[x][y] = length;
            inDegree[y]++;//入度+1
        }
        System.out.println(Arrays.toString(inDegree));
        for (int i = 1; i < n + 1; i++) {
            int targetPoi = -1;
            //选取入度为0的节点
            for (int j = 1; j < n + 1; j++) {
                if (inDegree[j] == 0 && !visited[j]) {
                    targetPoi = j;
                }
            }

            if (targetPoi == -1) {
                System.out.println("找不到入度为0的节点，图有问题(有闭环)，将会退出");//这一段可以优化
                return;
            }
            System.out.println("选取节点：" + targetPoi);
            topo.add(targetPoi);//保存拓扑信息

            visited[targetPoi] = true;

            int maxcost = Integer.MIN_VALUE;//记录事件的最早发生时间
            //更新拓扑结构
            for (int k = 1; k < n + 1; k++) {
                if (cost[targetPoi][k] > 0) {
                    inDegree[k]--;
                }
                if (visited[k] && cost[k][targetPoi] > 0 && (ve[k] + cost[k][targetPoi]) > maxcost) {
                    maxcost = ve[k] + cost[k][targetPoi];
                }
            }
            if (maxcost != Integer.MIN_VALUE) {
                ve[targetPoi] = maxcost;
            }

        }
//        System.out.println(topo);
        System.out.println("ve:"+Arrays.toString(ve));
        //至此，事件的最早开始时间全部计算完毕，下面计算事件的最晚发生时间
        boolean[] reVisited = new boolean[n + 1];
        Arrays.fill(reVisited, false);
        reVisited[n] = true;
        vl[n] = ve[n];
        for (int i = 1; i < n + 1; i++) {
            int reMaxcost = Integer.MAX_VALUE;//记录事件的最晚发生时间
            int reTargetPoi = topo.get(n - i);
            for (int k = 1; k < n + 1; k++) {

                if (reVisited[k] && cost[reTargetPoi][k]>0 && vl[k] - cost[reTargetPoi][k] < reMaxcost) {
                    reMaxcost = vl[k] - cost[reTargetPoi][k];
                }
            }
            if (reMaxcost != Integer.MAX_VALUE) {
                vl[reTargetPoi] = reMaxcost;
                reVisited[reTargetPoi] = true;
            }
        }

        System.out.println("vl:" + Arrays.toString(vl));

    }

}

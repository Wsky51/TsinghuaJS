package com.pekingoj;

import java.util.*;

/**
 * Created on 2020/3/27
 * http://poj.org/problem?id=1273    poj 1273
 *
 * @author WuYi
 */
public class DrainageDitches {
    static int n, m;//n个节点，m条边
    static int[] prev;//prev[i]记录流向i点的前驱节点
    static int[] r;////记录在一条增广路中，流到i点时，此刻增广路上残余量的最小值，直到i == t时就是整条增广路上的残余量最小值
    static int[][] c;        //记录i到j的剩余流量
    static int count=0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List <Integer> list = new ArrayList <Integer>();
        while (true) {
            String rawLine = scanner.nextLine();

            String[] line = rawLine.split(" ");
            m = Integer.parseInt(line[0]);
            n = Integer.parseInt(line[1]);

            prev = new int[n + 1];
            c = new int[n + 1][n + 1];
            r = new int[n + 1];

            for (int i = 1; i <= m; i++) {
                line=scanner.nextLine().split(" ");
                int x = Integer.parseInt(line[0]);
                int y = Integer.parseInt(line[1]);//
                int capacity = Integer.parseInt(line[2]);//容量
                c[x][y] += capacity;
            }
            list.add(edmondsKarp(1, n));
            if (!scanner.hasNextLine()){
                break;
            }
        }
        for (int re : list) {
            System.out.println(re);
        }

    }

    //s为起点，t为终点

    public static int edmondsKarp(int s, int t) {
        int ans = 0;
        while (true) {
            //EK算法的核心是通过bfs不断查找增广路，同时建立反向弧
            //每次循环都要对v数组和p数组进行清空，因为是意图查找一条新的增广路了
            Arrays.fill(prev, -1);
            Arrays.fill(r, 0);
            Queue <Integer> queue = new LinkedList <Integer>();
            queue.add(s);//将首节点入栈
            r[s] = Integer.MAX_VALUE;

            //这一段类似于bfs
            while (!queue.isEmpty()) {
                Integer v = queue.remove();
                for (int i = 1; i < n + 1; i++) {
                    if (r[i] == 0 && c[v][i] > 0) {//r[i]原本是记录增广路实时的残量最小值，r[i]==0代表这个点还没有走过，且从p到i的残量大于0说明通路
                        r[i] = Math.min(r[v], c[v][i]);//实时更新r[i]的值，r[f]存储1条增广路中i点前所有水管残量的最小值，v[i]为该条增广路到i点为止，路径上的最小残量
                        prev[i] = v;//记录父节点信息
                        queue.add(i);//该节店入栈
                    }
                }
            }

            if (r[t] == 0) {//如果r[t]==0则代表找不到增广路了（中途出现了c[i][j]==0的情况）
                break;
            }

            ans += r[t];

            int temp = t;
            while (temp != s) {//若当前节点没有回溯到源点，一直更新其剩余流量的信息
                c[prev[temp]][temp] -= r[t];
                c[temp][prev[temp]] += r[t];//反向边
                temp = prev[temp];
            }

        }
        return ans;
    }

}

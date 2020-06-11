package com.pat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created on 2020/3/24
 * https://www.nowcoder.com/pat/5/problem/4315
 * 这个算法有一半的测试用例过了，有一半没过，目前还不清楚没过的那部分是什么原因
 * @author WuYi
 */
public class AllRoadsLeadtoRome {
    static List <String> list = new ArrayList <>();
    static int n;
    static int m;
    static String startStr;
    static int start;
    static int[] happiness;
    static List <Edge>[] edges;
    static int[] dist;
    static boolean[] visit;
    static boolean[] inPq;//是否在优先级队列中
    static int[] prev;//保存父节点信息
    static int[] pathNum;//记录第i个节点从起始节点到节点i一共有多少条最短路径
    static int[] happyAll;
    static int[] count;//记录从起点到终点一共有多少个节点

    public static void main(String[] args) throws IOException {
        init();
        int end = getIdByName("ROM");
        dijkstra(start, end);

        //输出第一行的数据
        System.out.print(dist[end] + " ");
        System.out.print(pathNum[end] + " ");
        calculeHappy(end);

        //输出第二行的数据
        printPath(start, end);

    }

    public static void calculeHappy(int end) {
        int totalHappy = 0, count = 0;
        int temp = end;
        LinkedList <Integer> list = new LinkedList <>();
        while (temp != start) {
            totalHappy += happiness[temp];
            count++;
            temp = prev[temp];
        }
        System.out.print(totalHappy + " ");
        System.out.println((totalHappy / count) + " ");

    }

    public static void printPath(int start, int end) {
        int temp = end;
        LinkedList <String> linkedList = new LinkedList <>();
        while (start != temp) {
            linkedList.addFirst(list.get(temp));
            temp = prev[temp];
        }

        System.out.print(startStr);

        while (!linkedList.isEmpty()) {
            System.out.print("->" + linkedList.remove());
        }
    }

    public static void dijkstra(int beg, int end) {
        PriorityQueue <Integer> pq = new PriorityQueue <>(new Comparator <Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return dist[o1] - dist[o2];
            }
        });
        pq.add(beg);
        inPq[beg] = true;
        while (!pq.isEmpty()) {

            Integer v = pq.remove();//选取目标节点
            inPq[v] = false;
            visit[v] = true;

            for (Edge e : edges[v]) {
                int u = e.other(v);//v的下一节点
                if (!visit[u]) {//如果被选取的节点没有被访问的话
                    if (dist[u] > (dist[v] + e.len)) {
                        dist[u] = dist[v] + e.len;
                        prev[u] = v;
                        pathNum[u] = pathNum[v];

                        happyAll[u] = happyAll[v] + happiness[u];
                        count[u] = count[v] + 1;
                    } else if (dist[u] == dist[v] + e.len) {//如果路径相等的情况下进行进一步比较
                        if (happyAll[v] + happiness[u] > happyAll[u]) {
                            prev[u] = v;
                        } else if (happyAll[v] + happiness[u] == happyAll[u]) {
                            if (count[v] + 1 < count[u]) {
                                prev[u] = v;
                            }
                        }
                        pathNum[u] += pathNum[v];
                    }
                    if (!inPq[u]) {
                        pq.add(u);
                        inPq[u] = true;
                    }
                }
            }
        }

    }

    private static void init() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] line = bf.readLine().split(" ");
        n = Integer.parseInt(line[0]);//n个城市
        m = Integer.parseInt(line[1]);//m条边

        edges = new List[n];
        edges[0] = new ArrayList <>();
        dist = new int[n];
        visit = new boolean[n];
        prev = new int[n];
        happiness = new int[n];
        inPq = new boolean[n];
        pathNum = new int[n];
        happyAll = new int[n];
        count = new int[n];

        Arrays.fill(prev, -1);
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        pathNum[0] = 1;

        startStr = line[2];
        start = 0;
        list.add(line[2]);//起点城市定为0号
        for (int i = 1; i < n; i++) {
            line = bf.readLine().split(" ");
            list.add(line[0]);
            happiness[i] = Integer.parseInt(line[1]);

            edges[i] = new ArrayList <>();
        }

        for (int i = 0; i < m; i++) {//处理m条边
            line = bf.readLine().split(" ");
            int x = getIdByName(line[0]);
            int y = getIdByName(line[1]);
            int len = Integer.parseInt(line[2]);
            Edge e = new Edge(x, y, len);
            edges[x].add(e);//无向图
            edges[y].add(e);
        }
    }

    public static int getIdByName(String name) {
        return list.indexOf(name);
    }

//    private static int cmp(int v, int u) {
//        int tempW = prev[u];//保存w的值
//        int tempV = v;
//        int happySumW = 0, happySumV = 0;
//        int countW = 0, countV = 0;
//        while (tempW != start) {
//            happySumW += happiness[tempW];
//            countW++;
//            tempW = prev[tempW];
//        }
//
//        while (tempV != start) {
//            happySumV += happiness[tempV];
//            countV++;
//            tempV = prev[tempV];
//        }
//
//        if (happySumW > happySumV) {
//            return prev[u];
//        } else if (happySumW < happySumV) {
//            return v;
//        } else {
//            if (countW < countV) {
//                return prev[u];
//            } else {
//                return v;
//            }
//        }
//
//    }

}

class Edge {
    int x;
    int y;
    int len;

    public Edge(int x, int y, int len) {
        this.x = x;
        this.y = y;
        this.len = len;
    }

    public int other(int v) {
        if (v != x && v != y) {
            System.out.println("Warn!!!a wrong param");
            return -1;
        }
        return this.x == v ? y : x;
    }
}

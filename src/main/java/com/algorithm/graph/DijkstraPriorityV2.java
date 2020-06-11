package com.algorithm.graph;

import java.util.*;

/**
 * Created on 2020/3/24
 * 最短路径，优先级队列第二种算法
 *
 * @author WuYi
 */
public class DijkstraPriorityV2 {
    static List <Integer>[] prev;
    static boolean[] marked;
    static int[] dist;
    public static ArrayList <edge>[] adj;//邻接表

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();//N为节点个数
        int M = scanner.nextInt();//M为边的个数

        prev = new List[N + 1];
        marked = new boolean[N + 1];
        dist = new int[N + 1];
        adj = new ArrayList[N + 1];

        for (int i = 0; i < N + 1; i++) {
            prev[i] = new ArrayList <>();
            adj[i] = new ArrayList <>();
        }
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        //输入数据
        for (int i = 0; i < M; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int len = scanner.nextInt();
            edge e = new edge(x, y, len);
            adj[x].add(e);//无向图
            adj[y].add(e);
        }
        dijktra(1, 8);
        System.out.println(Arrays.toString(dist));
        List <ArrayList <Integer>> list=new ArrayList <>();
        findPath(1,8,new LinkedList <>(),list);
        System.out.println(list);

    }

    public static void dijktra(int start, int end) {
        PriorityQueue <Integer> pq = new PriorityQueue <>(16, new Comparator <Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return dist[o1] - dist[o2];
            }
        });
        pq.add(start);
        while (!pq.isEmpty()) {
//            while(marked[pq.peek()]){
//                pq.remove();
//            }
            System.out.println("删除节点前pq："+pq);
            int v = pq.remove();
            System.out.println("del节点："+v);
            System.out.println("删除节点后pq"+pq);
            if (v == end) return;//到达了目标点
            marked[v] = true;
            for (edge e : adj[v]) {//对于被删除节点的每一条边进行遍历
                int w = e.other(v);
                if (!marked[w]) {
                    if (dist[w] > (dist[v] + e.t)) {
                        dist[w] = dist[v] + e.t;
                        prev[w].clear();
                        prev[w].add(v);
                    } else if (dist[w] == (dist[v] + e.t)) {//如果相等的话
                        prev[w].add(v);
                    }
                    if (!pq.contains(w)){//避免重复加入
                        pq.add(w);
                    }
                }
            }
        }
    }

    //start 为起点，end 为终点,查询在最短路径下从start到end的具体路径信息，支持同时有多条最短路径，结果存放在List <ArrayList <Integer>> list中
    private static void findPath(int start, int end, LinkedList <Integer> path, List <ArrayList <Integer>> list) {
        if (start == end) {
            path.addFirst(start);
            list.add(new ArrayList <Integer>(path));

            path.remove();
            return;
        }

        List <Integer> pre = prev[end];
        for (int a : pre) {
            path.addFirst(end);
            findPath(start, a, path, list);
            path.remove();
        }
    }
}

class edge {
    int v;//起始节点
    int w;//终止节点
    int t;//权重

    public edge(int v, int w, int t) {//从v到w,代价为t
        this.v = v;
        this.w = w;
        this.t = t;
    }

    public int other(int a) {
        return v == a ? w : v;
    }

    @Override
    public String toString() {
        return v + "-" + w + ":" + t;
    }
}

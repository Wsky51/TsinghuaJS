package com.pat;

import java.util.*;

/**
 * Created on 2020/3/23
 * https://www.nowcoder.com/pat/5/problem/4324， 最短路径，在最短路径下加了一些条件
 * @author WuYi
 */
public class PublicBikeManagemen {
    public static final int UN_REACHABLE = 100000;
    static List <Integer>[] prev;
    static int[] capacity;
    static int PERFECT;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int cmax = scanner.nextInt();//每个站点最多容纳的自行车数量
        int n = scanner.nextInt();//有多少个站点，不算管理中心的0号站点
        int sp = scanner.nextInt();//出问题站点的编号
        int m = scanner.nextInt();//有多少条路径
        PERFECT = cmax / 2;

        prev = new ArrayList[n + 1];//记录第i个节点前面的父节点(注意，可能有多个)
        capacity = new int[n + 1];
        int[][] cost = new int[n + 1][n + 1];
        int[] dist = new int[n + 1];//第i个节点的路径长度
        boolean[] visitedFlag = new boolean[n + 1];//保存该节点是否被访问过

        //初始化数据
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(cost[i], UN_REACHABLE);
        }
        Arrays.fill(dist, UN_REACHABLE);
        for (int i = 0; i < n + 1; i++) {
            prev[i] = new ArrayList <>();
        }

        for (int i = 1; i < n + 1; i++) {
            capacity[i] = scanner.nextInt();//编号为i的节点当前的容量
        }

        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int s = scanner.nextInt();
            cost[x][y] = cost[y][x] = s;//保存费用
        }

        //更新第一个节点相关的
        for (int i = 1; i < n + 1; i++) {
            if (cost[0][i] == UN_REACHABLE) {
                continue;
            }
            dist[i] = cost[0][i];
            prev[i].add(0);
        }

        //进行n次循环
        for (int i = 1; i < n + 1; i++) {

            int tarCost = UN_REACHABLE;
            int tarPath = -1;
            //选取距离最小的点
            for (int j = 1; j < n + 1; j++) {
                if (tarCost > dist[j] && !visitedFlag[j]) {
                    tarCost = dist[j];
                    tarPath = j;
                }
            }

            if (tarPath != -1) {
//                System.out.println("当前选取的点为：" + tarPath);
                visitedFlag[tarPath] = true;
            }

            //更新点
            for (int k = 1; k < (n + 1) && tarPath != -1; k++) {
                if (!visitedFlag[k] && dist[tarPath] + cost[tarPath][k] <= dist[k]) {
                    if (dist[tarPath] + cost[tarPath][k] < dist[k]) {
                        dist[k] = dist[tarPath] + cost[tarPath][k];
                        prev[k].clear();
                    }
                    prev[k].add(tarPath);
                }
            }
        }

//        System.out.println(Arrays.toString(dist));
//        System.out.println(Arrays.deepToString(prev));

        // 找到所有的路径
        ArrayList <ArrayList <Integer>> list = new ArrayList <ArrayList <Integer>>();
        LinkedList <Integer> path = new LinkedList <Integer>();
        findPath(0, sp, path, list);
//        System.out.println("list:" + list);
        calculate(list, 0, sp, cmax);

    }

    public static void searchPath(List <Integer>[] prev, int from, int to) {
        if (from == to) {
            System.out.print(to);
            return;
        }
        for (int i = 0; i < prev[to].size(); i++) {
            System.out.print(to + "->" + prev[to].get(i));
        }
    }

    //res为起点，proSta为终点
    private static void findPath(int res, int proSta, LinkedList <Integer> path, List <ArrayList <Integer>> list) {
        if (res == proSta) {
            path.addFirst(res);
            list.add(new ArrayList <Integer>(path));

            path.remove();
            return;
        }

        List <Integer> pre = prev[proSta];
        for (int a : pre) {
            path.addFirst(proSta);
            findPath(res, a, path, list);
            path.remove();
        }
    }

    private static void calculate(List <ArrayList <Integer>> lists, int start, int end, int cmax) {
        List <Integer> tempList = new ArrayList <>();
        int tempSend = Integer.MAX_VALUE, tempBack = Integer.MAX_VALUE;
        for (List <Integer> list : lists) {//一条可能的路径

            int send = 0;
            int back = 0;
            int curCap = 0;

            for (int node : list) {
                if (node == start) {
                    continue;
                }
                if (node == end) {
                    if (capacity[node] >= PERFECT) {//若最后节点有多余车辆且容量也有多余的
                        back = capacity[node] - PERFECT + curCap;
                    } else if (curCap >= (PERFECT - capacity[node])) {//若最后节点容量不足且当前容量足够，就从当前容量中调出
                        curCap -= (PERFECT - capacity[node]);
                        back = curCap;
                    } else {//容量不足，当前容量也不足
                        back = 0;
                        send += (PERFECT - capacity[node] - curCap);
                    }
                    break;
                }
                int t = capacity[node] - PERFECT;
                if (t >= 0 || curCap > Math.abs(t)) {//说明当前节点有剩余,或者当前节点少车，检查当前容量是否满足要求，如果满足，那就直接从容量中减去
                    curCap += t;
                } else {//否则，必须从车库中调用车
                    send += (Math.abs(t) - curCap);//把当前容量用完，在检查余量
                    curCap = 0;
                }
            }


            if (send < tempSend) {//优先找派出车辆少的
                tempList = list;
                tempSend = send;
                tempBack = back;
            } else if (send == tempSend) {//如果车辆数一样，那就选择送回数少的
                if (tempBack < back) {
                    tempList = list;
                    tempSend = send;
                    tempBack = back;
                }
            }
        }
        System.out.print(tempSend + " 0");
        for (int i = 1; i < tempList.size(); i++) {
            System.out.print("->" + tempList.get(i));
        }
        System.out.print(" " + tempBack);

    }

}












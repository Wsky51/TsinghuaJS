package com.algorithm.graph;

import java.util.*;

/**
 * Created on 2020/3/12
 * 注意，这个算法暂时有些问题
 * @author WuYi
 */
public class DijkstraWithPriorityQueue {

    public static final int UN_REACHABLE = 100000;
    //    public static final int VISITED = Integer.MAX_VALUE;
    int[] res;
    Queue <Integer> priorityQueue;
    int[] dist;//保存点i到点1的最短距离
    Comparator <Integer> myCompartor = new Comparator <Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return dist[o1] - dist[o2];
        }
    };

    public static void main(String[] args) {
        DijkstraWithPriorityQueue test=new DijkstraWithPriorityQueue();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();//N为节点个数
        int m = scanner.nextInt();//M为边的个数
        test.dist = new int[n + 1];
        int[][] cost = new int[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(cost[i], UN_REACHABLE);//初始化，MAX_VALUE为不可达
        }

        //输入数据
        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int len = scanner.nextInt();
//            cost[x][y] = len;//注意此处说明是有向图
            cost[x][y] = cost[y][x] = len;//如果上面那一个改成这个，就是无向图了
        }
        test.dijkstraWithPriorityQueue(cost, n);
    }

    /**
     * 这个算法和上面的dijkstra算法不同点在于使用了优先级队列PriorityQueue（小顶堆）来每次选取权值最小的顶点
     *
     * @param cost
     * @param n
     */
    public void dijkstraWithPriorityQueue(int[][] cost, int n) {
        int[] dist = new int[n + 1];//保存点i到点1的最短距离
        int[] parent = new int[n + 1];//保存节点的父节点信息,parent[i]=k代表第i个节点的父亲是k
        boolean[] visitedFlag = new boolean[n + 1];//保存该节点是否被访问过
        Arrays.fill(dist, UN_REACHABLE);
        Arrays.fill(parent, -1);//-1代表父节点信息为空
        dist[1] = 0;
        priorityQueue = new PriorityQueue <>(myCompartor);
        priorityQueue.add(1);
        //更新与第一个节点相连的所有节点路径长度
        for (int i = 2; i < n + 1; i++) {//找到所有与第一个顶点相连的那些点并更新
            if (cost[1][i] == UN_REACHABLE) {
                continue;
            }
            System.out.println("当前节点为i:" + i + ",cost值为：" + cost[1][i]);
//            dist[i] = cost[1][i];
//            priorityQueue.add(i);
            parent[i] = 1;
        }
        System.out.println("初始化队列为：" + priorityQueue + ",dist:" + Arrays.toString(dist));

        //当优先级小顶堆队列不为空，就一直更新节点信息
        while (!priorityQueue.isEmpty()) {
            int u = priorityQueue.remove();
            if (visitedFlag[u]) {//如果该节点已经被访问过了，就直接跳过
                continue;
            }
            visitedFlag[u] = true;
            System.out.println("当前节点选择：" + u + ",dist[" + u + "]=" + dist[u]);

            //更新所有与当前目标顶点有连接关系的顶点的优先级并加入到优先级队列中去
            for (int k = 2; k < n + 1; k++) {
                if (!visitedFlag[k] && (dist[u] + cost[u][k] < dist[k])) {
                    dist[k] = dist[u] + cost[u][k];
                    System.out.println("要把节点k=" + k + "加入到优先级队列中，此时dist[" + k + "]" + dist[k]);
                    priorityQueue.add(k);
                    parent[k] = u;
                }
            }
            System.out.println("更新之后：priorityQueue为：" + priorityQueue + ",dist:" + Arrays.toString(dist));

        }
        System.out.println("路径信息：" + Arrays.toString(dist));//保存最后结果
        System.out.println("父节点信息：" + Arrays.toString(parent));

    }

}

/**
 * 注意，此类用于优先级队列找priority最小的节点(小顶堆)，使得每次的寻找时间复杂度降至O(logn),而不是O(N)
 */
class Node implements Comparable <Node> {
    int id;
    int priority;//该节点的优先级，注意此值越小，代表其越会被优先访问

    @Override
    public int compareTo(Node o) {
        return this.priority - o.priority;
    }

    public Node(int id, int priority) {
        this.id = id;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", priority=" + priority +
                '}';
    }
}

package com.pat;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created on 2020/4/1
 *
 * @author WuYi
 */
public class AcuteStroke {
    static int n;
    static int m;
    static int l;
    static int t;
    static int[][][] cost;
    static boolean[][][] isCount;
    static boolean[][][] inq;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();//n行
        m = scanner.nextInt();//m列
        l = scanner.nextInt();//第l层
        t = scanner.nextInt();//阈值

        cost = new int[l][n][m];
        isCount = new boolean[l][n][m];
        inq = new boolean[l][n][m];

        //初始化完毕
        for (int i = 0; i < l; i++) {//第i层
            for (int j = 0; j < n; j++) {//第j行
                for (int k = 0; k < m; k++) {//第k列
                    cost[i][j][k] = scanner.nextInt();
                }
            }
        }

        initIsCount();
        int sum = 0;

        for (int i = 0; i < l; i++) {//第i层
            System.out.println(Arrays.deepToString(isCount[i]));
            for (int j = 0; j < n; j++) {//第j行
                for (int k = 0; k < m; k++) {//第k列
                    if (isCount[i][j][k]) {
                        sum++;
                    }
                }
            }
        }
        System.out.println(sum);

//        int sum = 0;
//        Queue <Node> queue = new LinkedList <>();
//        queue.add(new Node(0, 0, 0));
//        while (!queue.isEmpty()) {
//            Node n = queue.remove();
//            inq[n.layer][n.x][n.y] = false;
//            if (checkPoi(n.x + 1, n.y, n.layer) && !inq[n.layer][n.x + 1][n.y]) {//同一层的下一行
//                queue.add(new Node(n.x + 1, n.y, n.layer));
//                inq[n.layer][n.x + 1][n.y] = true;
//            }
//            if (checkPoi(n.x, n.y + 1, n.layer) && !inq[n.layer][n.x][n.y + 1]) {//同一层的下一列
//                queue.add(new Node(n.x, n.y + 1, n.layer));
//                inq[n.layer][n.x][n.y + 1] = true;
//            }
//            if (checkPoi(n.x, n.y, n.layer + 1) && !inq[n.layer + 1][n.x][n.y]) {//下一层
//                queue.add(new Node(n.x, n.y, n.layer + 1));
//                inq[n.layer + 1][n.x][n.y] = true;
//            }
//
//            if (isCount[n.layer][n.x][n.y] && cost[n.layer][n.x][n.y] > 0) {
//                sum += cost[n.layer][n.x][n.y];
//            }
//        }
//
//        System.out.println(sum);
    }

    public static void initIsCount() {

        for (int i = 0; i < l; i++) {//第i层
            for (int j = 0; j < n; j++) {//第j行
                for (int k = 0; k < m; k++) {//第k列
                    boolean inRange = cost[i][j][k] > 0;

                    boolean layerUp = inRange;
                    for (int o = 1; o < t; o++) {//阈值
                        layerUp = layerUp && checkPoi(i + o, j, k) && cost[i + o][j][k] > 0;
                    }
                    if (layerUp) {//如果层可以的话
                        for (int o = 0; o < t; o++) {
                            isCount[i + o][j][k] = true;
                        }
                    }

                    boolean xUp = inRange;
                    for (int o = 1; o < t; o++) {//阈值
                        xUp = xUp && checkPoi(i, j + o, k) && cost[i][j + o][k] > 0;
                    }
                    if (xUp) {//如果层可以的话
                        for (int o = 0; o < t; o++) {
                            isCount[i][j + o][k] = true;
                        }
                    }

                    boolean yUp = inRange;
                    for (int o = 1; o < t; o++) {//阈值
                        yUp = yUp && checkPoi(i, j, k + o) && cost[i][j][k + o] > 0;
                    }
                    if (yUp) {//如果层可以的话
                        for (int o = 0; o < t; o++) {
                            isCount[i][j][k + o] = true;
                        }
                    }

                }
            }
        }
    }

//    public static boolean isCount(int x, int y, int layer) {
//        if (checkPoi(x, y, layer + 1) && cost[layer + 1][x][y] > 0) {//其下一层有点
//            return true;
//        }
//        if (checkPoi(x, y, layer - 1) && cost[layer - 1][x][y] > 0) {//上一层有点
//            return true;
//        }
//        if (checkPoi(x, y + 1, layer) && cost[layer][x][y + 1] > 0) {//下一列有点
//            return true;
//        }
//        if (checkPoi(x, y - 1, layer) && cost[layer][x][y - 1] > 0) {
//            return true;
//        }
//        if (checkPoi(x + 1, y, layer) && cost[layer][x + 1][y] > 0) {
//            return true;
//        }
//        if (checkPoi(x - 1, y, layer) && cost[layer][x - 1][y] > 0) {
//            return true;
//        }
//        return false;
//    }

    public static boolean checkPoi(int layer, int x, int y) {
        if (layer < 0 || layer >= l) {
            return false;
        }
        if (x < 0 || x >= n) {
            return false;
        }
        if (y < 0 || y >= m) {
            return false;
        }

        return true;
    }

}

class Node {
    int x;
    int y;
    int layer;

    public Node(int x, int y, int layer) {
        this.x = x;
        this.y = y;
        this.layer = layer;
    }
}
package com.leetcode.onetaskperday;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created on 2020/4/8
 *
 * @author WuYi
 */
public class TheRangeOfRobot {
    public static void main(String[] args) {
        System.out.println(movingCount(2,3,0));
    }

    public static int movingCount(int m, int n, int k) {
        if (k == 0) {
            return 1;
        }
        int[][] data = new int[m][n];
        boolean[][] visit = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int sum = bitSum(i, j);
                data[i][j] = sum;
                if (sum > k) {
                    visit[i][j] = true;//表示该点不可到达
                }
            }
        }

        int count = 0;
        Queue <Node> queue = new LinkedList <>();
        queue.add(new Node(0, 0));
        while (!queue.isEmpty()) {
            Node cur = queue.remove();

            int x = cur.x;
            int y = cur.y;

            if (visit[x][y]) {
                continue;
            }
            count++;
            visit[x][y] = true;

            if (isOk(m, n, x - 1, y) && !visit[x - 1][y]) {
                queue.add(new Node(x - 1, y));
            }
            if (isOk(m, n, x + 1, y) && !visit[x + 1][y]) {
                queue.add(new Node(x + 1, y));
            }
            if (isOk(m, n, x, y - 1) && !visit[x][y - 1]) {
                queue.add(new Node(x, y - 1));
            }
            if (isOk(m, n, x, y + 1) && !visit[x][y + 1]) {
                queue.add(new Node(x, y + 1));
            }
        }
        return count;
    }

    static boolean isOk(int m, int n, int x, int y) {
        if (x < 0 || x >= m) {
            return false;
        }
        if (y < 0 || y >= n) {
            return false;
        }
        return true;
    }

    //计算数位和
    static int bitSum(int a, int b) {
        return calBit(a) + calBit(b);
    }

    static int calBit(int a) {
        int sum = 0;
        while (a != 0) {
            sum += a % 10;
            a /= 10;
        }
        return sum;
    }

    static void changeA(int a) {
        a = -1;
    }

    static class Node {
        int x;
        int y;

        public Node(int a, int b) {
            x = a;
            y = b;
        }
    }

}

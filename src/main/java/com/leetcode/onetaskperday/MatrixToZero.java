package com.leetcode.onetaskperday;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created on 2020/4/15
 *
 * @author WuYi
 */
public class MatrixToZero {
    static int n, m;

    public static void main(String[] args) {
        MatrixToZero demo = new MatrixToZero();
        int[][] matrix = {{0, 0, 0}, {
                0, 1, 0}, {
                1, 1, 1}};


        demo.updateMatrix(matrix);

    }

    public int[][] updateMatrix(int[][] matrix) {

        n = matrix.length;
        if (n == 0) {
            return null;
        }
        m = matrix[0].length;
        int[][] res = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(res[i], Integer.MAX_VALUE);
        }

        Queue <Node> queue = new LinkedList <>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    res[i][j] = 0;
                    queue.add(new Node(i, j, 0));
                }
            }
        }

        while (!queue.isEmpty()) {
            Node node = queue.remove();
            if (isOk(node.x + 1, node.y) && res[node.x + 1][node.y] == Integer.MAX_VALUE) {
                queue.add(new Node(node.x + 1, node.y, node.step + 1));
                res[node.x + 1][node.y] = node.step + 1;
            }
            if (isOk(node.x - 1, node.y) && res[node.x - 1][node.y] == Integer.MAX_VALUE) {
                queue.add(new Node(node.x - 1, node.y, node.step + 1));
                res[node.x - 1][node.y] = node.step + 1;
            }
            if (isOk(node.x, node.y + 1) && res[node.x][node.y + 1] == Integer.MAX_VALUE) {
                queue.add(new Node(node.x, node.y + 1, node.step + 1));
                res[node.x][node.y + 1] = node.step + 1;
            }
            if (isOk(node.x, node.y - 1) && res[node.x][node.y - 1] == Integer.MAX_VALUE) {
                queue.add(new Node(node.x, node.y - 1, node.step + 1));
                res[node.x][node.y - 1] = node.step + 1;
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(res[i]));
        }
        return res;
    }

    public boolean isOk(int x, int y) {
        if (x < 0 || x >= n) {
            return false;
        }
        if (y < 0 || y >= m) {
            return false;
        }
        return true;
    }

    class Node {
        int x;
        int y;
        int step;

        public Node(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }

}
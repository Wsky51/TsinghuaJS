package com.algorithm.dynamic;

/**
 * Created on 2020/3/8
 * 最短路径问题，leetcode,
 * @author WuYi
 */
public class MinimumPathSum {
    public static void main(String[] args) {
        int[][] cost = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        int m = cost.length;//n*m的矩阵
        int n = cost[0].length;
        int[][] min = new int[m][n];
        cal(cost,min,m,n);
        System.out.println(min[m-1][n-1]);

    }

    static void cal(int[][] cost, int[][] min, int m, int n) {
        min[0][0] = cost[0][0];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (i == 0) {
                    min[i][j] = min[i][j - 1] + cost[i][j];
                } else if (j == 0) {
                    min[i][j] = min[i - 1][j] + cost[i][j];
                } else {
                    min[i][j] = Math.min(min[i - 1][j], min[i][j - 1]) + cost[i][j];
                }

            }
        }
    }
    public int minPathSum(int[][] grid) {
        int m = grid.length;//n*m的矩阵
        int n = grid[0].length;
        int[][] min = new int[m][n];
        cal(grid,min,m,n);
        return min[m-1][n-1];
    }

}
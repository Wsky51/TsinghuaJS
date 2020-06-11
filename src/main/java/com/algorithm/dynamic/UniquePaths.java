package com.algorithm.dynamic;

/**
 * Created on 2020/3/9
 * leetcode 62 不同路径,小A需要从点(1,1)走到点(n,m),问，有多少种不同的走法
 * @author WuYi
 */
public class UniquePaths {
    public static void main(String[] args) {
        System.out.println(uniquePaths2(new int[][]{{0,0,0},{0,1,0},{0,0,0}}));
    }
    public static int uniquePaths(int m, int n) {
        int [][]res=new int[n+1][m+1];//n是行，m是列
        res[1][1]=1;
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < m+1; j++) {
                if (i==1&&j==1){
                    continue;
                }
                if (i==1){
                    res[i][j]=res[i][j-1];
                }
                else if (j==1){
                    res[i][j]=res[i-1][j];
                }else{
                    res[i][j]=res[i-1][j]+res[i][j-1];
                }

            }

        }

        return res[n][m];


    }

    /**
     * Leecode 第63题，不同路径2，加入了障碍物
     * @param obstacleGri
     * @return
     */
    public static int uniquePaths2(int[][] obstacleGri) {
        int n = obstacleGri.length;//行数
        int m = obstacleGri[0].length;//列数
        int [][]res=new int[n+1][m+1];//n是行，m是列
        res[1][1]=1;
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < m+1; j++) {
                if (obstacleGri[i-1][j-1]==1){//说明此处有障碍物
                    res[i][j]=0;
                    continue;
                }
                if (i==1&&j==1){
                    continue;
                }
                if (i==1){
                    res[i][j]=res[i][j-1];
                }
                else if (j==1){
                    res[i][j]=res[i-1][j];
                }else{
                    res[i][j]=res[i-1][j]+res[i][j-1];
                }

            }

        }

        return res[n][m];

    }
}
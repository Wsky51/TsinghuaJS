package com.leetcode.onetaskperday;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created on 2020/4/20
 *
 * @author WuYi
 */
public class IslandNum {
    public static void main(String[] args) {
        IslandNum demo = new IslandNum();
        System.out.println(demo.numIslands(new char[][]{
                {'1','0','1','1','0','1','1'}}
        ));

        System.out.println(demo.numIslands(new char[][]{
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        }));

        System.out.println(demo.numIslands(new char[][]{
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        }));

    }

    public int numIslands(char[][] grid) {
        int n = grid.length;
        if (n == 0) {
            return 0;
        }

        int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        Queue <Integer> queue = new LinkedList <>();
        int count = 0;
        int x;
        int y;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] || grid[i][j] == '0') {
                    continue;
                }
                //进行一次bfs
                queue.clear();
                queue.add(i * m + j);
                visited[i][j] = true;
//                System.out.println("i:"+i+",j:"+j);
                count++;
                while (!queue.isEmpty()) {
                    Integer remove = queue.remove();
                    int ri = remove / m;
                    int rj = remove % m;
//                    System.out.println("出队列：ri:"+ri+",rj:"+rj);
                    for (int k = 0; k < 4; k++) {
                        x = ri + dir[k][0];
                        y = rj + dir[k][1];
                        if (x >= 0 && x < n && y >= 0 && y < m && !visited[x][y] && grid[x][y] == '1') {
//                            System.out.println("入队列x:"+x+",y:"+y);
                            queue.add(x * m + y);
                            visited[x][y] = true;
                        }
                    }
                }
            }

        }
        return count;
    }

}

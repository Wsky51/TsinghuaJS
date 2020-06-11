package com.leetcode.personlGame;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2020/4/18
 *
 * @author WuYi
 */
public class NumberWays {

    public static void main(String[] args) {
        int i = numWays(3, new int[][]{{0, 2}, {2, 1}, {1, 2}, {0, 1}}, 2);
        System.out.println(i);
        System.out.println(numWays(4, new int[][]{{0, 3}, {0, 2}, {1, 2}, {2, 0}}, 3));
    }

    static List <Integer>[] lists;

    public static int numWays(int n, int[][] relation, int k) {
        if (k == 0) {
            return 0;
        }
        lists = new List[n];
        for (int i = 0; i < n; i++) {
            lists[i] = new ArrayList <>();

        }
        for (int[] arr : relation) {
            lists[arr[0]].add(arr[1]);
        }

        return dfs(0, n - 1, k);
    }

    public static int dfs(int be, int end, int k) {
        if (k == 1) {
            return lists[be].contains(end) ? 1 : 0;
        }
        if (k <= 0) {
            return 0;
        }
        if (be == end) {
            return 0;
        }
        int count = 0;
        k--;
        for (int i = 0; i < lists[be].size(); i++) {
            Integer nxt = lists[be].get(i);

            if (nxt == end) {
                if (k == 0) {//找到一条路径
                    count++;
                }
                continue;
            } else {
                int step = dfs(nxt, end, k);
                count = count + step;
            }
        }
        return count;

    }

}


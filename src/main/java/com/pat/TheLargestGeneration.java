package com.pat;

import java.util.Scanner;

/**
 * Created on 2020/4/1
 * pat https://www.nowcoder.com/pat/5/problem/4318
 *
 * @author WuYi
 */
public class TheLargestGeneration {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();//n个节点
        int m = scanner.nextInt();//m个节点有孩子

        int[] parent = new int[n + 1];

        int[] level = new int[n + 1];
        int[] countLev = new int[n + 1];
        parent[0] = -1;
        parent[1] = -1;

        level[1] = 1;
        countLev[1] = 1;

        /**
         * 初始化数据
         */
        for (int i = 0; i < m; i++) {
            int par = scanner.nextInt();
            int num = scanner.nextInt();
            for (int j = 0; j < num; j++) {
                int id = scanner.nextInt();
                parent[id] = par;

            }
        }
//my name is wuyi wuyi wuyi wu
        int maxCount = 0, maxLevel = 0;

        for (int i = 2; i < n + 1; i++) {
            int lev = 0;
            int tem = i;
            while (tem != -1) {
                lev++;
                tem = parent[tem];
            }
            level[i] = lev;
            countLev[lev]++;
            if (countLev[lev] > maxCount) {
                maxCount = countLev[lev];
                maxLevel = lev;
            }
        }

        System.out.println(maxCount+" "+maxLevel);

    }
}

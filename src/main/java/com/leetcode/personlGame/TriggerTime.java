package com.leetcode.personlGame;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2020/4/18
 *
 * @author WuYi
 */
public class TriggerTime {
    public static int[] getTriggerTime(int[][] increase, int[][] requirements) {
        int day = increase.length;
        int triLen = requirements.length;

        int[] res = new int[triLen];

        int[][] trigger = new int[triLen][3];
        for (int i = 0; i < triLen; i++) {
            Arrays.fill(trigger[i], -1);
        }

        int[] c = new int[day + 1];
        int[] r = new int[day + 1];
        int[] h = new int[day + 1];

        for (int i = 1; i <= day; i++) {
            c[i] = c[i - 1] + increase[i - 1][0];
            r[i] = r[i - 1] + increase[i - 1][1];
            h[i] = h[i - 1] + increase[i - 1][2];

            for (int j = 0; j < triLen; j++) {
                if (c[i] >= requirements[j][0] && trigger[j][0] == -1) {
                    trigger[j][0] = i;
//                    System.out.println("[j][0]:"+j+","+i);
                }
                if (r[i] >= requirements[j][1] && trigger[j][1] == -1) {
                    trigger[j][1] = i;
//                    System.out.println("[j][1]:"+j+","+i);
                }
                if (h[i] >= requirements[j][2] && trigger[j][2] == -1) {
                    trigger[j][2] = i;
//                    System.out.println("[j][2]:"+j+","+i);
                }
            }
        }

//        c[0] = increase[0][0];
//        r[0] = increase[0][1];
//        h[0] = increase[0][2];
//
//        for (int i = 0; i < triLen; i++) {
//            if (c[0] >= requirements[i][0]) {
//                trigger[i][0] = 0;
//            }
//            if (r[0] >= requirements[i][1]) {
//                trigger[i][1] = 0;
//            }
//            if (h[0] >= requirements[i][2]) {
//                trigger[i][2] = 0;
//            }
//        }

        for (int i = 0; i < triLen; i++) {
            if (requirements[i][0]==0&&requirements[i][1]==0&&requirements[i][2]==0){
                trigger[i][0]=0;
                trigger[i][1]=0;
                trigger[i][2]=0;
            }

        }

        for (int i = 0; i < triLen; i++) {
            res[i] = max(trigger[i][0], trigger[i][1], trigger[i][2]);
        }
        System.out.println(Arrays.toString(res));
        return res;
    }

    public static int max(int a, int b, int c) {
        if (a == -1 || b == -1 || c == -1) {
            return -1;
        }
        return Math.max(a, Math.max(b, c));
    }

    public static void main(String[] args) {
//        getTriggerTime(new int[][]{{2, 8, 4}, {2, 5, 0}, {10, 9, 8}}, new int[][]{{2, 11, 3}, {15, 10, 7}, {9, 17, 12}, {8, 1, 14}});
//        getTriggerTime(new int[][]{{0, 4, 5}, {4, 8, 8}, {8, 6, 1}, {10, 10, 0}},
//                new int[][]{{12, 11, 16}, {20, 2, 6}, {9, 2, 6}, {10, 18, 3}, {8, 14, 9}});
//        getTriggerTime(new int[][]{{1,1,1}},new int[][]{{0,0,0}});
        getTriggerTime(new int[][]{{6,3,4},{6,7,2}},new int[][]{{0,13,14},{0,5,5},{0,4,18},{4,3,4}});
    }
}

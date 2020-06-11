package com.tsinghuajishi;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created on 2020/4/22
 * 3 2
 * 170 180 168
 *
 * 168 170 170 172 173 173 173 174 175 177 177 180 189
 * @author WuYi
 */
public class MilitaryQueue {
    static int n, k;
    static int[] height;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        k = scanner.nextInt();
        height = new int[n];
        for (int i = 0; i < n; i++) {
            height[i] = scanner.nextInt();
        }
        Arrays.sort(height);
        if (n == k) {
            System.out.println(0);
            return;
        }
        if (k == 1) {
            int sub = height[n - 1] - height[0];
            System.out.println(sub * sub);
            return;
        }



    }
}

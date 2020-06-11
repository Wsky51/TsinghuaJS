package com.pekingoj;

import java.util.Scanner;

/**
 * Created on 2020/3/7
 *
 * @author WuYi
 */
public class SimpleSearch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();//n个顶点
        int m = scanner.nextInt();//m条边
        char[][] map = new char[n][n];
        boolean []row=new boolean[n];
        boolean []column=new boolean[n];


        for (int i = 0; i < n; i++) {
            String[] split = scanner.nextLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = split[i].charAt(0);
            }
        }


    }
}
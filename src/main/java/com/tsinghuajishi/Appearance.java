package com.tsinghuajishi;

import java.util.Scanner;

/**
 * Created on 2020/4/19
 * https://www.acwing.com/problem/content/1325/
 * @author WuYi
 */
public class Appearance {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n = scanner.nextInt();
        boolean[] arr=new boolean[1001];
        for (int i = 0; i < n; i++) {
            int i1 = scanner.nextInt();
            arr[i1]=true;
        }
        for (int i = 0; i < 1001; i++) {
            if (!arr[i]){
                System.out.println(i);
                return;
            }
        }
    }
}

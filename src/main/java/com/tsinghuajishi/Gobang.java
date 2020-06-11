package com.tsinghuajishi;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created on 2020/4/19
 * https://www.acwing.com/problem/content/1326/
 *
 * @author WuYi
 */
public class Gobang {
    public static final int A = 1;
    public static final int B = 2;

    static int[][] data;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if (n==0){
            System.out.println("Tie");
            return;
        }
        data = new int[16][16];
        for (int i = 0; i < data.length; i++) {
            Arrays.fill(data[i], 0);

        }
        for (int i = 1; i <= n; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            if ((i & 1) == 1) {//为奇数，则是A在下
                data[x][y] = A;
                if (isWin(A,x,y)){
                    System.out.println("A "+i);
                    return;
                }
            } else {//否则是b在下
                data[x][y] = B;
                if (isWin(B,x,y)){
                    System.out.println("B "+i);
                    return;
                }
            }
        }
        System.out.println("Tie");
    }

    public static boolean isWin(int person, int x, int y) {
        //计算横方向的数值
        int tempX = x, tempY = y;
        int count = 0;
        while (isOk(tempX, tempY) && data[tempX][tempY] == person) {
            count++;
            tempX--;
        }
        tempX = x + 1;
        while (isOk(tempX, tempY) && data[tempX][tempY] == person) {
            count++;
            tempX++;
        }
        if (count >= 5) {
            return true;
        }

        //计算竖方向
        tempX = x;
        tempY = y;
        count = 0;
        while (isOk(tempX, tempY) && data[tempX][tempY] == person) {
            count++;
            tempY--;
        }
        tempY = y + 1;
        while (isOk(tempX, tempY) && data[tempX][tempY] == person) {
            count++;
            tempY++;
        }
        if (count >= 5) {
            return true;
        }

        //计算左上到右下斜方向
        tempX = x;
        tempY = y;
        count = 0;
        while (isOk(tempX, tempY) && data[tempX][tempY] == person) {
            count++;
            tempX--;
            tempY--;
        }

        tempX = x + 1;
        tempY = y + 1;

        while (isOk(tempX, tempY) && data[tempX][tempY] == person) {
            count++;
            tempX++;
            tempY++;
        }
        if (count >= 5) {
            return true;
        }

        //计算左下到右上斜方向
        tempX = x;
        tempY = y;
        count = 0;
        while (isOk(tempX, tempY) && data[tempX][tempY] == person) {
            count++;
            tempX++;
            tempY--;
        }

        tempX = x - 1;
        tempY = y + 1;

        while (isOk(tempX, tempY) && data[tempX][tempY] == person) {
            count++;
            tempX--;
            tempY++;
        }
        if (count >= 5) {
            return true;
        }
        return false;

    }

    //检查边界条件
    public static boolean isOk(int x, int y) {
        if (x <= 0 || x >= 16) {
            return false;
        }
        if (y <= 0 || y >= 16) {
            return false;
        }
        return true;
    }
}

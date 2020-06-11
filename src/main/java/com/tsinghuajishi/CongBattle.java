package com.tsinghuajishi;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created on 2020/3/11
 * 2018年清华复试机试第1题
 *
 * @author WuYi
 */
public class CongBattle {
    static class Onion {
        int id;
        int x;
        int y;
        int dir;//当前要走的方位
        int f;//战斗力
        boolean isDead;

        public Onion(int id, int x, int y, int dir, int f) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.f = f;
            isDead = false;
        }

        @Override
        public String toString() {
            return x + " " + y+" "+isDead;//输出该葱的位置
        }
    }

    static Onion[] onions;
    static int[][] onionId;

    public static void main(String[] args) {
        int id = 0;
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();//n*m的棋盘
        int m = scanner.nextInt();
        int k = scanner.nextInt();//k个葱
        onions = new Onion[k];
        onionId = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(onionId[i], -1);
        }
        for (int i = 0; i < k; i++) {
            int x = scanner.nextInt();//xy坐标
            int y = scanner.nextInt();
            int d = scanner.nextInt();//方位
            int f = scanner.nextInt();//战斗力
            onions[i] = new Onion(i, x, y, d, f);
            onionId[x][y] = i;//x对应n
        }

        CongBattle test = new CongBattle();
        test.printInfoAfterT(n, m, k, 7);
    }

    //在t后每个葱的位置
    public void printInfoAfterT(int n, int m, int k, int t) {
        //经过t秒
        for (int i = 0; i < t; i++) {
            //对每一个葱循环,( &&
            for (int j = 0; j < k && !(onions[j].isDead); j++) {//葱没有死的话，就移动位置
                moveStepIfPoss(onions[j], n, m);
            }

        }
        for (int i = 0; i < k; i++) {
            System.out.println(onions[i]);

        }
    }

    public void moveStepIfPoss(Onion onion, int n, int m) {
        if (onion.y == 0 && onion.dir == 2) {//走到了左边界
            onion.dir = 3;
            checkOnionPos(onion);
            return;
        }
        if (onion.y == m - 1 && onion.dir == 3) {//右边界
            onion.dir = 2;
            checkOnionPos(onion);
            return;
        }
        if (onion.x == n - 1 && onion.dir == 1) {//下边界
            onion.dir = 0;
            checkOnionPos(onion);
            return;
        }
        if (onion.x == 0 && onion.dir == 0) {//上边界
            onion.dir = 1;
            checkOnionPos(onion);
            return;
        }

        switch (onion.dir) {
            case 0:
                onion.x--;//上
                checkOnionPos(onion);
                break;
            case 1:
                onion.x++;//下
                checkOnionPos(onion);
                break;
            case 2:
                onion.y--;//左
                checkOnionPos(onion);
                break;
            case 3:
                onion.y++;//右
                checkOnionPos(onion);
                break;
        }

    }

    public void checkOnionPos(Onion onion) {
        if (onionId[onion.x][onion.y] > 0) {//说明该位置已经有葱占据了
            battle(onion, onions[onionId[onion.x][onion.y]], onion.x, onion.y);
        } else {
            onionId[onion.x][onion.y] = onion.id;
        }
    }

    public void battle(Onion o1, Onion o2, int x, int y) {
        if (o1.f > o2.f) {//o1获胜
            o2.isDead = true;
            onionId[x][y] = o1.id;
        } else {
            o1.isDead = true;
            onionId[x][y] = o2.id;
        }
    }
}

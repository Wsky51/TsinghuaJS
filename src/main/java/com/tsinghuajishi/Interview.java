package com.tsinghuajishi;

import java.util.*;

/**
 * Created on 2020/3/18
 *
 * @author WuYi
 */
public class Interview {
    static int N;//总人数
    static int M;//需要的人数
    static int K;//最大高度差
    static Child[] childs;//保存学生信息

    public static void main(String[] args) {
        init();

        for (int i = M - 1; i < N; i++) {
            Arrays.sort(childs, 0, i + 1, new Comparator <Child>() {
                @Override
                public int compare(Child o1, Child o2) {
                    return o1.height - o2.height;
                }
            });
            for (int j = 0; j <= i - M + 1; j++) {//保证边界不超过
                //如果前n个人的高度就已经满足要求了，那就可以结束了
                if (childs[j + M - 1].height - childs[j].height <= K) {
                    System.out.println(i+1);
                    return;
                }
            }

        }
        System.out.println("impossible");

    }

    public static void init() {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();
        K = scanner.nextInt();
        childs = new Child[N];
        for (int i = 0; i < N; i++) {
            int h = scanner.nextInt();
            childs[i] = new Child(i + 1, h);
        }
    }

}

class Child {
    int id;
    int height;

    public Child(int id, int height) {
        this.id = id;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Child{" +
                "id=" + id +
                ", height=" + height +
                '}';
    }
}
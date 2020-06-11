package com.unsort;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created on 2020/3/3
 * https://vjudge.net/problem/HDU-1272 小希的迷宫 简单并查集的应用
 *
 * @author WuYi
 */
public class UnionDemo {
    private static int[] s = new int[100001];

    //查根节点元素
    static int find(int x) {
        while (s[x] != x) {
            x = s[x];
        }
        return x;
    }

    //是否是同一个祖先(同一集合)
    static boolean isLinked(int x, int y) {
        return find(x) == find(y);
    }

    //将集合做合并处理
    static void link(int x, int y) {
        int xp = find(x);
        int yp = find(y);
        if (xp < yp) {
            s[yp] = xp;
        } else {
            s[xp] = yp;
        }

    }

    public static void main(String[] args) {
        //初始化
        for (int i = 0; i < s.length; i++) {
            s[i] = i;
        }
        Scanner scanner = new Scanner(System.in);
        boolean isOk=true;


        int root=Integer.MAX_VALUE;
        //处理输出的数据
        while (true) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            if (x==0&&y==0){
                break;
            }
            //已经是连接的，说明有多余的通路，不符合要求
            if (isLinked(x,y)){
                isOk=false;
            }else{
                link(x,y);
            }
            int temp;
            if (root>(temp=find(x))){
                root=temp;
            }
        }
        for (int j = 0; j <s.length; j++) {
            if (j!=s[j]&&find(j)!=root){
                isOk=false;
                break;
            }
        }
        if (isOk){
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }

    }
}

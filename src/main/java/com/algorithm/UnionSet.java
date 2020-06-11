package com.algorithm;

import java.util.Arrays;

/**
 * Created on 2020/3/3
 * 并查集
 *
 * @author WuYi
 */
public class UnionSet {
    public void initial(int[] s) {
        Arrays.fill(s, -1);
    }

    /**
     * 查找操作，找到其根节点
     * @param s
     * @param x
     * @return
     */
    public int find(int[] s, int x) {
        while (s[x] >= 0) {
            x = s[x];
        }
        return x;
    }

    //合并两个根
    public void union(int[] s, int root1, int root2) {
        s[root2] = root1;
    }
}




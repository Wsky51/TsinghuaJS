package com.test;
import java.math.BigInteger;
import java.util.*;

/**
 * Created on 2020/3/3
 *
 * @author WuYi
 */
public class Test {
    private static final long MOD1 = 2009731336725594113l;
    private static final long MOD2 = 2019;

    static final long[] U = {314882150829468584l, 427197303358170108l,
            1022292690726729920l, 1698479428772363217l, 2006101093849356424l};

    static long[][] mod = new long[5][2020];

    static final long[] U_MOD_2019 = {32l, 1713l, 1583l, 1029l, 1420l};

    public static void main(String[] args) {
        int n=8,lim=1,len=0;
        int[] rev=new int[n];
        while (lim<=n){
            lim<<=1;
            len++;
        }
        System.out.println("len:"+len);
        for (int i = 0; i < n; i++) {
            rev[i]=(rev[i>>1]>>1)|((i&1)<<(len-1));
        }

        System.out.println(Arrays.toString(rev));
    }

    public static int resize(int n) {
        int a = n - 1;
        a |= a >>> 1;
        a |= a >>> 2;
        a |= a >>> 4;
        a |= a >>> 8;
        a |= a >>> 16;
        return a<0?1:a+1;
    }

    static class Node {
        int id;
        int val;

        public Node(int id, int val) {
            this.id = id;
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "id=" + id +
                    ", val=" + val +
                    '}';
        }
    }

}
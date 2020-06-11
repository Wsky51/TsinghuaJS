package com.pat;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created on 2020/3/25
 * pat https://www.nowcoder.com/pat/5/problem/4316
 *
 * @author WuYi
 */
public class HighestPriceinSupplyChain {
    static int[] parent;
    static int root;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        double p = scanner.nextDouble();//原始价
        double r = scanner.nextDouble();//上升比例

        parent = new int[n];

        for (int i = 0; i < n; i++) {
            int par = scanner.nextInt();
            parent[i] = par;
            if (par == -1) {
                root = i;
            }
        }

        int maxDeep = Integer.MIN_VALUE;
        int retailers = 0;
        for (int i = 0; i < n; i++) {
            int deep = getDeep(i);
            if (deep > maxDeep) {
                maxDeep = deep;
                retailers = 1;
            } else if (deep == maxDeep) {
                retailers++;
            }
        }
        BigDecimal dec = new BigDecimal(p + "");
        BigDecimal rbig = new BigDecimal((1 + r / 100) + "");
        for (int i = 0; i < maxDeep; i++) {
            dec = dec.multiply(rbig);
        }

        dec=dec.setScale(2,BigDecimal.ROUND_HALF_UP);
        System.out.println(dec+" "+retailers);
//        System.out.print( dec.setScale(2)+" "+retailers);

    }

    public static int getDeep(int node) {
        int deep = 0;
        while (node != root) {
            deep++;
            node = parent[node];
        }
        return deep;
    }
}

package com.leetcode.contest184;

import java.util.Arrays;

/**
 * Created on 2020/4/12
 *
 * @author WuYi
 */
public class ProcessQuery {
    public static void main(String[] args) {
        ProcessQuery demo=new ProcessQuery();
        System.out.println(Arrays.toString(demo.processQueries(new int[]{4,1,2,2},4)));
    }

    public int[] processQueries(int[] queries, int m) {
        int[] res = new int[queries.length];

        int[] query = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            query[i] = i - 1;
        }

        for (int i = 0; i < queries.length; i++) {
            res[i] = query[queries[i]];
            for (int j = 0; j < query.length; j++) {
                if (query[j] < res[i]) {
                    query[j]++;
                }
            }
            query[queries[i]] = 0;
        }
        return res;
    }

}

package com.leetcode.onetaskperday;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created on 2020/4/16
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author WuYi
 */
public class MergeInterval {
    public static void main(String[] args) {
        int[][] data = {{1, 9}, {2, 5}, {19, 20}, {10, 11}, {12, 20}, {0, 3}, {0, 1}, {0, 2}};
        int[][] merge = merge(data);
        System.out.println(Arrays.deepToString(merge));
    }

    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][0];
        }
        Arrays.sort(intervals, new Comparator <int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        List <Integer> list = new ArrayList <>();
        list.add(intervals[0][0]);
        list.add(intervals[0][1]);
        for (int i = 1; i <intervals.length ; i++) {
            int[] arr=intervals[i];
            int size=list.size();
            if (list.get(size-1)>=arr[0]) {
                if (list.get(size-1)<arr[1]){
                    list.set(size-1,arr[1]);
                }
                continue;
            }else{
                list.add(arr[0]);
                list.add(arr[1]);
            }
        }
        int n=list.size()/2;
        int[][] res=new int[n][2];
        for (int i = 0; i < n; i++) {
            res[i][0]=list.get(i*2);
            res[i][1]=list.get(i*2+1);
        }
        return res;
    }

}

package com.algorithm;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created on 2020/4/2
 * 后缀数组
 * <p>
 * Rank[i] : Suffix[i]在所有后缀中的排名
 * SA[i] : 满足Suffix[SA[1]] < Suffix[SA[2]] …… < Suffix[SA[Len]],即排名为i的后缀为Suffix[SA[i]] (与Rank是互逆运算)
 * </p>
 * <p>
 * 倍增算法此处不采用，采用lcp最长公共前缀算法
 * Height[i]为SA[i]与SA[i-1]的最长公共前缀的长度，即排名为i与排名为i-1的字符串的最长公共前缀的长度
 * Rank[i]为i位置对应前缀的排名(Rank[SA[i]]=i)
 * 而两个排名不相邻的最长公共前缀定义为排名在它们之间的Height的最小值。
 * H[i] : 等于Height[Rank[i]]，也就是后缀Suffix[i]和它前一名的后缀的最长公共前缀
 * </p>
 * <p>
 * <p>
 * 重要定理：
 * 如果一个一个数按SA中的顺序比较的话复杂度是O()级别的，想要快速的得到Height就需要用到一个关于H数组的性质。
 * H[i] ≥ H[i - 1] - 1!
 * 如果上面这个性质是对的，那我们可以按照H[1]、H[2]……H[Len]的顺序进行计算，那么复杂度就降为O(N)了！
 * 让我们尝试一下证明这个性质 :
 * 设Suffix[k]是排在Suffix[i - 1]前一名的后缀，则它们的最长公共前缀是H[i - 1]。都去掉第一个字符，
 * 就变成Suffix[k + 1]和Suffix[i]。如果H[i - 1] = 0或1,那么H[i] ≥ 0显然成立。
 * 否则，H[i] ≥ H[i - 1] - 1(去掉了原来的第一个,其他前缀一样相等)，
 * 所以Suffix[i]和在它前一名的后缀的最长公共前缀至少是H[i - 1] - 1。
 * </p>
 *
 * @author WuYi
 */
public class SuffixArray {
    private static final int MAX = 200;

    //rank[i] 第i个后缀的排名; SA[i] 排名为i的后缀位置; Height[i] 排名为i的后缀与排名为(i-1)的后缀的LCP
    //tax[i] 计数排序辅助数组; tp[i] rank的辅助数组(计数排序中的第二关键字),与SA意义一样。
    static int[] sa, rank, height, tax, tp;
    int n, m;

    public static void main(String[] args) {
        SuffixArray arr = new SuffixArray();
        arr.init();
        arr.suffix("aabaaaab");
//        for (int i = 1; i < 9; i++) {
//            System.out.print(sa[i]+" ");
//        }
        System.out.println("sa:"+Arrays.toString(sa));
        System.out.println("tax:"+Arrays.toString(tax));
        System.out.println("rank:" + Arrays.toString(rank));
        System.out.println("tp:" + Arrays.toString(tp));

    }

    void rSort() {
        //rank第一关键字,tp第二关键字
        for (int i = 0; i <= m; i++) tax[i] = 0;
        for (int i = 1; i <= n; i++) tax[rank[tp[i]]]++;
        for (int i = 1; i <= m; i++) tax[i] += tax[i - 1];

        //确保满足第一关键字的同时，再满足第二关键字的要求,计数排序,把新的二元组排序。
        for (int i = n; i >= 1; i--) sa[tax[rank[tp[i]]]--] = tp[i];
    }

    void suffix(String str) {
//        n=str.length();
//        //SA
//        for (int i = 1; i <= str.length(); i++) {
//            rank[i] = (int) (str.charAt(i - 1) - 'a');
//            tp[i] = i;
//        }
//        m = 127;
//        rSort(); //一开始是以单个字符为单位，所以(m = 127)}
        m = 127;
        n=str.length();
        tp[1] = 2;
        tp[2] = 3;
        tp[3] = 5;
        tp[4] = 7;
        tp[5] = 6;
        tp[6] = 7;
        tp[7] = 8;
        tp[8] = 8;

        rank[1] = 4;
        rank[2] = 6;
        rank[3] = 8;
        rank[4] = 1;
        rank[5] = 2;
        rank[6] = 3;
        rank[7] = 5;
        rank[8] = 7;
        rSort();
    }

    void init() {
        sa = new int[MAX];
        rank = new int[MAX];
        height = new int[MAX];
        tax = new int[128];
        tp = new int[MAX];
    }
}


package com.leetcode.onetaskperday;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2020/4/19
 * https://leetcode-cn.com/problems/count-the-repetitions/
 *
 * @author WuYi
 */
public class MaxRepetition {

    public static void main(String[] args) {
        MaxRepetition demo = new MaxRepetition();
        int maxRepetitions = demo.getMaxRepetitions("acbadbaba", 4, "abababab", 2);
        System.out.println(maxRepetitions);
    }

    // 循环节
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        if (n1 == 0) {
            return 0;
        }
        int s1cnt = 0, index = 0, s2cnt = 0;
        /** recall 是我们用来找循环节的变量，它是一个哈希映射
         * 我们如何找循环节？假设我们遍历了 s1cnt 个 s1，此时匹配到了第 s2cnt 个 s2 中的第 index 个字符
         * 如果我们之前遍历了 s1cnt' 个 s1 时，匹配到的是第 s2cnt' 个 s2 中同样的第 index 个字符，那么就有循环节了
         * 我们用 (s1cnt', s2cnt', index) 和 (s1cnt, s2cnt, index) 表示两次包含相同 index 的匹配结果
         * 那么哈希映射中的键就是 index，值就是 (s1cnt', s2cnt') 这个二元组
         * 循环节就是；
         *    - 前 s1cnt' 个 s1 包含了 s2cnt' 个 s2
         *    - 以后的每 (s1cnt - s1cnt') 个 s1 包含了 (s2cnt - s2cnt') 个 s2
         * 那么还会剩下 (n1 - s1cnt') % (s1cnt - s1cnt') 个 s1, 我们对这些与 s2 进行暴力匹配
         * 注意 s2 要从第 index 个字符开始匹配
         */
        Map <Integer, Pair> map = new HashMap <>();
        Pair preLoop, inLoop;
        while (true) {
            s1cnt++;
            for (char ch : s1.toCharArray()) {
                if (ch == s2.charAt(index)) {
                    index++;
                    if (index == s2.length()) {
                        s2cnt++;
                        index = 0;
                    }
                }
            }
            // 还没有找到循环节，所有的 s1 就用完了
            if (s1cnt == n1) {
                return s2cnt / n2;
            }
            // 出现了之前的 index，表示找到了循环节
            if (map.containsKey(index)) {
                // 前 s1cnt' 个 s1 包含了 s2cnt' 个 s2
                preLoop = map.get(index);//上一次index处时 s1用了多少个和s2用了多少个
                // 以后的每 (s1cnt - s1cnt') 个 s1 包含了 (s2cnt - s2cnt') 个 s2
                inLoop = new Pair(s1cnt - preLoop.first, s2cnt - preLoop.second);//今后，每过inLoop.first个s1，必定包含inLoop.second个s2
                break;
            } else {
                map.put(index, new Pair(s1cnt, s2cnt));
            }
        }
        // ans 存储的是 S1 包含的 s2 的数量，考虑的之前的 pre_loop 和 in_loop
        int ans = preLoop.second + ((n1 - preLoop.first) / inLoop.first) * inLoop.second;
        // S1 的末尾还剩下一些 s1，我们暴力进行匹配
        int rest = (n1 - preLoop.first) % inLoop.first;
        for (int i = 0; i < rest; i++) {
            for (char ch : s1.toCharArray()) {
                if (ch == s2.charAt(index)) {
                    index++;
                    if (index == s2.length()) {
                        ans++;
                        index = 0;
                    }
                }
            }

        }
        // S1 包含 ans 个 s2，那么就包含 ans / n2 个 S2
        return ans / n2;

    }

    class Pair {
        int first;
        int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

}

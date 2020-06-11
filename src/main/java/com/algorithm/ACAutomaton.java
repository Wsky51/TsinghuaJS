package com.algorithm;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created on 2020/3/30
 * 前置知识：KMP算法，Tire书
 * AC自动机，借助Tire树解决的问题：
 * 我们给出5个单词模式串，say，she，shr，he，her。给定字符串为 yasherhs。问多少个单词在匹配串中出现过。
 *
 * @author WuYi    she,he,her
 */
public class ACAutomaton {
    private static final int N = 1000;//定义一共有1000个节点

    int cnt;//计数
    int[][] tr = new int[N][26];//TRIE,tr[i][j]表示的是在tire树中第i个节点的第j个孩子
    int[] e = new int[N];//标记字符串结尾,e[i]表示第i各节点为字符全结尾
    int[] fail = new int[N];//fail指针，fail[i]表示第i个节点的父亲是谁
    int[] match = new int[10];//先假设有10个模式串
    int matchCnt;

    public static void main(String[] args) {
        ACAutomaton ac = new ACAutomaton();
        ac.insert("a");
        ac.insert("aa");
        ac.insert("shr");
        ac.insert("he");
        ac.insert("her");

        ac.build();
        int yasherhs = ac.query("aa");
        System.out.println(yasherhs);

    }

    void insert(String s) {//插入模式串
        int p = 0;//根节点是0号
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            if (tr[p][idx] == 0) {
                tr[p][idx] = ++cnt;
            }
            p = tr[p][idx];
        }
        e[p]++;//标记字符串的结尾
        match[matchCnt++] = p;// 记录每个模式串在 Trie 树上的终止节点
    }

    void build() {
        Queue <Integer> queue = new LinkedList <>();
        Arrays.fill(fail, 0);

        //首字符入队,也即第一层入队，bfs
        //不直接将0入队是为了避免指向自己
        for (int i = 0; i < 26; i++) {
            if (tr[0][i] > 0) {//根节点的所有孩子都加入队列中
                queue.add(tr[0][i]);
            }
        }

        while (!queue.isEmpty()) {
            Integer k = queue.remove();
            for (int i = 0; i < 26; i++) {
                if (tr[k][i] > 0) {//若当前位置有孩子
                    fail[tr[k][i]] = tr[fail[k]][i];//构建当前的 fail 指针
                    queue.add(tr[k][i]);//入队
                } else {
                    //匹配到空字符，则索引到父节点fail指针对应的字符，以供后续指针的构建
                    //类似并差集的路径压缩，把不存在的tr[k][i]全部指向tr[fail[k]][i]
                    //这句话在后面匹配主串的时候也能帮助跳转
                    tr[k][i] = tr[fail[k]][i];
                }
            }
        }
    }

    //这个query函数在此问题非常大，只能算一次文本串就没了，第二，计算不了每个模式串在文本串中出现的次数
    int query(String s) {
        int p = 0, res = 0;
        for (int i = 0; i < s.length(); i++) {
            p = tr[p][s.charAt(i) - 'a'];
            for (int j = p; j != 0 && e[j] != -1; j = fail[j]) {
                res += e[j];
                e[j] = -1;
            }
        }
        return res;
    }
    

}

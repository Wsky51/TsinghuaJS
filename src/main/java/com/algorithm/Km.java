package com.algorithm;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created on 2020/3/10
 * Km算法&匈牙利算法，解决二分图问题
 * 详解参考：https://www.cnblogs.com/wenruo/p/5264235.html
 *
 * @author WuYi
 */
public class Km {
    private final static int MAXN = 10;
    int[][] love;   // 记录每个妹子和每个男生的好感度
    int[] expGirl;      // 每个妹子的期望值
    int[] expBoy;       // 每个男生的期望值
    boolean[] visGirl;    // 记录每轮匹配中男生女生是否被尝试匹配过
    boolean[] visBoy;     //同上
    int[] match;        // 记录每个男生匹配到的妹子 如果没有则为-1
    int[] slack;        // 记录每个汉子如果能被妹子倾心最少还需要多少期望值

    int N = 3;//记录一边的人数，此处为2女，2男

    public static void main(String[] args) {
        Km demo = new Km();
        Scanner scanner=new Scanner(System.in);
        demo.init();
        for (int i = 0; i < demo.N; i++) {
            for (int j = 0; j < demo.N; j++) {
                demo.love[i][j]=scanner.nextInt();
            }
        }
        for (int i = 0; i < demo.N; i++) {
            System.out.println(Arrays.toString(demo.love[i]));

        }
        System.out.println("最终结果为："+demo.km());
        for (int i = 0; i < demo.N; i++) {
            System.out.println("男生"+i+"匹配的女生是："+demo.match[i]);
        }


    }

    public void init() {
        love = new int[MAXN][MAXN];
        expGirl = new int[MAXN];
        expBoy = new int[MAXN];
        visGirl = new boolean[MAXN];
        visBoy = new boolean[MAXN];
        match = new int[MAXN];
        slack = new int[MAXN];
        Arrays.fill(match, -1); // 初始每个男生都没有匹配的女生

    }

    /**
     * 土耳其算法，找出合适的路径
     * @param girl
     * @return
     */
    public boolean dfs(int girl) {
        System.out.println("给第"+girl+"找对象");
        visGirl[girl] = true;

        for (int boy = 0; boy < N; ++boy) {

            if (visBoy[boy]||love[girl][boy]==0) {//若该男生已经访问过了或者女生到男生就没有路径可走，那就跳过该男生
                continue; // 每一轮匹配 每个男生只尝试一次
            }

            int gap = expGirl[girl] + expBoy[boy] - love[girl][boy];
            System.out.println("当前给第"+girl+"女孩找对象，gap="+gap);

            if (gap == 0) {  // 如果符合要求
                visBoy[boy] = true;
                if (match[boy] == -1 || dfs(match[boy])) {    // 找到一个没有匹配的男生 或者该男生的妹子可以找到其他人
                    match[boy] = girl;
                    System.out.println("第"+girl+"个女孩匹配成功,匹配到的男孩是："+boy+",此时的match[boy]为："+"match["+boy+"]:"+match[boy]);
                    System.out.println("expGirl:"+Arrays.toString(expGirl)+",expBoy:"+Arrays.toString(expBoy));
                    return true;
                }
            } else {
                slack[boy] = Math.min(slack[boy], gap);  // slack 可以理解为该男生要得到女生的倾心 还需多少期望值 取最小值 备胎的样子
            }
        }
        return false;
    }

    int km() {

        // 每个女生的初始期望值是与她相连的男生最大的好感度
        for (int i = 0; i < N; ++i) {
            expGirl[i] = love[i][0];
            for (int j = 1; j < N; ++j) {
                expGirl[i] = Math.max(expGirl[i], love[i][j]);
            }
        }

        // 尝试为每一个女生解决归宿问题
        for (int i = 0; i < N; ++i) {
            System.out.println("在km->for循环里面给第："+i+"个女生找对象");
            Arrays.fill(slack, Integer.MAX_VALUE);

            while (true) {
                // 为每个女生解决归宿问题的方法是 ：如果找不到就降低期望值，直到找到为止
                Arrays.fill(visGirl,false);
                Arrays.fill(visBoy,false);

                if (dfs(i)) break;  // 找到归宿 退出

                // 如果不能找到 就降低期望值

                // 最小可降低的期望值
                int d = Integer.MAX_VALUE;
                for (int j = 0; j < N; ++j) {
                    if (!visBoy[j]) {
                        d = Math.min(d, slack[j]);
                    }
                }
                System.out.println("选取的d为："+d);
                for (int j = 0; j < N; ++j) {
                    // 所有访问过的女生降低期望值
                    if (visGirl[j]) {
                        expGirl[j] -= d;
                    }

                    // 所有访问过的男生增加期望值
                    if (visBoy[j]) {
                        expBoy[j] += d;
                    }
                    // 没有访问过的boy 因为girl们的期望值降低，距离得到女生倾心又进了一步！
                    else {
                        slack[j] -= d;
                    }
                }
            }
        }
        // 匹配完成 求出所有配对的好感度的和
        int res = 0;
        for (int i = 0; i < N; ++i)
            res += love[match[i]][i];

        return res;

    }
}
package com.algorithm.graph;

/**
 * Created on 2020/3/2
 *
 * @author WuYi
 */
public class Kmp {

    public static void main(String[] args) {
        String p="abc";
        String t="dsafedacdsaew";
        int iMinusj = match(p, t);
        int nMinusm=t.length()-p.length();
        if (iMinusj<=nMinusm){
            System.out.println("true");
        }else
            System.out.println("false");

    }

    /**
     * KMP主算法
     * 如何根据返回值i-j来判断是否匹配成功与否？
     * 答：若匹配成功必有j=m,i<=n 故此时必定有i-j<=n-m
     * 否则为匹配失败
     * @param p
     * @param t
     * @return
     */
    public static int match(String p, String t) {  //KMP算法
        int[] next = buildNext(p); //构造next表
        int n = t.length(), i = 0; //文本串指针
        System.out.println("n:"+n);
        int m = p.length(), j = 0; //模式串指针
        while (j < m && i < n) //自左向右逐个比对字符
            if (0 > j || t.charAt(i) == p.charAt(j)) { //若匹配，或P已移出最左侧（两个判断的次序不可交换）
                i++;j++;//则转到下一字符
            }
            else { //否则
                j = next[j];//模式串右移（注意：文本串不用回退）
            }
        System.out.println("i:"+i+",j:"+j);
        return i - j;
    }

    public static int[] buildNext(String p) {//构造模式串P的next表
        int m = p.length(), j = 0;//"主"串指针
        int[] N = new int[m];//next表
        int t = N[0] = -1;//模式串指针
        while (j < m - 1) {
            if (t < 0 || p.charAt(j) == p.charAt(t)) {//匹配
                j++;
                t++;
                N[j] = (p.charAt(j) != p.charAt(t) ? t : N[t]);
            } else {//失配
                t = N[t];
            }
        }
        return N;
    }
}

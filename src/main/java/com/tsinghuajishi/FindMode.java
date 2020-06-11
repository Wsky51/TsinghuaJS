package com.tsinghuajishi;

import java.util.Scanner;



/**
 * Created on 2020/3/2
 * 2019年清华研究生复试机试第一题
 *
 * @author WuYi
 */
public class FindMode {
    public static void main(String[] args) {

        /**
         * 准备数据
         */
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] digi = new int[n];
        for (int i = 0; i < n; i++) {
            digi[i] = scanner.nextInt();
        }

        int[][] res = new int[m][10];
        for (int num : digi) {
            int tempNum = num;
            for (int j = 0; j < m; j++) {
                int yushu = tempNum % 10;
                res[j][yushu]++;
                tempNum/=10;
            }
        }

        for (int j = 0; j < m; j++) {
            int count=Integer.MIN_VALUE;
            int mode=0;
            for (int i=0;i<10;i++){
                if(count<res[j][i]){
                    count=res[j][i];
                    mode=j;
                }
            }
            System.out.println(mode);

        }

    }

}

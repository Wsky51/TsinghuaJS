package com.tsinghuajishi;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created on 2020/3/12
 * 清华18年研究生复试机试第二题
 *
 * @author WuYi
 */
public class ThePath {
    static int mask_1, mask_2, mask_4, mask_8, mask_16;

    static {
        mask_1 = 0x55 | (0x55 << 8);
        mask_1 = mask_1 | (mask_1 << 16);
        mask_2 = 0x33 | (0x33 << 8);
        mask_2 = mask_2 | (mask_2 << 16);
        mask_4 = 0x0f | (0x0f << 8);
        mask_4 = mask_4 | (mask_4 << 16);
        mask_8 = 0xff | (0xff << 16);
        mask_16 = 0xff | (0xff << 8);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] point = new int[n];//记录每个点的权值
        for (int i = 0; i < n; i++) {
            point[i] = scanner.nextInt();
        }
        int[][] map = new int[n][n];//记录顶点之间路径的信息
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                map[i][j] = fx(point[i] & point[j]);
            }
        }

        /**
         * 打印边集信息
         */
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        int []dist=new int[n];//保存第0个节点到第N-1个节点的路径走法

        dist[1]=map[0][1];
        for (int i = 2; i <n ; i++) {
            for (int j = 0; j < i; j++) {
                if (map[j][i]==0){//说明j->i路径不通，直接跳过
                    continue;
                }
                dist[i]+=dist[j]+map[j][i]-1;
            }
        }

        System.out.println("结果"+Arrays.toString(dist));


    }

    //计算x的二进制数中有多少个1
    public static int fx(int x) {
        x = (x & mask_1) + ((x >> 1) & mask_1);
        x = (x & mask_2) + ((x >> 2) & mask_2);
        //下面三组最高位不会是1了
        x = (x + (x >> 4)) & mask_4;//x=(x&mask_4)+((x>>4)&mask_4);
        x = (x + (x >> 8)) & mask_8;//x=(x&mask_8)+((x>>8)&mask_8);
        x = (x + (x >> 16)) & mask_16;//x=(x&mask_16)+((x>>16)&mask_16);
        return x;
    }
}

//package com.cf;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created on 2020/4/13
 *
 * @author WuYi
 */
public class SortedAdjacent {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int len = scanner.nextInt();
            int data[]=new int[len];
            for (int j = 0; j < len; j++) {
                data[j]=scanner.nextInt();
            }
            int[] cal = cal(data);
            for (int j = 0; j < cal.length-1; j++) {
                System.out.print(cal[j]+" ");
            }
            System.out.println(cal[cal.length-1]);
        }
    }

    public static int[] cal(int[] data) {
        int len = data.length;
        if (len <= 1) {
            return data;
        }
        int[] res = new int[data.length];
        Arrays.sort(data);

        int count = 0;
        for (int i = len - 1; i >= 0; i = i - 2, count++) {
            res[i] = data[count];
        }


        count = len - 1;
        for (int i = len - 2; i >= 0; i = i - 2, count--) {
            res[i] = data[count];
        }
        return res;

    }

}

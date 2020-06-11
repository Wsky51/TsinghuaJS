package com.pekingoj;

import java.util.Scanner;

/**
 * Created on 2020/3/23
 * POJ 1005 题
 *
 * @author WuYi
 */
public class Houseboat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            double x = scanner.nextDouble();
            double y = scanner.nextDouble();
            res[i] = calYear(x, y);
        }
        for (int i = 1; i <n+1 ; i++) {
            System.out.println("Property "+i+": This property will begin eroding in year "+res[i-1]+".");
        }
        System.out.println("END OF OUTPUT.");

    }

    public static int calYear(double x, double y) {
        int year = 1;
        double xandy2 = x * x + y * y;
        while (((double) year * 100 / Math.PI) < xandy2) {
            year++;
        }
        return year;

    }
}

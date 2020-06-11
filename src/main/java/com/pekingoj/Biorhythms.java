package com.pekingoj;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created on 2020/3/23
 * POJ : 1006
 *
 * @author WuYi
 */
public class Biorhythms {
    public static void main(String[] args) {
        List <Integer> list = new ArrayList <Integer>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int p = scanner.nextInt();
            int e = scanner.nextInt();
            int i = scanner.nextInt();
            int d = scanner.nextInt();
            if (p == -1 && e == -1 && i == -1 && d == -1) {
                break;
            }
            int lcm = 21252;  // lcm(23,28,33)
            int n = (5544 * p + 14421 * e + 1288 * i - d + 21252) % 21252;
            if (n == 0)
                n = 21252;
            list.add(n);
        }
        for (int i = 1; i < list.size() + 1; i++) {
            System.out.println("Case " + i + ": the next triple peak occurs in " + list.get(i - 1) + " days.");

        }
    }
}

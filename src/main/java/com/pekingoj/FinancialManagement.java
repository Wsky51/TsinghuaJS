package com.pekingoj;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Created on 2020/3/23
 *
 * @author WuYi
 */
public class FinancialManagement {
    public static void main(String[] args) {
        double sum = 0;
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 12; i++) {
            double v = scanner.nextDouble();
            sum += v;
        }

        sum /= 12d;
        System.out.println("$"+new BigDecimal(sum+"").setScale(2));
    }
}

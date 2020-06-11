package com.pekingoj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created on 2020/3/22
 * poj 1003 http://poj.org/problem?id=1003
 * 3180K	204MS 未进行缓存，未进行二分
 * 3132K	141MS  进行了缓存，未进行二分
 *
 * @author WuYi
 */
public class Hangover {
    static double[] cache = new double[300];//缓存第n张卡片的总长度
    static int end = 0;//记录cache中的最后一个元素 ,1/2+1/3+...+1/(end+2)

    public static void main(String[] args) {
        cache[0] = (double) 1 / 2;
        Scanner scanner = new Scanner(System.in);
        List <Integer> list = new ArrayList <Integer>();
        List <Integer> list1 = new ArrayList <Integer>();
        while (true) {
            double d = scanner.nextDouble();
            if (d == 0.00) {
                break;
            }
            list.add(needCards(d));
        }
        for (int res : list) {
            System.out.println(res + " card(s)");
        }

    }

    public static int needCards(double d) {
        if (d <= 0.5) {
            return 1;
        }
        if (cache[end] < d) {
            double sum = cache[end];
            for (int i = end + 3; ; i++) {
                cache[i - 2] = sum + (double) 1 / i;
                end = i - 2;
                sum = cache[i - 2];
                if (sum >= d) {
                    return end + 1;
                }
            }
        } else {//否则，二分法求解
//            for (int i = 0; i < end + 1; i++) {
//                if (cache[i] >= d) {
//                    return i + 1;
//                }
//            }

            return binarySearch(cache, 0, end + 1, d);
        }
//        return -1;

    }

    public static int binarySearch(double[] arr, int from, int to, double val) {
        int mid = (from + to) / 2;
        while (from < to) {
            //如果相等则直接返回
            if (arr[mid] == val) {
                return mid;
            } else if (arr[mid] < val) {
                from = mid + 1;
            } else {
                to = mid;
            }
        }
        return from;
    }
}

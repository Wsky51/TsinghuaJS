package com.pat;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created on 2020/4/17
 * 3*5*6*7=630
 *
 * @author WuYi
 */
public class ConsecutiveFactors {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        printFactor(scanner.nextInt());
    }

    public static void printFactor(int n) {
        long lo, hi;
        lo = hi = 2;

        long reLo,reHi;
        reLo=reHi=0;
        long mul = 1;
        long count = 1;

        LinkedList <Long> queue = new LinkedList <>();
        long sqrt = (long) Math.sqrt(n + 0.5);
        for (long i = 2; i <= sqrt + 2; i++) {
            mul = mul * i;
            queue.addFirst(i);//加入到最后
            if (n % mul == 0) {//阶乘除的断就一直加进去
                continue;
            } else {
                hi = i - 1;
                if (hi - lo + 1 > count) {
                    reLo=lo;
                    reHi=hi;
                    count = hi - lo + 1;
                }
                while (n % mul != 0) {
                    Long re = queue.removeLast();
                    mul /= re;
                }
                if (queue.isEmpty()){//为空，说明全部都试完了，包括i
                    lo=hi=i+1;
                }else{
                    lo=queue.getLast();
                    hi=queue.getFirst();
                }
            }

        }

        if (reLo==reHi){
            System.out.println(1);
            for (int i = 2; i <=sqrt; i++) {
                if (n%i==0){
                    System.out.println(i);
                    return;
                }
            }
            System.out.println(n);
        }else {
            System.out.println(count);
            String res="";
            for (long i = reLo; i <=reHi ; i++) {
                res=res+i+"*";
            }
            System.out.println(res.substring(0,res.length()-1));
        }

//        System.out.println("lo:" + reLo + ",hi:" + reHi);

    }
}

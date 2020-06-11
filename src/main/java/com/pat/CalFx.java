package com.pat;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created on 2020/4/27
 *
 * @author WuYi
 */
public class CalFx {
    static final String[] dpStr = new String[101];//dp[i]表示9有多少个的情况，eg:dp[2]表示f(99)的值
    static final int[] DATA = new int[101];//DATA[i] 表示i个9的结果数,eg:DATA[3]表示f(999)的值
    static final int MOD = 20123;
    static final BigInteger MOD_BIG = new BigInteger(MOD + "");
    static final int[] FX = {0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 3};

    static {
        dpStr[0] = "0";
        DATA[0] = 0;
        dpStr[1] = "2";
        DATA[1] = 2;
        for (int i = 2; i < dpStr.length; i++) {
            dpStr[i] = (i * 2) + zeroNum(i - 1);
            DATA[i] = new BigInteger(dpStr[i]).mod(MOD_BIG).intValue();
        }

    }

    static String zeroNum(int n) {
        String res = "";
        String x = "0";
        while (n != 0) {
            if ((n & 1) == 1) {
                res += x;
            }
            n /= 2;
            x = x + x;
        }
        return res;

    }

    public static void main(String[] args) {
        int a = 0, b = 0;
        for (int i = 100000; i <=110000 ; i++) {
            a=fx(i+"");
            b=forceSolution(i);
            if (a!=b){
                System.out.println("a:"+a+"，b:"+b+",i:"+i);
            }

        }


//        Scanner scanner=new Scanner(System.in);
//        String line;
//        while (scanner.hasNextLine()){
//            line=scanner.nextLine();
//            System.out.println(fx(line));
//        }

    }

    public static int forceSolution(int x) {
        int res = 0;
        for (int i = 0; i <= x; i++) {
            res = (res + (int) calF(i)) % MOD;
        }
        return res % MOD;
    }

    static long calF(long x) {
        long a = x;
        long mod = 0;
        long res = 0;
        while (a != 0) {
            mod = a % 10;
            a /= 10;
            if (mod == 1 || mod == 2) {
                res++;
            }
        }
        return res;
    }

    public static long pow(long x, long pow) {
        long res = 1;
        while (pow != 0) {
            if ((pow & 1) == 1) {
                res = res * x % MOD;
            }
            pow = pow / 2;
            x = (x % MOD * x) % MOD;
        }
        return res % MOD;
    }

    public static int fx(String str) {
        int len = str.length();
        int[] dp = new int[len + 1];
        dp[0] = 0;
        char ch = str.charAt(len - 1);
        int temp;
        dp[1] = FX[ch - '0'];
        for (int i = 2; i <= len; i++) {
            ch = str.charAt(len - i);
            temp = new BigInteger(str.substring(len - i + 1, len)).mod(MOD_BIG).intValue() % MOD;
            if (ch == '0') {
                dp[i] = (dp[i - 1]) % MOD;
            } else if (ch == '1') {
                dp[i] = ((DATA[i - 1] % MOD + dp[i - 1] % MOD +
                        (temp) % MOD) + 1) % MOD;
            } else if (ch == '2') {
                dp[i] = (2 * DATA[i - 1] % MOD + dp[i - 1] % MOD + (int) pow(10, i - 1) + temp + 1) % MOD;
            } else {//>2,eg:5
                dp[i] = ((ch - '0') * DATA[i - 1] % MOD + dp[i - 1] % MOD + (int) pow(10, i - 1) * 2) % MOD;
            }
        }
        return dp[len] % MOD;

    }
}

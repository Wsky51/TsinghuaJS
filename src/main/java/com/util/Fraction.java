package com.util;

/**
 * Created on 2020/3/23
 * 分数类，copy from https://blog.csdn.net/weixin_43145361/article/details/91796054
 * * The class {@code Fraction} contains methods for performing basic
 * arithmetic operations such as addition, subtraction, multiplication
 * and division functions.
 * <p>
 * <p> It has the following data fields:
 * <p>{@code sign} is the sign of the fraction.
 * <p>{@code numerator} is the numerator of the fraction.
 * <p>{@code denominator} is the denominator of the fraction.
 * </p>This class contains the following methods:<p>
 * add, sub, multiply and divide for basic arithmetic operations.
 *
 */
public class Fraction {
    public int sign;           // 符号
    public int numerator;      // 分子
    public int denominator;    // 分母

    /**
     * Constructs a fraction object from a numerator and a denominator.
     * @param numerator The numerator.
     * @param denominator The denominator.
     */
    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            try {
                throw new Exception("Denominator cannot be zero.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        sign = numerator * denominator > 0 ? 1 : -1;
        this.numerator = Math.abs(numerator);
        this.denominator = Math.abs(denominator);
        simplify();
    }

    /**
     * Simplify a fraction such as 2/4 -> 1/2.
     */
    public void simplify() {
        if(numerator == 0)
            return;

        int gcd = getGreatestCommonDivider(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;
    }

    /**
     * To get the greatest common divider of a and b.
     * @param a the first argument to calculate the divider.
     * @param b the first argument to calculate the divider.
     * @return
     */
    public int getGreatestCommonDivider(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);

        int gcd = (a < b ? a : b);
        for (; gcd >= 1; gcd--)
            if (a % gcd == 0 && b % gcd == 0)
                break;

        return gcd;
    }

    /***********************************************
     *     加减乘除四则运算和相应的四个方便操作的重载。
     ***********************************************/

    public Fraction add(Fraction fraction) {
        numerator = this.sign * (numerator * fraction.denominator) + (fraction.numerator * denominator) * fraction.sign;
        denominator = denominator * fraction.denominator;
        simplify();
        return this;
    }

    public Fraction sub(Fraction fraction) {
        return add(-fraction.numerator, fraction.denominator);
    }

    public Fraction multiply(Fraction fraction) {
        this.sign *= fraction.sign;
        this.numerator *= fraction.numerator;
        this.denominator *= fraction.denominator;
        this.simplify();
        return this;
    }

    public Fraction divide(Fraction fraction) {
        return multiply(fraction.sign * fraction.denominator, fraction.numerator);
    }

    public Fraction add(int n, int d) {
        return add(new Fraction(n, d));
    }

    public Fraction sub(int n, int m) {
        return add(-n, m);
    }

    public Fraction multiply(int n, int d) {
        return multiply(new Fraction(n, d));
    }

    public Fraction divide(int n, int d) {
        return divide(new Fraction(n, d));
    }

    /**
     * Converts to a floating number of Double.
     * @return
     */
    public double toDouble() {
        return 1.0 * sign * numerator / denominator;
    }

    @Override
    public String toString() {
        if (numerator == 0)
            return "0";

        if(denominator == 1)
            return  (sign == 1 ? "" : "-") + numerator;

        return (sign == 1 ? "" : "-") + numerator + "/" + denominator;
    }
}

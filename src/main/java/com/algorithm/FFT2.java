package com.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created on 2020/4/24
 *
 * @author WuYi
 */
public class FFT2 {

    public static void main(String[] args) throws Exception {
        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        String[] nAndm = bf.readLine().split(" ");
        int n=Integer.parseInt(nAndm[0]);
        int m=Integer.parseInt(nAndm[1]);
        String[] line1 = bf.readLine().split(" ");
        String[] line2 = bf.readLine().split(" ");

        int lim = resize(n + m);
        if (lim==m+n){
            lim*=2;
        }


        Complex[] an = new Complex[lim];
        Complex[] am = new Complex[lim];
        for (int i = 0; i <= n; i++) {
            an[i] = new Complex(Integer.parseInt(line1[i]), 0);
        }
        for (int i = n + 1; i < an.length; i++) {
            an[i] = new Complex(0, 0);
        }

        for (int i = 0; i <= m; i++) {
            am[i] = new Complex(Integer.parseInt(line2[i]), 0);
        }

        for (int i = m + 1; i < am.length; i++) {
            am[i] = new Complex(0, 0);
        }

        fft2(an, 1);
        fft2(am, 1);

        Complex[] data = new Complex[lim];
        for (int i = 0; i < lim; i++) {
            data[i] = an[i].multi(am[i]);
        }

        fft2(data, -1);
        int[] res = new int[lim];
        for (int i = 0; i < lim; i++) {
            res[i] = (int) (data[i].re() + 0.5);
        }
        int end = 0;
        for (int i = lim - 1; i >= 0; i--) {
            if (res[i] != 0) {
                end = i;
                break;
            }
        }
        String str = "";
        for (int i = 0; i <= end; i++) {
            str+=res[i]+" ";
        }
        System.out.println(str.trim());
    }

    //找到>=n的最小2的幂次，如resize(8)=8,resize(9)=16
    public static int resize(int n) {
        int a = n - 1;
        a |= a >>> 1;
        a |= a >>> 2;
        a |= a >>> 4;
        a |= a >>> 8;
        a |= a >>> 16;
        return a < 0 ? 1 : a + 1;
    }

    //进行一次傅里叶变换， lim为次数界，必须确保a的长度为2的次幂，也即lim为2的次幂
    public static void fft(Complex[] a, int lim) {
        int half = lim >>> 1;
        if (lim == 1) {
            return;
        }
        Complex[] a0 = new Complex[half];
        Complex[] a1 = new Complex[half];
        for (int i = 0; i < lim; i += 2) {
            a0[i >>> 1] = a[i];
            a1[i >>> 1] = a[i + 1];
        }

        fft(a0, half);
        fft(a1, half);

        double base = 2.0d * Math.PI / lim;
        for (int k = 0; k < half; k++) {
            Complex w = wn(k * base);//计算w(n,k)
            Complex t = w.multi(a1[k]);//t=w*a1[k]
            a[k] = a0[k].add(t);//a[k]=a0[k]+w*a1[k];
            a[k + half] = a0[k].minus(t);//a[k+n/2]=a0[k]-w*a1[k];

        }
    }

    //设n为数组a的原长度，lim表示次数界，将lim从1左移至大于n,并记录左移的次数，也就是二进制的位数
    //高效非迭代fft,opt代表为什么变换，若opt为1则表示正向变换，若opt为-1则表示进行逆变换ifft
    //注意，传入的a数组的长度必须是2的n次幂，如8,16等等，务必保证
    public static void fft2(Complex[] a, int opt) {
        if (opt != 1 && opt != -1) {
            System.out.println("ERROR!!!:opt val must be 1 or -1,1 means fft,-1 means ifft");
            return;
        }
        //对a进行resize,长度凑成2的幂次
        int lim = resize(a.length);//lim为数组a的次数界，如8,16，确保为2的幂次
        int len = log2(lim);
        int[] rev = new int[lim];
        for (int i = 0; i < lim; i++) {
            rev[i] = (rev[i >> 1] >> 1) | (((i & 1) << (len - 1)));
//            System.out.print(rev[i] + " ");
        }
//        System.out.println("处理后的a:"+ Arrays.toString(a)+",长度："+a.length);
        for (int i = 0; i < lim; ++i) {
            if (i < rev[i]) swap(a, i, rev[i]);
        }
        for (int dep = 1; dep <= log2(lim); ++dep) {
            int m = 1 << dep;
            double base = 2.0d * Math.PI / m;
            for (int k = 0; k < lim; k += m) {
                for (int j = 0; j < m / 2; ++j) {
                    Complex w = opt == 1 ? wn(j * base) : wn(j * base).conjugate();//计算w(n,k)
                    Complex t = w.multi(a[k + j + m / 2]);
                    Complex u = a[k + j];
                    a[k + j] = u.add(t);
                    a[k + j + m / 2] = u.minus(t);
                }
            }
        }

        if (opt == -1) {
            for (int i = 0; i < lim; ++i) {
                a[i].re /= lim;
            }
        }

//        System.out.println("--------------");
//        for (int i = 0; i < a.length; i++) {
//            System.out.println(a[i]);
//        }

    }

    public static int log2(int num) {
        int count = 0;
        while (num != 1) {
            num >>= 1;
            count++;
        }
        return count;
    }

    public static void swap(Complex[] a, int idx1, int idx2) {
        Complex temp = a[idx1];
        a[idx1] = a[idx2];
        a[idx2] = temp;
    }

    public static Complex wn(double angle) {
        return new Complex(Math.cos(angle), Math.sin(angle));
    }

    static class Complex {
        double re;//实部
        double im;//虚部

        public Complex(double re, double im) {
            this.re = re;
            this.im = im;
        }

        // return a string representation of the invoking Complex object
        public String toString() {
            if (im == 0) return re + "";
            if (re == 0) return im + "i";
            if (im < 0) return re + " - " + (-im) + "i";
            return re + " + " + im + "i";
        }

        public Complex add(Complex complex) {
            return new Complex(this.re + complex.re, this.im + complex.im);
        }

        public Complex minus(Complex complex) {
            return new Complex(this.re - complex.re, this.im - complex.im);
        }

        public Complex multi(Complex complex) {
            return new Complex(this.re * complex.re - this.im * complex.im, this.re * complex.im + this.im * complex.re);
        }

        //求该复数的共轭复数
        public Complex conjugate() {
            return new Complex(re, -im);
        }

        public double re() {
            return re;
        }

        public double im() {
            return im;
        }
    }
}


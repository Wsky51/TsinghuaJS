package com.leetcode.shuati;

public class Q605 {
    public static void main(String[] args) {
        Q605 demo=new Q605();
        boolean res = demo.canPlaceFlowers(new int[]{1}, 1);
        System.out.println("res:"+res);
    }
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if(n==0){
            return true;
        }
        if (flowerbed.length==1){
            if (flowerbed[0]==1){
                return false;
            }else if (n<=1){
                return true;
            }
        }
        if (flowerbed.length>1&&flowerbed[1]==0&&flowerbed[0]==0){
            flowerbed[0]=1;
            n--;
        }
        for (int i = 1; i < flowerbed.length-1; i++) {
            if (flowerbed[i]==1){
                continue;
            }
            if (flowerbed[i-1]==0&&flowerbed[i+1]==0){
                flowerbed[i]=1;
                n--;
            }
        }
        if (flowerbed[flowerbed.length-2]==0&&flowerbed[flowerbed.length-1]==0){
            flowerbed[flowerbed.length-1]=1;
            n--;
        }
        System.out.println("n:"+n);
        return n>0?false:true;
    }
}

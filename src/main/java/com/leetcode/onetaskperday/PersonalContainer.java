package com.leetcode.onetaskperday;

/**
 * Created on 2020/4/18
 *
 * @author WuYi
 */
public class PersonalContainer {
    public static void main(String[] args) {
        System.out.println(cal(new int[]{1,8,6,2,5,4,8,3,7}));
    }

    public static int cal(int[] data) {
        int max=0;
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = i + 1; j < data.length; j++) {
                int temp=(j-i)*Math.min(data[i],data[j]);
                if (temp>max){
                    max=temp;
                }
            }
        }
        return max;
    }

}

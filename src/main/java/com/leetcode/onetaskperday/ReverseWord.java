package com.leetcode.onetaskperday;

/**
 * Created on 2020/4/10
 *
 * @author WuYi
 */
public class ReverseWord {
    public static void main(String[] args) {
        ReverseWord demo=new ReverseWord();
        String s = demo.reverseWord(" a good   example");
        System.out.println(s);
    }

    public String reverseWord(String s) {
        if (s.trim().isEmpty()){
            return "";
        }
        String[] split = s.trim().split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = split.length-1; i >=0; i--) {
            if (split[i].trim().isEmpty()){
                continue;
            }
            sb.append(split[i].trim()+" ");
        }
        return sb.substring(0,sb.length()-1);
    }
}

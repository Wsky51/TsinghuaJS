package com.leetcode.contest184;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2020/4/12
 *
 * @author WuYi
 */
public class StringMatch {
    public static void main(String[] args) {
        StringMatch demo = new StringMatch();
        System.out.println(demo.stringMatching(new String[]{"leetcode","et","code"}));
    }

    public List <String> stringMatching(String[] words) {
        List <String> list = new ArrayList <>();
        boolean[] isOk = new boolean[words.length];
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (isOk[i] || i == j || (words[i].length() > words[j].length())) {
                    continue;
                }
                if (isSubStr(words[i], words[j])) {
//                    System.out.println("word[i]:" + words[i] + ",word[j]:" + words[j]);
                    isOk[i] = true;
                    list.add(words[i]);
                    break;
                }

            }

        }
        return list;

    }

    public boolean isSubStr(String a, String b) {
        if (b.contains(a)) {
            return true;
        }
        return false;
    }
}

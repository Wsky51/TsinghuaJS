package com.leetcode.onetaskperday;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2020/4/9
 *
 * @author WuYi
 */
public class Brackets {
    public static void main(String[] args) {
        Brackets demo=new Brackets();
        demo.generateParenthesis(3);
    }
    public List<String> generateParenthesis(int n){
        List<String> list=new ArrayList <>();
        dfs(n,n,"",list);
        System.out.println(list);
        return list;
    }

    public void dfs(int left,int right,String str,List<String> list){
        if (left==0&&right==0){//左右括号都没得用的时候
            list.add(str);
        }
        if(left>right){
            return;//减掉
        }
        if (left>0){//还有左括号可以用
            dfs(left-1,right,str+"(",list);
        }
        if (right>0){
            dfs(left,right-1,str+")",list);
        }

    }
}







package com.csp;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 2020/3/2
 * 这道题搞了半天还没做出来，先放一放
 * @author WuYi
 */
public class Chemicalequation2019123 {
    Pattern p=Pattern.compile("\\(\\w*\\)");
    public static void main(String[] args) {

    }

    //equation类似于 H2+02=H20, Ca(OH)2 ,CaCO3

    public static char isOk(String equation) {
        Map <String, Integer> leftMap = new HashMap();
        Map <String, Integer> rightMap = new HashMap();
        String[] split = equation.split("=");
        String[] leftPart = split[0].split("\\+");
        String[] rightPart = split[1].split("\\+");
        //对左侧等式每一个元素进行单独处理

        return leftMap.equals(rightMap) ? 'Y' : 'N';
    }

    public static void parseParts(String[] parts, Map <String, Integer> map) {
        for (String ele : parts) {//ele 是 单个的一个比如H20， H3PO4

            /**
             * 用来计算基础倍数和移动下标
             */
            String res = "";
            int i;
            for (i = 0; i < ele.length(); i++) {
                char c;
                if (!((c = ele.charAt(i)) >= '0' && c <= '9')) {
                    break;
                }
                res += c;
            }
            int baseMul = res.isEmpty() ? 1 : Integer.parseInt(res);

            int signBegin = 0, signEnd = 0;
            int signMul = 1;
            for (; i < ele.length(); i++) {
                char c = ele.charAt(i);

                /**
                 * 处理括号，计算开始和结束位置
                 */
                if (c == '(') {
                    signBegin = i;
                    int temp = i;
                    while (ele.charAt(temp) != ')') {
                        temp++;
                    }
                    signEnd = temp;
                }

                /**
                 * 计算右括号之后的那个数字
                 */
                String signMulStr = "";
                int signEnd1 = signEnd;
                while (signEnd1 < ele.length() - 1 && isDigi(ele.charAt(signEnd1 + 1))) {
                    signMulStr += ele.charAt(signEnd1 + 1);
                    signEnd1++;
                }
                signMul = signMulStr.isEmpty()?1:Integer.parseInt(signMulStr);




            }

        }

    }

    public static boolean isDigi(char c) {
        return c >= '0' && c <= '9';
    }

}

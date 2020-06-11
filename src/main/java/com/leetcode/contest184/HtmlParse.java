package com.leetcode.contest184;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2020/4/12
 *
 * @author WuYi
 */
public class HtmlParse {
    public static void main(String[] args) {
        HtmlParse demo=new HtmlParse();
        System.out.println(demo.entityParser("and I quote: &quot;...&quot;"));
    }
    public String entityParser(String text) {
        Map<String,String> map=new HashMap <>();
        map.put("&quot;","\"");
        map.put("&apos;","'");
        map.put("&amp;","&");
        map.put("&gt;",">");
        map.put("&lt;","<");
        map.put("&frasl;","/");
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < text.length();) {
            char ch = text.charAt(i);
            if (ch!='&'){
                sb.append(ch);
                i++;
                continue;
            }else {
                String tempStr="";
                int end=i;
                while(end<text.length()&&text.charAt(end)!=';'){
                    end++;
                    continue;
                }
                if (map.containsKey((tempStr=text.substring(i,end+1)))){
                    sb.append(map.get(tempStr));
                    i=end+1;
                }else {
                    sb.append(ch);
                    i++;
                    continue;
                }
            }
        }

        return sb.toString();
    }
}

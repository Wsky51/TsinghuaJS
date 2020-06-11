package com.leetcode.onetaskperday;

/**
 * Created on 2020/4/12
 *
 * @author WuYi
 */
public class LineIntersection {

    public static void main(String[] args) {
        LineIntersection demo=new LineIntersection();
        System.out.println(demo.intersection(0,0,1,1,1,0,2,1));
    }

    //判断线段(x1,y1)<->(x2,y2) 和线段(x3,y3)<->(x4,y4)是否相交,来自算法导论
    boolean intersection(int x1,int y1,int x2,int y2,int x3,int y3,int x4,int y4){
        int d1=direction(x3,y3,x4,y4,x1,y1);
        int d2=direction(x3,y3,x4,y4,x2,y2);
        int d3=direction(x1,y1,x2,y2,x3,y3);
        int d4=direction(x1,y1,x2,y2,x4,y4);
        if (((d1>0&&d2<0)||(d1<0&&d2>0))&&((d3>0&&d4<0)||(d3<0&&d4>0))){
            return true;
        }else if (d1==0&&onSegment(x3,y3,x4,y4,x1,y1)){
            return true;
        }else if (d2==0&&onSegment(x3,y3,x4,y4,x2,y2)){
            return true;
        }else if (d3==0&&onSegment(x1,y1,x2,y2,x3,y3)){
            return true;
        }else if(d4==0&&onSegment(x1,y1,x2,y2,x4,y4)){
            return true;
        }
        return false;

    }

    //以(x0,y0)为基准点，判断点(x1,y1)在点(x2,y2)是在顺时针还是逆时针,若返回为0，则为顺时针，否则为逆时针
    //若为0，则说明说明共线
    int direction(int x0, int y0, int x1, int y1, int x2, int y2) {
        int a = x1 - x0, b = x2 - x0;
        int c = y1 - y0, d = y2 - y0;
        return a * d - b * c;
    }

    //判断点(x0,y0)是否在线段(x1,y1)<->(x2,y2)中
    boolean onSegment(int x1,int y1,int x2,int y2,int x0,int y0){
        if (x0>=Math.min(x1,x2)&&x0<=Math.max(x1,x2)&&y0>=Math.min(y1,y2)&&y0<=Math.max(y1,y2)){
            return true;
        }
        return false;
    }

}
package com.csp;

import java.util.*;

/**
 * Created on 2020/3/2
 *
 * @author WuYi
 *         <p>
 *         7
 *         1 2
 *         2 1
 *         0 0
 *         1 1
 *         1 0
 *         2 0
 *         0 1
 */

public class Recyclebin2019122 {
    public static void main(String[] args) {
        int zeroCount = 0, oneCount = 0, twoCount = 0, threeCount = 0, fourCount = 0;
        Set <Point> set = new HashSet();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        //将坐标全部加入到其中
        for (int i = 0; i < n; i++) {
            int xPos = scanner.nextInt();
            int yPos = scanner.nextInt();
            set.add(new Point(xPos, yPos));
        }
        for (Point p : set) {
            Point left = new Point(p.x - 1, p.y);
            Point right = new Point(p.x + 1, p.y);
            Point top = new Point(p.x, p.y + 1);
            Point bottom = new Point(p.x, p.y - 1);
            //如果上下左右都有位置的话，就是一个选定的点了
            if (set.contains(left) && set.contains(right) && set.contains(top) && set.contains(bottom)) {
                Point southwest = new Point(p.x - 1, p.y - 1);
                Point southeast = new Point(p.x + 1, p.y - 1);
                Point northeast = new Point(p.x + 1, p.y + 1);
                Point northwest = new Point(p.x - 1, p.y + 1);
                int score = 0;
                if (set.contains(southwest)) {
                    score++;
                }
                if (set.contains(southeast)) {
                    score++;
                }
                if (set.contains(northeast)) {
                    score++;
                }
                if (set.contains(northwest)) {
                    score++;
                }
                switch (score) {
                    case 0:
                        zeroCount++;
                        break;
                    case 1:
                        oneCount++;
                        break;
                    case 2:
                        twoCount++;
                        break;
                    case 3:
                        threeCount++;
                        break;
                    case 4:
                        fourCount++;
                        break;
                }

            } else {
                continue;
            }
        }
        System.out.println(zeroCount);
        System.out.println(oneCount);
        System.out.println(twoCount);
        System.out.println(threeCount);
        System.out.println(fourCount);

    }

}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;
    }

    @Override
    public int hashCode() {
        int result = 5 + 17 * x;
        result = 31 * result + y;
        return result;
    }
}
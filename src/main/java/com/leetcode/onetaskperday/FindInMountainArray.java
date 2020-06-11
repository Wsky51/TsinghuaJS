package com.leetcode.onetaskperday;

/**
 * Created on 2020/4/29
 *
 * @author WuYi
 */
public class FindInMountainArray {
    public static void main(String[] args) {
        int[] arr = new int[]{0, 5, 6, 8, 10, 14, 18, 19, 20, 21, 25, 28, 30, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 44,45,46,49,50,51,52,53,55,57,59, 30, 22, 21, 20, 19, 18, 17, 14, 12, 11, 8, 3, 1};
        MountainArray mountainArr = new MountainArray(arr);

        for (int i = 0; i <arr.length; i++) {
            System.out.println(findInMountainArray(arr[i],mountainArr) + ",time:" + mountainArr.getTime());
            mountainArr.clearTime();
        }
//        System.out.println(findInMountainArray(3, mountainArr));
    }

    public static int findMaxIdx(MountainArray mountainArr) {
        int len = mountainArr.length();
        int lo = 0, hi = len, mi = 0;
        int max = 0;

        while (true) {
            mi = (lo + hi) >> 1;
            int data = mountainArr.get(mi);
            int pre = mi >= 1 ? mountainArr.get(mi - 1) : -1;
            int next = mi + 1 < len ? mountainArr.get(mi + 1) : Integer.MAX_VALUE;
            if (pre < data && data > next) {
                max = mi;
                break;
            } else if (pre < data) {//还在上升期
                lo = mi + 1;
            } else { //且 pre>data,处于下降期
                hi = mi - 1;
            }
        }
        return max;

    }

    public static int findInMountainArray(int target, MountainArray mountainArr) {

        int len = mountainArr.length();
        int maxIdx = findMaxIdx(mountainArr);
        int i = binarySearch(mountainArr, 0, maxIdx + 1, target);
        if (i != -1) {
            return i;
        } else {
            return binarySearchRe(mountainArr, maxIdx, len, target);
        }
    }

    static int binarySearch(MountainArray mountainArr, int lo, int hi, int val) {
        int mi = 0;
        while (lo < hi) {
            mi = (lo + hi) >> 1;
            int miVal = mountainArr.get(mi);

            if (val < miVal) {
                hi = mi;
            } else if (miVal < val) {
                lo = mi + 1;
            } else {
                return mi;
            }
        }
        return -1;
    }

    static int binarySearchRe(MountainArray mountainArr, int lo, int hi, int val) {
        int mi = lo;
        while (lo < hi) {
            mi = (lo + hi) >> 1;
            int miVal = mountainArr.get(mi);
            if (miVal > val) {
                lo = mi + 1;
            } else if (val > miVal) {
                hi = mi;
            } else {
                return mi;
            }
        }
        return -1;

    }

    static class MountainArray {
        static int time = 0;
        int[] arr;

        public MountainArray(int[] arr) {
            this.arr = arr;
            time = 0;
        }

        public int get(int index) {
            time++;
            return arr[index];
        }

        public int length() {
            return arr.length;
        }

        public int getTime() {
            return time;
        }

        public void clearTime() {
            time = 0;
        }
    }
}

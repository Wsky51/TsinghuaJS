//package com.algorithm;
//
///**
// * Created on 2020/3/8
// *
// * @author WuYi
// *         <p>
// *         // 暂时还是搞不懂这个算法怎么运作的
// */
//public class DancingLinks {
//    static final int N = 10;
//    static final int head = 100;
//    static int Up[], Down[], Left[], Right[];
//    int Col[], Row[], ans[];//C[N]表示N的列标，H[N]表示N的行标，ans[]用来储存结果
//    static int size;
//
//    public static void main(String[] args) {
//
//    }
//
//    boolean dance(int k) {
//        int c = Right[head];//取当前head指针的第一个右元素（也就是不包含Head指针的最左上角的那个元素）
//        if (c == head) {//说明已经到头了
////            Output();//Output the solution
//            return true;
//        }
//        remove(c);//c可以认为是最顶层(head->C1->C2->C3....-head那一层，无实际意义)
//        for (int i = Down[c]; i != c; i = Down[i]) {
//            ans[k] = Row[i];
//            for (int j = Right[i]; j != i; j = Right[j]) {
//                remove(Col[j]);
//            }
//            if (dance(k + 1)) {
//                return true;
//            }
//            for (int j = Left[i]; j != i; j = Left[j]) {
//                resume(Col[j]);
//            }
//        }
//        resume(c);
//        return false;
//    }
//
//    /**
//     * 去除节点c的左右指针，且c列下的所有右侧节点的上下指针清空
//     *
//     * @param c
//     */
//    void remove(int c) {
//        /**
//         * 将c节点的左右连接在一起
//         */
//        Left[Right[c]] = Left[c];
//        Right[Left[c]] = Right[c];
//        for (int i = Down[c]; i != c; i = Down[i]) {//从c节点down节点开始
//            for (int j = Right[i]; j != i; j = Right[j]) {//从当前节点的右节点开始
//                Up[Down[j]] = Up[j];//上下指针连接
//                Down[Up[j]] = Down[j];
//            }
//        }
//    }
//
//    /**
//     * remove操作的逆操作
//     *
//     * @param c
//     */
//    void resume(int c) {
//        Left[Right[c]] = c;
//        Right[Left[c]] = c;
//        for (int i = Up[c]; i != c; i = Up[i]) {
//            for (int j = Right[i]; j != i; j = Right[j]) {
//                Up[Down[j]] = j;
//                Down[Up[j]] = j;
//            }
//        }
//    }
//
//    /**
//     * 在第r行，第c列添加一个元素
//     *
//     * @param r row
//     * @param c column
//     */
//    void push(int r, int c) {
//        size++;
//        Down[size] = Down[c];
//        Up[size] = c;
//        Up[Down[c]] = size;
//        Down[c] = size;
//        Row[size] = r;
//        Col[size] = c;
//        if (H[r] < 0) {
//            H[r] = size;
//            R[size] = L[size] = size;
//        } else {
//            L[size] = H[r];
//            R[size] = R[H[r]];
//            L[R[H[r]]] = size;
//            R[H[r]] = size;
//        }
//    }
//}

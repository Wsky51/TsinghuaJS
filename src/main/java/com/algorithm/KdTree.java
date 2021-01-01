package com.algorithm;

import java.util.LinkedList;
import java.util.Queue;

public class KdTree {

    static boolean dimensionX = true;
    static Data[] datas = new Data[6];
    static {
        for (int i = 0; i < 6; i++) {
            datas[i] = new Data();
        }
        datas[0].x1 = 2;
        datas[1].x1 = 5;
        datas[2].x1 = 9;
        datas[3].x1 = 4;
        datas[4].x1 = 8;
        datas[5].x1 = 7;
        datas[0].x2 = 3;
        datas[1].x2 = 4;
        datas[2].x2 = 6;
        datas[3].x2 = 7;
        datas[4].x2 = 1;
        datas[5].x2 = 2;
    }

    public static void main(String[] args) {
        Tree root = new Tree();
        builtTree(root, datas);//进行递归构造kd树
//        printKdTree(root);
        Tree nodeX=new Tree();
        nodeX.mData=new Data();
        nodeX.mData.x1=3d;
        nodeX.mData.x2=4.5d;
        Tree res = searchNode(root, nodeX);
        System.out.println("查找到的叶子节点为"+res);
        System.out.println("两者之间的距离dis:"+calDis(res,nodeX));

    }

    public static double calDis(Tree node1,Tree node2){
        double x11=node1.mData.x1;
        double x12=node1.mData.x2;
        double x21=node2.mData.x1;
        double x22=node2.mData.x2;
        return Math.sqrt((x11-x21)*(x11-x21)+(x12-x22)*(x12-x22));
    }

    public static void printKdTree(Tree root) {
//        Tree temp=root;
        Queue<Tree> q = new LinkedList();
        q.add(root);
        while (!q.isEmpty()) {
//            System.out.println("q.length:"+q.size());
            Tree node = q.remove();
            System.out.println(node);

            if (node.left != null) {
                q.add(node.left);
            }
            if (node.right != null) {
                q.add(node.right);
            }
        }
    }

    //从KD树的root节点进行查找节点x，找到其范围
    public static Tree searchNode(Tree root, Tree x) {
        Data xData = x.mData;
        Tree temp = root;
        while (true) {
            if (dimensionX) {
                dimensionX = false;
                if (temp.mData.x1 < xData.x1) {
                    if (temp.right==null||temp.right.mData == null) {
                        return temp;
                    } else {
                        temp = temp.right;
                    }
                } else {
                    if (temp.left==null||temp.left.mData == null) {
                        return temp;
                    } else {
                        temp = temp.left;
                    }
                }
            } else {
                dimensionX = true;
                if (temp.mData.x2 < xData.x2) {
                    if (temp.right==null||temp.right.mData == null) {
                        return temp;
                    } else {
                        temp = temp.right;
                    }
                } else {
                    if (temp.left==null||temp.left.mData == null) {
                        return temp;
                    } else {
                        temp = temp.left;
                    }
                }
            }

        }
    }

    private static void builtTree(Tree root, Data[] datas) {
        if (datas == null) return;//当没有的时候说明这个不能成为节点
        if (datas.length == 1) {//只有一个就是他自己了，它和第一个判断不能换位置
            root.mData = datas[0];//设置数据
            return;
        } else {
            dataSort(datas, root.getSplit() % 2); //进行数据的排列，注意传参判断其是第几维划分
            root.left = new Tree();
            root.right = new Tree();
            root.left.setSplit(root.getSplit() + 1);//不要忘记写其左右节点的维度
            root.right.setSplit(root.getSplit() + 1);
            int middle = datas.length / 2;           //进行数据的对分
            root.mData = datas[middle];
            Data leftData[] = new Data[middle];
            for (int j = 0; j < middle; j++) {
                leftData[j] = datas[j];
            }
            Data rightData[];
            if (datas.length == 2) {
                rightData = null;
            } else {
                rightData = new Data[datas.length - 1 - middle];
                for (int k = middle + 1, j = 0; k < datas.length; k++, j++) {
                    rightData[j] = datas[k];
                }
            }
            builtTree(root.left, leftData); //递归
            builtTree(root.right, rightData);
        }
    }

    private static void dataSort(Data[] datas, int i) {//冒泡排序法
        if (i == 0) {
            for (int k = 0; k < datas.length - 1; k++) {
                for (int j = 0; j < datas.length - 1 - k; j++) {
                    if (datas[j].x1 > datas[j + 1].x1) {
                        Data temp = datas[j];
                        datas[j] = datas[j + 1];
                        datas[j + 1] = temp;
                    }
                }
            }
        } else {
            for (int k = 0; k < datas.length - 1; k++) {
                for (int j = 0; j < datas.length - 1 - k; j++) {
                    if (datas[j].x2 > datas[j + 1].x2) {
                        Data temp = datas[j];
                        datas[j] = datas[j + 1];
                        datas[j + 1] = temp;
                    }
                }
            }
        }
    }
}

//kd树
class Tree {
    public Tree left;//左节点
    public Tree father;
    public Tree right;
    public Data mData;

    public int split;//进行第几维度的划分

    public void setSplit(int split) {
        this.split = split;
    }

    public int getSplit() {
        return split;
    }

    @Override
    public String toString() {
        if (mData == null) {
            return null;
        }
        return mData.toString();
    }
}

class Data {
    public double x1;
    public double x2;

    @Override
    public String toString() {
        return "(" + x1 + "," + x2 + ")";
    }
}
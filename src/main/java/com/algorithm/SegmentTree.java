package com.algorithm;

import java.util.Arrays;

/**
 * Created on 2020/3/9
 * 线段树,参考 https://blog.csdn.net/qq_39826163/article/details/81436440
 * 推荐视频:https://www.bilibili.com/video/av47331849?from=search&seid=17356072154191995911
 * <p>
 * 1、每个节点的左孩子区间范围为[l，mid]，右孩子为[mid+1,r]
 * 2、对于结点k，左孩子结点为2*k，右孩子为2*k+1，这符合完全二叉树的性质
 * </p>
 * <p>
 * oj真题：https://www.luogu.com.cn/problem/P3374
 *
 * @author WuYi
 */
public class SegmentTree {

    public static void main(String[] args) {
        SegmentTree demo = new SegmentTree();
        int[] arr = {1, 3, 5, 7, 9, 11};//线段树解决任意从i到j的和问题
        SegmentNode[] tree = new SegmentNode[arr.length * 4 + 1];
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new SegmentNode();
        }
        demo.build(tree, arr, 0, 0, arr.length - 1);//构造树
        for (int i = 0; i < 15; i++) {
            System.out.println("tree[" + i + "].sum=" + tree[i].sum +
                    ",left:" + tree[i].left + ",right:" + tree[i].right);
        }

        System.out.println("-------------");
        demo.updateTree(tree, arr, 0, 4, 6);//更新arr[4]=6
        for (int i = 0; i < 15; i++) {
            System.out.println("tree[" + i + "].sum=" + tree[i].sum +
                    ",left:" + tree[i].left + ",right:" + tree[i].right);
        }

        System.out.println("res:"+demo.query(tree,arr,0,2,5));//查询2到5的和

    }

    /**
     * 建树
     * <p>
     * a.对于二分到的每一个结点，给它的左右端点确定范围。
     * b.如果是叶子节点，存储要维护的信息。
     * c.状态合并。
     * </p>
     * <p>
     * 根节点从0号开始，左孩子节点=i*2+1;右孩子节点为=i*2+2
     * </p>
     *
     * @param tree
     * @param input 用户提供的数据信息，叶子节点的sum信息由此而来
     * @param i     从i节点开始建树
     * @param left  该节点的左侧端数值
     * @param right 该节点的右侧端数值
     */
    public void build(SegmentNode[] tree, int[] input, int i, int left, int right) {
        tree[i].left = left;
        tree[i].right = right;
        if (left == right)//叶子节点
        {
            tree[i].sum = input[left];//该出的值由用户提供
            return;
        }
        int mid = (left + right) / 2;
        build(tree, input, i * 2 + 1, left, mid);//左孩子
        build(tree, input, i * 2 + 2, mid + 1, right);
        tree[i].sum = tree[i * 2 + 1].sum + tree[i * 2 + 2].sum;//状态合并，此结点的sum=两个孩子的sum之和
    }

    /**
     * @param tree
     * @param input
     * @param i     树的第i个节点
     * @param idx   需要修改的input的第idx个元素
     * @param val   需要修改的值
     */
    public void updateTree(SegmentNode[] tree, int[] input, int i, int idx, int val) {
        if (tree[i].left == tree[i].right) {
            input[idx] = val;
            tree[i].sum = val;
            return;
        }
        int mid = (tree[i].left + tree[i].right) / 2;
        if (idx >= tree[i].left && idx <= mid) {//若idx属于当前节点的左侧段
            updateTree(tree, input, i * 2 + 1, idx, val);//更新左节点
        } else {
            updateTree(tree, input, i * 2 + 2, idx, val);//更新右节点
        }
        tree[i].sum = tree[i * 2 + 1].sum + tree[i * 2 + 2].sum;//状态合并，此结点的sum=两个孩子的sum之和
    }

    /**
     * 该方法基于updateTree方法，将第idx元素增加val
     * @param tree
     * @param input
     * @param i
     * @param idx
     * @param val
     */
    public void add(SegmentNode[] tree, int[] input, int i, int idx, int val) {
        updateTree(tree,input,i,idx,input[idx]+val);
    }

    /**
     * 求出从[left,right]范围内的和是多少
     *
     * @param tree
     * @param input
     * @param i
     * @param left  求从left开始
     * @param right 到right结束这段范围的和
     */
    public int query(SegmentNode[] tree, int[] input, int i, int left, int right) {
        if (right < tree[i].left || left > tree[i].right) {
            return 0;
        }
        if (left <= tree[i].left && tree[i].right <= right) {
            return tree[i].sum;
        }
        if (tree[i].left == tree[i].right) {
            return tree[i].sum;
        }
        int mid = (tree[i].left + tree[i].right) / 2;
        int leftNode = i * 2 + 1;
        int rightNode = i * 2 + 2;
        int sumLeft = query(tree, input, leftNode, left, right);
        int sumRight = query(tree, input, rightNode, left, right);
        return sumLeft + sumRight;
    }

}

class SegmentNode {
    int left, right;//保存节点的左右线段信息，注意：此处的left,right不是该节点的左右孩子
    int sum;//当前节点该段的总和
}


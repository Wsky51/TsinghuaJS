package com.algorithm;

/**
 * Created on 2020/3/29
 * Tire树，也即字典树,根据定义完全自己手写
 *
 * @author WuYi
 */
public class TireTree {

    public static void main(String[] args) {
        TireNode root = new TireNode('0', null);
        insert(root, "code");

        insert(root, "cook");
        insert(root, "five");
        insert(root, "fat");
        insert(root, "cod");
        System.out.println("cod exist:"+existStr(root,"cod"));

        System.out.println("rooot:"+root);

        TireNode tC = root.getChild('c');
        System.out.println(tC);

        TireNode tO = root.getChild('c').getChild('o');
        System.out.println(tO);

        delete(root,"cod");
        System.out.println("cod exist:"+existStr(root,"cod"));

    }

    /**
     * 在root下插入一个单词
     *
     * @param root
     * @param str
     */
    public static void insert(TireNode root, String str) {
        if (str.trim().isEmpty()) {
            return;
        }

        TireNode p = root;
        for (int j = 0; j < str.length(); j++) {
            p.addChild(str.charAt(j),str.charAt(j));
            p = p.getChild(str.charAt(j));
        }

        p.setIsStr(true);//单词结束的地方设置为单词
        p.setCountAsWord(p.getCountAsWord() + 1);//将其以单词结尾数加一
    }

    /**
     * 查找str在字典树中出现了多少次
     *
     * @param root
     * @param str
     * @return
     */
    public static int getCountAsWord(TireNode root, String str) {
        TireNode p = search(root, str);
        if (p == null || !p.isStr()) {//str串不在字典树中，直接返回
            return 0;
        }
        return p.getCountAsWord();
    }

    /**
     * 在root节点下查找字符串str,如果存在，则返回对应最后的那个叶子节点(也即是查询串的最后一个字符的节点)
     *
     * @param root
     * @param str
     * @return
     */
    public static TireNode search(TireNode root, String str) {
        if (str.isEmpty()) {
            return null;//空串无意义
        }
        TireNode p = root;
        for (int i = 0; i < str.length(); i++) {
            if (p == null) {
                break;
            }
            p = p.getChild(str.charAt(i));
        }
        return p;
    }

    /**
     * 查询字符串是否在字典树中，若在，则返回true,否则false
     *
     * @param root
     * @param str
     * @return
     */
    public static boolean existStr(TireNode root, String str) {
        TireNode p = search(root, str);
        return p != null && p.isStr();
    }

    /**
     * 从字典树中删除str串
     *
     * @param root
     * @param str
     */
    public static void delete(TireNode root, String str) {
        TireNode p = search(root, str);
        if (p == null || !p.isStr()) {//str串不在字典树中，直接返回
            return;
        }

        if (p.isStr()) {//如果是单词标志，则去除标志
            p.setIsStr(false);
        }

        while (p != root) {
            if (p.isLeaf()) {//如果是叶子节点，就删除
                p.getParent().deleteChild(p.getC());
            } else {
                p.decreaseCnt();//将count值减一
            }
            p = p.getParent();
        }

    }

}

class TireNode {
    private static final int MAX_CHAR = 26;//26个英文字母，任何单词不会超过26个英文单词

    private char c;//该节点的字符
    private boolean isStr;//标记该结点处是否构成单词
    private int count;//记录该节点一共出现了多少次
    private int countAsWord;//以单词结尾出现了多少次
    private TireNode parent;//记录该节点的父节点
    private TireNode[] children = new TireNode[MAX_CHAR];//儿子分支

    private int size;//保存当前有多少个子节点

    public TireNode(char c, boolean isStr, int count, boolean isLeaf, TireNode parent, TireNode[] children) {
        this.c = c;
        this.isStr = isStr;
        this.count = count;
        this.parent = parent;
    }

    public TireNode(char c, int count, TireNode parent) {
        this.c = c;
        this.count = count;
        this.parent = parent;
    }

    public TireNode(char c, TireNode parent) {
        this(c, 1, parent);
    }

    public void addChild(int idx, char ch) {
        if (hasChild(idx)) {
            getChild(idx).count++;
        } else {
            children[idx] = new TireNode(ch, this);
            size++;
        }
    }

    public void addChild(char c, char ch) {
        addChild(c - 'a', ch);
    }

    public TireNode getChild(int idx) {
        return children[idx];
    }

    public TireNode getChild(char c) {
        return getChild(c - 'a');
    }

    public void deleteChild(int idx) {
        children[idx] = null;
        size--;
    }

    public void deleteChild(char c) {
        deleteChild(c - 'a');
    }

    public boolean hasChild(char c) {
        return hasChild(c - 'a');
    }

    public boolean hasChild(int idx) {
        return getChild(idx) != null;
    }

    public char getC() {
        return c;
    }

    public boolean isStr() {
        return isStr;
    }

    public int getCount() {
        return count;
    }

    public boolean isLeaf() {
        return getSize() == 0;
    }

    public TireNode getParent() {
        return parent;
    }

    public int getSize() {
        return size;
    }

    public void setIsStr(boolean isStr) {
        this.isStr = isStr;
    }

    public void increaseCnt() {
        this.count++;
    }

    public void decreaseCnt() {
        this.count--;
    }

    public void setParent(TireNode parent) {
        this.parent = parent;
    }

    public void setCountAsWord(int countAsWord) {
        this.countAsWord = countAsWord;
    }

    public int getCountAsWord() {
        return countAsWord;
    }

    @Override
    public String toString() {
        return "TireNode{" +
                "c=" + c +
                ", isStr=" + isStr +
                ", count=" + count +
                ", countAsWord=" + countAsWord +
                ", size=" + size +
                '}';
    }

}

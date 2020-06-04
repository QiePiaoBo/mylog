package com.mylog.common.format.Flyweight.Demo2;

public class Flyweight {
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeFactory().getTreeNode(3,4,"aa","aaaaa");
        TreeNode treeNode2 = new TreeFactory().getTreeNode(3,4,"aa","aaaaa");
        TreeNode treeNode3 = new TreeFactory().getTreeNode(3,4,"aa","aaaaa");
        TreeNode treeNode4 = new TreeFactory().getTreeNode(3,4,"aa","aaaaa");
        TreeNode treeNode5 = new TreeFactory().getTreeNode(3,4,"aa","aaaaa");
        TreeNode treeNode6 = new TreeFactory().getTreeNode(3,4,"aa","aaaaa");
        System.out.println(treeNode1 == treeNode2);
        System.out.println(treeNode1 == treeNode3);
        System.out.println(treeNode1 == treeNode4);
        System.out.println(treeNode1 == treeNode5);
        System.out.println(treeNode1 == treeNode6);
    }
}

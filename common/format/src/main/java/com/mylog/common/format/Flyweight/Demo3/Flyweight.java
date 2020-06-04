package com.mylog.common.format.Flyweight.Demo3;

public class Flyweight {
    public static void main(String[] args) {
        TreeFactory treeFactory = new TreeFactory();
        Tree tree1 = treeFactory.getTree("aaa", "aaaaa");
        Tree tree2 = treeFactory.getTree("aaa", "aaaaa");
        Tree tree3 = treeFactory.getTree("aaa", "aaaaa");
        Tree tree4 = treeFactory.getTree("bbb", "aaaaa");
        Tree tree5 = treeFactory.getTree("bbb", "aaaaa");
        Tree tree6 = treeFactory.getTree("bbb", "aaaaa");
//        TreeNode treeNode1 = new TreeNode(3, 4, treeFactory.getTree("aaa", "aaaaa"));
//        TreeNode treeNode2 = new TreeNode(3, 4, treeFactory.getTree("aaa", "aaaaa"));
//        TreeNode treeNode3 = new TreeNode(3, 4, treeFactory.getTree("aaa", "aaaaa"));
//        TreeNode treeNode4 = new TreeNode(3, 4, treeFactory.getTree("bbb", "aaaaa"));
//        TreeNode treeNode5 = new TreeNode(3, 4, treeFactory.getTree("bbb", "aaaaa"));
//        TreeNode treeNode6 = new TreeNode(3, 4, treeFactory.getTree("bbb", "aaaaa"));
        System.out.println(tree1 == tree2);
        System.out.println(tree1 == tree3);
    }
}

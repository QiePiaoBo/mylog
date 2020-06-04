package com.mylog.common.format.Flyweight.Demo2;

public class TreeFactory {

    public TreeNode getTreeNode(int x, int y, String name, String data){
        Tree tree = new Tree(name, data);
        return new TreeNode(x, y,tree);
    }
}

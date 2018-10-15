package com.buzz.test.core.tree;

public class BinarySearchTree<T extends Comparable<T>> {

    public TreeNode root;

    public boolean insert(T i) {

        if (root == null) {
            root = new TreeNode(i);
            return true;
        }


        TreeNode current = root;

        while (true) {
            if (i.compareTo((T) current.getData()) < 0) {
                if (current.getLeft() != null) {
                    current = current.getLeft();
                } else {
                    current.setLeft(new TreeNode(i));
                    break;
                }
            } else {

                if (current.getRight() != null) {
                    current = current.getRight();
                } else {
                    current.setRight(new TreeNode(i));
                    break;
                }

            }
        }
        return true;
    }

    public void traversePreOrder(TreeNode node, int level) {
        if (node == null || level < 0) {
            return;
        }

        traversePreOrder(node.getRight(), level + 1);
        System.out.println("'" + buildBlankSpace("  ", level) + node.getData() + "'");
        traversePreOrder(node.getLeft(), level + 1);


    }


    public String buildBlankSpace(String blank, int num) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < num; i++) {
            builder.append(blank);
        }
        return builder.toString();
    }

}

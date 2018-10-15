package com.buzz.test.core.tree;

import java.util.Arrays;
import java.util.List;

public class BinarySearchTreeTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 7, 90, -67, -11);
        //numberList = Arrays.asList(3, 2, 1, 5, 4, 6);
        numberList.forEach(binarySearchTree::insert);

        TreeNode root = binarySearchTree.root;
        binarySearchTree.traversePreOrder(root, 0);
    }
}

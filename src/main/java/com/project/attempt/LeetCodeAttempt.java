package com.project.attempt;

import java.util.LinkedList;

public class LeetCodeAttempt {

    public static void main(String[] args) {

        int[] preorder1 = new int[]{1,2,4,5,3,6,7};
        int[] postorder1 = new int[]{4,5,2,6,7,3,1};
        TreeNode node1 = constructBinaryTreeFromPreorderAndPostorderTraversal(preorder1, postorder1);

        System.out.println(node1.val);
        System.out.println(node1.left.val);
        System.out.println(node1.right.val);
        System.out.println(node1.left.left.val);
        System.out.println(node1.left.right.val);
        System.out.println(node1.right.left.val);
        System.out.println(node1.right.right.val);
        System.out.println();

        int[] preorder2 = new int[]{1};
        int[] postorder2 = new int[]{1};
        TreeNode node2 = constructBinaryTreeFromPreorderAndPostorderTraversal(preorder2, postorder2);

        System.out.println(node2.val);
        System.out.println();

        int[] preorder3 = new int[]{1,2};
        int[] postorder3 = new int[]{2,1};
        TreeNode node3 = constructBinaryTreeFromPreorderAndPostorderTraversal(preorder3, postorder3);

        System.out.println(node3.val);
        System.out.println(node3.left.val);
        System.out.println();

        int[] preorder4 = new int[]{1,2,3,4,5,6};
        int[] postorder4 = new int[]{6,5,4,3,2,1};
        TreeNode node4 = constructBinaryTreeFromPreorderAndPostorderTraversal(preorder4, postorder4);

        System.out.println(node4.val);
        System.out.println(node4.left.val);
        System.out.println(node4.right.val);
        System.out.println(node4.left.left.val);
        System.out.println();

    }

    public static TreeNode constructBinaryTreeFromPreorderAndPostorderTraversal(int[] preorder, int[] postorder) {

        LinkedList<TreeNode> treeNodes = new LinkedList<>();

        LinkedList<Integer> preOrderValues = new LinkedList<>();
        LinkedList<Integer> postOrderValues = new LinkedList<>();

        for (int i = 0; i < preorder.length; i++) {

            treeNodes.add(new TreeNode(preorder[i]));
            preOrderValues.add(preorder[i]);
            postOrderValues.add(postorder[i]);

        }

        while (preOrderValues.size() != 1) {

            System.out.println("Size: " + preOrderValues.size());
            System.out.println("Preorder: " + preOrderValues);
            System.out.println("Postorder: " + postOrderValues);

            for (int i = 0; i < preOrderValues.size(); i++) {

                int postOrderIndex = indexOfValue(postOrderValues, preOrderValues.get(i));

                if (postOrderIndex - 1 < 0) {
                    continue;
                }

                int nextPreorder1 = preOrderValues.get(i+1);
                int nextPreorder2 = -1;
                if (i+2 < preOrderValues.size()) {
                    nextPreorder2 = preOrderValues.get(i+2);
                }

                int nextPostorder1 = postOrderValues.get(postOrderIndex-1);
                int nextPostorder2 = -2;
                if (postOrderIndex-2 >= 0) {
                    nextPostorder2 = postOrderValues.get(postOrderIndex-2);
                }

                if ((nextPreorder1 == nextPostorder1)) {

                    System.out.println("Looking at Node (1): " + preOrderValues.get(i));
                    System.out.println();

                    if (nodeOfValue(treeNodes, preOrderValues.get(i)).left == null) {
                        nodeOfValue(treeNodes, preOrderValues.get(i)).left = nodeOfValue(treeNodes, nextPreorder1);
                    } else {
                        nodeOfValue(treeNodes, preOrderValues.get(i)).right = nodeOfValue(treeNodes, nextPreorder1);
                    }

                    preOrderValues.remove(i+1);
                    postOrderValues.remove(postOrderIndex-1);

                    break;

                } else if (i + 2 < preOrderValues.size()
                        && (nextPreorder1 == nextPostorder2 && nextPreorder2 == nextPostorder1)) {

                    System.out.println("Looking at Node (2): " + preOrderValues.get(i));
                    System.out.println();

                    nodeOfValue(treeNodes, preOrderValues.get(i)).left = nodeOfValue(treeNodes, nextPreorder1);
                    nodeOfValue(treeNodes, preOrderValues.get(i)).right = nodeOfValue(treeNodes, nextPreorder2);

                    preOrderValues.remove(i+2);
                    preOrderValues.remove(i+1);
                    postOrderValues.remove(postOrderIndex-1);
                    postOrderValues.remove(postOrderIndex-2);

                    break;

                }

            }

        }

        return treeNodes.get(0);

    }

    private static int indexOfValue(LinkedList<Integer> list, int value) {

        for (int i = 0; i < list.size(); i++) {

            if (list.get(i) == value) { return i; }

        }

        System.out.println("ERROR 1");
        return -1;

    }

    private static TreeNode nodeOfValue(LinkedList<TreeNode> list, int value) {

        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).val == value) { return list.get(i); }

        }

        System.out.println("ERROR 2");
        return null;

    }

}

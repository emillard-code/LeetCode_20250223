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
        System.out.println(node4.left.left.val);
        System.out.println(node4.left.left.left.val);
        System.out.println(node4.left.left.left.left.val);
        System.out.println(node4.left.left.left.left.left.val);
        System.out.println();

    }

    // This method constructs a valid binary tree when given both its preorder and postorder traversal.
    public static TreeNode constructBinaryTreeFromPreorderAndPostorderTraversal(int[] preorder, int[] postorder) {

        // We will have a LinkedList of all the nodes that will exist in the binary tree.
        // The order of the nodes in this LinkedList will be completely irrelevant besides
        // the very first element being the root node.
        LinkedList<TreeNode> treeNodes = new LinkedList<>();

        // We will also have LinkedList(s) containing all the values that exist in the preorder
        // and postorder traversals. The elements will be the same in both lists, but the main
        // difference between them will be the order of the elements.
        LinkedList<Integer> preOrderValues = new LinkedList<>();
        LinkedList<Integer> postOrderValues = new LinkedList<>();

        // We then instantiate the three LinkedList(s) with their appropriate values.
        // We will create a new node for every value in the traversal lists and add it
        // to LinkedList treeNodes, although we will link the nodes (i.e. linking
        // their left and right values to other nodes) later in this method.
        for (int i = 0; i < preorder.length; i++) {

            treeNodes.add(new TreeNode(preorder[i]));
            preOrderValues.add(preorder[i]);
            postOrderValues.add(postorder[i]);

        }

        // We then perform a loop that will look at both the preorder and postorder traversals.
        // We establish the connections (linking the left and right values of the nodes in LinkedList
        // treeNodes to other nodes in the same list), and construct a proper binary tree from the
        // currently disconnected nodes in LinkedList treeNodes using these traversals.
        // We will repeat this loop until there's only a single element left in the traversal lists,
        // at which point all the connections should have been made and the binary tree is completed.
        while (preOrderValues.size() != 1) {

            // We want to start the loop from the end of the preorder list as we want to establish
            // the connections at the levels furthest from the root node first. This is mostly to
            // avoid issues that might arise from the fact this loop gradually deletes elements
            // from the preorder and postorder traversal lists to perform its logic, so we want to
            // make sure that we establish all their connections first before they are deleted. This
            // mostly becomes an issue for binary trees that have a chain of multiple nodes that each
            // only have a single child.
            for (int i = preOrderValues.size() - 1; i >= 0; i--) {

                // We call a helper method to get the index of the current value in the postorder list.
                // We need the index of the current value in both the preorder and postorder lists
                // to perform our later logic.
                int postOrderIndex = indexOfValue(postOrderValues, preOrderValues.get(i));

                // In each iteration of the loop, we will at the very least be looking at the index
                // after the current preorder index and the index before the current postorder index.
                // Hence, if looking at either index would lead us to be out of bounds, we skip the
                // current iteration of the loop and move on to the next int i value.
                if (i + 1 >= preOrderValues.size() || postOrderIndex - 1 < 0) {
                    continue;
                }

                // We will store the value of the element that comes after the current index
                // in the preorder traversal list.
                int nextPreorder1 = preOrderValues.get(i+1);

                // If it doesn't go out of bounds, we will also store the value of the element that
                // comes two indexes later from the current element in the preorder traversal list.
                // We set a default value of -1 if it is indeed out of bounds.
                int nextPreorder2 = -1;
                if (i+2 < preOrderValues.size()) {
                    nextPreorder2 = preOrderValues.get(i+2);
                }

                // We will store the value of the element that comes before the current index
                // in the postorder traversal list.
                int nextPostorder1 = postOrderValues.get(postOrderIndex-1);

                // If it doesn't go out of bounds, we will also store the value of the element that
                // comes two indexes before from the current element in the postorder traversal list.
                // We set a default value of -2 if it is indeed out of bounds.
                int nextPostorder2 = -2;
                if (postOrderIndex-2 >= 0) {
                    nextPostorder2 = postOrderValues.get(postOrderIndex-2);
                }

                // We will then compare the next element in the preorder list with the previous
                // element in the postorder list. If they are equal, then it means that the node
                // of that element is a child of the current element that we're looking at.
                if ((nextPreorder1 == nextPostorder1)) {

                    // If the left value of the current node is empty, we then set the node
                    // of the next element to be the left node of the current element. Otherwise,
                    // if the right value of the current node is empty, we set it to that instead.
                    // If neither the left or right are null, then something is wrong, and we exit
                    // the program.
                    if (nodeOfValue(treeNodes, preOrderValues.get(i)).left == null) {
                        nodeOfValue(treeNodes, preOrderValues.get(i)).left = nodeOfValue(treeNodes, nextPreorder1);
                    } else if (nodeOfValue(treeNodes, preOrderValues.get(i)).right == null) {
                        nodeOfValue(treeNodes, preOrderValues.get(i)).right = nodeOfValue(treeNodes, nextPreorder1);
                    } else {
                        System.out.println("ERROR!");
                        System.exit(0);
                    }

                    // Once we establish the connection, we then remove the next element in the
                    // preorder list and the previous element in the postorder list as it is now
                    // properly linked to the binary tree.
                    preOrderValues.remove(i+1);
                    postOrderValues.remove(postOrderIndex-1);

                    // We then break from the loop, which will be repeated as long as the
                    // number of elements in the preorder and postorder lists are not 1 yet.
                    break;

                // We will then compare the next element in the preorder list with the element in
                // the postorder list two indexes ago. We will also compare the next element in the
                // postorder list with the element in the preorder list two indexes later. If both
                // of these comparisons turn out to be equal, then it means that the nodes of both
                // of these elements are children of the current element that we are looking at.
                // If either of the indexes that are two spaces away end up being out of bounds,
                // the default values from earlier for int nextPreorder2 and int nextPostorder2
                // will ensure that this if-condition will be skipped over.
                } else if (nextPreorder1 == nextPostorder2 && nextPreorder2 == nextPostorder1) {

                    // If either the left or right values are already pointing to somewhere else, then
                    // something is wrong, and we exit the program in that case.
                    if (nodeOfValue(treeNodes, preOrderValues.get(i)).left != null
                    || nodeOfValue(treeNodes, preOrderValues.get(i)).right != null) {
                        System.out.println("ERROR!");
                        System.exit(0);
                    }

                    // Otherwise, we set the left and right nodes of the current element to be the nodes
                    // of the two values that we performed the comparison checks on.
                    nodeOfValue(treeNodes, preOrderValues.get(i)).left = nodeOfValue(treeNodes, nextPreorder1);
                    nodeOfValue(treeNodes, preOrderValues.get(i)).right = nodeOfValue(treeNodes, nextPreorder2);

                    // We then remove both elements from both lists as they are now properly
                    // linked to the binary tree and we have established their connections.
                    preOrderValues.remove(i+2);
                    preOrderValues.remove(i+1);
                    postOrderValues.remove(postOrderIndex-1);
                    postOrderValues.remove(postOrderIndex-2);

                    // We then break from the loop, which will be repeated as long as the
                    // number of elements in the preorder and postorder lists are not 1 yet.
                    break;

                }

            }

        }

        // Once all the logic has been performed, we return the root of the binary tree.
        return treeNodes.get(0);

    }

    // A helper method that returns the index within a LinkedList of an int value we are looking for.
    private static int indexOfValue(LinkedList<Integer> list, int value) {

        for (int i = 0; i < list.size(); i++) {

            if (list.get(i) == value) { return i; }

        }

        // As long as valid values are inputted, there should be no way that we reach this point.
        // Hence, if we do exit the program.
        System.out.println("ERROR!");
        System.exit(0);
        return -1;

    }

    private static TreeNode nodeOfValue(LinkedList<TreeNode> list, int value) {

        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).val == value) { return list.get(i); }

        }

        // As long as valid values are inputted, there should be no way that we reach this point.
        // Hence, if we do exit the program.
        System.out.println("ERROR!");
        System.exit(0);
        return new TreeNode(-1);

    }

}

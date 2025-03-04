package com.project.solution;

public class LeetCodeSolution {

    public static void main(String[] args) {

        int[] preorder1 = new int[]{1,2,4,5,3,6,7};
        int[] postorder1 = new int[]{4,5,2,6,7,3,1};
        TreeNode node1 = constructFromPrePost(preorder1, postorder1);

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
        TreeNode node2 = constructFromPrePost(preorder2, postorder2);

        System.out.println(node2.val);
        System.out.println();

        int[] preorder3 = new int[]{1,2};
        int[] postorder3 = new int[]{2,1};
        TreeNode node3 = constructFromPrePost(preorder3, postorder3);

        System.out.println(node3.val);
        System.out.println(node3.left.val);
        System.out.println();

        int[] preorder4 = new int[]{1,2,3,4,5,6};
        int[] postorder4 = new int[]{6,5,4,3,2,1};
        TreeNode node4 = constructFromPrePost(preorder4, postorder4);

        System.out.println(node4.val);
        System.out.println(node4.left.val);
        System.out.println(node4.left.left.val);
        System.out.println(node4.left.left.left.val);
        System.out.println(node4.left.left.left.left.val);
        System.out.println(node4.left.left.left.left.left.val);
        System.out.println();

        int[] preorder5 = new int[]{1,2,4,8,9,5,11,3,6,13,7,14,15};
        int[] postorder5 = new int[]{8,9,4,11,5,2,13,6,14,15,7,3,1};
        TreeNode node5 = constructFromPrePost(preorder5, postorder5);

        System.out.println(node5.val);
        System.out.println(node5.left.val);
        System.out.println(node5.right.val);
        System.out.println(node5.left.left.val);
        System.out.println(node5.left.right.val);
        System.out.println(node5.right.left.val);
        System.out.println(node5.right.right.val);
        System.out.println(node5.left.left.left.val);
        System.out.println(node5.left.left.right.val);
        System.out.println(node5.left.right.left.val);
        System.out.println(node5.right.left.left.val);
        System.out.println(node5.right.right.left.val);
        System.out.println(node5.right.right.right.val);
        System.out.println();

    }

    public static TreeNode constructFromPrePost(int[] preorder, int[] postorder) {

        int numOfNodes = preorder.length;
        return constructTree(0, numOfNodes - 1, 0, preorder, postorder);

    }

    // Helper function to construct the tree recursively
    private static TreeNode constructTree(int preStart, int preEnd, int postStart, int[] preorder, int[] postorder) {

        // Base case: If there are no nodes to process, return null
        if (preStart > preEnd) return null;

        // Base case: If only one node is left, return that node
        if (preStart == preEnd) {
            return new TreeNode(preorder[preStart]);
        }

        // The left child root in preorder traversal (next element after root)
        int leftRoot = preorder[preStart + 1];

        // Calculate the number of nodes in the left subtree by searching in postorder
        int numOfNodesInLeft = 1;

        while (postorder[postStart + numOfNodesInLeft - 1] != leftRoot) {
            numOfNodesInLeft++;
        }

        TreeNode root = new TreeNode(preorder[preStart]);

        // Recursively construct the left subtree
        root.left = constructTree(preStart + 1, preStart + numOfNodesInLeft, postStart,
                preorder, postorder);

        // Recursively construct the right subtree
        root.right = constructTree(preStart + numOfNodesInLeft + 1, preEnd,
                postStart + numOfNodesInLeft, preorder, postorder);

        return root;

    }

}

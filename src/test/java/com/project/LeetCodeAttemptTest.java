package com.project;

import com.project.attempt.TreeNode;
import com.project.attempt.LeetCodeAttempt;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class LeetCodeAttemptTest {

    @Test
    public void constructBinaryTreeFromPreorderAndPostorderTraversalTest() {

        int[] preorder1 = new int[]{1,2,4,5,3,6,7};
        int[] postorder1 = new int[]{4,5,2,6,7,3,1};
        TreeNode node1 = LeetCodeAttempt.constructBinaryTreeFromPreorderAndPostorderTraversal(preorder1, postorder1);

        assertEquals(1, node1.val);
        assertEquals(2, node1.left.val);
        assertEquals(3, node1.right.val);
        assertEquals(4, node1.left.left.val);
        assertEquals(5, node1.left.right.val);
        assertEquals(6, node1.right.left.val);
        assertEquals(7, node1.right.right.val);

    }

}

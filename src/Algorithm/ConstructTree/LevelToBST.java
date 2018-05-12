package Algorithm.ConstructTree;


import PracticeInterview.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * }
 */


    // not leetcode
    // laicode:
//    Given the levelorder traversal sequence of a binary search tree, reconstruct the original tree.
//
//            Assumptions
//
//            The given sequence is not null
//            There are no duplicate keys in the binary search tree
//            Examples
//
//            levelorder traversal = {5, 3, 8, 1, 4, 11}
//
//            the corresponding binary search tree is
//
//            5
//
//            /    \
//
//            3        8
//
//            /   \        \
//
//            1      4        11
// 思路： 首先用queue， 先进先出。 每个node扩长到left 和right node
//    那么哪些是合格的node可以被push到queue里面呢？
// 根据bst的性质：1. 如果是left node， 必须比root 小，而且必须比root之前所有的祖先节点大。
    // 同理 right node
    // 那么就需要对每个node record一下他们的children node的最大和最小值。
public class LevelToBST {

    static class Node {
        TreeNode node;
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
        public Node(TreeNode node) {
            this.node = node;
        }
    }
    private static void print(TreeNode root) {
        if(root == null) {
            return;
        }
        print(root.left);
        System.out.println(root.val);
        print(root.right);

    }
    public static void main(String [] args) {
        TreeNode root = reconstruct(new int[]{5, 3, 8, 1, 4, 11});
        print(root);
    }
    public static TreeNode reconstruct(int[] level) {
        // Write your solution here.
        if(level == null || level.length == 0) {
            return null;
        }
        Queue<Node> q = new LinkedList<>();
        TreeNode root = new TreeNode(level[0]);
        q.offer(new Node(root));
        int p = 1;
        while(p != level.length){
            Node node = q.poll();
            //left
            if(p < level.length && level[p] < node.node.val && level[p] > node.min) {
                TreeNode treeNode = new TreeNode(level[p++]);
                Node left = new Node(treeNode);
                node.node.left = treeNode;
                q.offer(left);
                left.max = node.node.val;
                left.min = node.min;
            }
            if(p < level.length && level[p] > node.node.val && level[p] < node.max) {
                TreeNode treeNode = new TreeNode(level[p++]);
                Node right = new Node(treeNode);
                node.node.right = treeNode;
                q.offer(right);
                right.max = node.max;
                right.min = node.node.val;
            }
        }
        return root;
    }


}

package Test;

import Heap.test;

/**
 * Created by program on 10/15/2017.
 */
public class TreeTraverse {
    public static void main(String [] args) {
        TreeTraverse sol = new TreeTraverse();
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        sol.preorder(root);
        System.out.println();
        sol.preorder(root, -1);
        System.out.println();
        sol.inorder(root, -1);
    }
    private int pre = -1;
    public void preorder(TreeNode root) {
        if(root == null) {
            return;
        }
        System.out.print("pre:" + pre);
        System.out.println("cur:" + root.val);
        pre = root.val;
        preorder(root.left);
        preorder(root.right);
    }
    public void preorder(TreeNode root, int pre) {
        if(root == null) {
            return;
        }
        System.out.print("pre:" + pre);
        System.out.println("cur:" + root.val);
        pre = root.val;
        preorder(root.left, pre);
        preorder(root.right, pre);
    }
    public void inorder(TreeNode root, int pre) {
        if(root == null) {
            return;
        }

        preorder(root.left, pre);
        System.out.print("pre:" + pre);
        System.out.println("cur:" + root.val);
        pre = root.val;
        preorder(root.right, pre);
    }

}

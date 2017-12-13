package Algorithm.Interview.PocketGem;
import java.util.*;

/**
 * 1. max number: any node to any node, print path
 * 2. max number: leaf to leaf, print path
 *
 */
public class path_sum_with_path {
    public List<TreeNode> findpath(TreeNode root) {
        Node res = helper(root);
        if (res == null) return new ArrayList<>();
        List<TreeNode> cur = res.list;
        Collections.reverse(cur);
        return cur;
    }
    //To Leaf
    public Node helper(TreeNode root) {
        if (root == null) return null;
        Node left = helper(root.left);
        Node right = helper(root.right);
        if (left == null && right == null) {
            Node cur = new Node(root.val);
            cur.list.add(root);
            return cur;
        }
        //1
        // \
        //  2
        //
        if (left == null || (right != null && right.sum > left.sum)) {
            right.sum = right.sum + root.val;
            right.list.add(root);
            return right;
        } else {
            left.sum = left.sum + root.val;
            left.list.add(root);
            return left;
        }
    }
    //To any node in the tree 
    public Node backtrack(TreeNode root) {
        if (root == null) return null;
        Node left = backtrack(root.left);
        Node right = backtrack(root.right);
        //both are null, create a new node
        if (left == null && right == null) {
            Node cur = new Node(root.val);
            cur.list.add(root);
            return cur;
        }
        if(left == null || (right != null && right.sum > left.sum)) {
            if (right.sum < 0) {//right.sum > left.sum
                right = new Node(root.val);
            } else {
                right.sum += root.val;
            }
            right.list.add(root);
            return right;
        } else {// left != null
            if (left.sum < 0) {
                left = new Node(root.val);
            } else {
                left.sum += root.val;
            }
            left.list.add(root);
            return left;
        }
    }
    class Node {
        int sum;
        List<TreeNode> list;
        public Node(int sum) {
            this.sum = sum;
            list = new ArrayList<>();
        }
    }
    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
        public TreeNode (int val) {
            this.val = val;
            left = null;
            right = null;
        }
    }
}


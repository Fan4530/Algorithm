package Algorithm.Interview.PocketGem;

public class LCA_BT_BST {
    //  time : O(logn)
    //                    4
    //                   / \
    //                  1   5
    //                 / \
    //                0   3
    public TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return root;
        // both q and p are in the right part
        if (root.val < p.val && root.val < q.val)
            return lowestCommonAncestorBST(root.right, p, q);
        // both q and p are in the left part
        if (root.val > p.val && root.val > q.val)
            return lowestCommonAncestorBST(root.left, p, q);
        // find at least one p or q, or p and q are in the two parts of the root
        return root;
    }

    public TreeNode lowestCommonAncestorBT(TreeNode root, TreeNode p, TreeNode q) {
        //base case
        if (root == null || root == p || root == q)
            return root;
        TreeNode left = lowestCommonAncestorBT(root.left, p, q);
        TreeNode right = lowestCommonAncestorBT(root.right, p, q);
        if (left != null && right != null)
            return root;
        return left != null ? left : right;
    }
}

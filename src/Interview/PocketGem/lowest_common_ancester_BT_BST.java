package Interview.PocketGem;

public class lowest_common_ancester_BT_BST {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return root;
        if (root.val < p.val && root.val < q.val)
            return lowestCommonAncestor(root.right, p, q);
        if (root.val > p.val && root.val > q.val)
            return lowestCommonAncestor(root.left, p, q);
        return root;
    }
    public TreeNode lowestCommonAncestor_BT(TreeNode root, TreeNode p, TreeNode q) {
        //base case
        if (root == null || root == p || root == q)
            return root;
        TreeNode left = lowestCommonAncestor_BT(root.left, p, q);
        TreeNode right = lowestCommonAncestor_BT(root.right, p, q);
        if (left != null && right != null)
            return root;
        return left != null ? left : right;
    }
}

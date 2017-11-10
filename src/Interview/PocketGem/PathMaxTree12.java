package Interview.PocketGem;

public class PathMaxTree12 {

	// any node to node max sum
    static int max;
    public static int helper(TreeNode root) {
        if(root == null)
            return 0;
        //get the max of left and right maximum values plus previous node
        int left = helper(root.left);
        int right = helper(root.right);
        // if the contribution is negative, then use 0
        left = Math.max(left, 0);
        right = Math.max(right, 0);
        //update max value
        max = Math.max(max, root.val + left + right);
        // return the larger path plus root value
        return Math.max(left,right) + root.val;
    }
    
    public static int maxPathSum(TreeNode root) {
        max = Integer.MIN_VALUE;
        helper(root);
        return max;
    }
    
    
    // leaf to leaf max sum
    static int max_2 = Integer.MIN_VALUE;
    public static int maxPathL2L(TreeNode root) {
    	if(root == null) {
    		return 0;
    	}
    	int left = maxPathL2L(root.left);
    	int right = maxPathL2L(root.right);
    	// only when it is leaf node, update. Any nodes have at least two leaf node if they have two children leaves
        // e.g.   1         node 1, node 3, node 4, so this
        //      /  \
        //     2   4
        //    /
        //   3
    	if(root.left != null && root.right != null) {
    		max_2 = Math.max(max_2, root.val + left + right);
    		return Math.max(left, right) + root.val;
    	}
    	// only return
    	return root.left == null ? right + root.val : left + root.val;
    }
    
}

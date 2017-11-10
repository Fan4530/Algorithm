package Interview.PocketGem;

public class in_order_successor {
	// recursive inorder
    public TreeNode inorderSuccessor_r(TreeNode root, TreeNode p) {
        if(root == null || p == null)
            return null;
        if(root.val <= p.val) {
            return inorderSuccessor_r(root.right, p);
        } else {
            TreeNode res = inorderSuccessor_r(root.left, p);
            return res == null ? root : res;
        }
    }
    // iterative inorder
    public TreeNode inorderSuccessor_i(TreeNode root, TreeNode p) {
        TreeNode res = null;
        while(root != null) {
            if(root.val <= p.val)
                root = root.right;
            else {
                res = root;
                root = root.left;
            }
        }
        return res;
    }
    // inorder without value without parents
    TreeNode pre = null;
    TreeNode res = null;
    public void helper(TreeNode root, TreeNode p) {
        if (root == null)
            return;
        helper(root.left, p);
        if (pre != null) {
            if (pre == p) {
                res = root;
            }
        }
        pre = root;
        helper(root.right, p);
    }
    
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null)
            return null;
        helper(root, p);
        return res;
    }
    
    //Time complexity: O(h) - h - height of the tree
    public TreeNode InorderSuccessor(TreeNode p) {
        if (p.right != null)
        {
            return leftMostNode(p.right);
        }
        //If p dosn't have right child then its inorder successor will be one of p's ancestors, using parent link keep traveling up untill you get the node which is the left child of its parent. Then this parent node will be the inorder successor
        TreeNode parent = p.parent;
        while (parent != null && p == parent.right)//We are not root && we are the "right" child
        {
            p = parent;                  
            parent = parent.parent;  //Return the ancestor, go up
        }
        return parent;   	
    }
         
    //If p has a right child then its inorder successor will the leftmost node of p's right-subtree.
    public TreeNode leftMostNode(TreeNode node) {
        while (node.left != null)
            node = node.left;
        return node;
    }
          


    // predecessor recursive
    public TreeNode predecessor_r(TreeNode root, TreeNode p) {
    	  if (root == null)
    	    return null;

    	  if (root.val >= p.val) {
    	    return predecessor_r(root.left, p);
    	  } else {
    	    TreeNode right = predecessor_r(root.right, p);
    	    return (right != null) ? right : root;
    	  }
    }
    // predecessor iterative
    public TreeNode inorderPre_i(TreeNode root, TreeNode p) {
        TreeNode res = null;
        while (root != null) {
            if (root.val < p.val) {
                res = root;
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return res;
    }  
    public class TreeNode {
    	public int val;
    	TreeNode left;
    	TreeNode right;
    	TreeNode parent;
    	TreeNode(int val, TreeNode parent) {
    		this.val = val;
    		this.parent = parent;
    	}
    }
    
    public static void main(String[] args) {
    	in_order_successor i = new in_order_successor();
    	TreeNode root = i.new TreeNode(5,null);
    	root.left = i.new TreeNode(3,root);
    	root.right = i.new TreeNode(7, root);
    	root.left.left = i.new TreeNode(2, root.left);
    	root.left.right = i.new TreeNode(4, root.left);
    	root.right.left = i.new TreeNode(6, root.right);
    	root.right.right = i.new TreeNode(8, root.right);
    	TreeNode res = i.InorderSuccessor(root.left.right);
    	System.out.println(res.val);
    }
}

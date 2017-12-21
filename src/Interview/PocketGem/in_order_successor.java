package Interview.PocketGem;

public class in_order_successor {
	// recursive inorder
    public TreeNode inorderSuccessor1(TreeNode root, TreeNode p) {
        if(root == null || p == null)
            return null;
        // corner case: if p is the largest one, then the recursion will directly go to right,
        // and let left = null, both left and root are null

        //if root is larger, than go left(smaller)
        // record the value, becasue it may be valid
        // until it is null: not valid
        if(root.val > p.val) {
            TreeNode left = inorderSuccessor1(root.left, p);
            return  left != null ? left : root;
        }
        return inorderSuccessor1(root.right, p);
    }

    // iterative inorder
    public TreeNode inorderSuccessorIterative(TreeNode root, TreeNode p) {
        TreeNode res = null;
        while(root != null) {
            if(root.val > p.val) {
                //update the res, when root first larger than root
                res = (res == null || root.val < res.val) ? root : res;
                root = root.left;
            } else {
                root = root.right;
            }
        }
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



    // recursive inorder
    public TreeNode predecessor(TreeNode root, TreeNode p) {
        if(root == null || p == null)
            return null;
        // corner case: if p is the largest one, then the recursion will directly go to right,
        // and let left = null, both left and root are null

        //if root is larger, than go left(smaller)
        // record the value, becasue it may be valid
        // until it is null: not valid
        if(root.val < p.val) {
            TreeNode res = inorderSuccessor1(root.right, p);
            return  res != null ? res : root;
        }
        return inorderSuccessor1(root.left, p);
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

    public String reverseWords(String s) {
        // the sky is blue
        // if is '' or len, then reverse left, i - 1,
        char [] array = s.toCharArray();
        reverse(array, 0, array.length - 1);

        int left = 0;
        for(int i = 0; i <= array.length; i ++) {
            if(i == array.length || array[i] == ' ') {
                reverse(array, left, i - 1);
                left = i + 1;
            }
        }
        return new String(array);
    }
    private void reverse(char [] array, int left, int right) {
        while(left <= right) {
            char tmp = array[left];
            array[left++] = array[right];
            array[right--] = tmp;
        }
    }

}

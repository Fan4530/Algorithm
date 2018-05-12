package Algorithm.ConstructTree;

import PracticeInterview.TreeNode;

//laicode: Reconstruct Binary Search Tree With Preorder Traversal
// 也是找分断点。[[3,2,1,8,5,4,7,12,10,13]] 分断点在比root都小的都是左边，比他大的都在右边
public class PreToBST {
    public TreeNode reconstruct(int[] pre) {
        // Write your solution here.
        if(pre == null || pre.length == 0) {
            return null;
        }
        return helper(pre, 0, pre.length - 1);
    }
    private TreeNode helper(int [] pre, int left, int right) {
        if(left > right) {
            return null;
        }
        TreeNode root = new TreeNode(pre[left]);
        //int l1 = left + 1;
        int firstRight = right + 1;
        for(int i = left + 1; i <= right; i ++) {
            if(pre[i] > pre[left]) {
                firstRight = i;
                break;
            }
        }
        root.left = helper(pre, left + 1, firstRight - 1);
        root.right = helper(pre, firstRight, right);
        return root;
    }
}

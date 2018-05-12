package Algorithm.ConstructTree;

import PracticeInterview.TreeNode;

import java.util.HashMap;
import java.util.Map;

//105. Construct Binary Tree from Preorder and Inorder Traversal
class PreInorder {

    // preorder = [3,9,20,15,7]
    //  prel + 1, prel + leftChild, prel + leftChild + 1, prer
    // inorder = [9,3,15,20,7]
    //  inl, inl + leftChild - 1, inl + leftChild + 1, inr
    // x = map.get() = 1
    // leftChild = x - inl = 1;

    //[9]

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < inorder.length; i ++) {
            map.put(inorder[i], i);
        }
        return helper(map, preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);
    }
    public TreeNode helper(Map<Integer, Integer> map, int [] preorder, int prel, int prer,
                           int [] inorder, int inl, int inr) {
        if(inl > inr) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[prel]);
        int x = map.get(preorder[prel]);
        int leftChild = x - inl;
        root.left = helper(map, preorder, prel + 1, prel + leftChild, inorder, inl, inl + leftChild - 1);
        root.right = helper(map, preorder, prel + leftChild + 1, prer, inorder, inl + leftChild + 1, inr);
        return root;
    }
}
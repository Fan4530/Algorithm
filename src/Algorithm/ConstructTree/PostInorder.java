package Algorithm.ConstructTree;

import PracticeInterview.TreeNode;

import java.util.HashMap;
import java.util.Map;

// leetcode: 106. Construct Binary Tree from Inorder and Postorder Traversal
// 套路： inorder: 选取一个root， root的左边都是做孩子，root的右边都是右孩子
//       postorder: root从最后一个开始选。 前几个是做孩子，紧接着都是有孩子，就到了root
//       preorder: root从第一个开始选，root后面几个是左孩子，在右边都是右孩子
// 套路都是  根据上面的规律  找到一个 左孩子  和右孩子的 分断点。
public class PostInorder {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < inorder.length; i ++) {
            map.put(inorder[i], i);
        }
        return helper(map, inorder, 0, inorder.length - 1,
                postorder, 0, postorder.length - 1);
    }
    private TreeNode helper(Map<Integer, Integer> map, int [] inorder, int inl, int inr,
                            int [] postorder, int pol, int por) {
        if(inl > inr) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[por]);
        if(inl == inr) {
            return root;
        }
        int x = map.get(postorder[por]);
        int inLeft = x - inl;
        root.left = helper(map, inorder, inl, x - 1, postorder, pol, pol + inLeft - 1);
        root.right = helper(map, inorder, x + 1, inr, postorder, pol + inLeft, por - 1);
        return root;
    }

}

package PracticeInterview.Google;

import java.util.HashMap;
import java.util.Map;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}
// not a leetcode
// 给你一个preorder和postorder， 你能construct一个tree么？
// 假设所有的node都有left node
public class PrePost {
//    TreeNode root = sol(new int[]{1,2,4,5,3,6}, new int[]{4,5,2,6,3,1});
    //          1
    //         / \
    //        2   3
    //       / \  /
    //      4  5  6
    //
    //
    //
    public static void main(String [] args) {
        printPre(sol(new int[]{1,2,4,5,3,6}, new int[]{4,5,2,6,3,1}));

        System.out.println();
        printPost(sol(new int[]{1,2,4,5,3,6}, new int[]{4,5,2,6,3,1}));

    }
    private static void printPre(TreeNode root) {
        if(root == null) {
            return;
        }
        System.out.print(root.val + ",");
        printPre(root.left);
        printPre(root.right);
    }
    private static void printPost(TreeNode root) {
        if(root == null) {
            return;
        }
        printPost(root.left);
        printPost(root.right);
        System.out.print(root.val + ",");

    }
    public static TreeNode sol(int [] pre, int [] post) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < post.length; i ++) {
            map.put(post[i], i);
        }
        return helper(pre, 0, pre.length - 1, 0, post.length, map);
    }

    private static TreeNode helper(int [] pre, int prel, int prer,
                                   int postl, int postr, Map<Integer, Integer> map) {
        if(prel > prer) {
            return null;
        }
        TreeNode root = new TreeNode(pre[prel]);
        if(prel == prer) {
            return root;
        }
        int nextRootIdx = map.get(pre[prel + 1]);
        int leftLen = (nextRootIdx - postl + 1);
        root.left = helper(pre, prel + 1, leftLen + prel, postl, nextRootIdx, map);
        root.right = helper(pre, leftLen + prel + 1, prer, nextRootIdx + 1, postr - 1, map);
        return root;
    }



    // by zhangchi
    public static TreeNode solve(int[] preorder, int[] postorder) {
        TreeNode t = help(preorder, 0, preorder.length - 1, postorder, 0, postorder.length - 1);
        return t;
    }
    private static TreeNode help(int[] preorder, int s1, int e1,
                          int[] postorder, int s2, int e2) {
        if (s1 > e1) return null;
        TreeNode res = new TreeNode(preorder[s1]);
        if (s1 == e1) return res;
        int i = s2;
        while (postorder[i] != preorder[s1 + 1])
            i++;
        res.left = help(preorder, s1 + 1, s1 + 1 + i - s2, postorder, s2, i);
        res.right = help(preorder, s1 + 2 + i - s2, e1, postorder, i + 1, e2);
        return res;
    }
}


//{1, 2, 4, 5, 3, 6}
// 0              5
//{4, 5, 2, 6, 3, 1}
//
//                postIdx = 5

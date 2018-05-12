package PracticeInterview.Google.medium;
//class TreeNode {
//    TreeNode left;
//    TreeNode right;
//    int val;
//    public TreeNode(int val) {
//        this.val = val;
//        this.left = null;
//        this.right = null;
//    }
//}

// not a leetcode


import PracticeInterview.TreeNode;

//给你一个sorted array，让你返回一个complete BST
// 第一步： 计算h = log2(n + 1)  不包括leaf node
// 第二步: 计算叶子节点个数 leaves = 总数 - 前h层总节点数 = n - (2 ^ h - 1)
// 第三步: 目标是计算咱们的中心点root的左子树上有多少节点
//      第一部分： 是前h层的左子树，leftSubTree = 2 ^ (h - 1) - 1
//      第二部分： 是左子树上的叶子节点个数,
//                  如果叶子节点总数大于左子树上叶子节点最大容量，也就是 leaves > 2 ^ (h - 1)，那么leftComplete = 2 ^ (h - 1)
//                  否则： leftComplete = leaves
//      综上： 可以计算出mid = leftSubTree + leftComplete + left
//                                                      别忘记了left
// ok
//     4
//    / \
//   2   5
//  / \
// 1   3
//
//
// leaves = 5 - (4 - 1) = 2
//if 2 <= 2 ^ h, 2, else:  2 ^ 2
//2 ^ h / 2 - 1
public class CompleteBST {
    public static void main(String [] agrs) {
        print(completeBST(new int[]{1,2,3,4,5}));
        System.out.println();
        print(completeBST(new int[]{1,2}));
        System.out.println();

        print(completeBST(new int[]{1,2,3}));
        System.out.println();

        print(completeBST(new int[]{1,2,3,4,5}));
        System.out.println();

        print(completeBST(new int[]{1,2,}));
        System.out.println();


        print(completeBST(new int[]{1,2,3,4,5,6,7,8,9}));
        System.out.println();


        print(completeBST(new int[]{1}));
        System.out.println();




    }
    private static void print(TreeNode root) {
        if(root == null) {
            return;
        }
        print(root.left);
        System.out.print(root.val + ",");
        print(root.right);
    }
    private static TreeNode completeBST(int [] array) {
        return helper(array, 0, array.length - 1);
    }
    private static TreeNode helper(int [] array, int left, int right) {
        if(right - left < 0) {
            return null;
        }
        int total = right - left + 1;
        int h = (int)(Math.log(total + 1) / Math.log(2));
        int leaves = total - ((int)Math.pow(2, h) - 1);
        int mid = (int)Math.pow(2, h - 1) - 1 + left;// left sub tree
        int leftComplete = (int)Math.pow(2, h - 1);
        if (leaves > leftComplete) {
            mid += leftComplete;
        } else {
            mid += leaves;
        }
        TreeNode root = new TreeNode(array[mid]);
        root.left = helper(array, left, mid - 1);
        root.right = helper(array, mid + 1, right);
        return root;
    }
}


package Algorithm.ConstructTree;

import PracticeInterview.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class LevelToBinaryTree {
    public TreeNode construct(int[] level) {
        // Write your solution here.
        if(level == null || level.length == 0) {
            return null;
        }
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(level[0]);
        q.offer(root);
        for(int i = 0; i < level.length / 2; i ++) {
            TreeNode cur = q.poll();
            cur.left = new TreeNode(level[i * 2 + 1]);
            q.offer(cur.left);

            if(i * 2 + 2 < level.length) {
                cur.right = new TreeNode(level[i * 2 + 2]);
                q.offer(cur.right);
            }
        }
        return root;
    }
}

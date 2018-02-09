package Practical.pg;

public class tree2strstr2tree {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder res = new StringBuilder();
        dfs(root, res);
        return res.toString();
    }
    public void dfs(TreeNode root, StringBuilder res) {
        if (root == null) {
            res.append("null ");
            return;
        }
        res.append(root.val);
        res.append(" ");
        dfs(root.left, res);
        dfs(root.right, res);
    }

    int index = 0;
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] input = data.split(" ");
        return dfs_2(input);
    }
    public TreeNode dfs_2(String[] input) {
        if (input[index].equals("null")) {
            index++;
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(input[index]));
        index++;
        root.left = dfs_2(input);
        root.right = dfs_2(input);
        return root;
    }
}

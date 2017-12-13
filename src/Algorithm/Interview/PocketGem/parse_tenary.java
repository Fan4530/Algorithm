package Algorithm.Interview.PocketGem;
import java.util.*;
public class parse_tenary {
    public static void main(String[] args) {
        String test = "a?b?d:e:c";
        TreeNode root = build(test);
        inorder(root);
        int [] array = new int[3];
        HashSet<Integer> set = new HashSet<>();

    }
    // a?(b?d:e):(c)
    // time : height O(n), two branches, O(2^n)
    public static TreeNode build(String s) {
        if (s.length() == 0) return null;
        int index1 = s.indexOf('?'), index2 = s.lastIndexOf(':');
        char val = s.charAt(0);
        TreeNode root = new TreeNode(val);
        if (index1 == -1) return root;
        root.left = build(s.substring(index1 + 1, index2));
        root.right = build(s.substring(index2 + 1));
        return root;
    }





    public static void inorder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        inorder(root.left);
        inorder(root.right);
    }
    static class TreeNode {
        char val;
        TreeNode left;
        TreeNode right;
        public TreeNode(char val) {
            this.val = val;
        }
    }
    // with stack
    public String parseTernary(String expression) {
        if (expression == null || expression.length() == 0) return "";
        Deque<Character> stack = new LinkedList<>();

        for (int i = expression.length() - 1; i >= 0; i--) {
            char c = expression.charAt(i);
            if (!stack.isEmpty() && stack.peek() == '?') {

                stack.pop(); //pop '?'
                char first = stack.pop();
                stack.pop(); //pop ':'
                char second = stack.pop();

                if (c == 'T') stack.push(first);
                else stack.push(second);
            } else {
                stack.push(c);
            }
        }

        return String.valueOf(stack.peek());
    }
}

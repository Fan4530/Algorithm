package Interview.PocketGem;

public class tree2stringparethesis {
    public String tree2str(TreeNode t) {
        if (t == null)
            return "";
        
        // case 1
        if(t.left == null && t.right == null)
            return String.valueOf(t.val);
        String res = "";
        res += t.val + "(" + tree2str(t.left) + ")";
        if(t.right != null) {
            res += "(" + tree2str(t.right) + ")";
        }
        return res;
    }
    public TreeNode helper(char[] input, int left, int right) {
        if (left > right)
            return null;
        int index = left;
        StringBuilder sb = new StringBuilder();
        // this is the key here, if it is a digit or it is '-'
        while (index <= right && input[index] != '(') {
            sb.append(input[index]);
            index++;
        }
        
        TreeNode root = new TreeNode(Integer.parseInt(sb.toString()));
        if (index-1 == right)
            return root;
        
        int match = 0;
        int mid = left;
        while (index <= right) {
            if (input[index] == '(')
                match++;
            else if (input[index] == ')')
                match--;
            if (match == 0) {
                mid = index;
                break;
            }
            index++;
        }
        // this line, it is left + sb.length() + 1 pay attention please
        root.left = helper(input, left+sb.length()+1, mid-1);
        root.right = helper(input, mid+2, right-1);
        return root;
    }
    
    public TreeNode str2tree(String s) {
        if (s == "")
            return null;
        char[] input = s.toCharArray();
        return helper(input, 0, input.length-1);
    }
}

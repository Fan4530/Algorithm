package Algorithm.Interview.PocketGem;

public class tree_generic {
	public class TreeNode<T> {
		public T val;
		TreeNode<T> left;
		TreeNode<T> right;
		TreeNode(T val) {
			this.val = val;
		}
	}
	
	public static String tree2str(TreeNode<Character> root) {
		if(root == null)
			return "null";
		String left = tree2str(root.left);
		String right = tree2str(root.right);
		return "(" + root.val + " " + left + " " + right + ")";
	}
	
	public static TreeNode<Character> parse(String input) {
		if(input.equals("null"))
			return null;
		tree_generic t = new tree_generic();
		TreeNode<Character> root = t.new TreeNode<>(input.charAt(1));
		if(input.length() <= 13)
			return root;
		int match = 0;
		int mid = 0;
		for(int i = 3; i < input.length(); i++) {
			if(input.charAt(i) == '(')
				match++;
			if(input.charAt(i) == ')')
				match--;
			if(match == 0) {
				mid = i;
				break;
			}
		}
		root.left = parse(input.substring(3, mid+1));
		root.right = parse(input.substring(mid+2, input.length()-2));
		return root;
	}
	
	public static void main(String[] args) {
		tree_generic t = new tree_generic();
		TreeNode<Character> root = t.new TreeNode<>('a');
		root.left = t.new TreeNode<Character>('b');
		TreeNode<Character> rootright = t.new TreeNode<>('c');
		rootright.left = t.new TreeNode<Character>('d');
		root.right = rootright;
		String ouput = t.tree2str(root);
		TreeNode<Character> root2 = t.parse(ouput);
		System.out.println(root2.val);
	}
}

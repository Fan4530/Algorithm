package Algorithm.Interview.Linkedin;

import java.util.*;

public class ZigZagBinaryTree {
//	3
//   / \
//  9  20
//    /  \
//   15   7
//
//
//[
//  [3],  order = true
//  [20,9], order = false
//  [15,7]
//]
    public List<List<Integer>> method1(TreeNode root) {
		if(root == null) {
			return new ArrayList<List<Integer>>();
		}
		List<List<Integer>> res = new ArrayList<>();
		Queue<TreeNode> q = new LinkedList<>();
		q.offer(root);
		boolean order = true;
		while(!q.isEmpty()) {
			int size = q.size();
			List<Integer> list = new ArrayList<>();
			for(int i = 0; i < size; i ++) {
				TreeNode node = q.poll();
				list.add(node.val);//be careful about the key or val
				if(node.left != null) {
					q.offer(node.left);
				} 
				if(node.right != null) {
					q.offer(node.right);
				}
			}
			if(!order) {
				Collections.reverse(list);
			}
            order = !order;// be careful !!!!!
			res.add(list);
		}
		return res;
	}
//
//	3
//   / \
//  9  20
//    /  \
//   15   7
//
// Firt      15  7   |  9  20     Last
//
//[
//  [3],  order = true
//  [20,9], order = false       OfferFirst, right child,     pollLast
//  [15,7]
//]
	public List<List<Integer>> method2(TreeNode root) {
			if(root == null) {
				return new ArrayList<List<Integer>>();
			}
			List<List<Integer>> res = new ArrayList<>();
			Deque<TreeNode> stack = new LinkedList<>();
			stack.offerLast(root);
			boolean order = true;
			while(!stack.isEmpty()) {
				int size = stack.size();
				List<Integer> list = new ArrayList<>();
				for(int i = 0; i < size; i ++) {
					if(order) {
						TreeNode node = stack.pollFirst();
						list.add(node.val);//be carefull !!
						if(node.left != null) {
							stack.offerLast(node.left);
						}
						if(node.right != null) {
							stack.offerLast(node.right);
						}
					} else {
						TreeNode node = stack.pollLast();
						list.add(node.val);// be careful!!
						if(node.right != null) {
							stack.offerFirst(node.right);
						}
						if(node.left != null) {
							stack.offerFirst(node.left);
						}
					}
				}
				res.add(list);// be carefull!!
				order = !order;// be careful!!
			}

			return res;
	}
}
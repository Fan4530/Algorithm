package Practical.pg;

public class update_query_segment {
	
	public class SegmentTreeNode {
	    public int start, end;
	    public SegmentTreeNode left, right;
	    public int sum;
	    public SegmentTreeNode(int start, int end) {
	        this.start = start;
	        this.end = end;
	        this.left = null;
	        this.right = null;
	        this.sum = 0;
	    }
	}

    public class NumArrayTree {
        public SegmentTreeNode root = null;
        NumArrayTree(int[] nums) {
            this.root = BuildTree(nums, 0, nums.length - 1);
        }
        //O(2n) algorithm, O(2n) space
        private SegmentTreeNode BuildTree(int[] nums, int start, int end) {
            if (start > end) //Check invalid inout
                return null;
            else {
                SegmentTreeNode root = new SegmentTreeNode(start, end);
                if (start == end)
                    root.sum = nums[start];
                else {
                    int mid = start + (end - start) / 2;
                    root.left = this.BuildTree(nums, start, mid);
                    root.right = this.BuildTree(nums, mid + 1, end);
                    root.sum = root.left.sum + root.right.sum;
                }
                return root;
            }
            
        }
        public void Update(int i, int val)
        {
            Update(root, i, val);
        }
        //O(logN) time, O(h) space
        private void Update(SegmentTreeNode root, int pos, int val)
        {
            if (root.start == root.end)
                root.sum = val;
            else
            {
                int mid = root.start + (root.end - root.start) / 2;
                if (pos <= mid)
                    Update(root.left, pos, val);
                else
                    Update(root.right, pos, val);
                root.sum = root.left.sum + root.right.sum;
            }
        }
        public int SumRange(int i, int j)
        {
            return sumRange(root,i, j);
        }
        //O(logN) time, O(h) space
        private int sumRange(SegmentTreeNode root, int start, int end)
        {
            if (root.end == end && root.start == start)
                return root.sum;
            else
            {
                int mid = root.start + (root.end - root.start) / 2;
                if (end <= mid)   //All in the left
                    return sumRange(root.left, start, end);
                else if(start >= mid + 1)   //In the right
                    return sumRange(root.right, start,end);
                else                //Across mid point
                    return sumRange(root.right, mid + 1, end) + sumRange(root.left, start,mid);
            }
        }
    }
    
    public static void main(String[] args) {
    	int[] nums = {1,3,5,7,9};
    	update_query_segment u = new update_query_segment();
    	NumArrayTree t = u.new NumArrayTree(nums);
    	System.out.println(t.SumRange(1, 3));
    }
}

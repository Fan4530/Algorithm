package Interview.PocketGem;

public class kth_largest_ele {
	//average: O(n logk)
	// bad case for not random data
	// if we select mid
	//  5 2 1 4 3  --> 5 2 4 3 1 --> 5 3 4 2 1 --> ...
	//
	//  the 1 st largest --> k = 0
	//

	public int findKthLargest(int[] nums, int k) {
		int idx = helper(nums, k - 1, 0, nums.length - 1);// be careful, k - 1
		return nums[idx];
	}
	private int helper(int [] nums, int k, int l, int r) {
		if(l >= r) {
			return l;
		}
		int start = partition(nums, k, l, r);
		if(start == k) {
			return start;
		} else if(start < k) {
			return helper(nums, k, start + 1, r);
		} else {
			return helper(nums, k, l, start - 1);
		}
	}
	private int partition(int [] nums, int k, int l, int r) {
		int pivot = (int)(Math.random() * (r - l + 1)) + l;
		swap(nums, pivot, r);
		int start = l;
		int end = r - 1;
		while(start <= end) {
			if(nums[r] < nums[start]) {
				start ++;
			} else if(nums[r] >= nums[end]) {
				end --;
			} else {
				swap(nums, start ++, end --);
			}
		}
		swap(nums, r, start);
		return start;
	}
	private void swap (int [] nums, int l, int r) {
		int tmp = nums[l];
		nums[l] = nums[r];
		nums[r] = tmp;
	}

	public static void main(String[] args) {
		int[] nums = {1,5,4,3,2,7,8,0};
		kth_largest_ele k = new kth_largest_ele();
		System.out.println(k.findKthLargest(nums, 0));
	}
}

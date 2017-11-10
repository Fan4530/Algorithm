package Interview.PocketGem;

public class find_first_missing_positive {
	
	public static int find(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == i + 1 || nums[i] <= 0 || nums[i] > nums.length) i++;
            else if (nums[i] != nums[nums[i]-1]) 
                swap(nums, i, nums[i]-1);
            else
                i++;
        }
        int k = 0;
        while (k < nums.length && nums[k] == k+1) {
            k++;
        }

        return k+1;
	}
	
	public static void swap(int[] nums, int x, int y) {
		int temp = nums[x];
		nums[x] = nums[y];
		nums[y] = temp;
	}
	
	public static void main(String[] args) {
		find_first_missing_positive ff = new find_first_missing_positive();
		int[] nums = {4,4,4};
		System.out.println(ff.find(nums));
	}
	
}

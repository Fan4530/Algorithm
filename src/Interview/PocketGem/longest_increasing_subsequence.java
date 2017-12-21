package Interview.PocketGem;

public class longest_increasing_subsequence {
	// nlogn
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0) 
            return 0;
        int[] refined = new int[nums.length];
        int size = 1;
        refined[0] = nums[0];
        for(int i = 1; i < nums.length; i++) {
            int pos = binary_search(refined, nums[i], size);
            refined[pos] = nums[i];
            if(pos == size) {
                size++;
            }
        }
        return size;
    }
    //find rhe first one that is larger than target
    public int binary_search(int[] refined, int target, int size) {
        int left = 0;
        int right = size;
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(refined[mid] < target) {// if the target is larger, then remove
                left = mid + 1;
            } else 
                right = mid;
        }
        return left;
    }
    
    //dp n*n
    
    public int lengthOfLIS_DP(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int[] dp = new int[nums.length];
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int max_length = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    max_length = Math.max(max_length, dp[j]);
                }
            }
            max_length++;
            dp[i] = max_length;
            res = Math.max(res, max_length);
        }
        return res;
    }
}

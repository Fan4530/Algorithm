package DynamicProgram;

import java.util.Arrays;

/**
 * Created by program on 10/20/2017.
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        // index is the max value
        int index = 0;
        int pre = nums[0];
        for(int i = 1; i < nums.length; i ++) {
            if(nums[i] <= nums[index]) {
                int insert = Arrays.binarySearch(nums, 0, index, nums[i]);
                nums[insert] = nums[i];
            } else {
                nums[++index] = nums[i];
            }
        }
        return index + 1;
    }
    public static void main(String [] args) {
        LongestIncreasingSubsequence sol = new LongestIncreasingSubsequence();
        sol.lengthOfLIS(new int[]{2,1});
    }
}

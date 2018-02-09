package Practical.pg;

public class MaxProductSubArray {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        // initialize
        int max = nums[0];
        int min = nums[0];
        int globalMax = nums[0];
        for(int i = 1; i < nums.length; i++) {
            //pay attention to the temp
            int temp = max;
            // first max is to deal with negative values
            // second max is to deal with the number [0, 1]
            max = Math.max(Math.max(max * nums[i], min * nums[i]), nums[i]);
            min = Math.min(Math.min(temp * nums[i], min * nums[i]), nums[i]);
            globalMax = Math.max(globalMax, max);
        }
        return globalMax;
    }
}

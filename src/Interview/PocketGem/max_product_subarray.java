package Interview.PocketGem;

public class max_product_subarray {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int local_max = nums[0];
        int local_min = nums[0];
        int global_max = nums[0];
        for(int i = 1; i < nums.length; i++) {
            //pay attention to the temp
            int temp = local_max;
            local_max = Math.max(Math.max(local_max * nums[i], local_min * nums[i]), nums[i]);
            local_min = Math.min(Math.min(temp * nums[i], local_min * nums[i]), nums[i]);
            global_max = Math.max(global_max, local_max);
        }
        return global_max;
    }
}

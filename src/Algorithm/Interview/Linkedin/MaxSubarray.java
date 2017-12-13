package Algorithm.Interview.Linkedin;

/**
 * Created by program on 11/15/2017.
 */
public class MaxSubarray {
    public int maxSubArray(int[] nums) {
        // -2 1 -3 4 -1 2 1
        // positive contribution:
        // -2 1 -2 4 3 5 6
        //     if pre < 0, pre =
        //         else   pre +=

        if(nums == null || nums.length == 0) {
            return 0;
        }
        int globalMax = nums[0];
        int pre = nums[0];
        for(int i = 1; i < nums.length; i ++) {
            pre = nums[i] + (pre > 0 ? pre : 0);// if pre is not positive contribution, then set 0
            globalMax = Math.max(globalMax, pre);
        }
        return globalMax;
    }

    public int maxProduct(int[] nums) {
        //select and not select
        //           2      3        -2  4
        //   min     2    min()
        //   max     2
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int min = nums[0];
        int max = nums[0];
        int globalMax = nums[0];
        for(int i = 1; i < nums.length; i ++) {
            int tmp = max;
            max = Math.max(nums[i], Math.max(min * nums[i], max * nums[i]));
            min = Math.min(nums[i], Math.min(min * nums[i], tmp * nums[i]));
            globalMax = Math.max(globalMax, max);
        }
        return globalMax;
    }
}

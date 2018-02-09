package Practical.liyi;

import java.util.Arrays;

/**
 * Created by program on 11/15/2017.
 */
public class Partition {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) return false;
        Arrays.sort(nums);
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % k != 0 || sum < k) return false;
        sum = sum / k;

        return helper(nums, sum, nums.length - 1, new int[k]);
    }
    // array p: store different part of sum,  for each i, p[i] <= target
    // time complexty
    //
    //                        / ||| |\    k
    //                                               k layers
    //                                               k^k


    private boolean helper(int [] nums, int target,  int idx, int [] p) {
        if(idx == -1) {
            for(int t : p) {
                if(t != target) {
                    return false;
                }
            }
            return true;
        }
        for(int i = 0; i < p.length; i ++) {
            if(p[i] + nums[idx] <= target) {// may have zero
                p[i] += nums[idx];
                if(helper(nums, target, idx - 1, p)) {
                    return true;
                }
                p[i] -= nums[idx];
            }
        }
        return false;
    }
}

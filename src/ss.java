import java.util.Arrays;

/**
 * Created by program on 10/15/2017.
 */
public class ss {

        boolean res = false;

        public void helper(int[] nums, int index, int[] sub, int k, int target) {
            if (res == true)
                return;
            if (index == nums.length) {
                for (int i = 1; i < k; i++) {
                    if (sub[i] != sub[0]) {
                        return;
                    }
                }
                res = true;
                return;
            }
            for (int i = 0; i < k; i++) {
                if (sub[i] + nums[index] > target)
                    continue;
                sub[i] += nums[index];
                helper(nums, index+1, sub, k, target);
                sub[i] -= nums[index];
            }
        }

        public boolean canPartitionKSubsets(int[] nums, int k) {
            Arrays.sort(nums);
            int[] reverse = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                reverse[i] = nums[nums.length-i-1];
            }

            int[] sub = new int[k];
            int total = 0;
            for (int item : nums)
                total += item;
            int target = total % k;
            if (target != 0)
                return false;
            target = total / k;
            helper(reverse, 0, sub, k, target);
            return res;
        }
        public static void main(String [] args) {
            ss sol = new ss();
            sol.canPartitionKSubsets(new int[]{5, 4, 3, 2, 1}, 3);
        }

}

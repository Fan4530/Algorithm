package Practical.liyi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by program on 11/15/2017.
 */
public class Permutation {
    // swap and swap,

    //  1 2 3
    //
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < nums.length; i ++) {
            list.add(nums[i]);
        }
        helper(list, res, 0);
        return res;
    }
    private void helper(List<Integer> nums, List<List<Integer>> res, int index) {
        if(index == nums.size()) {
            res.add(new ArrayList<>(nums));
            return;
        }
        for(int i = index; i < nums.size(); i ++) {
            swap(nums, i, index);
            helper(nums, res, index + 1);
            swap(nums, i, index);
        }

    }
    private void swap(List<Integer> nums, int left, int right) {
        int tmp = nums.get(left);
        nums.set(left, nums.get(right));
        nums.set(right, tmp);
    }
}

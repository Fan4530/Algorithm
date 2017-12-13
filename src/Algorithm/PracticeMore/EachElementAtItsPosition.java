package Algorithm.PracticeMore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by program on 11/21/2017.
 * To deal with array use O(n) time ccomplexity
 *
 * mi
 */
public class EachElementAtItsPosition {
    //Find all elements disappear in an array
    // step 1: To mark the element that appear as negative,
    // step 2: check the position whose value is not negative
    public List<Integer> findDisappearedNumbers(int[] nums) {
        //  [4,3,2,7,8,2,3,1]
        // - - - -     - - -
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < nums.length; i ++) {
            int index = Math.abs(nums[i]) - 1;
            if(nums[index] > 0) {
                nums[index] = -nums[index];
            }
        }
        for(int i = 0; i < nums.length; i ++) {
            if(nums[i] > 0) {
                res.add(i + 1);
            }
        }
        return res;
    }


    public int firstMissingPositive(int[] nums) {
        // [3,4,-1,1]
        //
        //  -1 4 3 1
        //  -1 1 3 4
        //  -1 0
        // [3,3,3]
        //
        // to make arr[i] = i + 1
        // if not, check arr[i] > 0  and <= len  [because this is index + 1]
        // and    that element != its index  + 1[or it has been at its position]
        // if meet the above requirement, then, change the array[i] - 1 with i
        for(int i = 0; i < nums.length; i ++) {
            // nums[i] - 1 = index,  nums[index] = index + 1
            while(nums[i] > 0 && nums[i] <= nums.length && nums[i]!= nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        for(int i = 0; i < nums.length; i ++) {
            if(nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }
    private void swap(int [] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }
}

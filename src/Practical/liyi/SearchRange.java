package Practical.liyi;

/**
 * Created by program on 11/15/2017.
 */
public class SearchRange {
    public int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int start = searchFirstEqualOrLargerElement(nums, target);
        if(start >= nums.length || nums[start] != target) {
            // case 1: all the elements are samller than target, start = nums.length   e.g. 1, 2, 3   target 5,  start = 3
            // case 2: general case, not found.   e.g   1 2 4, target : 3,  find start = 2, 4 != 3
            return new int[]{-1, -1};
        }
        return new int[]{start, searchFirstEqualOrLargerElement(nums, target + 1) - 1};
    }

    // find the first number that is larger or equal to target
    //     1 2 3 3 3 3 5,  if target is 4, find index = 6, if the target is 3, find the target is 2
    //idx  0 1 2 3 4 5 6
    //case 1: if mid > target   right = mid,   cannot be right = mid - 1 becasue if target is not in the array, mid may still be the res
    //case 2: if mid == target  right = mid
    //case 3: if mid <target,   left = mid + 1
    public int searchFirstEqualOrLargerElement(int [] nums, int target) {
        int left = 0;
        int right = nums.length;
        while(left < right) {// leave one element that is not determined, be careful "<"
            int mid = left + (right - left) / 2;
            if(nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        // e.g.  1 2  target > mid = 1, then left = mid + 1, leave 2 not determined
        //       1 2  target = mid = 1, so 2 is larger than  target or is not the first of target, so leave 1 not determniend
        //       1 2  target < mid = 1, not determined if the array has the target, leave 1 not determined
        //                  (cannot be 2, if 2 is, 1 must is, because target is in the left of 1)
        return left;
    }
}

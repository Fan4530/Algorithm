package Interview.Linkedin.Linkedin1;

/**
 * Created by program on 1/11/2018.
 */
public class Increasing {
    public static void main(String [] nums) {
        Increasing sol = new Increasing();
        System.out.println(sol.increasing(new int[]{2,1,5,0,3}));
    }
    //连续3个递增 O(nlogn)
    public boolean increasing(int [] nums) {
         if(nums == null || nums.length <= 2) {
             return false;
         }
         int slow = 1;
         for(int i = 1; i < nums.length; i ++) {
             if(nums[i] > nums[slow - 1]) {
                 nums[slow++] = nums[i];
             } else {
                 nums[findFirstLargerEqual(nums, slow, nums[i])] = nums[i];
             }
         }
         System.out.println(slow);
         return slow >= 3;
     }
     private int findFirstLargerEqual(int [] nums, int r, int target) {
         int l = 0;
         while(l < r) {
             int mid = l + (r - l) / 2;
             if(nums[mid] >= target) {
                 r = mid;
             } else {
                 l = mid + 1;
             }
         }
         return l;
     }
     //更好的方法O(n)
    public boolean increasingTriplet2(int[] nums) {
        if(nums == null || nums.length <= 2) {
            return false;
        }
        int left = Integer.MAX_VALUE;
        int right = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i ++) {
            if(nums[i] <= left) {
                left = nums[i];
            } else if(nums[i] <= right) {
                right = nums[i];
            } else {
                return true;
            }
        }
        return false;
    }
}

package Interview.PocketGem;

/**
 * Created by program on 11/21/2017.
 */
public class FirstMissingPositive {

    public static int firstMissingPositive(int[] nums) {
        // [3,4,-1,1]
        //
        // 3
        // [-1,4,3,1]
        //
        // -1 not change   i = 1
        // nums[1] = 4
        //[-1,1,3,4]

        //nums[1] = 1
        //[1,-1,3,4]

        //nums[2] = 3
        //nums[3] = 4


        // [3,3,3]
        //
        // to make arr[i] = i + 1
        // if not, check arr[i] > 0  and <= len  [because this is index + 1]
        // and    that element != its index  + 1[or it has been at its position]
        // if meet the above requirement, then, change the array[i] - 1 with i
        for(int i = 0; i < nums.length; i ++) {
            // nums[i] - 1 = index,  nums[index] = index + 1
            int curIndex = nums[i] - 1;
            while(curIndex >= 0 && curIndex < nums.length && nums[curIndex] - 1!= curIndex) {
                swap(nums, i, curIndex);
                curIndex = nums[i] - 1;
            }
        }
        for(int i = 0; i < nums.length; i ++) {
            if(nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }
    private static void swap(int [] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }
    public static void main(String [] args) {
        System.out.println(firstMissingPositive(new int[]{3,4,-1,1}));
    }
}

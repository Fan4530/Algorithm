package Interview.PocketGem;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Created by program on 11/21/2017.
 */
public class SlidingWindowSmallest {
    // sliding window max
    public int[] maxSlidingWindow(int[] nums, int k) {
        // idea: new + larger element, must stay in the sliding window
        //       new + old and smaller + new elements, we are not sure, let them stay in the sliding windows
        //       old + smaller elements, not necessary to stay in the sliding windows

        if(k <= 0) {
            return new int[0];
        }
        int [] res = new int[nums.length - k + 1];
        Deque<Integer> dq = new ArrayDeque<>();
        int left = 0;
        for(int right = 0; right < nums.length; right ++) {

            // just need to push, not <= because
            // e.g [-7,-8,7,5,7,1,6,0]    len = 4
            // you need two 7s left in the dq, or when the last is 7, e.g.
            //[-7,-8,7,5,7,1,6,0]
            //       |     |
            // in this case, if you only have one 7, then the only 7 will be poll out
            // if smallest, just need to change <  to >
            while(!dq.isEmpty() && dq.peekFirst() < nums[right]) {
                dq.pollFirst();
            }
            dq.offerFirst(nums[right]);


            if(right >= k - 1) {// check the Last of the sliding windows is equal to nums[left]
                // Be careful !!! right >= k - 1
                if(nums[left] == dq.peekLast()) {// the largest one should be removed
                    res[left] = dq.pollLast();
                } else {
                    res[left] = dq.peekLast();
                }
                left ++;
            }

        }
        return res;
    }
    public static void main(String [] args) {

        SlidingWindowSmallest sol = new SlidingWindowSmallest();
        System.out.println(Arrays.toString(sol.maxSlidingWindow(new int[]{-7,-8,7,5,7,1,6,0}, 4)));
    }
}

package Heap;

import Test.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by program on 10/14/2017.
 */
public class test {

    public int findShortestSubArray(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i ++) {
            Integer frq = map.get(nums[i]);
            if(frq == null) {
                map.put(nums[i], 1);
            } else {
                map.put(nums[i], frq + 1);
            }
        }
        // Arrays.sort(new );
        // find the largest frq
        // Map.Entry<Integer, Integer> max =
        int max = 0;
        for(Map.Entry<Integer, Integer> e : map.entrySet()) {
            if(e.getValue() >  max) {
                max = e.getValue();
            }
        }
        if(max <= 1) {
            return 0;
        }
        ArrayList<Integer> maxList = new ArrayList<>();
        for(Map.Entry<Integer, Integer> e : map.entrySet()) {
            if(e.getValue() ==  max) {
                maxList.add(e.getKey());
            }
        }
        int res = Integer.MAX_VALUE;
        for(int i = 0; i < maxList.size(); i ++) {
            int count = max;
            int left = -1;
            int right = -1;
            for(int j = 0; j < nums.length; j ++) {
                if(maxList.get(i) == nums[j]) {
                    count --;
                    if(count == max - 1) {
                        left = j;
                    } else if(count == 0 ) {
                        right = j;
                    }
                }

            }
            res = Math.min(res, right - left + 1);
        }
        return res;
    }
    private int pre = -1;
    public static void main(String [] args) {
        test sol = new test();
        int[] array = new int[]{1};
        System.out.println(sol.findShortestSubArray(array));
    }

}

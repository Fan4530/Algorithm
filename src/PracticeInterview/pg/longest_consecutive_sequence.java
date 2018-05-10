package Practical.pg;
import java.util.*;
public class longest_consecutive_sequence {
    public int longestConsecutive(int[] nums) {
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num : nums) {
            if(!map.containsKey(num)) {
                int left = map.getOrDefault(num-1, 0);
                int right = map.getOrDefault(num+1, 0);
                int sum = left + right + 1;
                res = Math.max(res, sum);
                map.put(num, 1);
                map.put(num-left, sum);
                map.put(num+right, sum);

            }
        }
        return res;
    }
}

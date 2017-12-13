package Algorithm.Interview.PocketGem;
import java.util.*;
public class contains_duplicates3 {
    //contains duplicate 1:
    //bruit force O(n^2)   --> not change the order
    //sort: time:O(nlogn)  --> change the order
    //set: time: O(n)  space: 0(n) --> not change the order

    //contains duplicate 2:
    //Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array
    // such that nums[i] = nums[j] and the absolute difference between i and j is at most k.
    // sliding window + set

    // contains dup 3:

	// with TreeSet
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(nums == null || nums.length == 0 || k <= 0)
            return false;
        TreeSet<Long> set = new TreeSet<>();
        for(int i = 0; i < nums.length; i ++) {
            long cur = (long)(nums[i]);
            Long floor = set.floor(cur);
            Long ceiling = set.ceiling(cur);
            if((floor != null && nums[i] - floor <= t) || (ceiling != null && ceiling - nums[i] <= t))
                return true;
            set.add(cur);
            if(i >= k) {
                set.remove((long)nums[i-k]);
            }
        }
        return false;
    }
    // without TreeSet
    

//    桶的方法 O(n)
//
//    思想是分成t+1个桶，对于一个数，将其分到第key = num / (t + 1) 个桶中，我们只需要查找相同的和相邻的桶的元素就可以判断有无重复。
//
//    比如t = 4，那么0~4为桶0，5~9为桶1，10~14为桶2 。
//
//    使用t+1个桶是为啥？这是为了避免除以0的错误，可能t = 0.
//
//    下面，C++和Java的版本，如果num为负数，那么key -= 1，因为比C++/Java 的负数取整是：-2 /3 = 0，这题这样是不行的。
//
    public boolean containsNearbyAlmostDuplicate_2(int[] nums, int k, int t) {
        if (t < 0) return false;
        Map<Long, Long> d = new HashMap<>();
        long w = (long)t + 1;//avoid t = 0
        for (int i = 0; i < nums.length; ++i) {
            long m = getID(nums[i], w);//be careful,  nums < 0
            if (d.containsKey(m))
                return true;
            if (d.containsKey(m - 1) && Math.abs(nums[i] - d.get(m - 1)) < w)
                return true;
            if (d.containsKey(m + 1) && Math.abs(nums[i] - d.get(m + 1)) < w)
                return true;
            d.put(m, (long)nums[i]);
            if (i >= k) d.remove(getID(nums[i - k], w));
        }
        return false;
    }
    private long getID(long i, long w) {
        return i < 0 ? (i + 1) / w - 1 : i / w;
    }

}

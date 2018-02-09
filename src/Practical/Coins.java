package Practical;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by program on 1/24/2018.
 */
public class Coins {

    public static void main(String[] args) {
        Coins ob = new Coins();
        int[] coins = new int[]{1,2,3,4,5,6};
        ob.coinsII(coins);
    }

    public void coinsII(int [] nums) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        Set<Set<Integer>> list1 = new HashSet<>();
        Set<Set<Integer>> list2 = new HashSet<>();
        helper1(set1, set2, list1, list2, 0, 0, nums, 0);
        System.out.println(list1);
        System.out.println(list2);
    }
    private void helper1(Set<Integer> set1, Set<Integer> set2, Set<Set<Integer>> list1, Set<Set<Integer>> list2, int sum1, int sum2, int [] nums, int idx) {
        if(idx >= nums.length) {
            if(sum1 == sum2) {
                list1.add(new HashSet<>(set1));
                list2.add(new HashSet<>(set2));
            }
            return;
        }
        if(sum1 == sum2) {
            list1.add(new HashSet<>(set1));
            list2.add(new HashSet<>(set2));
        }
        if(!set1.contains(nums[idx]) && !set2.contains(nums[idx])) {
            set1.add(nums[idx]);
            helper1(set1, set2, list1, list2, sum1 + nums[idx], sum2, nums, idx + 1);
            set1.remove(nums[idx]);
            set2.add(nums[idx]);
            helper1(set1, set2, list1, list2, sum1, sum2 + nums[idx], nums, idx + 1);
            set2.remove(nums[idx]);
        }
        helper1(set1, set2, list1, list2, sum1, sum2, nums, idx + 1);
    }
}


package Interview.PocketGem;

import java.util.*;

/**
 * 1. two sum: not sorted
 * 2. two sum: sorted
 * 3. three sum: two pointers
 * 4. four sum: two pointers
 * 5: two sum: interface
 * 6: two sum: BST(not completed)
 */
public class TwoThreeFourSum {

    //assumption:
    //1. Will there are more than one solutions or more than one solution?
    //2. return index or number?
    //              !!if index, use map, cannot use two pointer or only set,  becasue it needs to sort and the index will change
    //3. if there are more than one solutions, will this array have duplicate? if yes, can my solutions have duplicate?
    //              You need to deduplicate if use two pointers
    //4. prefer optimized time or space
    //5. sorted or not?
    //              if sorted, use two pointers.


    /**
     *     //two sum:
         1. one solution
         2. return index
         3. only one solution, so no need to care
         4. space: O(n), time O(n)
     * @param nums
     * @param target
     * @return
     */

    public int[] twoSum(int[] nums, int target) {
        //you can add check corner case
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i ++) {
            if(map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * Sorted use two pointers
     * the assumptions are as the previous one
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSumSorted(int[] numbers, int target) {
        // you can add check corner case
        int left = 0;
        int right = numbers.length - 1;
        while(left < right) {
            int sum = numbers[left] + numbers[right];
            if(sum == target) {
                return new int[]{left + 1, right + 1};
            } else if(sum < target) {
                left ++;
            } else {
                right --;
            }
        }
        return new int[]{-1, -1};
    }

    /**
     *
     * @param nums
     * @return
     */
    //three sum
    public List<List<Integer>> threeSum(int[] nums) {
        //step 1: sort
        //step 2: for loop to the whole array
        //                 deduplicate!
        //step 3: two sum,   deduplicate
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < nums.length - 2;i ++) {
            if(i == 0 || nums[i] != nums[i - 1]) {
                int sum = 0 - nums[i];
                int left = i + 1;
                int right = nums.length - 1;
                while(left < right) {
                    if(nums[left] + nums[right] == sum) {
                        res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        // first ++, it is because we need to move though the while is not meet the requirement
                        // the following ++ is to complete the previous while loop
                        // e.g.     left = 1, right = 5 --> 2,4,
                        // check if (1 with 2) and (4 with 5) are duplicate
                        // if it is, check again, before check, 2,4 --> 3,3
                        while(left < right && nums[++left] == nums[left - 1]);// be careful, not left with left + 1
                        while(left < right && nums[--right] == nums[right + 1]);
                    } else if(nums[left] + nums[right] < sum) {
                        left ++;
                    } else {
                        right --;
                    }
                }
            }
        }
        return res;
    }
    public static void main(String [] args) {
        TwoThreeFourSum sol = new TwoThreeFourSum();
        sol.threeSum(new int[]{-1,0,1});
    }

    /**
     *
     * @param nums
     * @param target
     * @return
     */
    // four sum
    public List<List<Integer>> fourSum(int[] nums, int target) {
        //step 1: sort
        //step 2: for loop to the whole array
        //                 deduplicate!
        //step 3: two sum,   deduplicate
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int j = 0; j < nums.length - 3; j ++) {
            if(j == 0 || nums[j] != nums[j - 1]) {
                //you can choose if you need to prune
//                if (nums[j]+nums[j+1]+nums[j+2]+nums[j+3]>target)
//                    break;//be careful
//                if (nums[j]+nums[nums.length-1]+nums[nums.length-2]+nums[nums.length-3]<target)
//                    continue;

                int sum1 = target - nums[j];
                // three sum
                for(int i = j + 1; i < nums.length - 2;i ++) {
                    if(i == j + 1 || nums[i] != nums[i - 1]) {
                        int sum = sum1 - nums[i];//be careful, different sum
                        int left = i + 1;
                        int right = nums.length - 1;
                        while(left < right) {
                            if(nums[left] + nums[right] == sum) {
                                res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                                // first ++, it is because we need to move though the while is not meet the requirement
                                // the following ++ is to complete the previous while loop
                                // e.g.     left = 1, right = 5 --> 2,4,
                                // check if (1 with 2) and (4 with 5) are duplicate
                                // if it is, check again, before check, 2,4 --> 3,3
                                while(left < right && nums[++left] == nums[left - 1]);// be careful, not left with left + 1
                                while(left < right && nums[--right] == nums[right + 1]);
                            } else if(nums[left] + nums[right] < sum) {
                                left ++;
                            } else {
                                right --;
                            }
                        }
                    }
                }
            }
        }

        return res;
    }
}


/**
 *
 */
class TwoSumInterface {
    //method 1: optimzie the time
    //method 2: optimize the space complexity
    //    1 3 5
    //
    HashMap<Integer, Integer> map;
    /** Initialize your data structure here. */
    public TwoSumInterface() {
        map = new HashMap<>();

    }

    // method 1(not good):
    //             intuitive: add to arraylist,  O(1)
    //             find use two pointers(sort first):  time O(nlogn) space: O(n)
    //             or  use additional map to find: time O(n), space   O(n)


    // method 2: optimized add
    // if directly use map to store:
    //            add to map: time O(1)
    //            find: time O(n), sapce O(1)
    //
    // method 3: optimized find
    // if want to optimize find time
    // store sum also: 1 + 2 + 3 + ..n = O(n^2),  time O(n^2)   sapce O(n^2)
    //             find: O(1)

    // method 4(not good compared to method 2): heap:
    //           add: time: long(n),
    //          find: two pointers: O(n)
    /** Add the number to an internal data structure.. */
    public void add(int number) {
        Integer frq = map.get(number);
        if(frq == null) {
            map.put(number, 1);
        } else {
            map.put(number, 2);
        }

    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int num1 = entry.getKey();
            int num2 = value - num1;
            if(map.containsKey(num2)) {
                if(num1 != num2 || map.get(num1) == 2) {
                    return true;
                }
            }
        }
        return false;
    }
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */
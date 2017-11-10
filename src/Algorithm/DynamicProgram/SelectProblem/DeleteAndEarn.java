package Algorithm.DynamicProgram.SelectProblem;

import java.util.*;

/**
 * Created by program on 10/20/2017.
 */
public class DeleteAndEarn {
    // step 1: TreeSet(default sorted by key): key: number      value: count
    // step 2: dp: selected and not selected
    //              case 1: not continuous: just plus the previous max one: selected
    //              case 2: continuous:
    //                      if select cur one, cannot select the previous one
    //                      if not select this one, then choose the max of previous selected and not selected
    //Time complexity: create treemap: O(nlogn) +  dp O(n)    =O(nlogn)
    //Space: O(n)
    public int dp(int [] array) {
        if(array == null || array.length == 0) {
            return 0;
        }

        // count
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int i = 0; i < array.length; i ++) {
            Integer count = map.get(array[i]);
            if(count == null) {
                map.put(array[i], 1);
            } else {
                map.put(array[i], count + 1);
            }
        }
        //dp
        int selected = 0;
        int notSelected = 0;
        int preKey = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int curKey = entry.getKey();
            if(curKey - preKey > 1) {
                // if not continous, selected is the better choice
                selected = curKey * map.get(curKey) + selected;
            } else {
                int tmp = selected;
                //selected i --> cannot select i - 1
                selected = curKey * map.get(curKey) + notSelected;
                // not selected: --> you can select the i - 1
                notSelected = tmp;
            }
            preKey = curKey;
        }
        return Math.max(selected, notSelected);
//        // sort
//        int n = map.size();
//        int [] nums = new int[n];
//        int i = 0;
//        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
//            nums[i++] = entry.getKey();
//        }
//        Arrays.sort(nums);
//        // dp
//        int selected = nums[0] * map.get(nums[0]);
//        int notSelected = 0;
//        for(int j = 1; j < nums.length; j ++) {
//            if(nums[j] - nums[j - 1] > 1) {
//                // if not continous, selected is the better choice
//                selected = nums[j] * map.get(nums[j]) + selected;
//            } else {
//                int tmp = selected;
//                //selected i --> cannot select i - 1
//                selected = nums[j] * map.get(nums[j]) + notSelected;
//                // not selected: --> you can select the i - 1
//                notSelected = tmp;
//            }
//        }
//        return Math.max(selected, notSelected);
    }
    // 1 2 2 4 = 8
    // 1 2 2 3 = 4
    // 1 2 4   = 6
    // 1 2 3   = 4
    // []
    // 1
    // 1 2
    // 1 3
    //
    public static void main(String [] args) {
        DeleteAndEarn sol = new DeleteAndEarn();
        System.out.println(sol.dp(new int[]{1,2,3}) + " expect: 4");
        System.out.println(sol.dp(new int[]{1,2,4}) + " expect: 6");
        System.out.println(sol.dp(new int[]{2,3,3,3,4}) + " expect: 9");
        System.out.println(sol.dp(new int[]{1,2}) + " expect: 2");
        System.out.println(sol.dp(new int[]{1,3}));

        System.out.println(sol.dp(new int[]{1}));
        System.out.println(sol.dp(new int[]{1,2,3}));
        SortedMap<Integer, Integer> sort = new TreeMap<>();
        sort.put(1,1);
        sort.put(0,2);
        System.out.println();

    }
}

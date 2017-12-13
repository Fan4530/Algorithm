package Algorithm.Interview.Linkedin;

import java.util.*;

/**
 * Created by program on 12/7/2017.
 */
public class PermutationII {
    public static void main(String [] args) {
        permuteUnique(new int[]{1, 1, 2});
    }
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return res;
        }
        List<Integer> cur = new ArrayList<>();

        for(int i = 0; i < nums.length; i ++) {
            cur.add(nums[i]);
        }
        dfs(res, cur, 0);
        return res;
    }
    private static void dfs(List<List<Integer>> res, List<Integer> cur, int index) {
        if(index == cur.size()) {
            res.add(new ArrayList<>(cur));
            return;
        }
        Set<Integer> set = new HashSet<>();
        for(int i = index; i < cur.size(); i ++) {
            if(set.add(i)) {
                swap(cur, i, index);
                dfs(res, cur, index + 1);
                swap(cur, i, index);
            }
        }
    }
    private static void swap(List<Integer> cur, int left, int right) {
        int tmp = cur.get(left);
        cur.set(left, cur.get(right));
        cur.set(right, tmp);
    }
//    public List<List<Integer>> permuteUnique(int[] nums) {
//        int [] a = new int[]{1, 1};
//        List<Integer> res = Arrays.asList(nums);
//        //boolean nextPermutation(nums);    1 1 2 -- > 1 2 1
//        //first 1 1 2
//        List<Integer> cur = new ArrayList<>();
//        if(nums == null || nums.length == 0) {
//            return res;
//        }
//        boolean add = res.add(new ArrayList<Integer>(nums));
//        while(nextPermutation(nums)) {
//            res.add(new ArrayList<Integer>(Arrays.asList(nums)));
//        }
//        return res;
//    }
    // 1 2 3 4 0 -1  from the end, find the first one that is array[i - 1] < array[i]
    //                             record = i - 1  and reverse(i, array.length - 1)  --> if record -1, then return false
    //                             find the first one that is larger than i - 1, say i, and then do  swap(array, record, i)
    private boolean nextPermutation(int [] array) {
        int record = -1;
        for(int i = array.length - 1; i > 0; i --) {
            if(array[i - 1] < array[i]) {
                record = i - 1;
                break;
            }
        }
        if(record == -1) {
            return false;
        }
        reverse(array, record + 1);
        for(int i = record; i < array.length; i ++) {
            if(array[i] > array[record]) {
                swap(array, record, i);
            }
        }
        return true;
    }
    private void reverse(int [] array, int start) {
        int end = array.length - 1;
        while(start <= end) {
            swap(array, start ++, end--);
        }
    }
    private void swap(int [] array, int l, int r) {
        int tmp = array[l];
        array[l] = array[r];
        array[r] = tmp;
    }
}

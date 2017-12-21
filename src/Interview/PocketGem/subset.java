package Interview.PocketGem;
import java.util.*;
public class subset {
    public void helper(int[] nums, int start, List<Integer> cur_path, List<List<Integer>> res) {
        res.add(new LinkedList<Integer>(cur_path));
        
        for (int i = start; i < nums.length; i++) {
            cur_path.add(nums[i]);
            helper(nums, i+1, cur_path, res);
            cur_path.remove(cur_path.size()- 1);
        }
    }
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> path = new LinkedList<>();
        helper(nums, 0, path, res);
        return res;
    }
    
}

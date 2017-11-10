package Interview.PocketGem;
import java.util.*;

public class partion_nums {

	public static int partion(int[] nums, int target) {
		int count = 0;
		for(int num : nums) {
			if(num == target)
				count++;
		}
		int cur_count = 0;
		for(int i = 0; i < nums.length; i++) {
			int cur_right = nums[i] == target ? nums.length-i-1-(count-cur_count-1) : nums.length-i-1-(count-cur_count);
			if(cur_right == cur_count)
				return i;
			cur_count = nums[i] == target ? cur_count+1 : cur_count;
		}
		return -1;
	}
    public int partion_2(int[] nums, int target)
    {
        LinkedList<Integer> left = new LinkedList<>();
        LinkedList<Integer> right = new LinkedList<>();
        for (int i = 0, j = nums.length - 1; i < nums.length-1 && j > 0 && i <= j; i++, j--)
        {
            //Set i and j start at the first valid value
        	if(i == j) {
        		if(left.size() == right.size() && left.size() != 0)
        			return i;
        		else
        			return -1;
        	}
            while (nums[i] != target && i < nums.length-1) i++;
            while (nums[j] == target && j > 0) j--;
            //Add into list when meet valid cases
            if (nums[i] == target)
            {
                left.add(i);
            }
            if (nums[j] != target)
            {
                right.add(j);
            }
        }
        //Can't found that index.
        return -1;
    }
	public static void main(String[] args) {
		int[] nums = {1,1,3,3};
		partion_nums p = new partion_nums();
		System.out.println(p.partion(nums, 1));
		System.out.println(p.partion_2(nums, 1));
	}
}

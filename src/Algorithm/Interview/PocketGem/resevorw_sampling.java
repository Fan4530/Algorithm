package Algorithm.Interview.PocketGem;
import java.util.*;
public class resevorw_sampling {

//	public static int rand_range(int target) {
//		Random rand = new Random();
//		int x = rand.nextInt(20);
//		while(x > target) {
//			x = rand.nextInt(20);
//		}
//		return x;
//	}

	//Randomly pick k elemtns from a list, use time complexity O(1)
	public static void swap(int[] nums, int x, int y) {
		int temp = nums[x];
		nums[x] = nums[y];
		nums[y] = temp;
	}
	
	public static int[] pick(int[] nums, int k) {
		int[] res = new int[k];
		int size = nums.length;
		for(int i = 0; i < k; i++) {
			int x = (int)(Math.random() * size);
			res[i] = nums[x];
			swap(nums, x, size-1);
			size--;
		}
		return res;
	}
	
	// pick K ele from N where N is very big that can not put into the memory one time
	// first step is to fill it with first k eles in the N
	// then when comes to a new number which index is i, we random a number j = random(0,i);
	// if j fills in the 0 ----- k-1 then we replace the splot with this number
	
	

	public static void main(String[] args) {
		resevorw_sampling r = new resevorw_sampling();
		int[] nums = {1,3,5,7,9,10,20,30};
		int[] res = r.pick(nums, 3);
		System.out.println(Arrays.toString(res));
	}
}

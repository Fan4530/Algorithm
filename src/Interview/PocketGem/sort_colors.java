package Interview.PocketGem;

import java.util.*;

public class sort_colors {
	
	public enum Colors {
		Red(0), Green(1);
		
		public int val = 0;
		Colors(int val) {
			this.val = val;
		}
	}
	
	static int left, right;
	
	public static void sort(int[] nums, int t1, int t2) {
		if(nums == null || nums.length == 0)
			return;
//		int left = 0, right = nums.length-1;
		int index = 0;
		while(index <= right) {
			if(nums[index] == t1) {
				swap(nums, index++, left++);
			} else if(nums[index] == t2) {
				swap(nums, index, right--);
			} else 
				index++;
		}
	}


	// when meet 2, swap,
	// when meet 1 or 0, index ++,
	// when meet 0, index ++
	// all the elements before l, not include l, is 0
	// all the elements between l and i(not include i) is 1
	// all the elements after r(not include r) are 2
	// others are not determined
	// 0 0 0 0 1 1 1 X X X X 2 2 2
	//         l     i     r
	// after swap, left must be 0
	// after swap, right must be 2
	//
	public static void sort_3_colors(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int left = 0, right = nums.length - 1;
        int index = 0;
        while (index <= right) {
            if (nums[index] == 2) {
                swap(nums, index, right);
				//System.out.println(nums[right]);
                right--;// cannot let idx ++, since I am not sure what the new element is, 2 1 2 2


			} else if (nums[index] == 0) {
            	//System.out.println(nums[left]);// here it can be 0 or 1
				//1: any general case
				//0: initial
                swap(nums, index, left);
                //System.out.println(nums[left]);// can only be zero
                left++;
                index++; // must have this one,  case  0 1 2, if it swaps with itself
				//System.out.println(nums[left]);// can only be 1 or 2, it is when left meet 1 or 2. and after swap, and plus plus
            } else {
				//System.out.println(nums[left]);
				//System.out.println(nums[index]);//here index must be 1
				index++;
				//always
            }
        }

        // 0    1     2
		//      r
		//
	}
	public static void swap(int[] nums, int x, int y) {
		int temp = nums[x];
		nums[x] = nums[y];
		nums[y] = temp;
	}
	
	public static void sort_4_colors(int[] nums) {
		left = 0;
		right = nums.length-1;
		sort(nums, 0, 3);
		sort(nums, 1, 2);
	}

	
	public static void sort_Pixels(Pixel[] nums) {
		Arrays.sort(nums, new Comparator<Pixel>() {
			public int compare(Pixel e1, Pixel e2) {
				return Integer.compare(e1.val, e2.val);
			} 
		});
		
	}
	
	public static void sort_enum(Colors[] nums) {
		Arrays.sort(nums, new Comparator<Colors>() {
			public int compare(Colors e1, Colors e2) {
				return Integer.compare(e1.val, e2.val);
			}
		});
	}
	
	public static void main(String[] args) {
		sort_colors sc = new sort_colors();
//		int[] nums = {0,2,1,3,3,3,3,1,1,1,1,2,1,1,1,2,2,0,0,0};
//		sc.sort_4_colors(nums);
//		System.out.println(Arrays.toString(nums));


		int[] nums1 = {2,2,2,2,2,1,1,1,1,1, 0,0,0,0,0,0};
		sc.sort_3_colors(nums1);


//		Colors[] nums2 = {Colors.Red, Colors.Green, Colors.Red};
//		System.out.println(Arrays.toString(nums1));
//		sc.sort_enum(nums2);
//		System.out.println(Arrays.toString(nums2));
	}
	
	public class Pixel {
		int val;
		Pixel(int val) {
			this.val = val;
		}
	}
}

package Interview.Linkedin;

public class BinarySearch {
//	// 4 5 6 7 0 1 2    4
//       l           r
//       if mid >= left, left is sorted,
//       	 we need find if the target is in the sorted part,
//       if yes, go the sorted part,
//       if no, go to the unsorted part
//	// 1         0                                        check 1
//       left      right                                    check 0: left = mid + 1
//
//	// 0 1 2 3 4 5 6

	// 1 2 3 4 5 6 0
	//assumption: no duplicate
	public int search(int [] array, int target) {
		if(array == null || array.length == 0) {
			return -1;
		}
		int left = 0;
		int right = array.length - 1;
		while(left <= right) {
			int mid = (right - left) / 2 + left;
			if(array[mid] == target) {
				return mid;
			} else if(array[mid] >= array[left]) {// must be >=, or like 1 2, it wi
				if(target >= array[left] && target <= array[mid]) {// target must not mid, possible be left
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			} else {
				if(target >= array[mid] && target <= array[right]) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
		}
		return -1;
	}
}
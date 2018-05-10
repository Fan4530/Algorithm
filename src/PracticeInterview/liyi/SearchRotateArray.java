package Practical.liyi;

/**
 * Created by program on 11/7/2017.
 */
public class SearchRotateArray {
    public static void main(String [] args) {
        SearchRotateArray sol = new SearchRotateArray();
        System.out.println(sol.search(new int[]{1,2,3,4,0}, 2));
    }
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

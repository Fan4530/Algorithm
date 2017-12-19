package Algorithm.Interview.Uber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by program on 12/18/2017.
 */
public class KClosestArray {
    public static List<Integer> kClose(int target, int k, int [] array) {

        //assume k <= array.length
        if(k == 0 || array == null || array.length == 0) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        int l = 0;
        int r = array.length - 1;
        while(r - l + 1 > k) {
            int diffL = Math.abs(array[l] - target);
            int diffR = Math.abs(array[r] - target);
            if(diffL >= diffR) {
                l ++;
            } else if(diffL < diffR) {
                r --;
            }
        }
        for(int i = l; i <= r; i ++) {
            res.add(array[i]);
            System.out.print(array[i] + " ");
        }
        return res;
    }

    public static void main(String [] args) {
        kClose(5, 5, new int[]{1,2,3,4,5,6,7,8,9,10});
    }
}

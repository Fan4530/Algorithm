package Practical.Jingchi;

/**
 * Created by program on 11/27/2017.
 */
public class Square {
    public static double sqrt(double target, double error) {
        if(target < 0) {
            return -1;
        } else if(target <= 1) {
            return binarySearch(0, 1, target, error);
        } else {
            return binarySearch(0, target, target, error);
        }

    }
    private static double binarySearch(double left, double right, double target, double error) {
        while(true) {
            double mid = left + (right - left) / 2;
            if(target - error <= mid * mid && target + error >= mid * mid) {     //[target - error, target + error]
                return mid;
            } else if(target > mid * mid) {
               left = mid;
            } else {
                right = mid;
            }
        }
    }
    public static void main(String [] args) {
        System.out.println(sqrt(0.04, 0.0001));    }
}

package Interview.Linkedin;

/**
 * Created by program on 12/6/2017.
 */
public class Pow {
    public static double myPow(double x, int n) {
        // n can be any integer?
        // 0 ^ 0 = 1
        // 0 ^ n = 0
        // x ^ 0 = 1
        // x ^ 1 = x
        if(x == 0) {
            return 0;
        }
        if(n < 0) {
            return 1.0 / helper(x, n);
        } else {
            return helper(x, n);
        }

    }
    private static double helper(double x, int n) {// n = -2

        if(n == 1 || n == -1) {
            return x;
        }
        double half = myPow(x, n / 2); //n == -1   half = x
        if(n % 2 == 1 || n % 2 == -1) {
            return half * half * x;
        } else {
            return half * half;
        }
    }
    public static void main(String [] args) {
        myPow(2.0, -2);
    }
}

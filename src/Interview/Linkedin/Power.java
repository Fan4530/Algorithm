package Interview.Linkedin;

/**
 * Created by program on 11/15/2017.
 */
public class Power {
    // case n < 0       2 ^ -1 = 1 / 2, so    1 / myPowe1(x, - n)
    //      n > 0

    // corner case

    // n is odd
    public double myPow(double x, int n) {
        if(n < 0) {
            return 1 / myPow1(x, - n);
        } else {
            return myPow1(x, n);
        }
    }
    private double myPow1(double x, int n) {
        // case x = 0 --> 0
        // n = 0 --> 1
        // n == 1  or n == - 1
        //     return x it self
        //
        if(x == 0) {
            return 0;
        }
        if(n == 0) {
            return 1;
        }
        if(n == 1 || n == -1) {
            return x;// 3               1
        }

        double half = myPow1(x, n / 2) ;
        if(n % 2 == 1) {
            return half * x * half;
        } else {
            return half * half;
        }
    }
}

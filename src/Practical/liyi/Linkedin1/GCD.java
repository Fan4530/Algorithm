package Practical.liyi.Linkedin1;

/**
 * Created by program on 1/11/2018.
 */
public class GCD {
    public static int GCD(int a, int b) {
        if(a == 0) {
            return b;
        }
        return GCD(b % a, a);
    }
    public static void main(String [] args) {
        System.out.println(GCD(10, 15));
        System.out.println(GCD(30, 15));
    }
}

package Interview.Linkedin.Linkedin1;

/**
 * Created by program on 1/12/2018.
 */
public class nextAlter {
    public static void main(String [] args) {
        int n = 0;
        for(int i = 0; i < 10; i ++) {
            n = next(n);
            System.out.println(Integer.toBinaryString(n));
        }

    }
    private static int next(int n) {
        int i = 0;
        while(i ++ < 32) {
            int x1 = (1 << i) & n;
            int x2 = (1 << (i - 1)) & n;
            if(x1 == 0 &&  x2 == 0) {
                break;
            }
        }
        int next = (1 << --i) | n;
        while(i -- > 0) {
            next = (~(1 << i)) & next;

        }
        return next;
    }
}

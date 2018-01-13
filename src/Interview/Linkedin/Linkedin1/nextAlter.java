package Interview.Linkedin.Linkedin1;

/**
 * Created by program on 1/12/2018.
 */
public class nextAlter {
//    第二个是求下一个二进制为0，1间隔的数，如10（1010），16（10000），17（10001），18（10010），20（10100），要过大数据，考察bit操作

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

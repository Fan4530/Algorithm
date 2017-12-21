package Interview.PocketGem;

/**
 * Created by program on 11/10/2017.
 */
public class BitOperation {
    public int choose(int one, int two, int choice) {
        // -1  -->  11111111111111
        // -1 ^ x , x will be flipped
        return (one & choice) | ((choice ^ -1) & two);
    }
    public static void main(String [] agrs) {
        BitOperation sol = new BitOperation();
        // 11   11   10
        //  _   _
        // 11   10   10

        // 101  011  101
        System.out.println(sol.choose(5,3,5));
    }
}

package Algorithm.Interview.Linkedin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by program on 11/13/2017.
 */
public class IntegerFactorial {
    public List<List<Integer>> factorial(int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        helper(res, cur, n, n);
        return res;
    }
    // assumption: 12 * 1 ??   or 12 ?
    // we need an upper
    //  12 --> 12/3  = 4       list: 3
    //  we need the min of (i and n / i) so that there will not be duplicate number
    //  upper is different from n,    if we directly use min(i and n / i) as the n, the n will change and the
    // sum of product will not be 12 anymore
    // so we need another parameter upper.
    private void helper(List<List<Integer>> res, List<Integer> cur, int n, int upper) {
        if(n == 1) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for(int i = upper; i >= 2; i --) {//we don't need 1
            if(n % i == 0) {
                cur.add(i);
                helper(res, cur, n / i, Math.min(n / i, i));
                cur.remove(cur.size() - 1);
            }
        }
    }
    public static void main(String [] args) {
        IntegerFactorial sol = new IntegerFactorial();
        System.out.println(sol.factorial(12));
    }
}

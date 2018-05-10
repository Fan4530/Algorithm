package Practical.liyi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by program on 11/15/2017.
 */
public class RepeatedDNA {
    // AAAAACCCCC AAAAACCCCCCAAAAAGGGTTT
    // AAAAACCCCC AAAAACCCCC --> repeated, so AAAAACCCC is one of the result, use set to determine
    // is my res can be repeated
    public List<String> findRepeatedDnaSequences(String s) {
        HashSet set = new HashSet();
        HashSet res = new HashSet();
        for(int i = 0; i < s.length() - 9; i ++) {// be careful minus 9,  AAAAAAAAAA LEN = 10
            String sa = s.substring(i, i + 10);
            if(!set.add(sa)) {
                res.add(sa);
            }
        }
        return new ArrayList(res);
    }
}

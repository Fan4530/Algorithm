package Algorithm.DynamicProgram;

import java.util.Collections;
import java.util.HashSet;

/**
 * Created by program on 10/13/2017.
 */
public class DictionaryWordI {
    public boolean canBreak(String input, String[] dict) {
        // Write your solution here.
        HashSet<String> set = new HashSet<>();
        Collections.addAll(set, dict);
        int n = input.length();
        boolean [][] res = new boolean[n][n];
        // len
        for(int l = 0; l < n; l ++) {
            //start
            for(int s = 0; s + l < n; s ++) {
                //possibilities
                res[s][s + l] = set.contains(input.substring(s, s + l + 1));
                for(int p = 0; p + 1 <= l; p ++) {
                    if(res[s][s + l]) {
                        break;
                    }
                    res[s][s + l] |= res[s][s + p] && res[s + p + 1][s + l];
                }
            }
        }
        return res[0][n - 1];
    }
    public static void main(String [] args) {
        DictionaryWordI sol = new DictionaryWordI();
        String [] dict = new String[]{"rob", "cat"};

    }
}

package Algorithm.ClassicalProblemPracticeMore.BackTracking;


import java.util.*;

/**
 * Created by program on 12/19/2017.
 */
public class LetterCombinationOfNumber {
    //memorilization 的backtracking

    private String [] mapping = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    HashMap<String, List<String>> map = new HashMap<>();
    public List<String> letterCombinations(String digits) {
        if(digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        return dfs(digits);
    }
    private List<String> dfs(String digits) {
        if(digits.equals("")) {
            return Arrays.asList("");
        }
        if(map.containsKey(digits)) {
            return map.get(digits);
        }
        List<String> res = new ArrayList<>();
        String set = mapping[(digits.charAt(0) - '0')];
        for(int i = 0; i < set.length(); i++) {
            List<String> nextRes = dfs(digits.substring(1, digits.length()));
            for(String s : nextRes) {
                res.add(set.charAt(i) + s);
            }
        }
        map.put(digits, res);
        return res;
    }

}

package Algorithm.Interview.PocketGem;

import java.util.*;
public class WordBreak12 {
    // leetcode
    // dp[0] = true;
    // dp[1] = l, false,       dp[0] && l
    // dp[2] = le, false,      dp[0] && le  not true,   dp[1] e is not true
    // dp[i] |= dp[j] && susbtring(j, i)  j >= 0, j < i
    // dp[i] means the the substring from index 0 to i, if it can be divided into wordDict
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>();
        set.addAll(wordDict);
        int n = s.length();
        boolean [] dp = new boolean[n + 1];
        dp[0] = true;
        for(int i = 1; i <= n; i ++) {
            for(int j = 0; j < i; j ++) {
                dp[i] |= dp[j] && set.contains(s.substring(j, i));
                if(dp[i]) {
                    break;
                }
            }
        }
        return dp[n];
    }


    public static void main(String [] args) {
        WordBreak12 sol = new WordBreak12();
       // System.out.println(sol.wordBreak());
    }


    // word break 2: print all possible solutions
    // DFS: not the most optimized
    public List<String> wordBreak2(String s, List<String> wordDict) {
//         catsanddog


//         base case: idx == s.length();
//             add to res list
//                 return
//      idx = 0   i = 1 c   substring(idx, idx + i);
//                i = 2 ca
//                i = 3 cat  yes, add to stringbuilder.append( ), helper(i + 1)
//                 idx = 3
//         for(i = idx, s.length())
//             helper(idx + i)
        HashSet<String> set = new HashSet<>();
        set.addAll(wordDict);
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        helper(res, sb, 0, s, set);
        return res;
    }
    private void helper(List<String> res, StringBuilder sb, int idx, String s, HashSet<String> set) {
        //base case
        if(idx == s.length()) {
            sb.deleteCharAt(sb.length() - 1);
            res.add(sb.toString());
            return;
        }
        // i means the length of the substring
        for(int i = 1; i + idx < s.length(); i ++) {
            String cur = s.substring(idx, idx + i);
            if(set.contains(cur)) {
                int len = sb.length();
                sb.append(cur).append(" ");
                helper(res, sb, idx + i, s, set);
                sb.setLength(len);
            }
        }
    }
}

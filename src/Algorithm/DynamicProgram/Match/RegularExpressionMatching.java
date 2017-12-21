package Algorithm.DynamicProgram.Match;

/**
 * Created by program on 12/19/2017.
 */
import java.util.*;
public class RegularExpressionMatching {
    // This is the text editor interface.
// Anything you type or change here will be seen by the other person in real time.
    // 最容易犯的错误
    // 垫一层空的时候：
    //      s.charAt()  忘记要剪掉1
    //      for loop忘记循环到 <=
    // NPE: 解决办法： 写完以后, 所有都加限制条件，要细致，不能只是加一个大条件，那样会排除掉不该排除的东西！然后简化
        public static void main(String [] args) {
            RegularExpressionMatching sol = new RegularExpressionMatching();
            // System.out.println(sol.groupAnagrams(new String[]{"aab", "aba"}));
            // System.out.println(sol.replaceWords(Arrays.asList("go", "ma"), "I am a good man"));
            System.out.println(sol.isMatch("abc", "abc"));
        }
        public boolean isMatch(String s, String p) {
            if(s == null || p == null) {
                return true;
            }
            boolean [][] dp = new boolean[p.length() + 1][s.length() + 1];
            dp[0][0] = true;
            for(int i = 1; i <= p.length(); i ++) {
                for(int j = 0; j <= s.length(); j ++) {
                    if((j > 0) && (s.charAt(j - 1) == p.charAt(i - 1) || p.charAt(i - 1) == '.')) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                    if(p.charAt(i - 1) == '*') {
                        dp[i][j] = dp[i - 1][j] || (i > 1 ? dp[i - 2][j] : false);
                        if(i > 1 && j > 0 && (p.charAt(i - 2) == '.' || p.charAt(i - 2) == s.charAt(j - 1))) {
                            dp[i][j] |= dp[i - 1][j - 1] || dp[i][j - 1];
                        }
                    }
                }
            }
            return dp[p.length()][s.length()];
        }
}

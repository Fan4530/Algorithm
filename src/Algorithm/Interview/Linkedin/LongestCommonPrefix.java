package Algorithm.Interview.Linkedin;

/**
 * Created by program on 12/8/2017.
 */
public class LongestCommonPrefix {
    public static String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length <= 0 || strs[0].length() == 0) {
            return "";
        }
        String pre = strs[0];
        int idx = 0;
        for(; idx < pre.length(); idx++) {
            char c = pre.charAt(idx);
            for(int row = 1; row < strs.length; row ++) {
                if(idx >= strs[row].length() || strs[row].charAt(idx) != c) {
                    return pre.substring(0, idx);
                }
            }
        }
        return pre.substring(0, idx);
    }
    public static void main(String [] args) {
        System.out.println(longestCommonPrefix(new String[]{"abab","aba", "a"}));
    }
}

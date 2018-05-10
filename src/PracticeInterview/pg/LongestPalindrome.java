package Practical.pg;

public class LongestPalindrome {
    //   a a               a  c
    //   t f(len + 2)      f  t
    //  'a'
    //  personality: pailindrom will appear as pair
    public int longestPalindrome(String s) {
        char [] array = s.toCharArray();
        boolean[] map = new boolean[128];
        int len = 0;
        for(char c : array) {
            map[c] = !map[c];
            if(!map[c]) {
                len += 2;
            }
        }
        return len < array.length ? len + 1 : len;
    }
}

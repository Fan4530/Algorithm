package Practical.fineb;

/**
 * Created by program on 12/20/2017.
 */
public class PatternOrder {
    public boolean checkFollowPattern(String input, String pattern) {
        //step 1: use array to mark the order of the pattern
        //step 2: check each of the input,
        //          if the the order of last char is larger than the current char, return false;
        //          else   update last char
        //step 3: return true
        if(pattern == null || pattern.length() == 0) {
            return true;
        }
        int [] res = new int[256];
        for(int i = 0; i < pattern.length(); i ++) {
            res[pattern.charAt(i)] = i;
        }
        int last = pattern.charAt(0);
        for(int i = 1; i < input.length(); i ++) {
            if(res[last] > res[input.charAt(i)]) {
                return false;
            }
            last = input.charAt(i);
        }
        return true;
    }
    public static void main(String [] args) {
        PatternOrder sol = new PatternOrder();
        System.out.println(sol.checkFollowPattern("programming", "gra"));
    }
}

package Interview.Linkedin;

import java.util.HashMap;

/**
 * Created by program on 11/15/2017.
 */
class MinimumSubstring {
    String allAnagrams(String s, String t) {
        // Write your solution here.
        //assumption: both s and l are not null, s is not empty
        // corner case: l < s, l is empty, return result
        if(s.length() < t.length() || s.length() == 0) {
            return "";
        }
        // create an hashMap
        HashMap<Character, Integer> sliding = getMap(t);
        int min = Integer.MAX_VALUE;
        int globalLeft = 0;
        int f = 0;
        int slow = 0;
        int match = 0;
        while(f < s.length()) {
            char tmp = s.charAt(f);
            Integer frq = sliding.get(tmp);
            if(frq != null) {
                sliding.put(tmp, frq - 1);
                if(frq == 1) {
                    match ++;
                }
            }

            if(match == sliding.size() && f + 1 - slow < min) {
                globalLeft = slow;
                min = f - slow + 1;
            }
            // s.length = 1
            if(f >= t.length()) {
                tmp = s.charAt(slow);
                frq = sliding.get(tmp);
                if(frq != null) {
                    sliding.put(tmp, frq + 1);
                    if(frq == 0) {
                        match --;
                    }
                }

                slow ++;
            }
            f ++;


        }
        return min == Integer.MAX_VALUE ? "" : s.substring(globalLeft, globalLeft + min);
    }
    private HashMap<Character, Integer> getMap(String s) {
        HashMap<Character, Integer> sliding = new HashMap<Character, Integer>();
        for(int i = 0; i < s.length(); i++) {
            char tmp = s.charAt(i);
            Integer frq = sliding.get(tmp);
            if(frq == null) {
                sliding.put(tmp, 1);
            } else {
                sliding.put(tmp, frq + 1);
            }
        }
        return sliding;
    }
    public static void main(String [] args) {
        MinimumSubstring sol = new MinimumSubstring();
        System.out.println(sol.allAnagrams("abc", "ac"));
    }

}

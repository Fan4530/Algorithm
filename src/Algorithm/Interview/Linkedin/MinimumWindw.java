package Algorithm.Interview.Linkedin;

import java.util.HashMap;

/**
 * Created by program on 12/8/2017.
 */
public class MinimumWindw {
    public static String minWindow(String s, String t) {
        if (s == null || s.length() == 0 ||
                t == null || t.length() == 0) {
            return "";
        }
        HashMap<Character, Integer> map = getInitMap(t);
        int res = Integer.MAX_VALUE;
        String minStr = "";


        int i = 0;
        int j = 0;
        int left = 0;
        int match = 0;
        while(i < s.length()) {
            while (i < s.length() && match != map.size()) {
                char c = s.charAt(i);
                Integer frq = map.get(c);
                if(frq != null) {
                    map.put(c, frq - 1);
                    if(frq == 1) {
                        match ++;
                    }
                }
                i ++;
            }// if here is matched : rightBound = i - 1,
            //if is not matched, the following while loop will end
            while(j < s.length() && match == map.size()) {
                if(res > i - j) {//here is still match
                    left = j;
                    res = i - j;
                }
                char c = s.charAt(j);
                Integer frq = map.get(c);
                if(frq != null) {
                    map.put(c, frq + 1);
                    if(frq == 0) {
                        match --;
                    }
                }
                j ++;// here j is not in the window, j - 1 is
            }
        }
        return res == Integer.MAX_VALUE ? " " : s.substring(left, left + res);
    }


    public static void main(String [] args) {
        System.out.println(minWindow1("abb", "bb"));
    }
    public static HashMap<Character, Integer> getInitMap(String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < t.length(); i ++) {
            char c = t.charAt(i);
            Integer frq = map.get(c);
            if(frq == null) {
                map.put(c, 1);
            } else {
                map.put(c, frq + 1);
            }
        }
        return map;
    }

    public static String minWindow1(String s, String t) {
        int[] hash = new int[256];
        char[] S = s.toCharArray();
        char[] T = t.toCharArray();
        for (int i = 0; i < T.length; i++) {
            hash[T[i]]++;
        }
        int count = T.length;
        int begin = 0;
        int end = 0;

        int head = 0;
        int min = Integer.MAX_VALUE;
        while (end < S.length) {
            if (hash[S[end++]]-- > 0) {
                count--;
            }
            // if we enter the while loop
            // the end is not in the window, rightBound = end - 1
            // 比如说count = 1， end是window的右边界， 所以程序能进入了上面的循环，但是进入之后end就变成右边界+ 1了， 然后count --
            while (count == 0) {
                // 为什么是end - begin而不是end - begin + 1呢，就如上面所说，end是右边界 + 1， 而不是右边界
                // 但是begin还是左边界，只有结束while的时候，begin才是左边界 + 1
                if (min > end - begin) {
                    min = end - begin;
                    head = begin;
                }
                if (hash[S[begin++]]++ == 0) {//begin+ 1， 变成左边界+ 1，
                    count++;
                }
            }
        }
        return min == Integer.MAX_VALUE ? "" : s.substring(head, head + min);
    }
}

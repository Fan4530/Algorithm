package Algorithm.Interview.Linkedin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by program on 11/8/2017.
 */
public class Isomophic {
    // {‘fff’,‘abc’,‘foo’,‘haa’,‘www’,‘vvv’}-> { {‘fff’,www’,‘vvv’} , {‘haa’,‘foo’} , {‘abc’} }

    public List<List<String>> isomophicFollowUp(String [] list) {
        HashMap<String, List<String>> map = new HashMap<>();
        for(int i = 0; i < list.length; i ++) {
            String cur = list[i];
            String pattern = getPattern(cur);
            List<String> existedRes = map.get(pattern);
            if(existedRes == null) {
                existedRes = new ArrayList<>();
                map.put(pattern, existedRes);
            }
            existedRes.add(cur);
        }
        List<List<String>> res = new ArrayList<>();
        for(Map.Entry<String, List<String>> entry : map.entrySet()) {
            res.add(entry.getValue());
        }
        return res;
    }
    // ababa --> 01010
    private String getPattern(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int type = 0;
        String res = "";
        for(int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            Integer pattern = map.get(c);
            if(pattern == null) {
                map.put(c, type);
                type ++;
            }
            res += (char)(map.get(c) + '0');
        }
        return res;
    }

    private boolean isMophic(String one, String two) {
        int [] array = new int[256];
        for(int i = 0; i < one.length(); i ++) {
            array[one.charAt(i)] += i + 1;
            array[two.charAt(i) + 256] += i + 1;
            if(array[one.charAt(i)] != array[two.charAt(i) + 256]) {
                return false;
            }
        }
        return true;
    }
    public static void main(String [] args) {
        Isomophic sol = new Isomophic();
        String [] input = new String[]{"fff","abc","foo","haa","www","vvv"};
        System.out.println(sol.isomophicFollowUp(input));
        System.out.println(sol.getPattern("ababa"));

        System.out.println(sol.isMophic("aba", "ddc"));
    }
}

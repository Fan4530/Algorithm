package Practical.liyi.Linkedin1;

import java.util.*;

/**
 * Created by program on 1/11/2018.
 */
public class ReplaceAll {
    private static String replaceAll(String A, String B, String C) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < A.length(); i ++) {
            if(i + B.length() < A.length() && A.substring(i, i + B.length()).equals(B)) {
                sb.append(C);
                i += B.length() - 1;
            } else {
                sb.append(A.charAt(i));
            }
        }
        return sb.toString();
    }
    public static void main(String [] args) {
        System.out.println(replaceAll("I love you my dear, do you love me?", "love", "hate"));
        List<String> lista = new ArrayList<>();
        List<String> listb = new ArrayList<>();
        lista.add("b");
        lista.add("bb");
        listb.add("def");
        listb.add("dfg");
        System.out.println(replaceAllII("abbc", lista, listb));
    }
//    新题: 替换substring。举个简单的例子：
//    input: String s1= "abbc", List<String>() ={"b", "bb"}, List<String>() {"def", "dfg"}
//    output: {"adefdefc", "adfgc"}
    public static List<String> replaceAllII(String s, List<String> lista, List<String> listb) {
        Set<String> res = new HashSet<>();
        HashMap<String, Set<String>> memo = new HashMap<>();
        HashMap<String, String> map = new HashMap<>();
        for(int i = 0; i < lista.size(); i ++) {
            map.put(lista.get(i), listb.get(i));
        }
        Set<String> set = helper(s, map, 0,  memo);
        set.remove(s);
        return new ArrayList<>(set);
    }
    private static Set<String> helper(String s, HashMap<String, String> map,
                                 int idx,  HashMap<String, Set<String>> memo) {
        if(idx == s.length()) {
            Set<String> thisList = new HashSet<>();
            thisList.add("");
            return thisList;
        }
        //check the remain substring has been memorized
        String remain = s.substring(idx, s.length());
        if(memo.containsKey(remain)) {
            return memo.get(remain);
        }

        Set<String> thisList = new HashSet<>();
        memo.put(remain, thisList);
        Set<String> nextList;
        for(int len = 1; idx + len <= s.length(); len ++ ) {
            String str = s.substring(idx, idx + len);
            if (map.containsKey(str)) {
                nextList = helper(s, map, idx + len, memo);
                String curStr = map.get(str);
                for(String nextStr : nextList) {
                    thisList.add(curStr + nextStr);
                }
            }

        }

        nextList = helper(s, map, idx + 1, memo);
        for(String nextStr : nextList) {
            thisList.add(s.charAt(idx) + nextStr);
        }

        return thisList;
    }
}

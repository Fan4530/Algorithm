package Practical.liyi.Linkedin1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by program on 1/12/2018.
 */
public class AllIsoMorPhic {
    public static void main(String [] agrs) {
        AllIsoMorPhic sol = new AllIsoMorPhic();
        List<String> list = new ArrayList<>();
        list.add("abb");
        list.add("cdd");
        list.add("eff");
        list.add("ghh");
        list.add("cq5");
        list.add("lpp");
        list.add("o1p");
        System.out.println(sol.group(list));
        System.out.println(Integer.parseInt("null"));
    }

    public boolean isIsomorphic(String s, String t) {
        int [] array = new int[512];
        for(int i = 0; i < s.length(); i ++) {
            array[s.charAt(i)] += i + 1;
            array[t.charAt(i) + 256] += i + 1;
            if(array[s.charAt(i)] != array[t.charAt(i) + 256]) {
                return false;
            }
        }
        return true;
    }
    //Follow up: give a list, check if all of the string are isomorphic
    private boolean allList(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (!isIsomorphic(list.get(i), list.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
    //Follow up 2: group the isomorphic
    private List<List<String>> group(List<String> list) {
        if(list == null || list.size() == 0) {
            return new ArrayList<>();
        }
        List<List<String>> res = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
            int j = 0;
            for(j = 0; j < res.size(); j ++) {
                if(isIsomorphic(res.get(j).get(0), list.get(i))) {
                    res.get(j).add(list.get(i));
                    break;
                }
            }
            if(j == res.size()) {
                List<String> newlist = new ArrayList<>();
                newlist.add(list.get(i));
                res.add(newlist);
            }
        }
        return res;
    }
}

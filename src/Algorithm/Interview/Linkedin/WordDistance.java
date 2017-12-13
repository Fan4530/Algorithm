package Algorithm.Interview.Linkedin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by program on 11/14/2017.
 */
public class WordDistance {
    // assumption: both word1 and word2 are in the list??
    //
    //      2 3
    //              1    3    3    5    2     3
    //      -1                |
    //                      meet the equal value, update idx
    //                      words[i] != words[idx] --> update,  be careful npe, idx cannot be -1
    //                      to prevent the case that two number are both word1
    //
    //
    public int shortestDistance(String [] words, String word1, String word2) {
        int idx = -1;
        int res = Integer.MAX_VALUE;
        for(int i = 0; i < words.length; i ++) {
            if(words[i].equals(word1) || words[i].equals(word2)) {
                if(idx != -1 && !words[i].equals(words[idx])) { // prevent npe
                    res = Math.min(res, i - idx);
                }
                idx = i;
            }
        }
        return res;
    }


    //
    //      2 3
    //              1    3    3    5    2     3
    //      -1                |
    //                      meet the equal value, update idx
    //                      words[i] != words[idx] --> update,  be careful npe, idx cannot be -1
    //
    //  based on
    //
    // if word1 is able to equal to word2

    public int shortestDistanceII(String [] words, String word1, String word2) {
        int idx = -1;
        int res = Integer.MAX_VALUE;
        for(int i = 0; i < words.length; i ++) {
            if(words[i].equals(word1) || words[i].equals(word2)) {
                if(idx != -1 && (!words[i].equals(words[idx]) || word1.equals(word2))) { // prevent npe
                    //              not equals cases            equals cases: not care about the previous condition!
                    res = Math.min(res, i - idx);
                }
                idx = i;
            }
        }
        return res;
    }


    //Assumption: not consider the same input
    // if word1 can equal to word2
    // then use for for loop!

    //API: optimize by store all of the index in the array
    // It seems the time complexity is not optimized, but the average time complexty will be better
    //
    //                 2        (1,3,5)
    //                 3        (2,7,9)
    //                  move the smaller one
    //
    //
    HashMap<String, List<Integer>> map;
    public WordDistance(String[] words) {
        map = new HashMap<>();
        for(int i = 0; i < words.length; i ++) {
            List<Integer> list = map.get(words[i]);
            if(list == null) {
                list = new ArrayList<>();
                map.put(words[i], list);
            }
            list.add(i);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> l1 = map.get(word1);
        List<Integer> l2 = map.get(word2);
        int i1 = 0;
        int i2 = 0;
        int min = Integer.MAX_VALUE;
        while(i1 < l1.size() && i2 < l2.size()) {
            int idx1 = l1.get(i1);
            int idx2 = l2.get(i2);
            if(idx1 < idx2) {
                min = Math.min(min, idx2 - idx1);
                i1 ++;
            } else {
                min = Math.min(min, idx1 - idx2);
                i2 ++;
            }

        }
        return min;
    }
//if  word1 and word2 are same

//    while(i1 < l1.size() && i2 < l2.size()) {
//        int idx1 = l1.get(i1);
//        int idx2 = l2.get(i2);
//        if(idx1 != idx2) {
//            min = Math.min(min, Math.abs(idx1 - idx2));
//        }
//
//        if(idx1 < idx2) {
//            i1 ++;
//        } else {
//
//            i2 ++;
//        }
//
//    }
}

package Algorithm.DFS;

import java.util.*;

/**
 * Created by program on 12/17/2017.
 */
public class WordBreakII {
    public List<String> wordBreak(String s, List<String> wordDict) {
        //Map:
        // key :  string
        // value: List: of the result
        // H A B C D
        // dic: H A BC D ABC
        // H   len = 1   进入下一层dfs   ---> map:<HABCD, <H A BC D>>              往下面一层 ↓                 <HABCD, <H A BC D>, <H ABC D>>
        // A   len = 1   进入下一层              <ABCD, <A BC D>> 往上面一层        AB 不合法跳到， ABC  进入下一层    <ABCD, <A BC D>, <ABC D>>
        // BC  len = 2   进入下一层              <BCD, <BC D>> 往上面一层                          D    进入下一层    <D, <D>>
        // D   len = 1   进入下一层              <D, <D>>往上面一层                                     返回
        // base case, return             往上面一层↑
        //step 1: base case
        //step 2: 看看是否在map里面，如果是的话，就直接返回
        //step 3: for loop 长度， 将s分成s1和s2,  s1: 要判断的东西，看看是否在dict里面，如果是， 进行下一层循环，不是的话，就跳过
        //                                       s2: 进入到下一层循环，并返回一个s2的结果，那么我们要做的就是将s1添加到所有的返回的结果上
        //step 4: 将本次结果添加到<s, list>结果上
        List<String> res = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        set.addAll(wordDict);
        HashMap<String, List<String>> map = new HashMap<>();
        return dfs(s, set, map);
    }
    private List<String> dfs(String s, Set<String> set, HashMap<String, List<String>> map) {
        if(s.equals("")) {
            List<String> res = new ArrayList<>();
            res.add("");
            return res;
        }
        if(map.containsKey(s)) {
            return map.get(s);
        }
        List<String> res = new ArrayList<>();
        for(int len = 1; len <= s.length(); len ++) {
            String s1 = s.substring(0, len);
            String s2 = s.substring(len);
            if(set.contains(s1)) {
                List<String> nextRes = dfs(s2, set, map);
                for(String item : nextRes) {
                    if(item.equals("")) {
                        res.add(s1);
                    } else {
                        res.add(s1 + " " + item);
                    }

                }
            }
        }
        map.put(s, res);
        return res;
    }
}

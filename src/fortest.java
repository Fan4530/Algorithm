import java.util.*;

/**
 * Created by program on 12/18/2017.
 */
public class fortest {
// This is the text editor interface.
// Anything you type or change here will be seen by the other person in real time.
        public static void main(String [] args) {
            fortest sol = new fortest();
//
//            System.out.println(sol.insert(1));
//            System.out.println(sol.insert(2));
//            System.out.println(sol.insert(3));
//            System.out.println(sol.insert(3));
//            System.out.println(sol.getRandom());
//            System.out.println(sol.getRandom());
//            System.out.println(sol.remove(2));
            String [] str = "".split(" ");
            System.out.println();
            HashMap<Integer, Integer> map = new HashMap<>();
            System.out.println(map.put(1,1));
            System.out.println(map.put(1,2));
            System.out.println(sol.wordBreak("abcd", Arrays.asList("abc")));
        }

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
        HashMap<String, List<String>> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        set.addAll(wordDict);
        return dfs(map, s, set);
    }
    private List<String> dfs(HashMap<String, List<String>> map, String s, HashSet<String> set) {
        //base case
        if(s.equals("")) {
            return Arrays.asList("");
        }
        // check if map contains string
        List<String> res = map.get(s);
        // if has, directly return
        if(res != null) {
            return res;
        }
        //if not create a new res, and do for loop to the len
        res = new ArrayList<>();
        for(int len = 1; len <= s.length(); len++) {// pay attention: <=!!
            String s1 = s.substring(0, len);// check in this layer
            String s2 = s.substring(len);// pass to the next layer
            if(set.contains(s1)) {
                List<String> nextRes = dfs(map, s2, set);
                // if(nextRes.size() == 0) {//不能这样写， 因为size == 0可能是base case来的， 也可能是因为for loop毫无进展， 最后return res的时候res还是空的。 这样子会造成
                //String: abcd,   dict: ab cd abc
                //  会返回一个abc， 因为c 以后都毫无进展，返回一个空list,然后abc不管三七二十一都加了进去, 就变成了只有abc。

                //     res.add(s1);
                // }
                //如果写成下面这种情况，那么这里的equals("")一定是base case来的
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

        // dog cat cat dog  -->  0 1 1 0
        // a  b b a         -->  0 1 1 0
        // HashMap<String, Integer>
        // dog 0     sb: 0 1 1 0
        // cat 1
        // split: O(n), n is the len of the str
        // match, O(k), k is the number of the words
        // O(n)

        public boolean wordPattern(String pattern, String str) {
            if(pattern == null || str == null) {
                return false;
            }
            return convert(pattern.split("")).equals(convert(str.split(" ")));
        }
        public String convert(String [] str) {
            HashMap<String, Integer> map = new HashMap<>();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < str.length; i ++) {
                Integer res = map.get(str[i]);
                if(res == null) {
                    map.put(str[i], map.size());
                }
                sb.append(res);
            }
            return sb.toString();
        }






        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        public  boolean insert(int val) {
            if(map.containsKey(val)) {
                return false;
            }
            list.add(val);
            map.put(val, map.size());
            return true;
        }
        public  int getRandom() {
            return list.get((int)(Math.random() * list.size()));
        }
        public boolean remove(int val) {
            Integer idx = map.get(val);
            if(idx == null) {
                return false;
            }
            if(idx < list.size() - 1) {
                int last = list.get(list.size() - 1);
                list.set(idx, last);
                map.put(last, idx);
            }
            list.remove(list.size() - 1);
            map.remove(val);
            return true;
            // X 5 X X X 5
            //  idx      idxLast
            // STEP 1: list.set(idx, last);
            // step 2: remove in the list
            // step 3: change in the hashmap  map.put(last, idx);
            // step 4: remove in the hash
        }


}

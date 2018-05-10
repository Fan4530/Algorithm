package Algorithm.SlidingWindows;


import java.util.*;
//题目： leetcode上没有
// 有一个list， 一个set，问set里面的元素是不是list的subarray
// 比如 list = 3 4 3 5 6 4, set = 5 6 3, return true, 因为3 5 6正好是subarray

public class ContainsSubArraylist {
    public static void main (String [] args) {
        Set<Integer> set = new HashSet<>();
        set.add(3);
        set.add(4);
        set.add(5);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(6);
        System.out.println(ContainsSubArraylist(list, set));
    }

    public static boolean ContainsSubArraylist(List<Integer> list, Set<Integer> set) {
        Map<Integer, Integer> map = new HashMap<>();
        for(Integer i : set) {
            map.put(i, map.getOrDefault(i, 1));
        }
        int size = set.size();
        int left = 0;
        int match = 0;
        for(int i = 0; i < list.size(); i ++) {
            int val = list.get(i);
            Integer frq = map.get(val);
            if(frq != null) {
                map.put(val, frq - 1);
                if(frq == 1) {
                    match ++;
                }
            }

            if(i - left >= size) {
                val = list.get(left);
                frq = map.get(val);
                if(frq != null) {
                    //
                    map.put(val, frq + 1);
                    //不用考虑从-1到0的结果，因为就算是从-1他也是满足match的，所以-1刀0是不会变化。
                    // 不用担心-1的时候我们认为match会导致错误结果, 因为如果有一个元素的count = -1，说明他至少出现了一次（1到0，0到-1），
                    // 那么肯定至少有一个set里面的元素没有出现，因为那个元素被这个出现两次元素给占领了
                    if(frq == 0) {
                        match --;
                    }
                }
            }
            if(match == size) {
                return true;
            }
        }
        return false;
        //
    }

}



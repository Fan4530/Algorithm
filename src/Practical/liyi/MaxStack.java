package Practical.liyi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by program on 11/14/2017.
 */
class MaxStack {
    List<Integer> list;
    TreeMap<Integer, List<Integer>> map;
    /** initialize your data structure here. */

    public MaxStack() {
        list = new ArrayList<>();
        map = new TreeMap<>();
    }

    public void push(int x) {
        list.add(x);
        List<Integer> arraylist = map.get(x);
        if(arraylist == null) {
            arraylist = new ArrayList<>();
            map.put(x, arraylist);
        }
        arraylist.add(list.size() - 1);
    }

    public int pop() {
        int target = list.remove(list.size() - 1);
        List<Integer> arraylist = map.get(target);
        arraylist.remove(arraylist.size() - 1);
        if(arraylist.size() == 0) {
            map.remove(target);
        }
        return target;
    }

    public int top() {
        return list.get(list.size() - 1);
    }

    public int peekMax() {
        return map.lastKey();
    }

    public int popMax() {

        int max = map.lastKey();
        List<Integer> arrayList = map.get(max);

        int idx = arrayList.get(arrayList.size() - 1);
       // max = map.pollLastEntry().getKey();
        arrayList.remove(arrayList.size() - 1);
        if(arrayList.size() == 0) {
            map.remove(max);
        }
        list.remove(idx);
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            List<Integer> l = entry.getValue();
            for(int i = 0; i < l.size(); i ++) {
                if(l.get(i) > idx) {
                    l.set(i, l.get(i) - 1);
                }
            }
        }
        return max;
    }
    public static void main(String [] args) {
        MaxStack sol = new MaxStack();
        sol.push(5);
        sol.push(1);
        sol.push(5);
        sol.top();
        sol.popMax();
        sol.top();
        sol.peekMax();
        sol.pop();
        sol.top();
    }
}

package Heap;

import java.util.*;

/**
 * Created by program on 10/13/2017.
 */
public class TopKFrequency {
    public String[] topKFrequent(String[] combo, int k) {
        // Write your solution here.
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0 ; i < combo.length; i ++) {
            Integer frq = map.get(combo[i]);
            if(frq == null) {
                map.put(combo[i], 1);
            } else {
                map.put(combo[i], frq + 1);
            }
        }
        // priority queue
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(11, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                if(e1.getValue() == e2.getValue()) {
                    return 0;
                }
                return e1.getValue() < e2.getValue() ? -1 : 1;
            }
        });
        // import
        int i = 0;
        for(Map.Entry<String, Integer> e : map.entrySet()) {
            if(i < k) {
                minHeap.offer(e);
                i ++;
            } else if(!minHeap.isEmpty() && minHeap.peek().getValue() < e.getValue()) {
                minHeap.poll();
                minHeap.offer(e);
            }
        }
        // export
        int len = Math.min(k, map.size());
        String [] array = new String[len];
        for(int j = len - 1; j >= 0; j --) {
            array[j] = minHeap.poll().getKey();
        }
        return array;
    }
    public static void main(String [] args) {
        TopKFrequency sol = new TopKFrequency();
        System.out.println(Arrays.toString(sol.topKFrequent(new String[]{"d","a","c","b","d","a","b","b","a","d","d","a","d"}, 3)));
    }
}

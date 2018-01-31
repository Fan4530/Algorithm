package Interview.Uber;

import java.util.*;

/**
 * Created by program on 1/29/2018.
 */
public class ReorderLetter {
    public static String reorder(String str, int k) {
        if(str == null || str.length() <= 1) {
            return str;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        for(char c : str.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap =
                new PriorityQueue<>(11, (e1, e2) -> (Integer.compare(e2.getValue(), e1.getValue())));
        for(Map.Entry<Character, Integer> e : map.entrySet()) {
            maxHeap.offer(e);
        }
        Queue<Map.Entry<Character, Integer>> fq = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        while(!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> e = maxHeap.poll();
            sb.append(e.getKey());
            e.setValue(e.getValue() - 1);
            fq.offer(e);
            if(fq.size() >= k) {
                Map.Entry<Character, Integer> cur = fq.poll();
                if(cur.getValue() >= 1) {
                    maxHeap.offer(cur);
                }

            }
        }
        return sb.length() == str.length() ? sb.toString() : "";

    }
    public static void main(String [] agrs) {
        System.out.println(reorder("aabc", 2));
    }
}


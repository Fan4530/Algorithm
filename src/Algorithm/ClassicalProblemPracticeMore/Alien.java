package Algorithm.ClassicalProblemPracticeMore;

import java.util.*;

/**
 * Created by program on 11/21/2017: Alien
 * How to implement topological method --> hashmap<Character, HashSet>
 * How to create a graph:   --> HashMap<Character, HashSet>
 *          Be careful: to avoid to over calculate the indegree, check if the element has been add to the set
 *                          e.g.   f -> t;  f --> t; they are same! ,the indegree of t can only plus 1
 * How to combine graph and topligical to solve problem: BFS  -->
 *          step 1: add the elements that the indegree is 0 first  to a queue
 *          step 2: poll one element, and check its neibors, and then let the indegrees of these elements minus 1,
 *          step 3: after minus 1, the indregree is 0 (or before minus 1, the indegree is 1), then push them into the queue
 *          step 4: after the while loop, if indegree.size() != res.size()[the element been push into the queue and poll out to the res],
 *                      then has a loop.
 */
public class Alien {
    public static void main(String [] args) {
        String [] s = new String[]{ "za","zb","ca","cb"};
        System.out.println(alienOrder(s));
    }
    public static String alienOrder(String[] words) {
        // HashMap: graph
        // HashMap: degree
        // initialize the degree to 0
        HashMap<Character, HashSet<Character>> graph = new HashMap<>();
        HashMap<Character, Integer> degree = new HashMap<>();
        initialize(words, degree);
        buildGraphAndDegree(words, degree, graph);
        return BFS(words, degree, graph);
    }
    public static String BFS(String [] words, HashMap<Character, Integer> degree, HashMap<Character, HashSet<Character>> graph) {
        Queue<Character> queue = new LinkedList<>();
        for(Map.Entry<Character, Integer> entry : degree.entrySet()) {
            if(entry.getValue() == 0) {
                queue.offer(entry.getKey());
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!queue.isEmpty()) {
                Character c = queue.poll();
                sb.append(c);
                if(graph.get(c) != null) {// The last one, that is: the t -> f, e.g. f,
                    // its outdegree is zero, then graph.get(f) will be null
                    for(Character nei : graph.get(c)) {
                        int indegree = degree.get(nei);
                        degree.put(nei, indegree - 1);
                        if(indegree == 1) {
                            queue.offer(nei);
                        }
                    }
                }
        }
        return sb.length() == degree.size() ? sb.toString() : "";
    }
    public static void buildGraphAndDegree(String [] words, HashMap<Character, Integer> degree, HashMap<Character, HashSet<Character>> graph) {
        for(int i = 1; i < words.length; i ++) {
            String s1 = words[i - 1];
            String s2 = words[i];
            int len = Math.min(s1.length(), s2.length());
            for(int j = 0; j < len; j ++) {
                char c1 = s1.charAt(j);
                char c2 = s2.charAt(j);
                if(c1 != c2) {
                    //build graph
                    //c1 is outDegree, c2 is inDegree
                    HashSet<Character> set = graph.get(c1);
                    if(set == null) {
                        set = new HashSet<>();
                        graph.put(c1, set);
                    }
                    if(set.add(c2)) {
                        //avoid to add duplicate indegree
                        // e -> f
                        // e -> f    --> the indegree of f is 1
                        degree.put(c2, degree.get(c2) + 1);
                    }
                    break;//Be careful, once be recognized by the former char, then ignore the later chars
                    // e.g: ab ca,   only can get the res: a -> c
                }
            }
        }
    }
    public static void initialize(String[] words, HashMap<Character, Integer> degree) {
        for(String s : words) {
            for(int i = 0; i < s.length(); i++) {
                degree.put(s.charAt(i), 0);
            }
        }
    }
}

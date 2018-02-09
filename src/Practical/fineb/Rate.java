package Practical.fineb;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by program on 1/21/2018.
 */
// 思路:
    // 新建一个class， 存每一次request
    // HashMap<Nmae, TotalRequest>
    // Queue<NoDe>  --> 这俩都要写在method的外面
    //
    // 每次来的时候，如果次数大于100， 直接返回false
    // 否则的话： 如果queue不是空，且queue的peek的timestamps 和现在的timestams 相差1 就poll出来，并减去hahsmap中的部分
    //
    // 接下来有几种cases:
    // getOrDefault(name, 0)
    // 如果这个数字 + times >= 100
    // return false
    // 否则就push到queue， 然后改变map的值 return true
public class Rate {
    public static void main (String [] agrs) {
        Rate sol = new Rate();
        System.out.println(sol.Rate("A", 50, 0));
        System.out.println(sol.Rate("A", 52, 0.1));
        System.out.println(sol.Rate("A", 50, 1.2));
        System.out.println(sol.Rate("E", 111, 3.3));
    }
    class Node {
        String name;
        int times;
        double timestamps;
        public Node (String name, int times, double timestamps) {
            this.name = name;
            this.times = times;
            this.timestamps = timestamps;
        }
    }
    HashMap<String, Integer> map = new HashMap<>();
    Queue<Node> q = new LinkedList<>();
    public boolean Rate(String name, int times, double timestamps) {

        while(!q.isEmpty() && timestamps - q.peek().timestamps >= 1) {
                Node node = q.poll();
                map.put(node.name, map.get(node.name) - node.times);// total - node.times
        }


        Integer lastTotal = map.getOrDefault(name, 0);
        if(lastTotal + times >= 100) {
            return false;
        } else {
            q.offer(new Node(name, times, timestamps));
            map.put(name, lastTotal + times);
            return true;
        }

    }
}

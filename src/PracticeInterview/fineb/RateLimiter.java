package Practical.fineb;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by program on 12/18/2017.
 */


//中午刚刚面完，面经之前地里面出现过，可惜身体状态不好也没有好好准备，面的过程艰难无比。
//        RateLimit，允许每个IP在每秒内CALL100次，超过100次返回false。没有研究过uber的面经，这道题可能是高频题吧？





public class RateLimiter {
    static class Node {
        double timestamp;
        String ip;
        int times; // times for one call
        public Node(double timestamp, String ip, int times) {
            this.timestamp = timestamp;
            this.ip = ip;
            this.times = times; // times for one call
        }
    }
    /**
     *
     * @param timestamp
     * @param ip
     * @param calltimes times of one call
     * @return
     */
    // Queue
    HashMap<String, Integer> map = new HashMap<>();
    // ip    total calls
    Queue<Node> q = new LinkedList<>();
    // time stamp + ip + calltimes
    public boolean ipLimiter(double timestamp, String ip, int calltimes) {
        // poll which has been out of time
        while(!q.isEmpty() && timestamp - q.peek().timestamp >= 1) {
            Node n = q.poll();
            map.put(n.ip, map.get(n.ip) - n.times);
        }

        if(calltimes > 100) {
            return false;
        }

        int thisTotal = map.getOrDefault(ip, 0);
        if(thisTotal + calltimes > 100) {
            return false;
        } else {
            q.offer(new Node(timestamp, ip, calltimes));
            map.put(ip, thisTotal + calltimes);
            return true;
        }
    }


    public static void main(String [] args) {
        RateLimiter sol = new RateLimiter();
        System.out.println(sol.ipLimiter(0, "1", 99));// true
        System.out.println(sol.ipLimiter(0.1, "2", 99));// true
        System.out.println(sol.ipLimiter(0.99, "1", 99));// false

        System.out.println(sol.ipLimiter(1, "1", 99));// true
        System.out.println(sol.ipLimiter(0.99, "2", 99));//  false
        Logger log = new Logger();
        System.out.println(log.shouldPrintMessage(2, "1"));
        System.out.println(log.shouldPrintMessage(100, "1"));
        System.out.println(log.shouldPrintMessage(102, "1"));
    }
}

class Logger {
    HashMap<String, Integer> map;

    public Logger() {
        map = new HashMap<>();
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        Integer lastTime = map.get(message);
        if (lastTime == null || timestamp - lastTime >= 100) {
            map.put(message, timestamp);
            return true;
        }
        return false;
    }

}

// assumption: 100 requests per second


// class: name, timestamp, times
//
// HashMap<Node, TotalsNumber>
// Queue: Node
// when a node comes, poll the node, if its timestamp is not good
//
//


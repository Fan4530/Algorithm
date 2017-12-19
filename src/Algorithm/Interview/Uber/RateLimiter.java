package Algorithm.Interview.Uber;

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

        Integer preTimes = map.get(ip);
        if(preTimes == null) {
            map.put(ip, calltimes);
            q.offer(new Node(timestamp, ip, calltimes));
            return true;
        } else {
            if(preTimes + calltimes > 100) {
                return false;
            } else {
                map.put(ip, preTimes + calltimes);
                q.offer(new Node(timestamp, ip, calltimes));
                return true;
            }
        }
    }
    public static void main(String [] args) {
        RateLimiter sol = new RateLimiter();
        System.out.println(sol.ipLimiter(0, "1", 99));
        System.out.println(sol.ipLimiter(0.1, "2", 99));
        System.out.println(sol.ipLimiter(0.99, "1", 99));
        System.out.println(sol.ipLimiter(1, "1", 99));
        System.out.println(sol.ipLimiter(0.99, "2", 99));
    }
}

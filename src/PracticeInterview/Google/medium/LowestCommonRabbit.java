package PracticeInterview.Google.medium;

import java.util.*;

// not leetcode
// 给你一组rabbits， rabbit有两个爸妈，自己还有偶一个index的编号。
// 给你两个rabbits，找他们lowest common parents

//【思路】 这题是graph，一个节点会有两个爸爸， 所以和lowest common ansester不太一样
// 用two ends BFS


// 两种case
//           1
//          / \
//         2  3
//        /    \
//       4     5


//          6
//         / \
//        4   7
//             \
//              8
//               \
//                5
// 可能会出现以上两种情况: 4 有两个爸爸 2 和6，   同样5也有两个parent 8和3
// 如果要求取最小： 那么结果就是第二种case. 因为第二种的最小是1， 第一种的最小是2:  【注意： 这种case不能找到一个common的就结束，需要比较】
// 如果是要求取总长度： 第一种第二种都可以[这种也需要比较]
// ***如果要求取最大： 那就是取第一种case， 因为第一种的最大是2，第二种的最大是3. 。。。我猜会给这种case

// 取最大： 就如下面的答案，两个set两个queue
// 最小： 把set改成map<key: parent, value: distance from the source>
//    比如case1: map1: <2, 1>  <1, 2>
//              map2: <3, 1> 然后发现key: 1存在于map1里面, 于是就得到distance1: map1.get(1), distance2 = currentLevel
//
//
//
//
//
//


public class LowestCommonRabbit {
    public static void main(String [] args) {

        Map<Integer, List<Integer>> rabbits = new HashMap<>();


        rabbits.put(0, Arrays.asList(1,5));
        rabbits.put(1, Arrays.asList(6));
        rabbits.put(2, Arrays.asList(1));
        rabbits.put(3, Arrays.asList(2));
        rabbits.put(4, Arrays.asList(3));
        rabbits.put(5, Arrays.asList(6,4));
        rabbits.put(6, Arrays.asList());
        System.out.println(twoEnds(rabbits, 0, 4));

    }
    private static Integer twoEnds(Map<Integer, List<Integer>> rabbits, int a, int b) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        q1.offer(a);
        q2.offer(b);
        while(!q1.isEmpty() && !q2.isEmpty()) {
            int sizea = q1.size();
            for(int i = 0; i < sizea; i ++) {
                Integer ra = q1.poll();
                if(!set2.add(ra)) {
                    return ra;
                }

                for(Integer x : rabbits.get(ra)) {
                    if(set1.add(x)) {
                        q1.offer(x);
                    }
                }
            }
            int sizeb = q2.size();
            for(int i = 0; i < sizeb; i ++) {
                Integer rb = q2.poll();
                if(!set2.add(rb)) {
                    return rb;
                }
                for(Integer x : rabbits.get(rb)) {
                    if(set2.add(x)) {
                        q2.offer(x);
                    }
                }
            }

        }
        return null;
    }
}

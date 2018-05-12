package PracticeInterview.Google.easy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// not a leetcode
//第一题，给定一个输入的Double LinkedList，另一个输入的List<LinkedListNode>。
//        问输入的这些Node能构成几个group。group的定义是group内的node是相邻的。
//        例如给定输入双链表`1<->2<->3<->4`和`[1,3,4]`。则里面存在两个group:{1}和{3,4}。
public class DoubleLinkedNodeGroup {
    public static void main(String [] agrs) {
        DoubleNode n1 = new DoubleNode(1);
        DoubleNode n2 = new DoubleNode(2);
        DoubleNode n3 = new DoubleNode(3);
        DoubleNode n4 = new DoubleNode(4);
        DoubleNode n5 = new DoubleNode(5);


        n1.right = n2;
        n2.right = n3;
        n3.right = n4;
        n4.right = n5;

        n2.left = n1;
        n3.left = n2;
        n4.left = n3;
        n5.left = n4;


        List<DoubleNode> list = new ArrayList<DoubleNode>();
        list.add(n1);
        list.add(n3);
        list.add(n5);

        System.out.println(sol(list));
    }
    static class DoubleNode {
        DoubleNode left, right;
        int val;
        public DoubleNode (int val) {
            this.val = val;
        }
        public DoubleNode(int val, DoubleNode left, DoubleNode right) {
            this.left = left;
            this.right = right;
        }
    }
    public static int sol(List<DoubleNode> list) {
        if(list == null || list.size() == 0) {
            return 0;
        }
        int res = 0;
        Set<DoubleNode> set = new HashSet<>();
        set.addAll(list);
        for(DoubleNode n : list) {
            if(set.contains(n)) {
                remove(set, n);
                res ++;
            }
        }
        return res;
    }
    private static void remove(Set<DoubleNode> set, DoubleNode n) {
        DoubleNode cur = n;
        while(cur.right != null && set.remove(cur.right)) {
            cur = cur.right;
        }
        while(cur.left != null && set.remove(cur.left)) {
            cur = cur.left;
        }
    }
}

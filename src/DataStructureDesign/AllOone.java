package DataStructureDesign;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by program on 12/17/2017.
 */



public class AllOone {
    public static void main(String [] args) {
        AllOone sol = new AllOone();
//        sol.inc("a");
//        sol.inc("b");
//        sol.inc("b");
//        sol.inc("c");
//        sol.inc("c");
//        sol.inc("c");
//
//      //  sol.inc("Zhou");
//
//       // sol.inc("Pei");
//
//
//        sol.dec("b");
//        sol.dec("b");
//
//        System.out.println(sol.getMinKey());
//        sol.dec("a");
//
//        System.out.println(sol.getMaxKey());
//        System.out.println(sol.getMinKey());
        sol.inc("hello");
        sol.inc("goodbye");
        sol.inc("hello");
        sol.inc("hello");
        System.out.println(sol.getMaxKey());
        sol.inc("leet");
        sol.inc("code");
        sol.inc("leet");
        sol.dec("hello");
        sol.inc("leet");
        sol.inc("code");
        sol.inc("code");
        System.out.println(sol.getMaxKey());
    }

    static class Node {
        int val;
        Set<String> keySets;
        Node next;
        Node pre;
        public Node(String key, int val) {
            keySets = new HashSet<>();
            keySets.add(key);
            this.val = val;
        }
        public void insert(String key) {
            keySets.add(key);
        }
    }
    HashMap<String, Node> map;
    Node head;
    Node tail;
    /** Initialize your data structure here. */
    public AllOone() {
        map = new HashMap<>();
    }
    private void deleteNode(Node node) {
        if(head == null) {
            return;
        } else if(node == head && node == tail) {
            head = null;
            tail = null;
        } else if(node == head) {
            head = head.next;
            head.pre = null;
            node.next = null;
        } else if(node == tail) {
            tail = tail.pre;
            tail.next = null;
            node.pre = null;
        } else {
            Node pre = node.pre;
            Node next = node.next;
            pre.next = next;
            next.pre = pre;
            node.next = null;
            node.pre = null;
        }
    }
    private void addNewNodeAfter(Node node, String key) {
        Node newNode = new Node(key, node.val - 1);
        if(node.val == 1) {
            return;
        } else if(node == tail) {
            addNodeToTail(newNode);
        } else {
            Node next = node.next;
            next.pre = newNode;
            newNode.pre = node;
            node.next = newNode;
            newNode.next = next;
        }
    }
    // The val of the newNode is node.val + 1
    private void addNewNodeBefore(Node node, String key) {
        Node newNode = new Node(key, node.val + 1);
        if(node == head) {
            head = newNode;
            head.next = node;
            node.pre = head;
        } else {
            Node preNode = node.pre;
            preNode.next = newNode;
            newNode.next = node;
            node.pre = newNode;
            newNode.pre = preNode;
        }
    }
    private void addKeyTo(Node node, String key) {
        node.keySets.add(key);
    }
    private void removeKeyFrom(Node node, String key) {
        if(node == null) {
            return;
        }
        Set<String> set = node.keySets;
        if(set.size() == 1) {
            deleteNode(node);
        } else {
            set.remove(key);
        }
    }
    private void addNodeToTail(Node node) {
        if(head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.pre = tail;
            tail = tail.next;
        }
    }
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        Node node = map.get(key);
        //case 1: 这个key不存在， 分两种情况
        //      有tail而且tail的val = 1， 所以你直接加进去就好了
        //      没有tail的情况或者tail值不是1， 那就要新建一个node了
        //往上面冒泡：
        //case 2: 如果正好有和自己相差为1的node， 那么就直接加进去
        //case 3: 如果没有正好和自己相差1的node，那么就要新建一个node加载前面
        //记得时刻更新hash表。
        //最后记得要remove掉原来node里面的key
        if(node == null) {// 很容易犯错！ 如果tail == null, tail.val != 1, 都新建一个node
            if(tail == null || tail.val != 1) {
                addNodeToTail(new Node(key, 1));
            } else {//如果node.val == 1的话， 就直接加到里面
                addKeyTo(tail, key);
            }
            map.put(key, tail);
        } else if(node.pre == null || node.pre.val != node.val + 1) {
            addNewNodeBefore(node, key);
            map.put(key, node.pre);
        } else {
            addKeyTo(node.pre, key);
            map.put(key, node.pre);
        }
        removeKeyFrom(node, key);//如果remove的时候，node有可能是null！case 1 下来的
        //总结我们用到API
        //removeKeFrom:
        //addKeyTo
        //addNewNodeBefore
        //addNewNodeAfter    和上面的不完全一样， 这个还要加上node.val == 1的时候，就不用加了，直接删掉了
        //deleteNode         最为经典的5个case

    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        Node node = map.get(key);
        if(node == null) {
            return;
        } else if(node.next == null || node.next.val != node.val - 1) {
            addNewNodeAfter(node, key);//be careful of the node.val == 1
            map.put(key, node.next);
        } else {
            addKeyTo(node.next, key);
            map.put(key, node.next);
        }
        removeKeyFrom(node, key);// set only has one element
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if(head == null) {
            return "";
        }
        Iterator it =  head.keySets.iterator();
        return (String)it.next();
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if(tail == null) {
            return "";
        }
        Iterator it =  tail.keySets.iterator();
        return (String)it.next();
    }
}




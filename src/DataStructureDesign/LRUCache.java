package DataStructureDesign;

import java.util.HashMap;

/**
 * Created by program on 12/17/2017.
 */
public class LRUCache {
    public static void main(String [] agrs) {
        LRUCache sol = new LRUCache(2);
        sol.put(1,1);
        sol.put(2,2);
        System.out.println(sol.get(1));
        sol.put(3,3);
        System.out.println(sol.get(2));
        sol.put(4,4);
        System.out.println(sol.get(1));
        System.out.println(sol.get(3));
        System.out.println(sol.get(4));
        sol.put(3,3);
        sol.put(3,3);
        sol.put(3,3);
        sol.put(3,3);
        sol.put(3,3);
        System.out.println(sol.get(3));

        sol.put(5,300);
        System.out.println(sol.get(3));

        sol.put(3,3);
        sol.put(6,600);
        sol.put(7,700);
        System.out.println(sol.get(3));
        sol.put(3,3);
    }
    static class Node {
        int val;
        Node next;
        Node pre;
        int key;
        public Node(int key, int val) {
            this.val = val;
            this.key = key;
            next = null;
            pre = null;
        }
    }
    HashMap<Integer, Node> map;
    int capacity;
    int count;
    Node head;
    Node tail;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        count = 0;
        head = null;
        tail = null;
    }
    private void removeNode(Node n) {
        // the LinkedList is empty
        System.out.println(head.val + " " + tail.val);

        if(head == null) {
            return;
        } else if(n == head && n == tail) {// only one node
            System.out.println("!!!!!!!!!");

            head = null;
            tail = null;
        } else if(n == tail) {//remove tail
            tail = tail.pre;
            tail.next = null;
            n.pre = null;
        }else if(n == head) {// remove head
            head = head.next;
            head.pre = null;
            n.next = null;
        } else {
            Node pre = n.pre;
            Node next = n.next;
            pre.next = next;
            next.pre = pre;
        }
    }
    private void moveToHead(Node n) {
        if(head == null) {
            head = n;
            tail = n;
            return;
        }
        //connect head with n
        n.pre = null;
        n.next = head;
        head.pre = n;
        //change n to head
        head = n;
    }
    public int get(int key) {
        Node n = map.get(key);
        if(n == null) {
            return -1;
        }
        removeNode(n);
        moveToHead(n);
        return n.val;
    }

    public void put(int key, int value) {
        Node n = map.get(key);
        if(n == null) {
            moveToHead(new Node(key, value));
            map.put(key, head);
            count ++;
            if(count > capacity) {
                map.remove(tail.key);
                removeNode(tail);
                count --;
            }
        } else {
            removeNode(n);
            moveToHead(n);
            n.val = value;
        }
    }
}

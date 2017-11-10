package Algorithm.DFS;

import java.util.HashMap;

/**
 * Created by program on 10/16/2017.
 */

  class RandomListNode {
    public int value;
    public RandomListNode next;
    public RandomListNode random;
    public RandomListNode(int value) {
      this.value = value;
    }
  }

public class CloneRandom {
    public RandomListNode copyRandomList(RandomListNode head) {
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
        return dfs(map, head);
    }
    private RandomListNode dfs(HashMap<RandomListNode, RandomListNode> map, RandomListNode head) {
        if(head == null) {
            return null;
        }
        RandomListNode newHead = map.get(head);
        if(newHead != null) {
            return newHead;
        }
        map.put(head, newHead);
        newHead = new RandomListNode(head.value);
        newHead.next = dfs(map, head.next);
        newHead.random = dfs(map, head.random);
        return newHead;

    }
    public static void main(String [] args) {
        CloneRandom sol = new CloneRandom();
        RandomListNode node1 = new RandomListNode(0);
        RandomListNode node2 = new RandomListNode(1);
        node1.next = node2;
        //node1.random = node2;
        node2.next = node1;
        System.out.println(sol.copyRandomList(node1));
    }
}

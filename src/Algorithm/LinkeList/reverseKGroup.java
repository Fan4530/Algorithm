package Algorithm.LinkeList;

class ListNode {
    int val;
    ListNode next;
    public ListNode(int val) {
        this.val = val;
    }
    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
public class reverseKGroup {
    public static void main(String [] agrs) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        reverseKGroup sol = new reverseKGroup();
        sol.reverseKGroup(head, 2);
    }
    public ListNode reverseKGroup(ListNode head, int k) {
        //  1->2->3->        4->5
        //  h     n
        //  go next k, if null, return head
        ListNode n = head;
        int count = k;
        while(count -- > 0) {
            if(n == null) {
                return head;
            }
            n = n.next;
        }
        ListNode pre = reverseKGroup(n, k);
        reverse(head, n, pre);
        return head;
        //1   2   3    4    5
        //h       h         pre
        //             n    tail

    }
    private void reverse(ListNode head, ListNode tail, ListNode pre) {
        while(head != tail) {
            ListNode next = head.next;
            head.next = pre;
            next.next = head;
            pre = head;
            head = next;
        }
    }
}

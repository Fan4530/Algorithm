package Algorithm.Heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by program on 10/13/2017.
 */
public class KthSmallestSumInTwoSortedArrays {
    static class Sum {
        int x;
        int y;
        int sum;
        public Sum (int x, int y, int [] A, int [] B) {
            this.x = x;
            this.y = y;
            this.sum = A[x] + B[y];
        }
    }
    public int kthSum(int[] A, int[] B, int k) {
        // Write your solution here
        PriorityQueue<Sum> minHeap = new PriorityQueue<>(11, new Comparator<Sum>() {
            public int compare(Sum s1, Sum s2) {
                if(s1.sum == s2.sum) {
                    return 0;
                }
                return s1.sum < s2.sum ? -1 : 1 ;
            }
        });
        boolean [][] visited = new boolean[A.length][B.length];
        Sum s = new Sum(0, 0, A, B);
        minHeap.offer(s);
        visited[0][0] = true;
        while(k != 0) {
            s = minHeap.poll();
            k --;
            if(s.x + 1 < A.length && !visited[s.x + 1][s.y]) {
                minHeap.offer(new Sum(s.x + 1, s.y, A, B));
                visited[s.x + 1][s.y] = true;

            }
            if(s.y + 1 < B.length && !visited[s.x][s.y + 1]) {
                minHeap.offer(new Sum(s.x, s.y + 1, A, B));
                visited[s.x][s.y + 1] = true;

            }
        }
        return s.sum;// error : visisted is also necessary for   [0, 1]   [1, 0] --> [1, 1]
    }
}

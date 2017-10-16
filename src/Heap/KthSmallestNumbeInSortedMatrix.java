package Heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by program on 10/13/2017.
 */
public class KthSmallestNumbeInSortedMatrix {
    static class Point {
        int x;
        int y;
        int val;
        public Point(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
    public int kthSmallest(int[][] matrix, int k) {
        // Write your solution here.
        PriorityQueue<Point> minHeap = new PriorityQueue<Point>(11, new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                if(p1.val == p2.val) {
                    return 0;
                }
                return p1.val < p2.val ? -1: 1;
            }
        });
        int n = matrix.length;
        int m = matrix[0].length;
        Point p = new Point(0, 0, matrix[0][0]);
        minHeap.offer(p);
        boolean [][] visited = new boolean[n][m];
        visited[0][0] = true;
        while(k != 0) {
            p = minHeap.poll();
            if(p.x + 1 < n && visited[p.x + 1][p.y] == false) {// ???
                minHeap.offer(new Point(p.x + 1, p.y, matrix[p.x + 1][p.y]));
                visited[p.x + 1][p.y] = true;
            }
            if(p.y + 1 < m && visited[p.x][p.y + 1] == false) {// ???
                minHeap.offer(new Point(p.x, p.y + 1, matrix[p.x][p.y + 1]));
                visited[p.x][p.y + 1] = true;
            }
            k --;
        }
        return p.val;// error 1: this place, p must be defined out of the while loop !
    }
}

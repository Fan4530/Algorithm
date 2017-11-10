package Algorithm.BestFirstSearch;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by program on 10/11/2017.
 */
public class PointHeap {
    static class Point {
        int x;
        int y;
        Integer sum;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
            sum = (int) (Math.pow(2, x) * Math.pow(3, y));
        }
    }
    public int kth(int k) {
        PriorityQueue<Point> heap = new PriorityQueue<>(11, new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                return p1.sum.compareTo(p2.sum);
            }
        });
        long pre = 0;
        Point p = new Point(0, 0);
        heap.offer(p);
        for(int i = 0; i < k; i ++) {
            p = heap.poll();
            if(pre == p.sum) {
                i --;
            }
            heap.offer(new Point(p.x + 1, p.y));
            heap.offer(new Point(p.x, p.y + 1));

            pre = p.sum;
        }
        return p.sum;

    }
    public static void main(String [] args) {
        PointHeap sol = new PointHeap();
        System.out.println(sol.kth(5));
    }
}

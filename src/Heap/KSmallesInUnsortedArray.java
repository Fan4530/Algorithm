package Heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by program on 10/13/2017.
 */
public class KSmallesInUnsortedArray {
    public int[] kSmallest(int[] array, int k) {
        // Write your solution here
        // create a heap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(11, new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return i2.compareTo(i1);
            }
        });
        for(int i = 0; i < array.length; i ++) {
            if(i < k) {
                maxHeap.offer(array[i]);
            } else if(maxHeap.size() != 0 && maxHeap.peek() > array[i] ){// error 1: no peekFirst, just peek
                //error 2: when peek, or poll, make sure the heap is not empty
                maxHeap.poll();
                maxHeap.offer(array[i]);
            }
        }
        int [] res = new int[k];
        for(int i = k - 1; i >= 0; i --) {//error 3: pay attention to i --, not i ++
            res[i] = maxHeap.poll();
        }
        return res;
    }
}


package Conccurency;

import com.oracle.jrockit.jfr.Producer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;

/**
 * Created by program on 12/7/2017.
 */
class Q {
    private Queue<Integer> queue;
    private final int limit;
    public Q (int limit){
        queue = new LinkedList<>();
        this.limit = limit;
    }
    public synchronized void put(Integer ele) {
        while(queue.size() == limit) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(queue.size() == 0) {
            notifyAll();
        }
        queue.offer(ele);
    }
    public synchronized Integer take() {
        while(queue.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(queue.size() == limit) {
            notifyAll();
        }
        return queue.poll();
    }
}
public class ProducerConsumer {
    public static void main(String [] args) {
        Q q = new Q(5);
        List<Thread> threads = new ArrayList<>();
        for(int i = 0; i < 10; i ++) {
            threads.add(new Thread(){
                public void run() {
                    q.put(1);
                    System.out.println("put 1");
                }
            });
        }
        for(int i = 0; i < 10; i ++) {
            threads.add(new Thread() {
                public void run() {
                    System.out.println("Take " + q.take());
                }
            });
        }
        for(Thread t : threads) {
            t.start();
        }
    }
}

package Conccurency;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by program on 12/7/2017.
 */

class ReentranQ {
    private Queue<Integer> q;
    private final int limit;
    private Lock lock;
    private Condition notFull;
    private Condition notEmpty;

    public ReentranQ(int limit) {
        this.limit = limit;
        q = new LinkedList();
        this.lock = new ReentrantLock();
        this.notFull = lock.newCondition();//和lock联系起来了，full 一直await，直到full siginal的时候，解锁，解lock这把锁
        this.notEmpty = lock.newCondition();
    }
    public void put(Integer ele) {
        lock.lock();
        try{
            while(q.size() == limit) {
                notFull.await();
            }
            if(q.size() == 0) {
                notEmpty.signal();//给一个不满的信号，取弄醒所有因为空而睡着的take 线程
            }
            q.offer(ele);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public Integer take() {
        lock.lock();
        try{
            while(q.size() == 0) {
                notEmpty.await();
            }
            if(q.size() == limit) {
                notFull.signalAll();
            }
            return q.poll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }
}
public class ReentrantLockPrac {
    public static void main(String [] args) {
        ReentranQ q = new ReentranQ(5);
        List<Thread> threads = new ArrayList<>();
        final int[] count = {0};
        for(int i = 0; i < 10; i ++) {
            threads.add(new Thread(){
                public void run() {
                    q.put(count[0]);
                    System.out.println("put " + count[0]);

                    count[0]++;
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

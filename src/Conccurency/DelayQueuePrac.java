package Conccurency;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by program on 12/7/2017.
 */

public class DelayQueuePrac<E> {
    private static final Object NANOSECONDS = 1000;
    private ReentrantLock lock;
    PriorityQueue<E> q;
    Thread leader;
    Condition available;

    //这行是为了免报错
    private long delay;

    public DelayQueuePrac() {
        //DelayQueue  查看原码
      //  q = new PriorityQueue<>(new Comparator<E>((e1, e2) -> Long.compare(e1.getDelay(), e2.getDelay())));
        q = new PriorityQueue<>();
        available = lock.newCondition();
    }

    /**
     * leader元素的使用: 大家可能看到在我们的DelayQueue中有一个Thread类型的元素leader,那么他是做什么的呢,有什么用呢？
     * when a thread becomes the leader, it waits only for the next delay to elapse, but other threads await indefinitely.
     * 这里我们想象着我们有个多个消费者线程用take方法去取,内部先加锁,然后每个线程都去peek第一个节点.
     如果leader不为空说明已经有线程在取了,设置当前线程等待
     if (leader != null)
     available.await();

     take方法中为什么释放first元素？
     假设A线程阻塞完毕， 线程B进来获取first,进入else的阻塞操作,然后无限期等待

     A他应该被GC回收，但是因为他还持有first，就不能回收，导致内存泄漏


     */
    public boolean offer(E e) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            q.offer(e);
            if (q.peek() == e) {
                leader = null;
                available.signal();
            }
            return true;
        } finally {
            lock.unlock();
        }
    }
    /**
     * Retrieves and removes the head of this queue, waiting if necessary
     * until an element with an expired delay is available on this queue.
     *
     * @return the head of this queue
     * @throws InterruptedException {@inheritDoc}
     */
    public E take() throws InterruptedException {
        // 获取锁。每个延迟队列内聚了一个重入锁。
        final ReentrantLock lock = this.lock;
        // 获取可中断的锁。
        lock.lockInterruptibly();
        try {
            for (;;) {
                // 尝试从优先级队列中获取队列头部元素
                E first = q.peek();
                if (first == null)
                    // 无元素，当前线程节点加入等待队列，并阻塞当前线程
                    available.await();
                else {
                    // 通过延迟任务的 getDelay 方法获取延迟时间
          //          long delay = first.getDelay(NANOSECONDS);
                    if (delay <= 0)
                        // 延迟时间到期，获取并删除头部元素。
                        return q.poll();//在这里删除
                    first = null; // don't retain ref while waiting
                    if (leader != null)
                        available.await();
                    else {
                        Thread thisThread = Thread.currentThread();
                        leader = thisThread;
                        try {
                            // 线程节点进入等待队列 x 纳秒。
                            available.awaitNanos(delay);
                        } finally {
                            if (leader == thisThread)
                                leader = null;
                        }
                    }
                }
            }
        } finally {
            // 若还存在元素的话，则将等待队列头节点中的线程节点移动到同步队列中。
            if (leader == null && q.peek() != null)
                available.signal();
            lock.unlock();
        }
    }

}

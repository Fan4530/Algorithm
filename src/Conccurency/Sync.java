package Conccurency;

/**
 * Created by program on 12/7/2017.
 */

// synchronized: lock this method, at this time, other thread cannot run
class mySyc implements Runnable{
    private static int count;

    public mySyc() {
        count = 0;
    }

    public  void run() {
        synchronized(this) {
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(100);
                    // to make this thread sleep so that it will block here, if no synchronized,
                    // other thread will process first
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int getCount() {
        return count;
    }
}
public class Sync {
    public static void main(String [] args) {
        mySyc syncThread = new mySyc();
        // two threads:
        Thread thread1 = new Thread(syncThread, "SyncThread1");
        Thread thread2 = new Thread(syncThread, "SyncThread2");
        thread1.start();
        thread2.start();
    }
}

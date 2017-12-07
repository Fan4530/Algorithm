package Conccurency;

/**
 * Created by program on 12/6/2017.
 */
public class ThreadPrac {
        //两种重写线程run的方法
        //method 1: defien a class, implements Runnable, same to comparator  in Sync
        //method 2: as follows:
        public static void main(String args[]) throws InterruptedException {
            Thread t1 = new Thread() {
                public void run() {
                    System.out.println("h1");
                }
            };
            t1.start();// the JVM in start will call run, so you don't need to call run again
            t1.join();//the main thread will block here, until finish
            System.out.println("h2");

        }
    }


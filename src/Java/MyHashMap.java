package Java;

/**
 * Created by program on 12/8/2017.
 * 碰到这种题目不要怕
 * 1. 先写好Entry， 他是一个LinkedList
 * 2. 然后写好各个函数的头
 * 3. 先写好hash 和getIndex()
 * 4. 再写get， 然后其实就可以各种抄来抄去了
 *
 * synchronized 加在public 接口， 对bucket有操作的method
 * get remove size
 */
public class MyHashMap<K, V> {
    static class Entry<K, V> {
        V value;
        K key;
        Entry<K, V> next;
        Entry (K key, V value) {
            this.value = value;
            this.key = key;
        }
        Entry () {

        }
        public V getValue() {
            return value;
        }
        public K getKey() {
            return key;
        }
        private void setValue(V value) {
            this.value = value;
        }
        private void setKey(K key) {
            this.key = key;
        }
    }
    private int size = 0;
    private int capacity = 10;
    private Entry<K, V> [] entries;
    public MyHashMap(int cap) {
        this.capacity = cap;
        this.size = 0;
        this.entries = (Entry<K, V>[])(new Entry[capacity]);
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    private long getBucket(long hashcode) {
        return hashcode % capacity;
    }
    private int hash(K key) {
        //hashCode() will return negative value, we need to guarantee it is nonnegative
        //cannot use key.hashCode >= 0? code : -code;
        //-Integer.Min_value w= Integer.MIN_VALUE  (1 11111111)   取反+ 1还是他自己
        return key.hashCode() & 0X7FFFFFFF;
    }
    private int getIndex(K key) {
        return hash(key) % entries.length;
    }
    private boolean equalsKey(K k1, K k2) {
        // null == obj ,  this will result in error, so we need to define
        // k1 == k2 --> if they are the same obj?
        // if not, if they are equal? null.equals(k2) is not allowed
        // (cannot handle new class)
        return k1 == k2 || k1 != null &&  k1.equals(k2);
    }
    public boolean containsKey(K key) {
        int idx = getIndex(key);
        Entry<K, V> entry = entries[idx];
        while(entry != null) {
            if(equalsKey(entry.getKey(), key)) {
                return true;
            }
            entry = entry.next;
        }
        return false;
    }
    public synchronized V get(K key) {
        int idx = getIndex(key);
        Entry<K, V> entry = entries[idx];
        while(entry != null) {
            if(equalsKey(entry.getKey(), key)) {
                return entry.getValue();
            }
            entry = entry.next;
        }
        return null;
    }
    private synchronized void put(K key, V value) {
        int idx = getIndex(key);
        Entry<K, V> entry = entries[idx];
        // empty
        if(entry == null) {
            entries[idx] = new Entry<K, V>(key, value);
            size++;
            return;
        }
        // not empty bucket
        Entry<K, V> pre = null;
        while(entry != null) {
            if(equalsKey(entry.getKey(), key)) {
                entry.setValue(value);
            }
            pre = entry;
            entry = entry.next;
        }
        size ++;
        pre.next = new Entry<K, V>(key, value);
    }
    public V remove(K key) {
        int idx = getIndex(key);
        Entry<K, V> entry = entries[idx];
        if(entry == null) {
            return null;
        }
        //not empty
        Entry<K, V> dummy = new Entry<K, V>();
        dummy.next = entry;
        Entry<K, V> pre = dummy;
        V res = null;
        while(entry != null) {
            if(equalsKey(entry.getKey(), key)) {
                res = entry.getValue();
                break;
            }
            pre = entry;
            entry = entry.next;
        }
        pre.next = pre.next.next;
        entries[idx] = dummy.next;//be careful !
        dummy.next = null;// memory leak
        size--;
        return res;

    }

    public static void main(String [] agrs) throws InterruptedException {
        MyHashMap<String, Integer> map = new MyHashMap<>(5);
//        map.put("zhangfan", 1);
//        System.out.println(map.containsKey("zhangfan"));
//        map.remove("zhangfan");
//        System.out.println(map.get("zhangfan"));
//        System.out.println(map.containsKey("zhangfan"));
        Thread t1 = new Thread(){
            public void run() {
                map.put("zhangfan", 1);
            }
        };
        Thread t2 = new Thread(){
            public void run() {
                map.put("zhangfan", 2);
            }
        };
        Thread t3 = new Thread(){
            public void run() {
                map.get("zhangfan");
            }
        };
        t1.start();
        t2.start();
        t3.start();
        t1.join();// join 的作用就是让主线程等到t1执行完
        // 运行结果是1， 如果没有join，那么运行结果是null
        t3.join();
        t2.join();

        System.out.println(map.get("zhangfan"));

    }



}

package Conccurency;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by program on 12/7/2017.
 */
class MyHashMap<K, T> {
    static class Node<K, T>{
        K key;
        T value;
        public Node(K key, T value) {
            this.key = key;
            this.value = value;
        }
    }
}
public class ConcurHashMap {

    public static void main(String [] args) {
        Map m = Collections.synchronizedMap(new HashMap<Integer, Integer>());
        // where should add synchronized to map API?
        // operation with bucket
        // put, get, clear, containsKey, containsValue, remove, size
        //
        //
        //  one problem ==>  don't need to lock all of the method, not scalable!
        //      for example: actually if two threads : put(1, 1) , put(2, 2), it is no problem
        // ==> concurrent hashmap: it is really complex even to get a size !!
        // Generic hashmap
        MyHashMap.Node<Integer, Integer> n = new MyHashMap.Node<Integer, Integer>(1, 1);

    }
}

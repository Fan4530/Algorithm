package Interview.Linkedin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by program on 11/8/2017.
 */
public class RandomizedSet {

    //    arraylist
    // map:  key: numeber        value:    index of the array

    // when delete element x, using swap
    // step 1: get the last element of the array,    last = array.get(array.size() - 1) , this is the new key of the map
    // step 2: get the index of the element that should be deleted,   idx = map.get(element),
    // step 3: map.put(last, indx);
    // step 4: swap(arraylist, idx, arraylist.size() - 1);
    // step 5: remove the last element of arraylist, arraylist.remove(arraylist.size() - 1);   map.remove(element);

    //  no duplicate
    // Follow up: what if there is no element when getRandom?
    // ----------------------

    HashMap<Integer, Integer> map;
    List<Integer> list;
    Random rand = new Random();
    public RandomizedSet() {
        map = new HashMap<Integer, Integer>();
        list = new ArrayList<>();
    }
    public boolean insert(int val) {
        if(map.containsKey(val)) {
            return false;
        }

        map.put(val, list.size());// becaureful!, if use list.size(). then you should put element befroe add val
        list.add(val);
        return true;
    }
    public boolean remove(int val) {
        //step 1: update new map
        //		key: last   value: idx
        if(!map.containsKey(val)) {
            return false;
        }
        int idx = map.get(val);
        if(idx < list.size() - 1) {// be careful the case : only one number in the list.
            int last = list.get(list.size() - 1);
            map.put(last, idx);
            list.set(idx, last);
        }
        list.remove(list.size() - 1);
        map.remove(val);
        return true;
    }
    public int getRandom() {
        try {
            if(list.size() == 0) {
                throw new IOException();
            }
            return list.get(rand.nextInt(list.size()));
        } catch (IOException e) {
            System.out.println("not enough elements:" + e);
        }
        return -1;
    }
    public static void main(String [] args) {
        RandomizedSet sol = new RandomizedSet();
        System.out.println(sol.getRandom());
    }

}
package Interview.Uber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by program on 1/21/2018.
 */

//思路：新建一个class Point
    // HashMap<cityid, List<Point>>
    // 如果有序的进来， get的时候就不用sort一下，否则需要sort一下
    // Follow up：如果单机存不下咋办？
    //存不同的机器里面： 存哪个机器呢？ hashcode of key % number of machine

public class CityTemperature {
    class Point {
        int cityid;
        int timestamps;
        int temperature;
        public Point(int cityid, int temperature, int timestamps ) {
            this.cityid = cityid;
            this.timestamps = timestamps;
            this.temperature = temperature;
        }
    }
    HashMap<Integer, List<Point>> map;
    // key: city   value: list of Point

    public CityTemperature() {
        map = new HashMap<>();
    }
    // if the timestamps is sorted by the time
    public void insert(int cityid, int temperature, int timestamps) {
        List<Point> list = map.get(cityid);
        if(list == null) {
            list = new ArrayList<>();
            map.put(cityid, list);
        }
        list.add(new Point(cityid, temperature, timestamps));

    }
    public int getTemperature(int cityid, int target) {
        List<Point> list = map.get(cityid);
        return getEqualOrFirstSmaller(list, target);
    }
    private int getEqualOrFirstSmaller(List<Point> list, int target) {
        //if is not sorted
        Collections.sort(list, (l1, l2) -> Integer.compare(l1.timestamps, l2.timestamps));
        int left = 0;
        int right = list.size() - 1;
        //1 2 4 6 7 // -> 5
        //if mid > target, remove     right = mid - 1;
        //if mid <= target, remove left = mid;
        while(left < right - 1) {
            int mid = left + (right - left) / 2;
            if(list.get(mid).timestamps > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        int t1 = list.get(left).timestamps;
        int t2 = list.get(right).timestamps;
        // what if ?? the given target is smaller than all of the number
        //              t1      t2
        //     target
        if(t2 <= target ) {
            return list.get(right).temperature;
        } else if(t1 <= target) {
            return list.get(left).temperature;
        } else {
            return Integer.MIN_VALUE;
        }

    }
    public static void main(String [] agrs) {
        CityTemperature sol = new CityTemperature();
        sol.insert(1001, 15, 2);
        sol.insert(1001, 50, 11);
        sol.insert(1001, 20, 5);
        sol.insert(1001, 30, 7);
        sol.insert(1001, 40, 10);
        System.out.println(sol.getTemperature(1001, 4));
        System.out.println(sol.getTemperature(1001, 11));
        System.out.println(sol.getTemperature(1001, 8));
        System.out.println(sol.getTemperature(1001, 0));
    }
}

// 1     3      target = 0
// left right
// 0     1
// mid = 1
// right = mid - 1 = 0
//

// 1 2 2 5 7
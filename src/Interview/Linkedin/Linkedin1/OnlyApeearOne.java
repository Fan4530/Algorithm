package Interview.Linkedin.Linkedin1;

import java.util.*;

/**
 * Created by program on 1/11/2018.
 */
public class OnlyApeearOne {
    public static int[] findNumbersAppearanceOnce(int[] data) {

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < data.length; i ++) {
            map.put(data[i], map.getOrDefault(data[i], 0) + 1);
        }
        List<Integer> list = new ArrayList<>();
        for(Map.Entry<Integer, Integer> e : map.entrySet()) {
            if(e.getValue() == 1) {
                list.add(e.getKey());
            }
        }
        int [] res = new int[list.size()];
        for(int i = 0; i < res.length; i ++) {
            res[i] = list.get(i);
        }
        return res;
    }
    public static void main(String[] args) {
        int[] data1 = {2, 4, 3, 6, 3, 2, 5, 5};
        int[] result1 = findNumbersAppearanceOnce(data1);
        System.out.println(Arrays.toString(result1));
        int[] data2 = {1,2,2,2,3};
        int[] result2 = findNumbersAppearanceOnce(data2);
        System.out.println(Arrays.toString(result2));
        int[] data3 = {1,2,2,3,4};
        int[] result3 = findNumbersAppearanceOnce(data3);
        System.out.println(Arrays.toString(result3));
    }
}

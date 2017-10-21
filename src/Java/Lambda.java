package Java;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by program on 10/19/2017.
 */
public class Lambda {
    public static void main(String [] args) {
        // initializing unsorted short array
        Short sArr[] = new Short[]{3, 13, 1, 9, 21};

        // let us print all the elements available in list
        for (short number : sArr) {
            System.out.println("Number = " + number);
        }


//
//        // sorting array with reverse order using comparator
//        Arrays.sort(sArr, new Comparator<Short>() {
//            @Override
//            public int compare(Short o1, Short o2) {
//                return 0;
//            }
//        });
        Arrays.sort(sArr, (Short i1, Short i2) -> i2 - i1);
        PriorityQueue<Short> minHeap = new PriorityQueue<>(11, (Short i1, Short i2) ->
            {
                if(i1 == i2) {
                    return 0;
                }
                return i1 > i2 ? 1 : -1;
            }
        );
        // let us print all the elements available in list
        System.out.println("short array with some sorted values(1 to 4) is:");
        for (short number : sArr) {
            System.out.println("Number = " + number);
        }
    }
}
